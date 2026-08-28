# 🛒 AI-Powered E-Commerce Backend Microservices Platform

A production-inspired, enterprise-level **AI-Powered E-Commerce Backend** built using **Java 21**, **Spring Boot 3**, and **Microservices Architecture**.

The project follows **Clean Architecture**, **SOLID Principles**, **Enterprise Design Patterns**, and modern backend engineering practices while progressively evolving into a cloud-native distributed system.

---

# 🚀 Project Overview

This project demonstrates how a modern enterprise-grade e-commerce backend is designed using independent microservices.

Each microservice owns its business logic and communicates through **Service Discovery** and an **API Gateway**.

The project is being developed phase-by-phase, gradually introducing production-grade technologies such as Docker, production databases, Spring Security, Kafka, AI, Kubernetes, AWS, and CI/CD.

---

# 🏗️ Microservices

| Microservice           | Status    |
| ---------------------- | --------- |
| ✅ Identity Service     | Completed |
| ✅ Catalog Service      | Completed |
| ✅ Inventory Service    | Completed |
| ✅ Cart Service         | Completed |
| ✅ Order Service        | Completed |
| ✅ Payment Service      | Completed |
| ✅ Notification Service | Completed |
| ✅ AI Service           | Completed |
| ✅ Eureka Server        | Completed |
| ✅ API Gateway          | Completed |

---

# 🌐 Infrastructure

* ✅ Netflix Eureka Server
* ✅ Spring Cloud Gateway
* ✅ Service Discovery
* ✅ API Routing
* 🚧 Docker & Docker Compose — Next Phase

---

# 🛠️ Tech Stack

## Backend

* Java 21
* Spring Boot 3
* Spring MVC
* Spring Data JPA
* Maven
* Lombok
* ModelMapper

## Databases

* H2 Database — Development

## Spring Cloud

* Netflix Eureka
* Spring Cloud Gateway

## Documentation

* OpenAPI
* Swagger UI

## DevOps

* Docker — Planned/Next Phase
* Docker Compose — Planned/Next Phase

## Others

* REST APIs
* Global Exception Handling
* DTO Validation
* Pagination
* Sorting
* Soft Delete
* Constructor Injection
* API Versioning

---

# 📚 Architecture

The project follows a layered Clean Architecture approach:

```text
Controller
    │
    ▼
 Service
    │
    ▼
 Factory
    │
    ▼
 Mapper
    │
    ▼
Repository
    │
    ▼
 Database
```

The architecture is designed to keep responsibilities separated and make the system easier to maintain, test, and extend.

---

# 🌐 System Architecture

```text
                         Client
                           │
                           ▼
                  API Gateway :8080
                           │
                           ▼
                  Eureka Server :8761
                           │
        ┌──────────────────┼──────────────────┐
        │                  │                  │
        ▼                  ▼                  ▼
 Identity :8081      Catalog :8082      Inventory :8083
        │
        ├───────────────┐
        ▼               ▼
   Cart :8084       Order :8085
                        │
              ┌─────────┼─────────┐
              ▼         ▼         ▼
        Payment :8086  Notification :8087
                          

                    AI Service :8088
```

### API Gateway

The **API Gateway** acts as the single entry point for client requests.

It is responsible for:

* Request routing
* Service discovery-based routing
* Forwarding requests to microservices
* Centralized API entry point
* Lower-case service ID handling
* API versioning support

The Gateway uses **Spring Cloud Gateway** together with **Eureka Service Discovery**.

---

# 🎯 Design Patterns Used

### Currently Implemented

* ✅ Factory Pattern
* ✅ Strategy Pattern
* ✅ Adapter Pattern
* ✅ State Pattern
* ✅ Specification Pattern
* ✅ Mapper Pattern
* ✅ Repository Pattern
* ✅ Dependency Injection

### Planned

* Saga Pattern
* Outbox Pattern
* Inbox Pattern
* Cache-Aside Pattern
* Circuit Breaker Pattern
* Observer Pattern
* CQRS
* Event Sourcing

---

# ✨ Features

## 👤 Identity Service

* User Management
* Address Management
* Role Management
* Soft Delete
* Pagination
* Validation

## 📦 Catalog Service

* Product Management
* Category Management
* Brand Management
* Product Images
* Product Reviews
* Dynamic Filtering
* Specification Pattern
* Pagination
* Sorting

## 📊 Inventory Service

* Inventory Management
* Stock Tracking
* Reserve Stock
* Release Stock
* Availability Validation

## 🛒 Cart Service

* Add Item
* Remove Item
* Update Quantity
* Cart Total
* Price Calculation

## 📑 Order Service

* Place Order
* Cancel Order
* Order History
* Order State Management

## 💳 Payment Service

* Mock Payment Gateway
* Multiple Payment Strategies
* Payment Adapter
* Refund Processing
* Payment History

## 📧 Notification Service

* Email Notification
* SMS Notification
* Push Notification
* Strategy Pattern

## 🤖 AI Service

* AI Chat Assistant
* Product Recommendation
* Review Summarization
* Recommendation Strategies
* Mock Semantic Search

---

# 🧩 Common Features

* REST APIs
* DTO Validation
* Constructor Injection
* Global Exception Handling
* Pagination
* Soft Delete
* OpenAPI Documentation
* Swagger UI
* API Versioning

---

# 🌐 Phase 3 — Service Discovery

Phase 3 introduced **Netflix Eureka Service Discovery**.

### Implemented

* ✅ Eureka Server
* ✅ Eureka Client configuration
* ✅ Dynamic service registration
* ✅ Dynamic service discovery
* ✅ Service-to-service discovery using service names
* ✅ IP-based service instance configuration

Each microservice registers itself with the Eureka Server.

```text
                    Eureka Server
                       :8761
                          ▲
                          │
        ┌─────────────────┼─────────────────┐
        │                 │                 │
        ▼                 ▼                 ▼
   Identity          Catalog           Inventory
   Service           Service             Service
        │
        ▼
     Cart
     Order
     Payment
     Notification
     AI
```

---

# 🚪 Phase 4 — API Gateway

Phase 4 introduced **Spring Cloud Gateway** as the centralized entry point for the entire microservices platform.

### Implemented

* ✅ Spring Cloud Gateway
* ✅ Gateway application
* ✅ Route configuration
* ✅ Eureka-based service discovery
* ✅ Dynamic routing
* ✅ Service ID-based routing
* ✅ Lower-case service ID configuration
* ✅ Gateway running on port `8080`

### Gateway Architecture

```text
Client
  │
  │ HTTP Request
  ▼
API Gateway :8080
  │
  │ Service Discovery
  ▼
Eureka Server :8761
  │
  ├──► Identity Service :8081
  ├──► Catalog Service :8082
  ├──► Inventory Service :8083
  ├──► Cart Service :8084
  ├──► Order Service :8085
  ├──► Payment Service :8086
  ├──► Notification Service :8087
  └──► AI Service :8088
```

### Example Gateway Route

```yaml
spring:
  cloud:
    gateway:
      routes:
        - id: ai-service
          uri: lb://AI-Service
          predicates:
            - Path=/api/ai/**
```

Here:

* `id` is the **route identifier** chosen by the developer.
* `uri` identifies the destination service.
* `lb://` tells Gateway to use **load-balanced service discovery**.
* `AI-Service` refers to the service registered in Eureka.
* `Path=/api/ai/**` is the **predicate** that determines which requests match this route.

For example:

```text
Client
   │
   │ GET /api/ai/recommendations
   ▼
API Gateway :8080
   │
   │ Path predicate matches
   ▼
AI-Service
```

The Gateway therefore provides a single entry point instead of exposing every microservice directly to clients.

---

# 📦 Current Database

### Development Database

* H2 Database

Production databases will be introduced in Phase 6.

---

# 📈 Current Progress

| Phase                                    | Status        |
| ---------------------------------------- | ------------- |
| ✅ Phase 0 – Foundations                  | Completed     |
| ✅ Phase 1 – System Design                | Completed     |
| ✅ Phase 2 – Core Business Microservices  | Completed     |
| ✅ Phase 3 – Service Discovery (Eureka)   | Completed     |
| ✅ Phase 4 – API Gateway                  | **Completed** |
| 🚧 Phase 5 – Docker & Docker Compose     | Next          |
| ⏳ Phase 6 – Production Databases         | Planned       |
| ⏳ Phase 7 – Enterprise Security          | Planned       |
| ⏳ Phase 8 – Inter-Service Communication  | Planned       |
| ⏳ Phase 9 – Distributed Transactions     | Planned       |
| ⏳ Phase 10 – AI Integration              | Planned       |
| ⏳ Phase 11 – Caching                     | Planned       |
| ⏳ Phase 12 – Resilience                  | Planned       |
| ⏳ Phase 13 – Configuration Management    | Planned       |
| ⏳ Phase 14 – Observability               | Planned       |
| ⏳ Phase 15 – API & Contract Testing      | Planned       |
| ⏳ Phase 16 – Cloud Deployment            | Planned       |
| ⏳ Phase 17 – CI/CD                       | Planned       |
| ⏳ Phase 18 – Kubernetes                  | Planned       |
| ⏳ Phase 19 – Enterprise Security Upgrade | Planned       |
| ⏳ Phase 20 – Performance Engineering     | Planned       |
| ⏳ Phase 21 – Production Readiness        | Planned       |

---

# 🚀 Upcoming Roadmap

## 🚧 Phase 5 – Docker & Docker Compose

### Docker

* Dockerfile
* Docker Images
* Docker Containers
* Multi-stage Builds
* Docker Networking
* `.dockerignore`
* Environment Variables
* Volumes
* Health Checks
* Containerized Microservices

### Docker Compose

* Multi-container deployment
* Service dependencies
* Custom networks
* Volumes
* Environment configuration
* Health checks
* Service discovery inside Docker
* Gateway + Eureka + Microservices deployment

---

## 🚧 Phase 6 – Production Databases

### Databases

* Identity → MySQL
* Catalog → MongoDB
* Inventory → PostgreSQL
* Cart → Redis
* Order → PostgreSQL
* Payment → PostgreSQL
* Notification → PostgreSQL
* AI → PostgreSQL + Qdrant

### Additional Topics

* Flyway
* Liquibase
* Database Migration
* Indexing
* Transactions
* Optimistic Locking
* Pessimistic Locking
* Query Optimization

---

## 🔐 Phase 7 – Enterprise Security

* Spring Security
* JWT Authentication
* BCrypt
* RBAC
* Refresh Tokens
* OAuth2 Basics
* SecurityFilterChain
* JWT Filter
* AuthenticationManager
* UserDetailsService
* PasswordEncoder

---

## 🔄 Phase 8 – Inter-Service Communication

### Synchronous

* Spring WebClient
* OpenFeign

### Asynchronous

* Apache Kafka

### Enterprise Concepts

* Retry
* Timeout
* Circuit Breaker
* Fallback
* Consumer Groups
* Dead Letter Queue
* Schema Evolution
* Idempotent Consumers

---

## 💰 Phase 9 – Distributed Transactions

* Saga Pattern
* Choreography
* Orchestration
* Compensation
* Outbox Pattern
* Inbox Pattern
* Idempotency

---

## 🤖 Phase 10 – AI Integration

* LangChain4j
* OpenAI
* Gemini
* Qdrant
* Semantic Search
* Product Recommendation
* AI Chat Assistant
* Shopping Copilot
* Review Summary

---

## ⚡ Phase 11 – Caching

* Redis
* Cache-Aside
* Write Through
* Write Behind
* TTL
* Cache Eviction

---

## 🛡️ Phase 12 – Resilience

* Resilience4j
* Circuit Breaker
* Retry
* Bulkhead
* Rate Limiter
* Time Limiter
* Fallback

---

## ⚙️ Phase 13 – Configuration Management

* Spring Cloud Config
* Environment Profiles
* Vault

---

## 📊 Phase 14 – Observability

* Logback
* Structured Logging
* Correlation IDs
* Micrometer
* Prometheus
* Grafana
* Zipkin
* Elasticsearch
* Logstash
* Kibana
* Alertmanager

---

## 🧪 Phase 15 – API & Contract Testing

* REST Assured
* WireMock
* Spring Cloud Contract
* Pact
* Testcontainers
* End-to-End Testing
* Consumer-Driven Contract Testing

---

## ☁️ Phase 16 – Cloud Deployment

### AWS

* EC2
* ECS
* EKS
* RDS
* ElastiCache
* S3
* IAM
* Route 53
* ALB
* CloudWatch
* Secrets Manager

### Infrastructure as Code

* Terraform

---

## 🚀 Phase 17 – CI/CD

### GitHub Actions

```text
Build
  ↓
Unit Tests
  ↓
Integration Tests
  ↓
Contract Tests
  ↓
Static Analysis
  ↓
Security Scan
  ↓
Docker Build
  ↓
Push Images
  ↓
Deploy
```

### Optional

* Jenkins
* Argo CD

---

## ☸️ Phase 18 – Kubernetes

* Deployments
* StatefulSets
* Services
* ConfigMaps
* Secrets
* Ingress
* HPA
* Persistent Volumes
* Persistent Volume Claims

Deploy:

* Microservices
* Databases
* Kafka
* Redis
* Qdrant

---

## 🔒 Phase 19 – Enterprise Security Upgrade

* Keycloak
* OAuth2
* OpenID Connect
* SSO
* Google Login
* GitHub Login
* Multi-Realm
* Service Accounts
* Fine-Grained Authorization

---

## 🚀 Phase 20 – Performance Engineering

### Tools

* JMeter
* k6
* Gatling

### Metrics

* Throughput
* Latency
* P95
* P99
* Error Rate
* CPU
* Memory

### Optimization

* JVM
* Database
* Cache
* Thread Pools
* Garbage Collection

---

## 🏁 Phase 21 – Production Readiness

* Health Checks
* Readiness Probes
* Liveness Probes
* Graceful Shutdown
* Feature Flags
* Blue-Green Deployment
* Canary Deployment
* Rollback Strategy
* Backup & Restore
* Disaster Recovery
* Multi-Environment Support

---

# 🎯 Enterprise Design Patterns

* Factory Pattern
* Strategy Pattern
* Singleton Pattern
* Builder Pattern
* Specification Pattern
* Repository Pattern
* Mapper Pattern
* State Pattern
* Adapter Pattern
* Observer Pattern
* Gateway Pattern
* Saga Pattern
* Outbox Pattern
* Inbox Pattern
* Cache-Aside Pattern
* Circuit Breaker Pattern
* Dependency Injection
* CQRS — Optional
* Event Sourcing — Optional

---

# 📖 Learning Goals

This project covers a complete backend engineering roadmap including:

* Enterprise Java Development
* Spring Boot
* Microservices
* Distributed Systems
* Design Patterns
* Docker
* Production Databases
* Spring Cloud
* Spring Security
* Kafka
* AI Integration
* Kubernetes
* AWS Cloud
* CI/CD
* Enterprise Software Architecture

---

# ⭐ Project Status

🚧 **Actively Under Development**

### Current Milestone

**Phase 4 — API Gateway ✅**

The platform currently contains:

```text
8 Business Microservices
        +
Eureka Service Discovery
        +
Spring Cloud API Gateway
        =
Distributed E-Commerce Backend
```

The next milestone is **Phase 5 — Docker & Docker Compose**, where the complete distributed system will be containerized and deployed as a multi-container environment.

---

# 🎯 Final Goal

Build a **production-ready AI-Powered E-Commerce Backend Microservices Platform** that demonstrates:

* Enterprise Software Architecture
* Distributed Systems
* Cloud-Native Development
* AI Integration
* DevOps
* Kubernetes
* AWS Deployment
* CI/CD
* Observability
* Security
* Performance Engineering
* Modern Backend Engineering Best Practices
