package com.exercise.demographicsample.domain;

import javax.persistence.*;
import java.util.Date;


public class Person {

    private String name;

    private Long PPS;


    private String birthDate;


    private Integer phoneNumber;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPPS() {
        return PPS;
    }

    public void setPPS(Long PPS) {
        this.PPS = PPS;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
