/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.jpaexample1.impl.dao.jpa.test;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.solent.com504.jpaexample1.impl.dao.jpa.DAOFactoryJPAImpl;
import org.solent.com504.jpaexample1.model.dao.PersonDAO;
import org.solent.com504.jpaexample1.model.dto.Person;
import org.solent.com504.jpaexample1.model.dto.Role;

/**
 *
 * @author cgallen
 */
public class PersonDAOTest {

    final static Logger LOG = LogManager.getLogger(PersonDAOTest.class);

    private PersonDAO personDao = null;

    private DAOFactoryJPAImpl daoFactory = new DAOFactoryJPAImpl();

    @Before
    public void before() {
        personDao = daoFactory.getPersonDAO();
        assertNotNull(personDao);
    }

    // initialises database for each test
    private void init() {
        // delete all in database
        personDao.deleteAll();
        // add 5 patients
        for (int i = 1; i < 6; i++) {
            Person p = new Person();
            p.setAddress("address_" + i);
            p.setFirstName("firstName_" + i);
            p.setSecondName("secondName_" + i);
            p.setRole(Role.PATIENT);
            personDao.save(p);
        }
    }

    @Test
    public void createPersonDAOTest() {
        LOG.debug("start of createPersonDAOTest");
        // this test simply runs the before method

        LOG.debug("end of createPersonDAOTest");
    }

    @Test
    public void findByIdTest() {
        LOG.debug("start of findByIdTest()");

        //TODO implement test
        List<Person> personList = personDao.findAll();
        assertNotNull(personList);
        assertTrue(!personList.isEmpty());
        Person person1 = personList.get(0);
        LOG.debug("person1=" + person1);
        Long person1Id = person1.getId();
        Person person2 = personDao.findById(person1Id);
        LOG.debug("person2=" + person2);

        assertTrue(person1.toString().equals(person2.toString()));

        LOG.debug("end of findByIdTest()");
    }

    @Test
    public void saveTest() {
        LOG.debug("start of saveTest()");
        //
        init();

        List<Person> persons = personDao.findAll();
        assertFalse(persons.isEmpty());

        // get animal in middle of index
        int index = persons.size() / 2;
        Person person = persons.get(index);
        LOG.debug("initial person index:" + index + " " + person);

        // change values
        person.setAddress("new address");
        person.setFirstName("new first name");
        LOG.debug("new person details " + person);

        personDao.save(person);
        Long id = person.getId();

        LOG.debug("end of saveTest()");
    }

    @Test
    public void findAllTest() {
        LOG.debug("start of findAllTest()");

        init();
        List<Person> personList = personDao.findAll();
        assertNotNull(personList);

        // init should insert 5 people
        assertEquals(5, personList.size());

        // print out result
        String msg = "";
        for (Person person : personList) {
            msg = msg + "\n   " + person.toString();
        }
        LOG.debug("findAllTest() retrieved:" + msg);

        LOG.debug("end of findAllTest()");
    }

    @Test
    public void deleteByIdTest() {
        LOG.debug("start of deleteByIdTest()");
        //TODO implement test 

        assertNotNull(personDao);
        init();

        List<Person> persons = personDao.findAll();
        assertFalse(persons.isEmpty());

        Person a = persons.get(0);
        LOG.debug("deleting " + a);
        Long id = a.getId();

        personDao.deleteById(id);

        LOG.debug("end of deleteByIdTest()");
    }

    @Test
    public void deleteTest() {
        LOG.debug("start of deleteTest()");
        //TODO implement test (fail)

        assertNotNull(personDao);
        init(); // initialise database

        List<Person> persons = personDao.findAll();
        assertFalse(persons.isEmpty());

        Person a = persons.get(0);
        LOG.debug("deleting " + a);
        Long id = a.getId();

        personDao.delete(a);

        Person a2 = personDao.findById(id);
        assertNull(a2);

        LOG.debug("end ofdeleteTest()");
    }

    @Test
    public void deleteAllTest() {
        LOG.debug("start of deleteAllTest())");
        //
        LOG.debug("start of deleteTest()");
        assertNotNull(personDao);
        init(); // initialise database

        List<Person> persons = personDao.findAll();
        assertFalse(persons.isEmpty());

        personDao.deleteAll();

        LOG.debug("end of deleteAllTest()");
    }

    @Test
    public void findByRoleTest() {
        LOG.debug("start of findByIdTest()");
        // 
        List<Person> personList = personDao.findByRole(Role.PATIENT);
        assertNotNull(personList);
        assertTrue(!personList.isEmpty());
        Person person1 = personList.get(0);
        LOG.debug("person1=" + person1);

        LOG.debug("end of findByIdTest()");
    }

    @Test
    public void findByNameTest() {
        LOG.debug("start of findByNameTest()");
        // (fail)
        
        List<Person> personList = personDao.findAll();
        assertNotNull(personList);
       assertTrue(!personList.isEmpty());
        Person person1 = personList.get(0);
        String firstName=person1.getFirstName();
        String secondName = person1.getSecondName();
        LOG.debug("firstName=" + firstName);
        LOG.debug("secondName=" + person1.getSecondName());
        
        personList = personDao.findByName(firstName, secondName);
        Person person2 = personList.get(0);
        LOG.debug(person1.toString() +"   "+person2.toString());
        assertTrue(person1.toString().equals(person2.toString()));
        
        LOG.debug("end of findByNameTest())");

    }
}
