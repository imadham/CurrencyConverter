package com.imad.demo.services;

import com.imad.demo.model.HistoryModel;
import com.imad.demo.model.ValuteModel;
import com.imad.demo.repositories.CurrenciesRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * service for saving searching and deleting data in DB
 * using JpaRepository
 */
@Service
public class ValuteServiceImplementation implements ValuteService {

    private final CurrenciesRepository currenciesRepository;

    public ValuteServiceImplementation(CurrenciesRepository currenciesRepository) {
        this.currenciesRepository = currenciesRepository;
    }

    @Override
    public ValuteModel findFirstByName(String name) {
        return currenciesRepository.findFirstByName(name);
    }

    @Override
    public void deleteAll() {
        currenciesRepository.deleteAll();
        currenciesRepository.flush();
    }


    @Override
    public Set<ValuteModel> findAll() {
        Set<ValuteModel> result = new HashSet<>();
        currenciesRepository.findAll().forEach(result::add);
        return result;
    }

    public ValuteModel findByCharCodeAndDate(String charCode, LocalDate date)
    {
        return currenciesRepository.findByCharCodeAndDate(charCode, date);
    }
    public Set<ValuteModel> findByCharCodeAndDateBefore(String charCode, LocalDate date)
    {
        return currenciesRepository.findByCharCodeAndDateBefore(charCode, date);
    }

    public ValuteModel findRecent(String charCode){
        return currenciesRepository.findRecent(charCode);
    };

    @Override
    public ValuteModel findById(Long aLong) {
        return currenciesRepository.findById(aLong).orElse(null);
    }

    @Override
    public ValuteModel save(ValuteModel object) {
        return currenciesRepository.save(object);
    }

    @Override
    public void delete(ValuteModel object) {
    currenciesRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
    currenciesRepository.deleteById(aLong);
    }

    @Override
    public Set<ValuteModel> findAllByDate(LocalDate date) {
        return currenciesRepository.findAllByDate(date);
    }
}
