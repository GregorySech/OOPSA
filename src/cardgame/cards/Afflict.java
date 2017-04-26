/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.cards;

import cardgame.AbstractCardEffect;
import cardgame.Card;
import cardgame.CardGame;
import cardgame.ChangePowerToughnessCreatureDecorator;
import cardgame.Creature;
import cardgame.Effect;
import cardgame.Player;
import cardgame.SingleTargetEffect;
import cardgame.TriggerAction;
import cardgame.Triggers;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author jona
 */
public class Afflict  implements Card{
      
    private class AfflictEffect extends AbstractCardEffect implements SingleTargetEffect{ 
      
       private Creature target;
       private AfflictTriggerAction deactivator;
        private AfflictDecorator di;
       
       public AfflictEffect(Player p, Card c){
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
               di = new AfflictDecorator(target);
               deactivator = new AfflictTriggerAction(di);
                Triggers t= CardGame.instance.getTriggers();
                t.register(Triggers.END_FILTER, deactivator);
           }
         if(target.getToughness() < 1){
             target.remove();
         }
           
       }
       private void chooseCreature(Player p) {
            int i = 0, j;
            List<Creature> playercreature = p.getCreatures();
            List<Creature> plc = new ArrayList(playercreature);

            for (Creature c : playercreature) {
                if (c.targetable()) {
                    plc.add(c);
                }
            }
            do {
                i = 0;
                for (Creature c : plc) {
                    System.out.println("[" + (++i) + "]" + c);
                }
                System.out.println("[0] to end selection");
                j = CardGame.instance.getScanner().nextInt();
                if (j != 0 && j <= plc.size()) {
                    target = plc.get(j - 1);
                } else {
                    target = null;
                }
            } while (j < 0 || j > plc.size());
        }

       @Override
       public void chooseTarget() {
           int last;
           do{
               System.out.println("Whom creature do you want to target :");
               System.out.println("[1]" + "Player's creatures");
               System.out.println("[2]" + "Adversary's creatures");
               last=CardGame.instance.getScanner().nextInt();
           }while(last <1 || last > 2);
           if(last==1){
               chooseCreature(owner);
           }
           else{
                chooseCreature(CardGame.instance.getRival(owner));
           }
       }

       @Override
       public Object getTarget() {
           return target;
       }

     }
    private class AfflictTriggerAction implements TriggerAction{
        private AfflictDecorator d ;
       
        public AfflictTriggerAction( AfflictDecorator decorator){
            d=decorator;
        }
        @Override
        public void execute(Object args) {
           d.removeCreatureDecorator(d);
        }
        
    }
    
    private class AfflictDecorator extends ChangePowerToughnessCreatureDecorator{
        
        public AfflictDecorator(Creature decoratore) {
            super(decoratore, -1, -1);
        }
        
    }
    
    
    @Override
    public Effect getEffect(Player owner) {
        return new AfflictEffect(owner, this);
    }

    @Override
    public String name() {
        return "Afflict";
    }

    @Override
    public String type() {
        return "Instant";
    }

    @Override
    public String ruleText() {
        return "Target creature gets -1/-1 until end of turn";
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
