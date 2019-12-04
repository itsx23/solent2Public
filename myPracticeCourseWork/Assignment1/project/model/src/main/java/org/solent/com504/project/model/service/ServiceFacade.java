package org.solent.com504.project.model.service;

import java.util.List;
import org.solent.com504.project.model.dto.Person;

public interface ServiceFacade {
    
    public String getHeartbeat();
    
    public boolean arrivedOnSite(String name, String location);
    
    public List<Person> findallPersons();
    
    
    
}
