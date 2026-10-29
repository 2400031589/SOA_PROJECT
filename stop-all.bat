@echo off
echo ========================================================
echo Stopping PrimeHaven Microservices...
echo ========================================================

for %%p in (8080 8081 8082 8083 8084 8761) do (
    for /f "tokens=5" %%a in ('netstat -aon ^| findstr :%%p ^| findstr LISTENING') do (
        echo Stopping service on port %%p (PID: %%a)...
        taskkill /F /PID %%a >nul 2>&1
    )
)

echo Done. All services stopped.
