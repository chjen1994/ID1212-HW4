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
    
    

    
    public currency_converter findCurrencyFrom(String currency) {
        Query query = em.createQuery("SELECT c FROM currency_converter c");
        List<currency_converter> currencies = query.getResultList();
	if(currencies == null || currencies.isEmpty()) {
            throw new EntityNotFoundException("No such currency in the db: " + currency);
        }
        for(currency_converter c : currencies) {
            if(c.getUserCurrency().equals(currency)) {
                return c;
          }
        }
        throw new EntityNotFoundException("No such currency in the db: " + currency);
    }


}
