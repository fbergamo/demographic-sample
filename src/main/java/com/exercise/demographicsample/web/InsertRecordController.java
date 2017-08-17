package com.exercise.demographicsample.web;

import com.exercise.demographicsample.domain.Person;
import com.exercise.demographicsample.services.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
public class InsertRecordController {
    @Autowired
    private IPersonService personService;



    @RequestMapping(value="/insert", method= RequestMethod.GET)
    public String showPage(Model model) {
        model.addAttribute("person",new Person());
        return "insert";
    }

    @RequestMapping(value="/insert", method= RequestMethod.POST)
    public void savePerson(@ModelAttribute Person person){
        personService.addPerson(person);
    }

}