package ru.rksp.processorservice.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class EventDto {
    private String identifier;
    private String payerFullName;
    private BigDecimal amount;
    private String currency;
    private String paymentMethod;
    private LocalDateTime eventDate;

    public EventDto() {
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
