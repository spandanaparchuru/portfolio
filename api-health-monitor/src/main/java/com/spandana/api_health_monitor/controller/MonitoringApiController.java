package com.spandana.api_health_monitor.controller;

import com.spandana.api_health_monitor.entity.HealthCheckResult;
import com.spandana.api_health_monitor.entity.Incident;
import com.spandana.api_health_monitor.repo.HealthCheckResultRepository;
import com.spandana.api_health_monitor.repo.IncidentRepository;
import com.spandana.api_health_monitor.repo.MonitoredEndpointRepository;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MonitoringApiController {

    private final MonitoredEndpointRepository endpointRepo;
    private final HealthCheckResultRepository resultRepo;
    private final IncidentRepository incidentRepo;

    public MonitoringApiController(MonitoredEndpointRepository endpointRepo,
                                   HealthCheckResultRepository resultRepo,
                                   IncidentRepository incidentRepo) {
        this.endpointRepo = endpointRepo;
        this.resultRepo = resultRepo;
        this.incidentRepo = incidentRepo;
    }

    @GetMapping("/results")
    public List<HealthCheckResult> allResults() {
        return resultRepo.findAll();
    }

    @GetMapping("/incidents")
    public List<Incident> allIncidents() {
        return incidentRepo.findAll();
    }

    @PatchMapping("/incidents/{id}/resolve")
    public Incident resolveIncident(@PathVariable Long id, @RequestParam(required = false) String notes) {
        Incident incident = incidentRepo.findById(id).orElseThrow();
        incident.setStatus("RESOLVED");

        if (notes != null && !notes.isBlank()) {
            incident.setNotes(incident.getNotes() + " | Resolved Notes: " + notes);
        }
        return incidentRepo.save(incident);
    }
}