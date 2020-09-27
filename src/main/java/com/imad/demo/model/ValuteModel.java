package com.imad.demo.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * model for DB
 */
@Entity
@Table(name = "valute")
public class ValuteModel {

    @Id
    @SequenceGenerator(name="seq-gen",sequenceName="MY_SEQ_GEN", initialValue=1, allocationSize=1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="seq-gen")
    //@GenericGenerator(name = "uuid", strategy = "uuid2")
    private Integer id;
//    @Column(name = "numcode")
//    private String numCode;
    @Column(name = "charcode")
    private String charCode;
//    @Column(name = "nominal")
//    private String nominal;
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

//    public String getNumCode() {
//        return numCode;
//    }
//
//    public void setNumCode(String numCode) {
//        this.numCode = numCode;
//    }

    public String getCharCode() {
        return charCode;
    }

    public void setCharCode(String charCode) {
        this.charCode = charCode;
    }

//    public String getNominal() {
//        return nominal;
//    }
//
//    public void setNominal(String nominal) {
//        this.nominal = nominal;
//    }

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
}
