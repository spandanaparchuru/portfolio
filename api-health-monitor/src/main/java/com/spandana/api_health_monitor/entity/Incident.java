package com.spandana.api_health_monitor.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Incident {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private MonitoredEndpoint endpoint;

    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(nullable = false)
    private String status = "OPEN"; // OPEN or RESOLVED

    @Column(nullable = false, length = 500)
    private String summary;

    @Column(length = 2000)
    private String notes;

    // Getters & Setters
    public Long getId() { return id; }

    public MonitoredEndpoint getEndpoint() { return endpoint; }
    public void setEndpoint(MonitoredEndpoint endpoint) { this.endpoint = endpoint; }

    public LocalDateTime getCreatedAt() { return createdAt; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getSummary() { return summary; }
    public void setSummary(String summary) { this.summary = summary; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }    
}
