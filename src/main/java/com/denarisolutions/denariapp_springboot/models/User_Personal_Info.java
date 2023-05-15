package com.denarisolutions.denariapp_springboot.models;
import jakarta.persistence.*;

@Entity
@Table(name="users_personal_info")
public class User_Personal_Info {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String SSN;
    @Column
    private String DOB;
    @Column
    private String phoneNumber;

    public User_Personal_Info(long id, String SSN, String DOB, String phoneNumber) {
        this.id = id;
        this.SSN = SSN;
        this.DOB = DOB;
        this.phoneNumber = phoneNumber;
    }

    public User_Personal_Info() {
    }

    public String getSSN() {
        return SSN;
    }

    public void setSSN(String SSN) {
        this.SSN = SSN;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}
