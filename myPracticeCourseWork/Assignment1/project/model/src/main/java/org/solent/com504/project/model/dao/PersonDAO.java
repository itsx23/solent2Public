package org.solent.com504.project.model.dao;

import java.util.List;
import org.solent.com504.project.model.dto.Person;
import org.solent.com504.project.model.dto.Role;

public interface PersonDAO {

    public Person findById(Long id);

    public Person save(Person person);

    public List<Person> findAll();

    public void deleteById(long id);

    public Person delete(Person person);

    public void deleteAll();

    public List<Person> findByRole(Role role);

    public List<Person> findByName(String firstName, String secondName);
    
    public List<Person> retrievePerson(Person person);

    public Person updatePerson(Person person);
    
    public Person createPerson(Person person);
}
