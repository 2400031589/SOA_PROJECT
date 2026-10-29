@echo off
echo ========================================================
echo Starting PrimeHaven Microservices...
echo ========================================================

cd /d "%~dp0"

echo [1/6] Checking Eureka Server (Port 8761)...
netstat -ano | findstr :8761 >nul
if %errorlevel% equ 0 (
    echo Eureka Server is already running on port 8761.
) else (
    echo Starting Eureka Server on port 8761...
    cd /d "%~dp0eureka-server"
    start "Eureka Server [8761]" java -jar target\eureka-server-0.0.1-SNAPSHOT.jar
    timeout /t 8 /nobreak
)

echo [2/6] Starting Property Service (Port 8081)...
cd /d "%~dp0property-service"
start "Property Service [8081]" java -jar target\property-service-0.0.1-SNAPSHOT.jar

echo [3/6] Starting Visit Service (Port 8082)...
cd /d "%~dp0visit-service"
start "Visit Service [8082]" java -jar target\visit-service-0.0.1-SNAPSHOT.jar

echo [4/6] Starting Inquiry Service (Port 8083)...
cd /d "%~dp0inquiry-service"
start "Inquiry Service [8083]" java -jar target\inquiry-service-0.0.1-SNAPSHOT.jar

echo [5/6] Starting Auth Service (Port 8084)...
cd /d "%~dp0auth-service"
start "Auth Service [8084]" java -jar target\auth-service-0.0.1-SNAPSHOT.jar

echo [6/6] Starting API Gateway (Port 8080)...
cd /d "%~dp0api-gateway"
start "API Gateway [8080]" java -jar target\api-gateway-0.0.1-SNAPSHOT.jar

echo.
echo All microservices are launching in separate console windows.
echo Please allow ~15 seconds for all services to initialize and register with Eureka.
