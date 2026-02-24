package ru.rksp.ingestservice.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class EventDto {
    private String identifier;
    private String payerFullName;   // фио_плательщика
    private BigDecimal amount;      // сумма
    private String currency;        // валюта
    private String paymentMethod;   // способ_оплаты
    private LocalDateTime eventDate;

    public EventDto() {
    }

    public EventDto(String identifier, String payerFullName, BigDecimal amount, String currency, String paymentMethod, LocalDateTime eventDate) {
        this.identifier = identifier;
        this.payerFullName = payerFullName;
        this.amount = amount;
        this.currency = currency;
        this.paymentMethod = paymentMethod;
        this.eventDate = eventDate;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getPayerFullName() {
        return payerFullName;
    }

    public void setPayerFullName(String payerFullName) {
        this.payerFullName = payerFullName;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public LocalDateTime getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDateTime eventDate) {
        this.eventDate = eventDate;
    }
}
