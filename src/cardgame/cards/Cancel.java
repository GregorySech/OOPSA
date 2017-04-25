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
import cardgame.CardGame;

/**
 *
 * @author denny
 */
public class Cancel implements Card {
     private class CancelEffect extends AbstractCardEffect{

        public CancelEffect(Player p, Card c){
            super(p,c);
        }
        @Override
        public boolean play(){
            return super.play();
        }

        @Override
        public void resolve(){
            CardGame.instance.getStack().removeNext();
        }

    }
    @Override
    public Effect getEffect(Player owner) {
        return new CancelEffect(owner, this);
    }

    @Override
    public String name() {
        return "Cancel";
    }

    @Override
    public String type() {
        return "Instant";
    }

    @Override
    public String ruleText() {
        return "Counter target spell.\n";
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
