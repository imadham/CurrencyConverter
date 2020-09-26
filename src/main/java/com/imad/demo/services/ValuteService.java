package com.imad.demo.services;

import com.imad.demo.model.ValuteModel;

public interface ValuteService extends CrudService<ValuteModel, Long> {

    ValuteModel findByName(String name);
    void deleteAll();
}
