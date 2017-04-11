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
    
    protected Creature c;
    
    public CreatureDecorator(Creature c){ this.c=c; }
        
    public boolean tap(){ return c.tap(); }
    public boolean untap(){ return c.untap(); }
    public boolean isTapped(){ return c.isTapped(); }
    public void attack(){c.attack(); }
    public void defend(Creature cr){ c.defend(cr); }
    public void inflictDamage(int dmg){ c.inflictDamage(dmg); }
    public void resetDamage(){ c.resetDamage(); }
    public int getPower(){ return c.getPower(); }
    public int getToughness(){ return c.getToughness(); }
    public List<Effect> effects(){ return c.effects(); }
    public List<Effect> avaliableEffects(){ return c.avaliableEffects(); }
    
    public String name(){ return c.name(); }
    public void insert(){ c.insert(); }
    public void remove(){ c.remove(); }
}
