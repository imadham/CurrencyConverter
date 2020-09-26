package com.imad.demo.dataloader;

import com.imad.demo.getting_data.GettingData;
import com.imad.demo.model.ValuteModel;
import com.imad.demo.services.ValuteService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * get data from Class GettingDataImplementation and save to DB
 */
@Component
public class DataLoader implements CommandLineRunner {

    private final GettingData gettingData;
    private final ValuteService valuteService;

    public DataLoader(GettingData gettingData, ValuteService valuteService) {
        this.gettingData = gettingData;
        this.valuteService = valuteService;
    }

    @Override
    public void run(String... args) throws Exception {

        Set<ValuteModel> valutesModel = new HashSet<>();
        valutesModel = gettingData.getValutesModel();

        for (ValuteModel valuteModel : valutesModel){
            valuteService.save(valuteModel);
        }
    }
}
