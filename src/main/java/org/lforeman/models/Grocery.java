package org.lforeman.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;

@javax.persistence.Entity
public class Grocery {
    @Id
    @GeneratedValue
    private int id;


    //@NotNull
    @Size(min = 3, max = 15)
    private String name;

    @NotNull
    @Size(min = 1, message = "Description must not be empty")
    private String description;

    @ManyToOne
    private Storage storage;

    private Date purchaseDate;


    private Date expirationDate;

    @ManyToOne
    private User user;


    public Grocery(int id, String name, String description, Storage storage, Date purchaseDate, Date expirationDate, User user) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.user = user;
        this.storage = storage;
        this.purchaseDate = purchaseDate;
        this.expirationDate = expirationDate;
    }
    public Grocery(){}
    public Grocery(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser(){
        return user;
    }

    public void setUser(User user){
        this.user = user;
    }
}
