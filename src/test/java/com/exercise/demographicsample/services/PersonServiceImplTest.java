package com.exercise.demographicsample.services;


import com.exercise.demographicsample.domain.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PersonServiceImplTest {
    @Autowired
    private TestEntityManager entityManager;


    @TestConfiguration
    static class PersonServiceImplTestContextConfiguration {

        @Bean
        public IPersonService personService() {
            return new PersonServiceImpl();
        }
    }

    @Autowired
    private IPersonService personService;

    @Test
    public void testFindAllCall() {
        final Integer TEST_NUM_PEOPLE = 10;

        for(int i=0; i<TEST_NUM_PEOPLE;i++){
            entityManager.persist(getPerson((long)i));
        }

        entityManager.flush();

        List<Person> found = personService.findAll();

        assertThat(found.size())
                .isEqualTo(TEST_NUM_PEOPLE);
    }


    @Test
    public void testFindByPPSCall() {
        Long testPPS = (long) 1234;


        entityManager.persist(getPerson(testPPS));
        entityManager.flush();

        List<Person> found = personService.findByPPS(testPPS);

        assertThat(found.size())
                .isEqualTo(1);
    }

    @Test
    public void testFindByPPSCall_withEmptyDB() {
        Long testPPS = (long) 1234;

        List<Person> found = personService.findByPPS(testPPS);

        assertThat(found.size())
                .isEqualTo(0);
    }

    @Test
    public void testFindByPPSCall_withWrongPPS() {
        Long testPPS = (long) 1234;

        entityManager.persist(getPerson(testPPS));
        entityManager.flush();

        List<Person> found = personService.findByPPS(testPPS + 1);

        assertThat(found.size())
                .isEqualTo(0);
    }

    @Test
    public void testAddPerson_withNoPPS(){
        Person person = getPerson((long)1234);
        person.setPPS(null);
        try {
           personService.addPerson(person);
        } catch (DataIntegrityViolationException ex){
            assertThat(ex).hasMessageContaining("NULL not allowed for column \"PPS\"");
        }

    }


    @Test
    public void testAddPerson(){
        Person person = getPerson((long)1234);

        personService.addPerson(person);

        assertThat(personService.findAll().size()).isEqualTo(1);

    }

    private Person getPerson(long PPS){
        Person person = new Person();
        person.setName("Federico");
        person.setPPS(PPS);
        person.setBirthDate(new Date());
        return person;
    }

}
