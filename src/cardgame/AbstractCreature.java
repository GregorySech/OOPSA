/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

import cardgame.visitor.Visitor;

/**
 *
 * @author atorsell
 */
public abstract class AbstractCreature implements Creature {

    protected Player owner;
    protected boolean isTapped = false;
    protected int damageLeft = getToughness();
    /*
    Dummy decorator per non compromettere polimorfismo all'aggiunta e rimozione
    di decoratori.
     */
    private final CreatureDecorator headDecorator;

    protected AbstractCreature(Player owner) {
        this.owner = owner;
        this.headDecorator = new HeadCreatureDecorator(this);
    }

    @Override
    public boolean tap() {
        if (isTapped) {
            System.out.println("creature " + name() + " already tapped");
            return false;
        }

        System.out.println("tapping creature " + name());
        isTapped = true;
        return true;
    }

    @Override
    public boolean untap() {
        if (!isTapped) {
            System.out.println("creature " + name() + " not tapped");
            return false;
        }

        System.out.println("untapping creature " + name());
        isTapped = false;
        return true;
    }

    @Override
    public boolean isTapped() {
        return isTapped;
    }

    @Override
    public void attack() {
        this.tap();
    }

    @Override
    public void defend(Creature c) {
    }

    @Override
    public void inflictDamage(int dmg) {
        damageLeft -= dmg;
        if (damageLeft <= 0) {
            owner.destroy(this);
        }
    }

    @Override
    public void resetDamage() {
        damageLeft = polymorph().getToughness(); //necessito delle statistice complete
    }

    /*
    Per rendere più "semplice" l'accesso al polimorfismo riempio il campo con
    i dummy decorator. polimorph() servirà principalmente alle creature e 
    in casi particolari quando non possiedo il riferimento alla testa
    della catena di decorazione.
    */
    
    @Override
    public void insert() {
        owner.getCreatures().add(headDecorator);
        CardGame.instance.getTriggers().trigger(Triggers.ENTER_CREATURE_FILTER, headDecorator);
    }

    @Override
    public void remove() {
        owner.getCreatures().remove(headDecorator);
        CardGame.instance.getTriggers().trigger(Triggers.EXIT_CREATURE_FILTER, headDecorator);
    }

    @Override
    public String toString() {
        return name() + " (Creature)";
    }

    @Override
    public Creature removeCreatureDecorator(CreatureDecorator cd) {
        return headDecorator.removeCreatureDecorator(cd);
    }

    @Override
    public void addCreatureDecorator(CreatureDecorator cd) {
        headDecorator.addCreatureDecorator(cd);
    }

    @Override
    public Creature polymorph() {
        return headDecorator;
    }

    @Override
    public boolean targetable() {
        return true;
    }

    @Override
    public void changeOwner(Player p) {
        owner.getCreatures().remove(headDecorator);
        owner = p;
        p.getCreatures().add(headDecorator);
    }

    @Override
    public boolean isDefender() {
        return false;
    }

    @Override
    public int getDamageLeft() {
        return damageLeft;
    }

    @Override
    public void setDamageLeft(int dmg) {
        this.damageLeft = dmg;
    }

    /**
     * 
     * @param cpv Visitor da accettare, "overloading" dinamico.
     */
    @Override
    public void accept(Visitor cpv) {
        headDecorator.accept(cpv);
    }

}
