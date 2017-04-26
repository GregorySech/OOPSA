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
import cardgame.CardStack;
import cardgame.SingleTargetEffect;
import java.util.*;


/**
 *
 * @author denny
 */
public class Cancel implements Card {
     private class CancelEffect extends AbstractCardEffect implements SingleTargetEffect {
        private Effect target;
        public CancelEffect(Player p, Card c){
            super(p,c);
        }
        @Override
        public boolean play(){
            chooseTarget();
            return super.play();
        }

        @Override
        public void resolve(){
            CardGame.instance.getStack().remove(target);
        }

        @Override
        public void chooseTarget() {
            int i =0,j; 
            System.out.println("Cancel targeting phase :");
            System.out.println("Choose the target :");
            
            CardStack StackEffetti=CardGame.instance.getStack();
            Iterator iter= StackEffetti.iterator();
            while(iter.hasNext()){
                System.out.println(Integer.toString(i + 1) + ") " + iter.next());
            }
            j=CardGame.instance.getScanner().nextInt();
            iter = StackEffetti.iterator();
            for(i=0;i<j-1;i++){
               iter.next();
            }
            target=(Effect)iter.next();
            }

        @Override
        public Object getTarget() {
            return target;
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
