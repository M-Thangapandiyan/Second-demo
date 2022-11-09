package com.ideas2it.productManagement.model;   

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@MappedSuperclass
public class BaseModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
	@CreationTimestamp
	@Column(name = "created_at")
    private Date createdAt;
	
	@UpdateTimestamp
	@Column(name = "updated_at")
    private Date updatedAt;
	
	@Column(name = "is_deleted", columnDefinition = "tinyInt default 0")
    private boolean isDeleted;
   
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }
   
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
    
    public Date getUpdatedAt() {
        return updatedAt;
    }
   
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt =  updatedAt;
    }

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
}