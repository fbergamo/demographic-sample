package com.exercise.demographicsample.domain;

import java.util.Date;

public class Person {
    private String name;
    private Long PPS;
    private Date birthDate;
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

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
