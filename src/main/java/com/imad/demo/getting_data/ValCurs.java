package com.imad.demo.getting_data;

import javax.xml.bind.annotation.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * model representing xml root element with child elements
 * use jaxb to convert xml into object of this class
 */
@XmlRootElement (name = "ValCurs")
@XmlAccessorType(XmlAccessType.FIELD)
public class ValCurs {

    @XmlAttribute(name = "Date")
    private String date;

    @XmlElement(name = "Valute")
    private Set<Valute> valuteSet = new HashSet<Valute>();

    public ValCurs() {
    }

    public Set<Valute> getValuteSet() {
        return valuteSet;
    }

    public void setValuteSet(Set<Valute> valuteSet) {
        this.valuteSet = valuteSet;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ValCurs valCurs = (ValCurs) o;
        return date.equals(valCurs.date) &&
                valuteSet.equals(valCurs.valuteSet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, valuteSet);
    }

    @Override
    public String toString() {
        return "ValCurs{" +
                "date='" + date + '\'' +
                ", valuteSet=" + valuteSet +
                '}';
    }
}
