/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siec.model.persistence.entity;

import br.com.siec.model.persistence.interfaces.IMetodoDePagamento;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @version 1.0.0 17 November, 2013
 * @author Josimar Alves
 */
@Entity
@Table(name = "TB_PAYPAL_PPL", schema = "siec")
public class PayPal implements IMetodoDePagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PPL_CODE")
    private long id;
    
    @Column(name="PPL_PAYMENT_AMOUNT")
    private String paymentAmount;
    
    @Column(name = "PPL_CURRENCY_CODE_TYPE")
    private static String CURRENCY_CODE_TYPE = "BRL";
    
    @Column(name = "PPL_SHIP_TO_NAME")
    private String shipToName;
    
    @Column(name = "PPL_SHIP_TO_STREET")
    private String shipToStreet;
    
    @Column(name = "PPL_SHIP_TO_CITY")
    private String shipToCity;
    
    @Column(name = "PPL_SHIP_TO_STATE")
    private String shipToState;
    
    @Column(name = "PPL_SHIP_TO_ZIP")
    private String shipToZip;
    
    @Column(name = "PPL_PHONE_NUM")
    private String phoneNum;
    
    @Column(name = "PPL_CREDIT_CARD_TYPE")
    private String creditCardType;
    
    @Column(name = "PPL_CREDIT_CARD_NUMBER")
    private String creditCardNumber;
    
    @Column(name = "PPL_EXP_DATE")
    private String expDate;
    
    @Column(name = "PPL_CVV2")
    private String cvv2;
    
    @Column(name = "PPL_FIRST_NAME")
    private String firstName;
    
    @Column(name = "PPL_LAST_NAME")
    private String lastName;
    
    @Column(name = "PPL_IP_ADDRESS")
    private String IPAddress;

    public PayPal(String paymentAmount, String shipToName, String shipToStreet, 
            String shipToCity, String shipToState, String shipToZip, String phoneNum, 
            String creditCardType, String creditCardNumber, 
            String expDate, String cvv2, String firstName, String lastName, String IPAddress) {
        this.paymentAmount = paymentAmount;
        this.shipToName = shipToName;
        this.shipToStreet = shipToStreet;
        this.shipToCity = shipToCity;
        this.shipToState = shipToState;
        this.shipToZip = shipToZip;
        this.phoneNum = phoneNum;
        this.creditCardType = creditCardType;
        this.creditCardNumber = creditCardNumber;
        this.expDate = expDate;
        this.cvv2 = cvv2;
        this.firstName = firstName;
        this.lastName = lastName;
        this.IPAddress = IPAddress;
    }   
    
    public PayPal(){
        
    }
    
    @Override
    public void pagar(double valor) {
        System.out.println("Efetuando Pagamento Via PayPal........");
        System.out.println("Valor do Pagamento: " + CURRENCY_CODE_TYPE + " " + valor);
        System.out.println("Pagamento Efetuado.....");
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(String paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getShipToName() {
        return shipToName;
    }

    public void setShipToName(String shipToName) {
        this.shipToName = shipToName;
    }

    public String getShipToStreet() {
        return shipToStreet;
    }

    public void setShipToStreet(String shipToStreet) {
        this.shipToStreet = shipToStreet;
    }

    public String getShipToCity() {
        return shipToCity;
    }

    public void setShipToCity(String shipToCity) {
        this.shipToCity = shipToCity;
    }

    public String getShipToState() {
        return shipToState;
    }

    public void setShipToState(String shipToState) {
        this.shipToState = shipToState;
    }

    public String getShipToZip() {
        return shipToZip;
    }

    public void setShipToZip(String shipToZip) {
        this.shipToZip = shipToZip;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getCreditCardType() {
        return creditCardType;
    }

    public void setCreditCardType(String creditCardType) {
        this.creditCardType = creditCardType;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public String getExpDate() {
        return expDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

    public String getCvv2() {
        return cvv2;
    }

    public void setCvv2(String cvv2) {
        this.cvv2 = cvv2;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getIPAddress() {
        return IPAddress;
    }

    public void setIPAddress(String IPAddress) {
        this.IPAddress = IPAddress;
    }
}
