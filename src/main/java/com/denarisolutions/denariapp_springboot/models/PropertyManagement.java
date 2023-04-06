package com.denarisolutions.denariapp_springboot.models;

import jakarta.persistence.*;

@Entity
@Table(name="property_management")
public class PropertyManagement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String individual_company;
    @Column
    private String name;
    @Column
    private String contact;
    @Column
    private String email;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIndividual_company() {
        return individual_company;
    }

    public void setIndividual_company(String individual_company) {
        this.individual_company = individual_company;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
