package com.imad.demo.controllers;
import com.imad.demo.model.HistoryModel;
import com.imad.demo.model.ValuteModel;
import com.imad.demo.services.HistoryService;
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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;

/**
 * controller class with post and get mapping and one html template
 */
@Controller
public class IndexController {

    private final ValuteService valuteService;
    private final CommandLineRunner commandLineRunner;
    private final HistoryService historyService;

    public IndexController(ValuteService valuteService, CommandLineRunner commandLineRunner, HistoryService historyService) {

        this.valuteService = valuteService;
        this.commandLineRunner = commandLineRunner;
        this.historyService = historyService;
    }

    @GetMapping({"","/index","/index/{from}/{to}/{value}"})
    public String index(Model model, @PathVariable(name = "from", required = false) String from,
                        @PathVariable(name = "to", required = false) String to,
                        @PathVariable(name = "value", required = false) Double amount) throws Exception {
        /**
         * check if the data in DB are up to date
         * if not delete old data and get new data
         * using dataloader from web source
         */

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
        Boolean validInput = true;
        /**
         * history model object to save transaction
         */
        HistoryModel historyModel = new HistoryModel();
        /**
         * check if index is redirected from post or is opened by the user without post
         */
        if (from != null && to != null && amount != null) fromPost = true;
        /**
         * if the index loaded by user without post use default values
         */
        if (!fromPost)
        {
            currencies1.setFirst("USD");
            currencies1.setSecond("EUR");
            amount = 1D;
        } else
            {
                /**
                 * if it's redirection set values submitted by user
                 * validInput is temporary variable if user inter 0 value it will not be saved in history
                 */
                currencies1.setFirst(from);
                currencies1.setSecond(to);
                if(amount <= 0 ) validInput = false;
                }
        /**
         * create 2 valute models to convert from/to
         * and get now date to verify that rates are up tp date
         */
        ValuteModel valuteModelFrom ;
        ValuteModel valuteModelTo ;
        LocalDate date = LocalDate.now();

        valuteModelFrom = valuteService.findByCharCodeAndDate(currencies1.getFirst(), date);
        valuteModelTo = valuteService.findByCharCodeAndDate(currencies1.getSecond(), date);
        /**
         * if there is no up to date rate try to get new values from site
         */
        if(valuteModelFrom == null || valuteModelTo == null)
            try {
                System.out.println("refreshing data");
                commandLineRunner.run();
                valuteModelFrom = valuteService.findByCharCodeAndDate(currencies1.getFirst(),date);
                valuteModelTo = valuteService.findByCharCodeAndDate(currencies1.getSecond(),date);
                valuteModelFrom.getCharCode();
                }catch(NullPointerException e)
                {
                    /**
                     * if error occurred, for example the website is not working use most recent rates
                     */
                    System.err.println("Today's data not Found, Using most recent date rates");
                    valuteModelFrom = valuteService.findRecent(currencies1.getFirst());
                    valuteModelTo = valuteService.findRecent(currencies1.getSecond());

                }
        if(valuteModelFrom != null && valuteModelTo != null)
        {

            firstValue = valuteModelFrom.getValue();
            secondValue = valuteModelTo.getValue();
        } else
            {
                /**
                 * if there is no date in DB and Getting data cant read xml file(currenciesdata.xml)
                 * return error page TODO
                 */
                System.out.println("No data in the DB");
                return "nodata";
            }
        /**
         * Bigdecimal to set precision on conversion
         */
        BigDecimal bigDecimal = BigDecimal.valueOf(firstValue * amount / secondValue)
                .setScale(3, RoundingMode.HALF_DOWN);

        /**
         * saving results in array (easier to parse in html file)
         */
        String[] result = new String[2];
        result[0] = amount + " " + currencies1.getFirst() +" = ";
        result[1] = (bigDecimal) + " " + currencies1.getSecond();

        /**
         * send all avialable valutes, results and Currencies object to save user input
         */
        model.addAttribute("valuteModels", valuteModels);
        model.addAttribute("selectedcurrencies", new Currencies());
        model.addAttribute("results", result);

        /**
         * if the transaction where successful save it in history
         */
        if(fromPost && validInput && !from.equals(to)) {
            historyModel.setCurrencyFrom(from);
            historyModel.setCurrencyTo(to);
            historyModel.setAmount(amount);
            historyModel.setExchangeDate(date);
            historyModel.setRatesDate(valuteModelFrom.getDate());
            historyService.save(historyModel);
        }
        return "index";
        }

    /**
     * @param currencies get user input
     * @return redirect to get mapping with user values as PathVariable
     */

    @PostMapping({"/","/index",""})
    public String convert(@ModelAttribute("selectedcurrencies") Currencies currencies){
        String from = valuteService.findFirstByName(currencies.getFirst()).getCharCode();
        String to = valuteService.findFirstByName(currencies.getSecond()).getCharCode();
        return "redirect:/index/"+from+"/"+to+"/"+currencies.getAmount();
    }

    @GetMapping("/history")
    public String history(Model model)
    {
        /**
         * send history data to history html page
         */
        model.addAttribute("valuteModels", valuteService.findAll());
        model.addAttribute("selectedcurrencies", new Currencies());
        model.addAttribute("history",historyService.findAll());
        return "history";
    }

    @GetMapping("/rates/{date}")
    public String getRates(@PathVariable("date") String date, Model model)
    {
        /**
         * send rates in transaction day to html template
         */
        try {
            System.out.println(date);
            DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate d = LocalDate.parse(date,df);

        model.addAttribute("valuteModels", valuteService.findAll());
        model.addAttribute("selectedcurrencies", new Currencies());
        model.addAttribute("results", valuteService.findAllByDate(d));
            String result[] = new String[3];
            result[0] = 1 + " " + "USD" +" = ";
            result[1] = (1) + " " + "USD";
            result[2] = date;
            model.addAttribute("result", result);
        return "ratehistory";
        }catch (Exception e)
        {
            e.printStackTrace();
            return "redirect:/index";
        }

    }
}
