# PrimeHaven Microservices Starter Script (PowerShell)
$rootDir = $PSScriptRoot

Write-Host "==========================================" -ForegroundColor Cyan
Write-Host " Starting PrimeHaven Microservices..." -ForegroundColor Cyan
Write-Host "==========================================" -ForegroundColor Cyan

$services = @(
    @{ Name = "eureka-server";    Port = 8761; Dir = "$rootDir\eureka-server";    Jar = "target\eureka-server-0.0.1-SNAPSHOT.jar" },
    @{ Name = "property-service"; Port = 8081; Dir = "$rootDir\property-service"; Jar = "target\property-service-0.0.1-SNAPSHOT.jar" },
    @{ Name = "visit-service";    Port = 8082; Dir = "$rootDir\visit-service";    Jar = "target\visit-service-0.0.1-SNAPSHOT.jar" },
    @{ Name = "inquiry-service";  Port = 8083; Dir = "$rootDir\inquiry-service";  Jar = "target\inquiry-service-0.0.1-SNAPSHOT.jar" },
    @{ Name = "auth-service";     Port = 8084; Dir = "$rootDir\auth-service";     Jar = "target\auth-service-0.0.1-SNAPSHOT.jar" },
    @{ Name = "api-gateway";      Port = 8080; Dir = "$rootDir\api-gateway";      Jar = "target\api-gateway-0.0.1-SNAPSHOT.jar" }
)

foreach ($s in $services) {
    $existing = Get-NetTCPConnection -LocalPort $s.Port -State Listen -ErrorAction SilentlyContinue
    if ($existing) {
        Write-Host "[$($s.Name)] already running on port $($s.Port)." -ForegroundColor Yellow
    } else {
        Write-Host "Starting [$($s.Name)] (Port $($s.Port))..." -ForegroundColor Green
        Start-Process cmd.exe -ArgumentList "/c title $($s.Name) & cd /d `"$($s.Dir)`" & java -jar $($s.Jar)"
        if ($s.Name -eq "eureka-server") {
            Write-Host "Waiting for Eureka Server to initialize..." -ForegroundColor DarkGray
            Start-Sleep -Seconds 6
        } else {
            Start-Sleep -Seconds 3
        }
    }
}

Write-Host "`nWaiting 10 seconds for services to complete initialization..." -ForegroundColor Cyan
Start-Sleep -Seconds 10

Get-NetTCPConnection -State Listen -ErrorAction SilentlyContinue |
    Where-Object { $_.LocalPort -in 8761, 8080, 8081, 8082, 8083, 8084 } |
    Select-Object LocalAddress, LocalPort |
    Sort-Object LocalPort |
    Format-Table -AutoSize
