package com.exercise.demographicsample.web;

import com.exercise.demographicsample.domain.Person;
import com.exercise.demographicsample.services.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import javax.validation.Valid;
import java.time.*;
import java.time.temporal.TemporalField;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
    public String savePerson(@Valid Person person,BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()) {
            return "insert";
        }

        if(!checkDate(person.getBirthDate())){
            model.addAttribute("message","The Birth Date cannot be in future and must be over 16 years old.");
            return "insert";
        }

        if(!checkPPS(person.getPPS())){
            model.addAttribute("message","This PPS is already in DB.");
            return "insert";
        }

        // Add 08 to the phone number
        if(person.getPhoneNumber()!=null && person.getPhoneNumber()!=""){
            person.setPhoneNumber("08" + person.getPhoneNumber());
        }

        personService.addPerson(person);

        return "redirect:/show";
    }

    private boolean checkDate(Date selectedDate){
        Date now = Date.from(Instant.now());

        if(now.before(selectedDate)) return false;


        LocalDate localNow = now.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate selectedDateNow = selectedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        Period duration = Period.between(selectedDateNow,localNow);

        if(duration.getYears()<16) return false;

        return true;
    }

    private boolean checkPPS(Long PPS){
        List<Person> foundPers = personService.findByPPS(PPS);

        if(foundPers.size()==1)return false;

        return true;
    }

}