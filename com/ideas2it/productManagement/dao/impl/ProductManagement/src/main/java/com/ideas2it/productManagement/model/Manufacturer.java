package com.ideas2it.productManagement.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Manufacturer extends BaseModel {
	@Column(name = "brand", columnDefinition = "varchar(255)")
    private String brand;
	@Column(name = "place", columnDefinition = "varchar(255)")
    private String place;
    @OneToMany(cascade = CascadeType.ALL, mappedBy =  "manufacturer")
    private List<Product> products;

    public Manufacturer(){}
    
    public Manufacturer(String brand, String place) {
		this.brand = brand;
		this.place = place;
	}

	public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
   
    public String getPlace() {
        return place;
    }
   
    public void setPlace(String place) {
        this.place = place;
    }
   
    public List<Product> getProducts() {
        return products;
    }
   
    public void setProducts(List<Product> products) {
        this.products = products;
    }
    
    public String toString() {
        return (" id = " + getId() + "\n"
               + " Manufacturer Brand = " + brand + "\n"
               + " Manufacturer Place = " + place );
             
    }
}

