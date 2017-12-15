/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter_model;

/**
 *
 * @author davidren
 */
public interface currency_converterDTO {
    //public double getConvertedAmount(double amount, String fromCurrency, String toCurrency);
   
    public String convert(String outCurrency, double amount);
    public double getEURO();
    public double getUSD();
    public double getSEK();
    public double getJPY();
    
    public void setJPY(double JPY);
    public void setSEK(double SEK);
    public void setUSD(double USD);
    public void setEURO(double EURO);
            
            
}
