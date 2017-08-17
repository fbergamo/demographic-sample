package com.exercise.demographicsample.web;

import com.exercise.demographicsample.domain.Person;
import com.exercise.demographicsample.services.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class InsertRecordController {
    @Autowired
    private IPersonService personService;


    @RequestMapping("/insert")
    public String showPage() {
        return "insert";
    }

    @RequestMapping(value="/savePerson", method= RequestMethod.POST)
    public void savePerson(@RequestParam(value="person", required = true) Person person){
        personService.addPerson(person);
    }

}