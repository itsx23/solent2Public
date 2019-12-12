package org.solent.com504.project.model.service;

import java.util.List;
import org.solent.com504.project.model.dto.Person;

public interface ServiceFacade {
    
    public String getHeartbeat();
    
    public boolean arrivedOnSite(String name, String location);
    
    public List<Person> findallPersons();
    
    public void deletePerson(Long id);
    
    public Person updatePerson(Person person);
    
    public Person createPerson(Person person);
    
    public Person retrievePerson(Person person);
    
    public Person retrievePersonById(Long id);
    
}
