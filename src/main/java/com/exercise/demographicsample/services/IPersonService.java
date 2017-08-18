package com.exercise.demographicsample.services;

import com.exercise.demographicsample.domain.Person;

import java.util.List;

public interface IPersonService {

    public List<Person> findAll();

    public List<Person> findByPPS(Long PPS);

    public void addPerson(Person person);

}
