/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

import java.util.List;

/**
 *
 * @author Elena
 */
public abstract class CreatureDecorator implements Creature {

    protected Creature decoratedCreature;

    public CreatureDecorator(Creature decoratedCreature) {
        this.decoratedCreature = decoratedCreature;
    }

    @Override
    public boolean tap() {
        return decoratedCreature.tap();
    }

    @Override
    public boolean untap() {
        return decoratedCreature.untap();
    }

    @Override
    public boolean isTapped() {
        return decoratedCreature.isTapped();
    }

    @Override
    public void attack() {
        decoratedCreature.attack();
    }

    @Override
    public void defend(Creature cr) {
        decoratedCreature.defend(cr);
    }

    @Override
    public void inflictDamage(int dmg) {
        decoratedCreature.inflictDamage(dmg);
    }

    @Override
    public void resetDamage() {
        decoratedCreature.resetDamage();
    }

    @Override
    public int getPower() {
        return decoratedCreature.getPower();
    }

    @Override
    public int getToughness() {
        return decoratedCreature.getToughness();
    }

    @Override
    public List<Effect> effects() {
        return decoratedCreature.effects();
    }

    @Override
    public List<Effect> avaliableEffects() {
        return decoratedCreature.avaliableEffects();
    }

    @Override
    public String name() {
        return decoratedCreature.name();
    }

    @Override
    public void insert() {
        decoratedCreature.insert();
    }

    @Override
    public void remove() {
        decoratedCreature.remove();
    }

    @Override
    public void addCreatureDecorator(CreatureDecorator cd) {
        decoratedCreature.addCreatureDecorator(cd);
    }

    @Override
    public Creature getCreatureDecoratorHead() {
        return decoratedCreature.getCreatureDecoratorHead();
    }

    @Override
    public void removeCreatureDecorator(CreatureDecorator cd) {
        decoratedCreature.removeCreatureDecorator(cd);
    }

    public Creature getDecoratedCreature() {
        return decoratedCreature;
    }

    public void setDecoratedCreature(Creature c) {
        decoratedCreature = c;
    }

    @Override
    public boolean targetable() {
        return decoratedCreature.targetable();
    }

    @Override
    public void changeOwner(Player p) {
        decoratedCreature.changeOwner(p);
    }

    @Override
    public String toString() {
        return decoratedCreature.toString();
    }

}
