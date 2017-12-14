/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter_integration;


import converter_model.currency_converter;
import converter_model.currency_converterDTO;

import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
/**
 *
 * @author davidren
 */
@TransactionAttribute(TransactionAttributeType.MANDATORY)
@Stateless
public class currency_rateDAO {
    @PersistenceContext(unitName = "convereterPU")
    private EntityManager em;
    

    public void storeConverter(currency_converter newConverter) {
        em.persist(newConverter);
        
    }
    
    
    public currency_converterDTO findConverter() {
        List result = em.createQuery("SELECT c FROM currency_converter c").getResultList();
        currency_converter converter = (currency_converter) result.get(result.size() - 1);
        if (converter == null) {
            throw new EntityNotFoundException("No currencies registered!");
        }
        return converter;
    }   
    
    public currency_converter findCurrencyFrom(String currency) {
        Query query = em.createQuery("SELECT c FROM currency_converter c");
        List<currency_converter> currencies = query.getResultList();
	if(currencies == null || currencies.isEmpty()) {
            throw new EntityNotFoundException("No currency with name: " + currency);
        }
        for(currency_converter c : currencies) {
            if(c.getUserCurrency().equals(currency)) {
                return c;
          }
        }
        throw new EntityNotFoundException("No currency with name: " + currency);
    }

    
    public currency_converter findCurrentCurrency(String userCurrency, String outputCurrency, Double amountToConvert){
        //System.out.println(userCurrency);
        currency_converter currentCurrency = em.find(currency_converter.class, userCurrency);
        
        if (currentCurrency == null) {
            throw new EntityNotFoundException("Currency does not exist " + userCurrency);
        }
        
        return currentCurrency;
    }
}
