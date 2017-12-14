/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter_controller;


import converter_integration.currency_rateDAO;
import converter_model.currency_converter;
import converter_model.currency_converterDTO;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import java.io.*;


/**
 *
 * @author davidren
 */

@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@Stateless
public class converterFacade {
 
    @EJB currency_rateDAO converterDAO;
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
//    public void storeRates(double rateEuro, double rateUsd, double rateGbp) {
//        currency_converter newConverter = new currency_converter(rateEuro, rateUsd, rateGbp);
//        converterDAO.storeConverter(newConverter);
//    }
    public String converter(String userCurrency, String outputCurrency, Double amountToConvert) {
        System.out.println(userCurrency);
        currency_converter currency1 = converterDAO.findCurrencyFrom(userCurrency);
      //currency_converter currency = converterDAO.findCurrentCurrency(userCurrency, outputCurrency, amountToConvert);
      //System.out.println("haha150"+currency1.getUserCurrency());
      return currency1.convert(outputCurrency, amountToConvert);
    }
    

    
    
    
    public String startConversion(double euro, double usd, double sek, double jpy, double amountToConvert, String inCurrency, String outCurrency) {
        
        currency_converter newConverter = new currency_converter(inCurrency, euro, usd, sek, jpy);
        return newConverter.convert(outCurrency, amountToConvert);
    }
}
