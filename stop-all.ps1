# PrimeHaven Microservices Stopper Script
Write-Host "Stopping PrimeHaven Microservices..." -ForegroundColor Yellow

$ports = @(8080, 8081, 8082, 8083, 8084, 8761)

foreach ($port in $ports) {
    $conns = Get-NetTCPConnection -LocalPort $port -State Listen -ErrorAction SilentlyContinue
    if ($conns) {
        $pids = $conns.OwningProcess | Select-Object -Unique
        foreach ($pidToKill in $pids) {
            try {
                Stop-Process -Id $pidToKill -Force -ErrorAction SilentlyContinue
                Write-Host "Stopped process $pidToKill on port $port." -ForegroundColor Green
            } catch {
                Write-Warning "Could not stop process $pidToKill on port $port."
            }
        }
    } else {
        Write-Host "No process listening on port $port." -ForegroundColor DarkGray
    }
}

Write-Host "Done." -ForegroundColor Cyan
