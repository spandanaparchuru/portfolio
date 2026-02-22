package com.spandana.api_health_monitor.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class HealthCheckResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Many results belong to one endpoint
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private MonitoredEndpoint endpoint;

    private LocalDateTime checkedAt = LocalDateTime.now();

    private int statusCode;

    private long latencyMs;

    private boolean success;

    @Column(length = 2000)
    private String errorMessage;

    // Getters & Setters
    public Long getId() { return id; }

    public MonitoredEndpoint getEndpoint() { return endpoint; }
    public void setEndpoint(MonitoredEndpoint endpoint) { this.endpoint = endpoint; }

    public LocalDateTime getCheckedAt() { return checkedAt; }
    public void setCheckedAt(LocalDateTime checkedAt) { this.checkedAt = checkedAt; }

    public int getStatusCode() { return statusCode; }
    public void setStatusCode(int statusCode) { this.statusCode = statusCode; }

    public long getLatencyMs() { return latencyMs; }
    public void setLatencyMs(long latencyMs) { this.latencyMs = latencyMs; }

    public boolean isSuccess() { return success; }
    public void setSuccess(boolean success) { this.success = success; }

    public String getErrorMessage() { return errorMessage; }
    public void setErrorMessage(String errorMessage) { this.errorMessage = errorMessage; }    
}
