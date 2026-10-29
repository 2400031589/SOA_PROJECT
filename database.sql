-- ==========================================================
-- PostgreSQL Setup (Default configured on port 5433)
-- Run these commands in pgAdmin, psql, or your PostgreSQL client:
-- ==========================================================
CREATE DATABASE primehaven_property;
CREATE DATABASE primehaven_visit;
CREATE DATABASE primehaven_inquiry;

-- ==========================================================
-- MySQL Setup (If you choose to switch to MySQL on port 3306)
-- ==========================================================
-- CREATE DATABASE IF NOT EXISTS primehaven_property;
-- CREATE DATABASE IF NOT EXISTS primehaven_visit;
-- CREATE DATABASE IF NOT EXISTS primehaven_inquiry;

-- Note: Spring Data JPA creates and updates the tables automatically
-- because spring.jpa.hibernate.ddl-auto=update is enabled.
