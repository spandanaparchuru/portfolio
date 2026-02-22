package com.spandana.api_health_monitor.controller;

import com.spandana.api_health_monitor.entity.MonitoredEndpoint;
import com.spandana.api_health_monitor.repo.MonitoredEndpointRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/endpoints")
public class EndpointApiController {

    private final MonitoredEndpointRepository repo;

    public EndpointApiController(MonitoredEndpointRepository repo) {
        this.repo = repo;
    }

    // List all endpoints
    @GetMapping
    public List<MonitoredEndpoint> list() {
        return repo.findAll();
    }

    // Add a new endpoint
    @PostMapping
    public MonitoredEndpoint add(@RequestBody MonitoredEndpoint endpoint) {
        // default values if not provided
        if (endpoint.getMethod() == null) endpoint.setMethod("GET");
        if (endpoint.getExpectedStatus() == 0) endpoint.setExpectedStatus(200);
        return repo.save(endpoint);
    }

    // Enable/disable an endpoint
    @PatchMapping("/{id}/active")
    public MonitoredEndpoint setActive(@PathVariable Long id, @RequestParam boolean value) {
        MonitoredEndpoint ep = repo.findById(id).orElseThrow();
        ep.setActive(value);
        return repo.save(ep);
    }    
}
