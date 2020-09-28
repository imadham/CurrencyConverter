package com.imad.demo.model;

import ch.qos.logback.core.util.StringCollectionUtil;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

/**
 * model to save history
 * no need for relations between tables
 * have sequence for auto generating Ids
 */
@Entity
@Table(name = "History")
public class HistoryModel {

    @Id
    @SequenceGenerator(name="seq-gen",sequenceName="MY_SEQ_GEN", initialValue=1, allocationSize=1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="seq-gen")
    private Integer id;

    @Column(name = "convertedFrom")
    private String currencyFrom;

    @Column(name = "CoovertedTo")
    private String currencyTo;

    @Column(name = "Amount")
    private Double amount;

    @Column(name = "transactionDate")
    private LocalDate exchangeDate;

    @Column(name = "ratesDate")
    private LocalDate ratesDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCurrencyFrom() {
        return currencyFrom;
    }

    public void setCurrencyFrom(String currencyFrom) {
        this.currencyFrom = currencyFrom;
    }

    public String getCurrencyTo() {
        return currencyTo;
    }

    public void setCurrencyTo(String currencyTo) {
        this.currencyTo = currencyTo;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }


    public LocalDate getExchangeDate() {
        return exchangeDate;
    }

    public void setExchangeDate(LocalDate exchangeDate) {
        this.exchangeDate = exchangeDate;
    }

    public LocalDate getRatesDate() {
        return ratesDate;
    }

    public void setRatesDate(LocalDate ratesDate) {
        this.ratesDate = ratesDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HistoryModel that = (HistoryModel) o;
        return currencyFrom.equals(that.currencyFrom) &&
                currencyTo.equals(that.currencyTo) &&
                amount.equals(that.amount) &&
                exchangeDate.equals(that.exchangeDate) &&
                ratesDate.equals(that.ratesDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currencyFrom, currencyTo, amount, exchangeDate, ratesDate);
    }

    @Override
    public String toString() {
        return "HistoryModel{" +
                "id=" + id +
                ", currencyFrom='" + currencyFrom + '\'' +
                ", currencyTo='" + currencyTo + '\'' +
                ", amount=" + amount +
                ", exchangeDate=" + exchangeDate +
                ", ratesDate=" + ratesDate +
                '}';
    }
}
