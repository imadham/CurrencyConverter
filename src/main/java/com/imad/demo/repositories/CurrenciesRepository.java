package com.imad.demo.repositories;

import com.imad.demo.model.HistoryModel;
import com.imad.demo.model.ValuteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Set;


public interface CurrenciesRepository extends JpaRepository<ValuteModel, Long > {

    ValuteModel findFirstByName(String name);
    ValuteModel findByCharCodeAndDate(String charCode, LocalDate date);
    Set<ValuteModel> findByCharCodeAndDateBefore(String charCode, LocalDate date);


    /**
     * postgreSQL query to get most recent values when there is no up to date data
     * @param charCode
     * @return
     */
    @Query(value = "SELECT DISTINCT ON (charcode) * FROM valute where charcode = :charCode ORDER BY charcode , date DESC;", nativeQuery=true)
    ValuteModel findRecent(@Param("charCode") String charCode);

    Set<ValuteModel> findAllByDate(LocalDate date);


}
