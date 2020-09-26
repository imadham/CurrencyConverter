package com.imad.demo.getting_data;

import com.imad.demo.converters.ConvertDataModel;
import com.imad.demo.converters.ConvertDataModelImpl;
import com.imad.demo.model.ValuteModel;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.net.MalformedURLException;
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
    public Set<ValuteModel> getValutesModel() {
        try {

            Set<ValuteModel> valuteModels = new HashSet<>();

            URL url = new URL("http://www.cbr.ru/scripts/XML_daily.asp");

            JAXBContext jaxbContext = JAXBContext.newInstance(ValCurs.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            ValCurs valcurs = (ValCurs) jaxbUnmarshaller.unmarshal(url);


            /**
             * format date into valid LocalDate object
             */

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String date1 = LocalDate.parse(valcurs.getDate(), formatter).format(formatter2);
            LocalDate date2 = LocalDate.parse(date1);


            for(Valute valute : valcurs.getValuteSet())
            {
                valute.setDate(date2) ;
                valuteModels.add(convertDataModel.valutesToVauteModel(valute));
            }

            return valuteModels;


        } catch (JAXBException | MalformedURLException e) {
            e.printStackTrace();
            return null;
        }

    }



}
