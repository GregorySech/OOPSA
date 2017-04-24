/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.cards;

import cardgame.AbstractCardEffect;
import cardgame.Card;
import cardgame.CardGame;
import cardgame.Creature;
import cardgame.Effect;
import cardgame.Player;
import cardgame.SingleTargetEffect;

/**
 *
 * @author jona
 */
public class VolcanicHammer implements Card {

    private class VolcanicHammerEffect extends AbstractCardEffect implements SingleTargetEffect{

        private Object target;
        public VolcanicHammerEffect(Player p, Card c){
            super(p,c);
        }
        @Override
        public boolean play(){
            chooseTarget();
            return super.play();
        }

        @Override
        public void resolve(){
             if(target instanceof Player)
              ((Player)target).inflictDamage(3);
           else 
               ((Creature)target).inflictDamage(3);
         }

        @Override
        public void chooseTarget() {
            int last;
            System.out.println("Volcanic Hammer targetting phase : ");
            do{
                System.out.println("Choose the target :");
                System.out.println("[1]" + CardGame.instance.getCurrentPlayer().name());
                System.out.println("[2]" + CardGame.instance.getCurrentAdversary().name());
                System.out.println("[3]" + CardGame.instance.getCurrentPlayer().getCreatures());
                System.out.println("[4]" + CardGame.instance.getCurrentAdversary().getCreatures());
                last= CardGame.instance.getScanner().nextInt();
            }while(last < 1 || last > 2);
        }

        @Override
        public Object getTarget() {
            return target;
        }
    }
    @Override
    public Effect getEffect(Player owner) {
        return new VolcanicHammerEffect(owner, this);
    }

    @Override
    public String name() {
        return "Volcanic Hammer";
    }

    @Override
    public String type() {
        return "Sorcery";
    }

    @Override
    public String ruleText() {
        return "Target player or creature gets 3 damage";
    }

    @Override
    public boolean isInstant() {
        return false;
    }

    @Override
    public String toString() {
        return name() + " (" + type() + ") [" + ruleText() + "]";
    }
}
