/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

/**
 *
 * @author Elena
 */
public class ChangePowerToughnessCreatureDecorator extends CreatureDecorator {

    private final int powerAdded;
    private final int toughnessAdded;

    public ChangePowerToughnessCreatureDecorator(Creature decoratore, int powerAdded, int toughnessAdded) {
        super(decoratore);
        this.powerAdded = powerAdded;
        this.toughnessAdded = toughnessAdded;

    }

    @Override
    public int getPower() {
        return decoratedCreature.getPower() + powerAdded;
    }

    @Override
    public int getToughness() {
        return decoratedCreature.getToughness() + toughnessAdded;
    }

}
