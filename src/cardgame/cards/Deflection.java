/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.cards;

import cardgame.AbstractCardEffect;
import cardgame.Card;
import cardgame.CardGame;
import cardgame.CardStack;
import cardgame.Effect;
import cardgame.Player;
import cardgame.SingleTargetEffect;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author jona
 */
public class Deflection implements Card {

    private class DeflectionEffect extends AbstractCardEffect implements SingleTargetEffect{
        private SingleTargetEffect target;
        public DeflectionEffect(Player p, Card c){
            super(p,c);
        }
        
        @Override
        public boolean play(){
            chooseTarget();
            return super.play();
        }
        
        @Override
        public void resolve() {
           if(target != null){
               target.chooseTarget();
           }
        }
        @Override
        public void chooseTarget(){
            int i=0, j;
            SingleTargetEffect d;
            CardStack StackEffetti =CardGame.instance.getStack();
            
            //effectT will contain the effect that are SingleTargetEffect
            List <Effect> effectT = new ArrayList();
            Iterator it= StackEffetti.iterator();
            while(it.hasNext()){
                d=(SingleTargetEffect)(Effect)it.next();
                if (d instanceof SingleTargetEffect)
                    effectT.add(d);
            }
            
            Iterator iter = effectT.iterator();
            while(iter.hasNext()){
                System.out.println(Integer.toString(i+1)+ ") " + iter.next());
            }
            j=CardGame.instance.getScanner().nextInt();
            iter= effectT.iterator();
            for(i=0 ; i<j-1; i++){
                iter.next();
            }
            target=(SingleTargetEffect) iter.next();
        }
        
        @Override
        public Object getTarget(){
            return target;
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
