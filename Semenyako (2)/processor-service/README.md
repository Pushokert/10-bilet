# Processor Service

Сервис обрабатывает сообщения из RabbitMQ, сохраняет события в PostgreSQL и записывает агрегаты в ClickHouse.

## Запуск

1. Запустите инфраструктуру через Docker Compose (из корня проекта см. основной README):

```bash
cd processor-service
docker-compose up -d
```

2. После поднятия контейнеров запустите приложение:

```bash
mvn spring-boot:run
```

Таблица в ClickHouse создаётся при старте приложения автоматически.

## API

- `POST /api/v1/events/count` — получение количества записей из PostgreSQL и сохранение результата в ClickHouse.

## Swagger

Документация и тестовый интерфейс доступны по адресу: http://localhost:8081/swagger-ui.html
