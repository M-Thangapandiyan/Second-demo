package com.ideas2it.productManagement.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Dealer extends BaseModel {
	@Column(name = "company", columnDefinition = "varchar(255)")
    private String company;
	@Column(name = "location", columnDefinition = "varchar(255)")
    private String location;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy =  "dealer")
    
    private List<Product> products;
  
    public Dealer(){}

    public Dealer(String company, String location) {
        this.company = company;
        this.location = location;
    }
    
    public void setCompany(String company) {
        this.company = company;
    }
   
    public String getCompany() {
        return company;
    }

    public String getLocation() {
        return location;
    }
   
    public void setLocation(String location) {
        this.location = location;
    }
   
    public List<Product> getProducts() {
        return products;
    }
   
    public void setProducts(List<Product> products) {
        this.products = products;
    }
    
    public String toString() {
        return(" Deale Id = " + getId() + "\n"
               + " Dealer Company = " + company + "\n"
               + " Dealer Location = " + location);
    }
}
  