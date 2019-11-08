/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.jpaexample1.impl.dao.jpa;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.solent.com504.jpaexample1.model.dao.PersonDAO;
import org.solent.com504.jpaexample1.model.dto.Person;
import org.solent.com504.jpaexample1.model.dto.Role;

/**
 *
 * @author cgallen
 */
public class PersonDAOJpaImpl implements PersonDAO {

    final static Logger LOG = LogManager.getLogger(PersonDAOJpaImpl.class);

    private EntityManager entityManager;

    public PersonDAOJpaImpl(EntityManager em) {
        this.entityManager = em;
    }

    @Override
    public Person findById(Long id) {
        Person person = entityManager.find(Person.class, id);
        return person;
    }

    @Override
    public Person save(Person person) {
        entityManager.getTransaction().begin();
        entityManager.persist(person);  // NOTE merge(animal) differnt semantics
        // entityManager.flush() could be used
        entityManager.getTransaction().commit();
        return person;
    }

    @Override
    public List<Person> findAll() {
        TypedQuery<Person> q = entityManager.createQuery("SELECT p FROM Person p", Person.class);
        List<Person> personList = q.getResultList();
        return personList;
    }

    @Override
    public void deleteById(long id) {
        entityManager.getTransaction().begin();
        Query q = entityManager.createQuery("DELETE FROM Person a WHERE a.id=:id");
        q.setParameter("id", id);
        q.executeUpdate();
        entityManager.getTransaction().commit();
    }

    @Override
    public Person delete(Person person) {
        entityManager.getTransaction().begin();
        Query q = entityManager.createQuery("DELETE FROM Person");
        q.setParameter("Person", person);
        q.executeUpdate();
        entityManager.getTransaction().commit();
        return null;
    }

    @Override
    public void deleteAll() {
        entityManager.getTransaction().begin();
        entityManager.createQuery("DELETE FROM Person ").executeUpdate();
        entityManager.getTransaction().commit();
    }

    @Override
    public List<Person> findByRole(Role role) {

        Person person = entityManager.find(Person.class, role);
        return (List<Person>) person;

    }

    @Override
    public List<Person> findByName(String firstName, String secondName) {
        /*
            queryString = queryString + "AND a.name LIKE :name "; //':name' ";
            paramMap.put("name", animalTemplate.getName());
         */
        entityManager.getTransaction().begin();
        Query q = entityManager.createQuery("select a FROM Person a WHERE person.firstName=:firstName");
        q.setParameter("firstName", firstName);
        q.executeUpdate();
        Query q2 = entityManager.createQuery("select a FROM Person a WHERE secondName.=:secondName");
        q.setParameter("firstName", firstName);
        q.executeUpdate();
        entityManager.getTransaction().commit();
        return null;
    }

}
