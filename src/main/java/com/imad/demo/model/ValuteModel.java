package com.imad.demo.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

/**
 * model for DB valutes
 */
@Entity
@Table(name = "valute")
public class ValuteModel{

    @Id
    @SequenceGenerator(name="seq-gen",sequenceName="MY_SEQ_GEN", initialValue=1, allocationSize=1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="seq-gen")

    private Integer id;
    @Column(name = "charcode")
    private String charCode;
    @Column(name = "name")
    private String name;
    @Column(name = "value")
    private Double value;
    @Column(name = "date")
    private LocalDate date;

    public ValuteModel() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCharCode() {
        return charCode;
    }

    public void setCharCode(String charCode) {
        this.charCode = charCode;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }



    @Override
    public String toString() {
        return "ValuteModel{" +
                "id=" + id +
                ", charCode='" + charCode + '\'' +
                ", name='" + name + '\'' +
                ", value=" + value +
                ", date=" + date +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ValuteModel that = (ValuteModel) o;
        return charCode.equals(that.charCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(charCode);
    }
}
