package org.lforeman.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Storage {

@Id
@GeneratedValue
private int id;

@NotNull
@Size(min=3, max=15)
private String name;

@OneToMany
@JoinColumn(name = "storage_id")
private List<Grocery> groceries = new ArrayList<>();

// constructors
public Storage() { }

public Storage(String name) {
    this.name = name;
}

    // Getters Setters
public int getId() { return id; }

public String getName() { return name; }

public void setName(String name) {
this.name = name;}

    public List<Grocery> getGroceries() {
        return groceries;
    }
}

