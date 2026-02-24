# Экзаменационная работа

Автор: Семеняко Степан Данилович

## Требования для запуска

Перед запуском убедитесь, что установлены и доступны в консоли:

- **Java 17** — выполните `java -version`
- **Maven** — выполните `mvn -version`
- **Docker Desktop** — приложение запущено, в консоли доступна команда `docker-compose`

## Запуск (по шагам)

Выполняйте шаги по порядку. Для каждого шага используйте отдельное окно PowerShell (или терминал). Окна с запущенными сервисами не закрывайте.

Все команды ниже выполняются из **корня проекта** (папка, в которой находятся `ingest-service` и `processor-service`). Сначала откройте PowerShell и перейдите в эту папку: `cd путь\к\проекту`.

### Шаг 1. Запуск инфраструктуры (Docker)

Откройте PowerShell и выполните:

```powershell
cd processor-service
docker-compose up -d
```

Подождите 30–40 секунд. Будут запущены PostgreSQL (порт 5433), ClickHouse (8123), RabbitMQ (5672, веб-интерфейс 15672).

### Шаг 2. Запуск ingest-service (порт 8080)

Откройте **новое** окно PowerShell и выполните:

```powershell
cd ingest-service
mvn spring-boot:run
```

Дождитесь появления строки вида `Started IngestServiceApplication...`. Окно не закрывайте.

### Шаг 3. Запуск processor-service (порт 8081)

Откройте **ещё одно** окно PowerShell и выполните:

```powershell
cd processor-service
mvn spring-boot:run
```

Дождитесь появления строки `Started ProcessorServiceApplication...`. Окно не закрывайте.

### Шаг 4. Проверка работоспособности

Откройте **новое** окно PowerShell и выполните команды по очереди.

**Отправка события в ingest:**

```powershell
$event = @{ identifier = "test-1"; payerFullName = "Иванов Иван Иванович"; amount = 1500.50; currency = "RUB"; paymentMethod = "card"; eventDate = (Get-Date -Format "yyyy-MM-ddTHH:mm:ss") } | ConvertTo-Json
Invoke-RestMethod -Uri "http://localhost:8080/api/v1/events" -Method Post -Body $event -ContentType "application/json"
```

В ответ Вы получите сообщение вида `Event sent to queue successfully`.

**Ожидание и запрос счётчика в processor:**

```powershell
Start-Sleep -Seconds 2
Invoke-RestMethod -Uri "http://localhost:8081/api/v1/events/count" -Method Post -ContentType "application/json"
```

В ответ Вы получите сообщение вида `Event count: 1 saved to ClickHouse`.

Если оба запроса выполнились без ошибок, система работает корректно.

---

## Структура проекта

- **ingest-service** — приём событий платежей по REST и отправка в RabbitMQ (очередь `events.raw`).
- **processor-service** — чтение сообщений из RabbitMQ, запись в PostgreSQL и ClickHouse.

## API

- **ingest (порт 8080):** `POST /api/v1/events` — приём события платежа (JSON: identifier, payerFullName, amount, currency, paymentMethod, eventDate).
- **processor (порт 8081):** `POST /api/v1/events/count` — подсчёт записей в PostgreSQL и сохранение результата в ClickHouse.

Документация Swagger доступна по адресам: http://localhost:8080/swagger-ui.html и http://localhost:8081/swagger-ui.html

## Базы данных

### PostgreSQL (порт 5433 на хосте)

Таблица: `сырые_события_платежей`
- `идентификатор` (VARCHAR, PRIMARY KEY)
- `фио_плательщика` (VARCHAR)
- `сумма` (DECIMAL)
- `валюта` (VARCHAR)
- `способ_оплаты` (VARCHAR)
- `дата_события` (TIMESTAMP)

### ClickHouse

Таблица: `агрегаты_событий_платежей`
- `дата_и_время_записи` (DateTime)
- `количество_записей` (UInt64)

## Технологии

- Spring Boot 3.2.0
- Spring AMQP (RabbitMQ)
- Spring Data JPA (PostgreSQL)
- ClickHouse JDBC
- Swagger/OpenAPI 3
- Docker Compose
