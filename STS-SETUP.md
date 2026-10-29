# Spring Tool Suite Setup

1. Extract the ZIP.
2. In STS: File -> Import -> Maven -> Existing Maven Projects.
3. Select the extracted PrimeHaven-Microservices folder.
4. Import each of the six module folders.
5. Start PostgreSQL (or MySQL) and create the three databases (`primehaven_property`, `primehaven_visit`, `primehaven_inquiry`) using database.sql.
6. Verify credentials and port in:
   property-service/src/main/resources/application.properties
   visit-service/src/main/resources/application.properties
   inquiry-service/src/main/resources/application.properties
7. Run Eureka first (port 8761), then the three CRUD services (8081, 8082, 8083), Auth Service (8084), and Gateway (8080).
8. Test endpoints directly or through Gateway.

Gateway examples:
POST http://localhost:8080/properties
GET  http://localhost:8080/properties
POST http://localhost:8080/visits
GET  http://localhost:8080/visits
POST http://localhost:8080/inquiries
GET  http://localhost:8080/inquiries

Direct service URLs can also be used on ports 8081, 8082, and 8083.
