/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter_model;

import java.io.Serializable;
import java.util.Random;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
/**
 *
 * @author davidren
 */
@Entity
public class currency_converter implements currency_converterDTO, Serializable{
    @Id
    @Basic(optional = false)
    @Column(name = "userCurrency")
    private String userCurrency;
    @Basic(optional = false)
    @Column(name = "SEK")
    private double SEK;
    @Basic(optional = false)
    @Column(name = "EURO")
    private double EURO;
    @Basic(optional = false)
    @Column(name = "USD")
    private double USD;
    @Basic(optional = false)
    @Column(name = "JPY")
    private double JPY;
    
    public currency_converter() {
    }
    public currency_converter(String inCurrency, double euro, double usd, double sek, double jpy) {
        this.userCurrency = inCurrency;
        this.EURO = euro;
        this.USD = usd;
        this.SEK = sek;
        this.JPY = jpy;
    }
    
    
    @Override
    public String convert(String outCurrency, double amount){
        System.out.println("in model: out curr "+outCurrency+" amount "+amount+" ");
        double output = 0;
        switch (outCurrency) {
            case "SEK":
                output = this.SEK * amount;
                break;
            case "EURO":
                output = this.EURO * amount;
                break;
            case "USD":
                output = this.USD * amount;
                break;
            case "JPY":
                output = this.JPY * amount;
                break;

        }
        //System.out.println("result: in string"+String.valueOf(output) + " in double "+output);
        return String.valueOf(output);
    }
    
    


    public String getUserCurrency() {
        return userCurrency;
    }

    public void setUserCurrency(String inCurrency) {
        this.userCurrency = inCurrency;
    }
    
    
    
    @Override
    public double getEURO() {
        return EURO;
    }

    @Override
    public double getUSD() {
        return USD;
    }

    @Override
    public double getSEK() {
        return SEK;
    }
    @Override
    public double getJPY() {
        return JPY;
    }
    
    
    
    @Override
    public void setJPY(double JPY) {
        this.JPY = JPY;
    }
    @Override
    public void setSEK(double SEK){
        this.SEK = SEK;
    }
    @Override
    public void setUSD(double USD){
        this.USD = USD;
    }
    @Override
    public void setEURO(double EURO){
        this.EURO = EURO;
    }
    
}
