/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.factoryandfacade.model;

import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author 4delma06
 */
public class FarmFacadeImpl implements FarmFacade {

    private List<Animal> allAnimals = new ArrayList<>();

    @Override
    public List<Animal> getAllAnimals() {

        List<Animal> animalList = new ArrayList<>(allAnimals);
        //for (Animal a : AllAnimals){
        //animalList.add(a);
        // }     
        return animalList;

    }

    @Override
    public void addDog(String name) {
        Animal d = AnimalObjectFactory.createDog();
        d.setName(name);
        allAnimals.add(d);
    }

    @Override
    public void addCat(String name) {
      Animal c = AnimalObjectFactory.createCat();
      c.setName(name);
      allAnimals.add(c);
    }

    @Override
    public void addCow(String name) {
       Animal cw =  AnimalObjectFactory.createCow();
       cw.setName(name);
       allAnimals.add(cw);
    }

}
