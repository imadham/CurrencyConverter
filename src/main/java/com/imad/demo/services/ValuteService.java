package com.imad.demo.services;

import com.imad.demo.model.HistoryModel;
import com.imad.demo.model.ValuteModel;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Set;

public interface ValuteService extends CrudService<ValuteModel, Long> {

    ValuteModel findFirstByName(String name);
    void deleteAll();
    ValuteModel findByCharCodeAndDate(String charCode, LocalDate date);
    Set<ValuteModel> findByCharCodeAndDateBefore(String charCode, LocalDate date);
    ValuteModel findRecent(String charCode);

    Set<ValuteModel> findAllByDate(LocalDate date);
}
