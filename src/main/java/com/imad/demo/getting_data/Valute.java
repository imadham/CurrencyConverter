package com.imad.demo.getting_data;

import javax.xml.bind.annotation.*;

import java.time.LocalDate;
import java.util.Objects;

/**
 * model to represent values (currency data) from xml into object of this class
 */
@XmlRootElement(name = "Valute")
@XmlAccessorType(XmlAccessType.FIELD)
public class Valute {

//    @XmlAttribute(name = "ID")
//    private String id;

//    @XmlElement(name = "NumCode")
//    private String numCode;
    @XmlElement(name = "CharCode")
    private String charCode;
    @XmlElement(name = "Nominal")
    private String nominal;
    @XmlElement(name = "Name")
    private String Name;
    @XmlElement(name = "Value")
    private String value;

    private LocalDate date;

    public Valute() {
    }

//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
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

    public String getNominal() {
        return nominal;
    }

    public void setNominal(String nominal) {
        this.nominal = nominal;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Valute valute = (Valute) o;
        return //id.equals(valute.id) &&
                //numCode.equals(valute.numCode) &&
                charCode.equals(valute.charCode) &&
                nominal.equals(valute.nominal) &&
                Name.equals(valute.Name) &&
                value.equals(valute.value) &&
                date.equals(valute.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(/*id, numCode, */charCode, nominal, Name, value, date);
    }

    @Override
    public String toString() {
        return "Valute{" +
//                "id='" + id + '\'' +
//                ", numCode='" + numCode + '\'' +
                ", charCode='" + charCode + '\'' +
                ", nominal='" + nominal + '\'' +
                ", Name='" + Name + '\'' +
                ", value='" + value + '\'' +
                ", date=" + date +
                '}';
    }
}
