package com.spandana.api_health_monitor.service;

import com.spandana.api_health_monitor.entity.HealthCheckResult;
import com.spandana.api_health_monitor.entity.Incident;
import com.spandana.api_health_monitor.entity.MonitoredEndpoint;
import com.spandana.api_health_monitor.repo.HealthCheckResultRepository;
import com.spandana.api_health_monitor.repo.IncidentRepository;
import com.spandana.api_health_monitor.repo.MonitoredEndpointRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class MonitoringService {

    private final MonitoredEndpointRepository endpointRepo;
    private final HealthCheckResultRepository resultRepo;
    private final IncidentRepository incidentRepo;

    private final RestTemplate restTemplate = new RestTemplate();

    public MonitoringService(MonitoredEndpointRepository endpointRepo,
                             HealthCheckResultRepository resultRepo,
                             IncidentRepository incidentRepo) {
        this.endpointRepo = endpointRepo;
        this.resultRepo = resultRepo;
        this.incidentRepo = incidentRepo;
    }

    // Runs every 60 seconds
    @Scheduled(fixedDelay = 60000)
    public void runChecks() {
        List<MonitoredEndpoint> endpoints = endpointRepo.findByActiveTrue();

        for (MonitoredEndpoint ep : endpoints) {
            checkOneEndpoint(ep);
        }
    }

    private void checkOneEndpoint(MonitoredEndpoint ep) {
        long start = System.currentTimeMillis();

        HealthCheckResult result = new HealthCheckResult();
        result.setEndpoint(ep);

        try {
            ResponseEntity<String> response =
                    restTemplate.getForEntity(ep.getUrl(), String.class);

            long latency = System.currentTimeMillis() - start;

            int status = response.getStatusCode().value();
            boolean ok = (status == ep.getExpectedStatus());

            result.setStatusCode(status);
            result.setLatencyMs(latency);
            result.setSuccess(ok);
            result.setErrorMessage(null);

            resultRepo.save(result);

            // If success, we don't create incidents
            if (!ok) {
                handleFailure(ep, "Unexpected HTTP status: " + status);
            } else {
                // If success, you may optionally auto-resolve open incidents later (optional)
            }

        } catch (Exception ex) {
            long latency = System.currentTimeMillis() - start;

            result.setStatusCode(0);
            result.setLatencyMs(latency);
            result.setSuccess(false);
            result.setErrorMessage(ex.getMessage());

            resultRepo.save(result);

            handleFailure(ep, "Request failed: " + ex.getClass().getSimpleName());
        }
    }

    private void handleFailure(MonitoredEndpoint ep, String reason) {
        // Get last 3 results
        List<HealthCheckResult> last10 = resultRepo.findTop10ByEndpointOrderByCheckedAtDesc(ep);

        long last3Failures = last10.stream()
                .limit(3)
                .filter(r -> !r.isSuccess())
                .count();

        // If 3 failures in a row, create incident if no open incident exists
        if (last3Failures >= 3) {
            boolean hasOpenIncident =
                    incidentRepo.findFirstByEndpointAndStatusOrderByCreatedAtDesc(ep, "OPEN").isPresent();

            if (!hasOpenIncident) {
                Incident incident = new Incident();
                incident.setEndpoint(ep);
                incident.setStatus("OPEN");
                incident.setSummary("API health check failing for: " + ep.getName());
                incident.setNotes("Auto-created after 3 consecutive failures. Reason: " + reason);

                incidentRepo.save(incident);
            }
        }
    }
}