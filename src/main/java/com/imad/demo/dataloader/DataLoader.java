package com.imad.demo.dataloader;

import com.imad.demo.getting_data.GettingData;
import com.imad.demo.model.ValuteModel;
import com.imad.demo.services.ValuteService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBException;
import java.net.MalformedURLException;
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

        try {
            Set<ValuteModel> valutesModel = new HashSet<>();
            valutesModel = gettingData.getValutesModel();

            if (valutesModel.size() > 0) {
                valuteService.deleteAll();
                for (ValuteModel valuteModel : valutesModel) {
                    valuteService.save(valuteModel);
                }
            }
        }catch (Exception e)
        {
            throw e;
        }
    }
}
