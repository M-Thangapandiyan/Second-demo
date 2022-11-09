package com.ideas2it.productManagement.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.ideas2it.productManagement.util.DateUtil;
import com.ideas2it.productManagement.util.enumaration.Colour;

@Entity
public class Product extends BaseModel {
	@Column(name = "product_code", columnDefinition = "varchar(255)")
    private String code;
	@Column(name = "name", columnDefinition = "varchar(255)")
    private String name;
    @Column(name = "colour")
    @Enumerated(EnumType.STRING)
    private Colour colour;
    @Column(name = "price", columnDefinition = "int")
    private int price;
    @Column(name = "date_of_manufacture")
    private Date dateOfManufacture;
    
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "manufacture_id", columnDefinition = "int")
    private Manufacturer manufacturer;
    
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "dealer_id", columnDefinition = "int")
    private Dealer dealer;

    public Product(){}
    
    public Product(String code, String name, int price, Colour colour, Date dateOfManufacture) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.colour = colour;
        this.dateOfManufacture = dateOfManufacture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
   
    public Colour getColour() {
        return colour;
    }

    public void setColour(Colour colour) {
        this.colour = colour;
    }
 
    public int getPrice() {
        return price;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
 
    public String getCode() {
        return code;
    }
   
    public void setPrice(int price) {
        this.price = price;
    }
    
    public void setDate(Date dateOfManufacture) {
        this.dateOfManufacture = dateOfManufacture;
    }   
      
    public Date getDate() {
        return dateOfManufacture;
    }
   
    public int getLifeTime(Date dateOfManufacture) {
        return DateUtil.find(dateOfManufacture, DateUtil.currentDate);
    }
      
    public Manufacturer getManufacturer() {
        return manufacturer;
    }
   
    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }
   
    public Dealer getDealer() {
        return dealer;
    }
   
    public void setDealer(Dealer dealer) {
        this.dealer = dealer;
    }
   
    @Override
    public String toString() {
        return("\n**** Product Details ****" + "\n" 
               + " Product Code   : " + code + "\n"
               + " Product Name   : " + name  + "\n" 
               + " Product Colour : " + colour + "\n" 
               + " Product Price  : " + price + "\n" 
               + " Product Id     : " + getId() + "\n"   
               + " Product Manufacture date :"+ dateOfManufacture + "\n"
               + " Product LifeTime : " + getLifeTime(dateOfManufacture));
    }
}

    
    
    