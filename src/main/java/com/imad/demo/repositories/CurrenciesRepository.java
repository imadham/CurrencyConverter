package com.imad.demo.repositories;

import com.imad.demo.model.ValuteModel;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CurrenciesRepository extends JpaRepository<ValuteModel, Long > {

    ValuteModel findByName(String name);
}
