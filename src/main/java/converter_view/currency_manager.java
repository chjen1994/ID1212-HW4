/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter_view;

import converter_controller.converterFacade;
//import converter_model.currency_converterDTO;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author davidren
 */
@Named("converter_Manager")
@ConversationScoped
public class currency_manager implements Serializable{
    @EJB
    private converterFacade converterfacade;
    private double amountToConvert;
    private String userCurrency;
    private String outputCurrency;
    private String outputCurrencyAmount;
    private Exception transactionFailure;
    @Inject
    private Conversation conversation;
    
    
    public void convert(){
        try{
            startConversation();
            transactionFailure = null;
            System.out.println(this.userCurrency);
            System.out.println(this.outputCurrency);
            System.out.println(this.amountToConvert);
            outputCurrencyAmount = converterfacade.converter(userCurrency,outputCurrency,amountToConvert );
            System.out.println("result in manager out curr "+outputCurrencyAmount);
        }catch (Exception e) {
            handleException(e);
        }
    }

    /**
     *
     * @return
     */
    public String getOutputCurrencyAmount(){
        return outputCurrencyAmount;
    }
    
    /**
     *
     * @param userCurrency
     */
    public void setUserCurrency(String userCurrency){
        this.userCurrency = userCurrency;
    }
    
    /**
     *
     * @return
     */
    public String getUserCurrency(){
        return userCurrency;
    }
    
    /**
     *
     * @param outputCur
     */
    public void setOutputCurrency(String outputCur){
        this.outputCurrency = outputCur;
    }
    
    /**
     *
     * @return
     */
    public String getOutputCurrency(){
        return outputCurrency;
    }
      
    /**
     *
     * @param amountToConvert
     */
    public void setAmountToConvert(double amountToConvert){
        this.amountToConvert = amountToConvert;
    }
    
    /**
     *
     * @return
     */
    public double getAmountToConvert(){
        return amountToConvert;
    }
    
    
    private void startConversation() {
        if (conversation.isTransient()) {
            conversation.begin();
        }
    }

    private void stopConversation() {
        if (!conversation.isTransient()) {
            conversation.end();
        }
    }
    
    private void handleException(Exception e) {
        stopConversation();
        e.printStackTrace(System.err);
        transactionFailure = e;
    }
    
    public boolean getSuccess() {
        return transactionFailure == null;
    }
    
    public Exception getException() {
        return transactionFailure;
    }
   
}
