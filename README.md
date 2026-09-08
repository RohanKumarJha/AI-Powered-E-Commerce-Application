# 🛒 AI-Powered E-Commerce Backend Microservices Platform

A production-inspired, enterprise-level **AI-Powered E-Commerce Backend** built using **Java 21**, **Spring Boot 3**, and **Microservices Architecture**.  
Follows **Clean Architecture**, **SOLID Principles**, and **Enterprise Design Patterns**, evolving into a **cloud-native distributed system**.

---

## 🚀 Project Overview
- Independent microservices with **Service Discovery** + **API Gateway**  
- Progressive adoption of Docker, Production Databases, Spring Security, Kafka, AI, Kubernetes, AWS, CI/CD  

---

## 🏗️ Microservices
✅ Identity • ✅ Catalog • ✅ Inventory • ✅ Cart • ✅ Order • ✅ Payment • ✅ Notification • ✅ AI • ✅ Eureka Server • ✅ API Gateway  

---

## 🌐 Infrastructure
- Netflix Eureka Server  
- Spring Cloud Gateway  
- Service Discovery & API Routing  
- Docker + Docker Compose  
- Containerized Microservices  

---

## 🛠️ Tech Stack
**Backend:** Java 21, Spring Boot 3, Spring MVC, Spring Data JPA, Maven, Lombok, ModelMapper  
**Databases:** H2 (Dev), MySQL, MongoDB, PostgreSQL, Redis, Qdrant  
**Spring Cloud:** Eureka, Gateway  
**Docs:** OpenAPI, Swagger UI  
**DevOps:** Docker, Docker Compose  
**Others:** REST APIs, Exception Handling, DTO Validation, Pagination, Sorting, Soft Delete, API Versioning  

---

## 📚 Architecture
Controller → Service → Factory → Mapper → Repository → Database

Code

---

## 🌐 System Architecture
Client → API Gateway :8080 → Eureka :8761
├─ Identity :8081
├─ Catalog :8082
├─ Inventory :8083
├─ Cart :8084
├─ Order :8085
├─ Payment :8086
├─ Notification :8087
└─ AI :8088

Code

---

## 🎯 Design Patterns
**Implemented:** Factory, Strategy, Adapter, State, Specification, Mapper, Repository, Dependency Injection  
**Planned:** Saga, Outbox, Inbox, Cache-Aside, Circuit Breaker, Observer, CQRS, Event Sourcing  

---

## ✨ Features
- **Identity:** User, Address, Role Mgmt, Soft Delete, Pagination, Validation  
- **Catalog:** Product, Category, Brand, Reviews, Dynamic Filtering, Specification Pattern  
- **Inventory:** Stock Tracking, Reserve/Release Stock, Availability Validation  
- **Cart:** Add/Remove Items, Update Quantity, Cart Total, Price Calculation  
- **Order:** Place/Cancel Orders, History, State Mgmt  
- **Payment:** Mock Gateway, Multiple Strategies, Refunds, History  
- **Notification:** Email, SMS, Push Notifications  
- **AI:** Chat Assistant, Product Recommendation, Review Summarization, Semantic Search  

---

## 📦 Current Databases (Phase 6)
- Identity → MySQL ✅ (migrated with Flyway, roles seeded, persistence verified)  
- Catalog → MongoDB ⏳  
- Inventory → PostgreSQL ⏳  
- Cart → Redis ⏳  
- Order → PostgreSQL ⏳  
- Payment → PostgreSQL ⏳  
- Notification → PostgreSQL ⏳  
- AI → PostgreSQL + Qdrant ⏳  

---

## 📈 Progress Roadmap
- ✅ Phase 0 – Foundations  
- ✅ Phase 1 – System Design  
- ✅ Phase 2 – Core Microservices  
- ✅ Phase 3 – Service Discovery  
- ✅ Phase 4 – API Gateway  
- ✅ Phase 5 – Docker & Compose  
- 🚧 Phase 6 – Production Databases (In Progress: MySQL indexing + transactions)  
- ⏳ Phase 7 – Enterprise Security → Phase 21 – Production Readiness (Planned)  

---

## 🎯 Final Goal
Build a **production-ready AI-Powered E-Commerce Backend Microservices Platform** showcasing:  
- Enterprise Architecture • Distributed Systems • Cloud-Native Development  
- AI Integration • DevOps • Kubernetes • AWS Deployment  
- CI/CD • Observability • Security • Performance Engineering  
- Modern Backend Best Practices  

---

⭐ **Status:** Actively Under Development  
📍 Current Milestone: **Phase 6 – Production Databases**
