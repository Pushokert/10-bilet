# Ingest Service

Сервис принимает события платежей по REST API и отправляет их в очередь RabbitMQ `events.raw`.

## Запуск

1. Убедитесь, что RabbitMQ запущен (например, через docker-compose в папке `processor-service`; см. основной README проекта).

2. Запустите приложение:

```bash
mvn spring-boot:run
```

## API

- `POST /api/v1/events` — приём события платежа и отправка в RabbitMQ очередь `events.raw`.

Пример тела запроса:

```json
{
  "identifier": "12345",
  "payerFullName": "Иванов Иван Иванович",
  "amount": 1500.50,
  "currency": "RUB",
  "paymentMethod": "card",
  "eventDate": "2024-01-15T10:30:00"
}
```

## Swagger

Документация и тестовый интерфейс доступны по адресу: http://localhost:8080/swagger-ui.html
