package org.lforeman.models;


import org.hibernate.validator.constraints.Email;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {

    @Id
    @NotNull
    @Size(min = 5, max = 15, message = "Username must be between 5 and 15 characters")
    private String username;

    @Email
    private String email;

    @NotNull
    @Size(min = 6)
    private String password;

    public List<Grocery> getGroceries() {
        return groceries;
    }

    @OneToMany
    @JoinColumn(name = "user_id")
    private List<Grocery> groceries = new ArrayList<>();

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User() {


    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setGroceries(List<Grocery> groceries) {
        this.groceries = groceries;

    }
}

