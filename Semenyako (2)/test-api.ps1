# Простой скрипт для тестирования API
# Запусти его в PowerShell: .\test-api.ps1

Write-Host "=== Тест ingest-service ===" -ForegroundColor Green

# Тестовое событие платежа
$event = @{
    identifier = "test-$(Get-Date -Format 'yyyyMMddHHmmss')"
    payerFullName = "Иванов Иван Иванович"
    amount = 1500.50
    currency = "RUB"
    paymentMethod = "card"
    eventDate = (Get-Date -Format "yyyy-MM-ddTHH:mm:ss")
} | ConvertTo-Json

Write-Host "Отправляю событие..." -ForegroundColor Yellow
try {
    $response = Invoke-RestMethod -Uri "http://localhost:8080/api/v1/events" -Method Post -Body $event -ContentType "application/json"
    Write-Host "✓ Успешно! $response" -ForegroundColor Green
} catch {
    Write-Host "✗ Ошибка: $_" -ForegroundColor Red
    Write-Host "Убедись, что ingest-service запущен на порту 8080" -ForegroundColor Yellow
    exit
}

Write-Host "`nЖду 2 секунды..." -ForegroundColor Yellow
Start-Sleep -Seconds 2

Write-Host "`n=== Тест processor-service ===" -ForegroundColor Green
Write-Host "Подсчитываю события..." -ForegroundColor Yellow
try {
    $response = Invoke-RestMethod -Uri "http://localhost:8081/api/v1/events/count" -Method Post -ContentType "application/json"
    Write-Host "✓ Успешно! $response" -ForegroundColor Green
} catch {
    Write-Host "✗ Ошибка: $_" -ForegroundColor Red
    Write-Host "Убедись, что processor-service запущен на порту 8081" -ForegroundColor Yellow
    exit
}

Write-Host "`n=== Все тесты пройдены! ===" -ForegroundColor Green
