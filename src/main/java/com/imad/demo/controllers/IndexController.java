package com.imad.demo.controllers;

import com.imad.demo.model.ValuteModel;
import com.imad.demo.services.ValuteService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.NoRouteToHostException;
import java.time.LocalDate;
import java.util.Set;

/**
 * controller class with post and get mapping and one html template
 */
@Controller
public class IndexController {

    private final ValuteService valuteService;
    private final CommandLineRunner commandLineRunner;

    public IndexController(ValuteService valuteService, CommandLineRunner commandLineRunner) {

        this.valuteService = valuteService;
        this.commandLineRunner = commandLineRunner;
    }

    @GetMapping({"","/index","/index/{from}/{to}/{value}"})
    public String index(Model model, @PathVariable(name = "from", required = false) String from,
                        @PathVariable(name = "to", required = false) String to,
                        @PathVariable(name = "value", required = false) Double value) throws Exception {
        try {
            /**
             * check if the data in DB are up to date
             * if not delete old data and get new data
             * using dataloader from web source
             */
            LocalDate date = LocalDate.now();
            if (!date.equals(valuteService.findAll().stream()
                    .findFirst().orElse(null).getDate())) {
                System.out.println("refreshing data");
                valuteService.deleteAll();
                commandLineRunner.run();
            }

            /**
             * @param currencies1
             * use this parameter for saving currencies which will be converted from/to
             * and the amount to be converted
             * if this is first time user loading page before
             * inputting any values give set default value
             * as converting 1 USD to EURO
             * @param formPost is to determinate if user have inputted data
             *                 or it is first page load
             */
            Currencies currencies1 = new Currencies();
            Set<ValuteModel> valuteModels = valuteService.findAll();
            Double firstValue = 0D;
            Double secondValue = 0D;
            Boolean fromPost = false;
            if (from != null && to != null && value != null) fromPost = true;

            if (!fromPost) {
                currencies1.setFirst("USD");
                currencies1.setSecond("EUR");
                value = 1D;
            }
            /**
             * get requested data from DB calculate currency rates and
             * put the results is string then send the results to html template
             */
            for (ValuteModel valuteModel : valuteModels) {
                if (fromPost) {
                    if (valuteModel.getCharCode().equals(from)) {
                        firstValue = valuteModel.getValue();
                        currencies1.setFirst(valuteModel.getCharCode());
                    }
                    if (valuteModel.getCharCode().equals(to)) {
                        secondValue = valuteModel.getValue();
                        currencies1.setSecond(valuteModel.getCharCode());
                    }
                } else {
                    if (valuteModel.getCharCode().equals(currencies1.getFirst()))
                        firstValue = valuteModel.getValue();
                    else if (valuteModel.getCharCode().equals(currencies1.getSecond()))
                        secondValue = valuteModel.getValue();
                }
            }

            BigDecimal bigDecimal = BigDecimal.valueOf(firstValue * value / secondValue)
                    .setScale(3, RoundingMode.HALF_DOWN);

            String[] result = new String[2];
            result[0] = value + " " + currencies1.getFirst() +" = ";
            result[1] = (bigDecimal) + " " + currencies1.getSecond();
            /**
             * @param valuteModels is for displaying add available currencies
             * @param Currencies for saving user input
             * @param result for sending result to thymeleaf
             */
            model.addAttribute("valuteModels", valuteModels);
            model.addAttribute("selectedcurrencies", new Currencies());
            model.addAttribute("results", result);
            return "index";
        }catch (Exception e)
        {
            System.err.println("no route to host" +
                    "there is either no internet or the website is not working");
            return "nointernet";
        }
    }
    /**
     * @param currencies get user input
     * @return redirect to get mapping with user values as PathVariable
     */

    @PostMapping({"/","/index",""})
    public String convert(@ModelAttribute("selectedcurrencies") Currencies currencies){
        String from = valuteService.findByName(currencies.getFirst()).getCharCode();
        String to = valuteService.findByName(currencies.getSecond()).getCharCode();
        return "redirect:/index/"+from+"/"+to+"/"+currencies.getAmount();
    }

}
