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
public abstract class AbstractEnchantment implements Enchantment {
    
    protected Player owner;
    
    protected AbstractEnchantment(Player owner) {
        this.owner = owner;
    }
    
    @Override
    public void insert() {
        CardGame.instance.getTriggers().trigger(Triggers.ENTER_ENCHANTMENT_FILTER, this);
    }
    
    @Override
    public void remove() {
        owner.getEnchantments().remove(this);
        CardGame.instance.getTriggers().trigger(Triggers.EXIT_ENCHANTMENT_FILTER, this);
    }
    
    @Override
    public String toString() {
        return name() + " (Enchantment)";
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
    public void accept(Visitor cpv) {
        cpv.visit(this);
    }
    
}
