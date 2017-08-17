package com.exercise.demographicsample.web;

import com.exercise.demographicsample.domain.Person;
import com.exercise.demographicsample.services.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class ShowRecordsController {
    @Autowired
    private IPersonService personService;



    @RequestMapping(value="/show", method= RequestMethod.GET)
    public String showPage(Model model) {
        List<Person> persons = personService.findAll();
        model.addAttribute("persons",persons);
        return "show";
    }
}
