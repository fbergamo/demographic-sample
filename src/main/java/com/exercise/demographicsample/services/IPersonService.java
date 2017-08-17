package com.exercise.demographicsample.services;

import com.exercise.demographicsample.domain.Person;

import java.util.List;

public interface IPersonService {

    public List<Person> findAll();

    public void addPerson(Person person);

}
