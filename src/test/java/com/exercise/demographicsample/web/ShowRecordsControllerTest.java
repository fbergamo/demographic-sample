package com.exercise.demographicsample.web;

import com.exercise.demographicsample.domain.Person;
import com.exercise.demographicsample.services.IPersonService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;
import java.util.Random;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class ShowRecordsControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private IPersonService personService;

    @Before
    public void setup() throws Exception {
        Random rand = new Random();

        Person person = new Person();
        person.setPPS((long) rand.nextInt(100));
        person.setName("Federico");
        person.setBirthDate(new Date());

        this.personService.addPerson(person);

        this.mockMvc = webAppContextSetup(webApplicationContext).build();

    }

    @Test
    public void getShowRecords() throws Exception {
        mockMvc.perform(get("/show/")).andExpect(status().isOk());
    }


    @Test
    public void getShowRecords_showPerson() throws Exception {
        mockMvc.perform(get("/show/")).andExpect(model().attributeExists("persons"));
        mockMvc.perform(get("/show/")).andExpect(model().size(1));
    }
}
