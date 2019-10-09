package org.solent.com504.factoryandfacade.impl;

import org.solent.com504.factoryandfacade.model.Sheep;

public class SheepImpl extends AbstractAnimal implements Sheep {

    @Override
    public String getSound() {
        return Sheep.SOUND;
    }

    @Override
    public String getAnimalType() {
        return Sheep.ANIMAL_TYPE;
    }
}
