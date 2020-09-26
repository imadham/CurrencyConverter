package com.imad.demo.converters;

import com.imad.demo.getting_data.Valute;
import com.imad.demo.model.ValuteModel;

public interface ConvertDataModel {

    public ValuteModel valutesToVauteModel(Valute valute);
    public Valute valuteModelToValute(ValuteModel valuteModel);
}
