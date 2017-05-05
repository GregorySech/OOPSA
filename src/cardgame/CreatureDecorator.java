package cardgame;

import cardgame.visitor.Visitor;
import java.util.List;

/**
 *
 * @author Elena
 * 
 * Implementazione del pattern decorator per Creature.
 * Un decoratore generalmente se non modifica il comportamento
 * di un oggetto rispetto ad un suo metodo non fa altro che 
 * demandare il comportamento a ciò che decora.
 */
public abstract class CreatureDecorator implements Creature {

    protected Creature decoratedCreature; //Riferimento alla creatura decorata.

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
    public Creature polymorph() {
        return decoratedCreature;
    }

    @Override
    public Creature removeCreatureDecorator(CreatureDecorator cd) {
        if(this == cd)
            return decoratedCreature;
        else if (decoratedCreature instanceof CreatureDecorator)
            decoratedCreature = decoratedCreature.removeCreatureDecorator(cd);
        return decoratedCreature;
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

    @Override
    public boolean isDefender() {
        return decoratedCreature.isDefender();
    }

    @Override
    public int getDamageLeft() {
        return decoratedCreature.getDamageLeft();
    }

    @Override
    public void setDamageLeft(int dmg) {
        decoratedCreature.setDamageLeft(dmg);
    }

    @Override
    public void accept(Visitor cpv) {
        decoratedCreature.accept(cpv);
    }
    
}
