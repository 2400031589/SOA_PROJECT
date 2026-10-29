# PrimeHaven Real Estate Listings & Property Inspection Scheduling Platform

Spring Boot microservices project for STS/Eclipse.

## Modules
- eureka-server : 8761
- api-gateway : 8080
- auth-service : 8084
- property-service : 8081
- visit-service : 8082
- inquiry-service : 8083

## Build
You can build the entire project from the root folder:
```bash
mvn clean package
```
Or open each folder as an Existing Maven Project in Spring Tool Suite.

## Start order
1. eureka-server
2. property-service
3. visit-service
4. inquiry-service
5. auth-service
6. api-gateway

## CRUD endpoints
Property:
POST /properties
GET /properties
GET /properties/{id}
PUT /properties/{id}
DELETE /properties/{id}

Visit:
POST /visits
GET /visits
GET /visits/{id}
PUT /visits/{id}
DELETE /visits/{id}

Inquiry:
POST /inquiries
GET /inquiries
GET /inquiries/{id}
PUT /inquiries/{id}
DELETE /inquiries/{id}

Auth:
POST /auth/login

Gateway routes:
http://localhost:8080/properties/**
http://localhost:8080/visits/**
http://localhost:8080/inquiries/**
http://localhost:8080/auth/**

Note: CRUD services are configured for PostgreSQL (port 5433 by default).
Create the databases `primehaven_property`, `primehaven_visit`, and `primehaven_inquiry`
using `database.sql`, then adjust credentials in each service's `application.properties`
if necessary.

