package com.spandana.api_health_monitor.repo;

import com.spandana.api_health_monitor.entity.Incident;
import com.spandana.api_health_monitor.entity.MonitoredEndpoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IncidentRepository extends JpaRepository<Incident, Long> {
    Optional<Incident> findFirstByEndpointAndStatusOrderByCreatedAtDesc(MonitoredEndpoint endpoint, String status);
}