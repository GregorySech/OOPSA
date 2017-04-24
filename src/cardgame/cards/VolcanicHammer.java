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
import java.util.List;

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
            if(target != null){
           if(target instanceof Player)
              ((Player)target).inflictDamage(3);
           else {
                ((Creature)target).inflictDamage(3); 
             } 
         }
        }

        @Override
        public void chooseTarget() {
            int last;
            System.out.println("Volcanic Hammer targetting phase : ");
            do{
                System.out.println("What do you want to target :");
                System.out.println("[1]" + "A Player");
                System.out.println("[2]" + " A Creature");
                last= CardGame.instance.getScanner().nextInt();
                if(last==1){
                    do{
                        System.out.println("[1]" + CardGame.instance.getCurrentPlayer().name());
                        System.out.println("[2]" + CardGame.instance.getCurrentAdversary().name());
                        last= CardGame.instance.getScanner().nextInt();
                    }while(last<1 || last >2);
                    if(last==1)
                       target=CardGame.instance.getCurrentPlayer();
                    else 
                       target=CardGame.instance.getCurrentAdversary();
                }
                else {
                    System.out.println("What creature do you want to target:");
                     do{
                        System.out.println("[1]" + "Player creatures");
                        System.out.println("[2]" +"Adversary Creature");
                        last= CardGame.instance.getScanner().nextInt();
                    }while(last<1 || last >2);
                    if(last==1){
                        int i =0,j; 
                        List <Creature> playercreature=CardGame.instance.getCurrentPlayer().getCreatures();
                        do{
                           for(Creature c: playercreature){
                                System.out.println(playercreature.get(i));
                            }
                             System.out.println("[0] to end selection");
                            j=CardGame.instance.getScanner().nextInt();
                            if(j != 0 && j<= playercreature.size()&& !playercreature.isEmpty()){
                                target=playercreature.get(j);
                            }
                            else 
                                target=null;
                        }while(j != 0);   
                    }
                    else{
                        int i=0, j;
                         List <Creature> adversarycreature=CardGame.instance.getCurrentAdversary().getCreatures();
                        do{
                           for(Creature c: adversarycreature){
                                System.out.println(adversarycreature.get(i));
                            }
                             System.out.println("[0] to end selection");
                            j=CardGame.instance.getScanner().nextInt();
                            if(j != 0 && j<= adversarycreature.size() && !adversarycreature.isEmpty()){
                                target=adversarycreature.get(j);
                            }
                            else 
                                target=null;
                        }while(j != 0);   
                    }
                }
                
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
