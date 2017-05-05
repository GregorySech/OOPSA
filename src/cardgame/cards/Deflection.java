/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.cards;

import cardgame.AbstractCardEffect;
import cardgame.Card;
import cardgame.CardGame;
import cardgame.Effect;
import cardgame.Player;
import cardgame.SingleTargetEffect;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author jona
 */
public class Deflection implements Card {

    private class DeflectionEffect extends AbstractCardEffect implements SingleTargetEffect {

        private SingleTargetEffect target;

        public DeflectionEffect(Player p, Card c) {
            super(p, c);
        }

        @Override
        public boolean play() {
            chooseTarget();
            return super.play();
        }

        @Override
        public void resolve() {
            if (target != null) {
                target.chooseTarget();
            }
        }

        @Override
        public void chooseTarget() {
            System.out.println("Deflection targetting phase: [0] to skip");
            ArrayList<SingleTargetEffect> list = new ArrayList<>();
            Effect e;
            for(Iterator<Effect> iter = CardGame.instance.getStack().iterator(); iter.hasNext(); ){
                e = iter.next();
                if(e instanceof SingleTargetEffect)
                list.add((SingleTargetEffect) e);
            }
            int i, choice;
            do{
                i = 0;
                for(SingleTargetEffect ste : list){
                    System.out.println("["+(++i)+"]"+ste);
                }
                choice = CardGame.instance.getScanner().nextInt();
            }while(i<0 || i > list.size());
            if(i > 0)
            target = list.get(i-1);
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
