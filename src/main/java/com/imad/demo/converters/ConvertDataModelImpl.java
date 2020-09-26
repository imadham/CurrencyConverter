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
        valuteModel.setId(valute.getId());
        valuteModel.setNumCode(valute.getNumCode());
        valuteModel.setCharCode(valute.getCharCode());
        valuteModel.setNominal(valute.getNominal());
        valuteModel.setName(valute.getName());
        //convert to actual value
        valuteModel.setValue(Double.parseDouble(valute.getValue().replace(",","."))/Integer.parseInt(valute.getNominal()));
        valuteModel.setDate(valute.getDate());

        return valuteModel;

    }

    @Override
    public Valute valuteModelToValute(ValuteModel valuteModel) {
        Valute valute = new Valute();
        valute.setId(valuteModel.getId());
        valute.setNumCode(valuteModel.getNumCode());
        valute.setCharCode(valuteModel.getCharCode());
        valute.setNominal(valuteModel.getNominal());
        valute.setName(valuteModel.getName());
        valute.setValue(""+valuteModel.getValue());
        valute.setDate(valuteModel.getDate());

        return valute;
    }
}
