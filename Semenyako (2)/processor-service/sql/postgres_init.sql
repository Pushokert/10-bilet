-- Таблица для сырых событий платежей (билет №10)
CREATE TABLE IF NOT EXISTS сырые_события_платежей (
    идентификатор VARCHAR(255) PRIMARY KEY,
    фио_плательщика VARCHAR(255),
    сумма DECIMAL(19, 2),
    валюта VARCHAR(10),
    способ_оплаты VARCHAR(100),
    дата_события TIMESTAMP
);
