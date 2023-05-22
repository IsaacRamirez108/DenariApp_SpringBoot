package com.denarisolutions.denariapp_springboot.models;

import jakarta.persistence.*;

@Entity
@Table(name="rental_data")
public class RentalData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    // Still need to figure out how to go about uploading lease document
    //@Column
    //private String imgUrl;
    @Column (nullable = false)
    private String monthly_rent;
    @Column (nullable = false)
    private String moveIn_date;
    @Column (nullable = false)
    private String moveOut_date;
    @Column (nullable = false)
    private String rental_portal;
    @Column (nullable = false)
    private String form_payment;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private  User user;

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne
    @JoinColumn(name = "propertymanagement_id")
    private PropertyManagement propertymanagement;

    public RentalData(long id, String monthly_rent, String moveIn_date, String moveOut_date, String rental_portal, String form_payment) {
        this.id = id;
        this.monthly_rent = monthly_rent;
        this.moveIn_date = moveIn_date;
        this.moveOut_date = moveOut_date;
        this.rental_portal = rental_portal;
        this.form_payment = form_payment;
    }
    public RentalData(String monthly_rent, String moveIn_date, String moveOut_date, String rental_portal, String form_payment) {
        this.monthly_rent = monthly_rent;
        this.moveIn_date = moveIn_date;
        this.moveOut_date = moveOut_date;
        this.rental_portal = rental_portal;
        this.form_payment = form_payment;
    }

    public RentalData() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMonthly_rent() {
        return monthly_rent;
    }

    public void setMonthly_rent(String monthly_rent) {
        this.monthly_rent = monthly_rent;
    }

    public String getMoveIn_date() {
        return moveIn_date;
    }

    public void setMoveIn_date(String moveIn_date) {
        this.moveIn_date = moveIn_date;
    }

    public String getMoveOut_date() {
        return moveOut_date;
    }

    public void setMoveOut_date(String moveOut_date) {
        this.moveOut_date = moveOut_date;
    }

    public String getRental_portal() {
        return rental_portal;
    }

    public void setRental_portal(String rental_portal) {
        this.rental_portal = rental_portal;
    }

    public String getForm_payment() {
        return form_payment;
    }

    public void setForm_payment(String form_payment) {
        this.form_payment = form_payment;
    }

}
