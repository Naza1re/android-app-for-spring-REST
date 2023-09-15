package com.example.springserverapp.model;

public class Employee {

    private Long id;

    private String name;

    private String location;

    private String branch;
    public Employee(){

    }

    public Employee(String name, String location, String branch) {
        this.name = name;
        this.location = location;
        this.branch = branch;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}

