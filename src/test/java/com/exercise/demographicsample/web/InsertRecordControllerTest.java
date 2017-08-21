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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;
import java.util.Map;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class InsertRecordControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private IPersonService personService;

    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();

    }

    @Test
    public void getInsertRecord() throws Exception {
        mockMvc.perform(get("/insert/")).andExpect(status().isOk());
    }

    @Test
    public void getInsertRecord_noPPS() throws Exception {
        MvcResult result = mockMvc.perform(post("/insert").contentType(APPLICATION_FORM_URLENCODED)).andReturn();

        Person person = (Person)result.getModelAndView().getModel().entrySet().iterator().next().getValue();

        assertThat(person.getPPS()).isEqualTo(null);
    }

    @Test
    public void getInsertRecord_wrongDate() throws Exception {
        Long PPS = (long)1234;
        String name = "Federico";
        Date date = new Date();

        MvcResult result = mockMvc.perform(post("/insert").contentType(APPLICATION_FORM_URLENCODED)
                .param("PPS",PPS.toString()).param("name",name).param("birthDate", date.toString())).andReturn();

        Person person = (Person)result.getModelAndView().getModel().entrySet().iterator().next().getValue();

        assertThat(person.getBirthDate()).isEqualTo(null);
    }

}
