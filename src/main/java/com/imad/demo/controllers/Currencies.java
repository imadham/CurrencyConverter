package com.imad.demo.controllers;


    /** class for binding data from user input form to controller
     * the class contain three fields for currency which will be converted from/to and amount
     */
public class Currencies {

        /**
         *      * @param {String} first - currency to convert from
         *      * @param {String} second - currency to convert to
         *      * @param {String} amount - amount to convert
         */

    private String first;

    private String second;

    private String amount;


    public Currencies() {
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getSecond() {
        return second;
    }

    public void setSecond(String second) {
        this.second = second;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

}
