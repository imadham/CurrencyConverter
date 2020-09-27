package com.imad.demo.getting_data;

import com.imad.demo.model.ValuteModel;

import javax.xml.bind.JAXBException;
import java.net.MalformedURLException;
import java.util.Set;

public interface GettingData {

    public Set<ValuteModel> getValutesModel() throws Exception;
}
