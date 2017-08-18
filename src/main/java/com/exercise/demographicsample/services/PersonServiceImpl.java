package com.exercise.demographicsample.services;

import com.exercise.demographicsample.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements IPersonService {

    @Autowired
    private JdbcTemplate jtm;


    @Override
    public List<Person> findAll() {
        String sql = "SELECT * FROM Persons";
        return jtm.query(sql, new BeanPropertyRowMapper(Person.class));
    }


    @Override
    public List<Person> findByPPS(Long PPS) {
        String sql = "SELECT * FROM Persons WHERE PPS='" + PPS + "'";
        return jtm.query(sql, new BeanPropertyRowMapper(Person.class));
    }

    @Override
    public void addPerson(Person person) {
        String sql = "INSERT INTO Persons(PPS,NAME,PHONE_NUMBER,BIRTH_DATE) VALUES (?,?,?,?)";
        jtm.update(sql, person.getPPS(),person.getName(),person.getPhoneNumber(),person.getBirthDate());
    }
}
