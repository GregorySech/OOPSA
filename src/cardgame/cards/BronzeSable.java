/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.cards;

import cardgame.AbstractCreature;
import cardgame.AbstractCreatureCardEffect;
import cardgame.Card;
import cardgame.Creature;
import cardgame.Effect;
import cardgame.Player;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Elena
 */
public class BronzeSable implements Card{
    
     
    public class BronzeSableEffect extends AbstractCreatureCardEffect{
            public BronzeSableEffect(Player p, Card c){super(p,c);}
            @Override
            protected Creature createCreature() { return new BronzeSableCreature(owner); }
    }
    

    @Override
    public Effect getEffect(Player owner) {
        return new BronzeSableEffect(owner,this);
    }
    
    private class BronzeSableCreature extends AbstractCreature{
        
        ArrayList<Effect> all_effects= new ArrayList<>();
        ArrayList<Effect> tap_effects= new ArrayList<>();
        
        BronzeSableCreature(Player owner){
            super(owner);
        }
        
        @Override
        public int getPower() {
            return 2;
        }

        @Override
        public int getToughness() {
            return 1;
        }

        @Override
        public List<Effect> effects() {
            return all_effects;
        }

        @Override
        public List<Effect> avaliableEffects() {
            return tap_effects;
        }

        @Override
        public String name() {
            return "Bronze Sable";            
        }
    
    }

    @Override
    public String name() {
            return "Bronze Sable";            
    }

    @Override
    public String type() {
        return "Creature";
    }

    @Override
    public String ruleText() {
        return "Put in play a creature Bronze Sable (2/1)";
    }

    @Override
    public boolean isInstant() {
        return false;
    }
    
   @Override
    public String toString() { return name() + " (" + type() + ") [" + ruleText() +"]";}
   
    
}
