package com.imad.demo.converters;

import com.imad.demo.getting_data.Valute;
import com.imad.demo.model.ValuteModel;

/**
 * convert values obtained from the web
 * into system models and vice versa
 */
public class ConvertDataModelImpl implements ConvertDataModel {
    @Override
    public ValuteModel valutesToVauteModel(Valute valute) {
        ValuteModel valuteModel = new ValuteModel();
        valuteModel.setCharCode(valute.getCharCode());
        valuteModel.setName(valute.getName());
        /**
         * divide value by nominal to get actual price
         */
        valuteModel.setValue(Double.parseDouble(valute.getValue().replace(",","."))/Integer.parseInt(valute.getNominal()));
        valuteModel.setDate(valute.getDate());

        return valuteModel;

    }

    @Override
    /**
     * no use for this method
     */
    public Valute valuteModelToValute(ValuteModel valuteModel) {
        Valute valute = new Valute();
        valute.setCharCode(valuteModel.getCharCode());
        valute.setName(valuteModel.getName());
        valute.setValue(""+valuteModel.getValue());
        valute.setDate(valuteModel.getDate());

        return valute;
    }
}
