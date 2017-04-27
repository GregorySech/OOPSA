/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

/**
 *
 * @author atorsell
 */
public abstract class AbstractCreature implements Creature {

    protected Player owner;
    protected boolean isTapped = false;
    protected int damageLeft = getToughness();

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
        damageLeft = getCreatureDecoratorHead().getToughness();
    }

    @Override
    public void insert() {
        owner.getCreatures().add(this);
        CardGame.instance.getTriggers().trigger(Triggers.ENTER_CREATURE_FILTER, this);
    }

    @Override
    public void remove() {
        owner.getCreatures().remove(this);
        CardGame.instance.getTriggers().trigger(Triggers.EXIT_CREATURE_FILTER, this);
    }

    @Override
    public String toString() {
        return name() + " (Creature)";
    }

    @Override
    public void removeCreatureDecorator(CreatureDecorator cd) {
        getCreatureDecoratorHead().removeCreatureDecorator(cd);
    }

    @Override
    public void addCreatureDecorator(CreatureDecorator cd) {
        getCreatureDecoratorHead().addCreatureDecorator(cd);
    }

    @Override
    public Creature getCreatureDecoratorHead() {
        return headDecorator;
    }

    @Override
    public boolean targetable() {
        return true;
    }

    @Override
    public void changeOwner(Player p) {
        remove();
        owner = p;
        insert();
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

    
    
}
