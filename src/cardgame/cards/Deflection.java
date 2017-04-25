/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.cards;

import cardgame.AbstractCardEffect;
import cardgame.Card;
import cardgame.Effect;
import cardgame.Player;

/**
 *
 * @author jona
 */
public class Deflection implements Card {

    private class DeflectionEffect extends AbstractCardEffect{

        public DeflectionEffect(Player p, Card c){
            super(p,c);
        }
        @Override
        public void resolve() {
            /*non ne ho idea*/
        }
        
    }
     @Override
    public Effect getEffect(Player owner) {
        return new DeflectionEffect(owner, this);
    }

    @Override
    public String name() {
       return "Deflection";
    }

    @Override
    public String type() {
       return "Instant";
    }

    @Override
    public String ruleText() {
       return "Change the target of target spell with a single target";
    }

    @Override
    public boolean isInstant() {
        return true;
    }
    
    @Override
    public String toString() {
        return name() + " (" + type() + ") [" + ruleText() + "]";
    }
    
}
