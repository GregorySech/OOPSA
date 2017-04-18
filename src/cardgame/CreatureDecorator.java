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
        
    @Override
    public boolean tap(){ return c.tap(); }
    @Override
    public boolean untap(){ return c.untap(); }
    @Override
    public boolean isTapped(){ return c.isTapped(); }
    @Override
    public void attack(){c.attack(); }
    @Override
    public void defend(Creature cr){ c.defend(cr); }
    @Override
    public void inflictDamage(int dmg){ c.inflictDamage(dmg); }
    @Override
    public void resetDamage(){ c.resetDamage(); }
    @Override
    public int getPower(){ return c.getPower(); }
    @Override
    public int getToughness(){ return c.getToughness(); }
    @Override
    public List<Effect> effects(){ return c.effects(); }
    @Override
    public List<Effect> avaliableEffects(){ return c.avaliableEffects(); }
    
    @Override
    public String name(){ return c.name(); }
    @Override
    public void insert(){ c.insert(); }
    @Override
    public void remove(){ c.remove(); }

    @Override
    public void addCreatureDecorator(CreatureDecorator cd){ c.addCreatureDecorator(cd);}
    @Override
    public CreatureDecorator getFirstCreatureDecorator(){ return c.getFirstCreatureDecorator();}
    
    
}
