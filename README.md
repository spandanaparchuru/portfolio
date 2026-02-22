![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen)
![Java](https://img.shields.io/badge/Java-17-orange)
![Build](https://img.shields.io/badge/Build-Passing-success)
![Status](https://img.shields.io/badge/Status-Portfolio%20Ready-003336)

## API Health Monitor (Spring Boot)

Production APIs can fail quietly — and teams often find out only after users report issues.  
This project is a lightweight monitoring + incident workflow service that checks endpoints on a schedule, captures latency/status, and automatically creates incidents after repeated failures.

**What it demonstrates:**
- Production-style health monitoring & failure detection
- Incident creation after 3 consecutive failures (noise-controlled)
- Manual resolution workflow (OPEN → RESOLVED)
- RESTful API design with persistence (JPA + H2)
- Simple dashboard UI for quick visibility

flowchart TD
  U[User / Postman / Dashboard] -->|REST calls| C[Spring Boot Controllers]
  C --> S[Monitoring Service<br/>Scheduled Health Checks]
  S -->|HTTP requests| E[External APIs / Endpoints]
  S --> R[(H2 Database)]
  R -->|stores| H[HealthCheckResult]
  R -->|stores| I[Incident]
  C --> R
  D[dashboard.html] -->|fetch /api/results & /api/incidents| C



