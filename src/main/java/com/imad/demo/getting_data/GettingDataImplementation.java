package com.imad.demo.getting_data;

import com.imad.demo.converters.ConvertDataModel;
import com.imad.demo.converters.ConvertDataModelImpl;
import com.imad.demo.model.ValuteModel;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

/**
 * get xml data from web page and convert it
 * into java objects using jaxb
 */

@Component
public class GettingDataImplementation implements GettingData {

    private final ConvertDataModel convertDataModel;

    public GettingDataImplementation() {
        this.convertDataModel = new ConvertDataModelImpl();
    }

    @Override
    public Set<ValuteModel> getValutesModel() throws Exception {
        Set<ValuteModel> valuteModels = new HashSet<>();
        ValCurs valcurs;
        Unmarshaller jaxbUnmarshaller = null;
        try
        {
            /**
             * try getting data from website
             */
            URL url = new URL("http://www.cbr.ru/scripts/XML_daily.asp");
            JAXBContext jaxbContext = JAXBContext.newInstance(ValCurs.class);
            jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            valcurs = (ValCurs) jaxbUnmarshaller.unmarshal(url);
        }catch (Exception e)
        {
            /**
             * if the site unreachable for some reason use local version of data
             */
            System.err.println(" error in loading data from website, using default data from already stored xml");
            File xmlFile = new File("src/main/resources/currenciesdata.xml");
            valcurs = (ValCurs) jaxbUnmarshaller.unmarshal(xmlFile);
        }
        /**
         * add ruble currency with nominal and value of 1
         */
        Valute RUR = new Valute();
        RUR.setName("Рубль");
        RUR.setCharCode("RUR");
        RUR.setValue("1");
        RUR.setNominal("1");
        valcurs.getValuteSet().add(RUR);
        /** format date into valid LocalDate object */
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date1 = LocalDate.parse(valcurs.getDate(), formatter).format(formatter2);
        LocalDate date2 = LocalDate.parse(date1);
        for (Valute valute : valcurs.getValuteSet()) {
            valute.setDate(date2);
            valuteModels.add(convertDataModel.valutesToVauteModel(valute));
        }
        return valuteModels;
    }



}
