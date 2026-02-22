package com.spandana.api_health_monitor.repo;

import com.spandana.api_health_monitor.entity.HealthCheckResult;
import com.spandana.api_health_monitor.entity.MonitoredEndpoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HealthCheckResultRepository extends JpaRepository<HealthCheckResult, Long> {
    List<HealthCheckResult> findTop10ByEndpointOrderByCheckedAtDesc(MonitoredEndpoint endpoint);
}