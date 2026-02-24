package ru.rksp.processorservice.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "сырые_события_платежей")
public class RawPaymentEvent {

    @Id
    @Column(name = "идентификатор")
    private String identifier;

    @Column(name = "фио_плательщика")
    private String payerFullName;

    @Column(name = "сумма", precision = 19, scale = 2)
    private BigDecimal amount;

    @Column(name = "валюта")
    private String currency;

    @Column(name = "способ_оплаты")
    private String paymentMethod;

    @Column(name = "дата_события")
    private LocalDateTime eventDate;

    public RawPaymentEvent() {
    }

    public RawPaymentEvent(String identifier, String payerFullName, BigDecimal amount, String currency, String paymentMethod, LocalDateTime eventDate) {
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
