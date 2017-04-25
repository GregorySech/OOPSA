/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.cards;

import cardgame.AbstractEnchantment;
import cardgame.AbstractEnchantmentCardEffect;
import cardgame.Card;
import cardgame.Effect;
import cardgame.Enchantment;
import cardgame.Player;
import cardgame.CardGame;
import cardgame.Creature;
import cardgame.SingleTargetEffect;
import cardgame.Triggers;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jona
 */
public class AncestralMask implements Card{
    
 private class AncestralMaskEffect extends AbstractEnchantmentCardEffect implements SingleTargetEffect{
         private Creature target;
         
        AncestralMaskEffect(Player p, Card c){
            super(p,c);
        }

        @Override
        protected Enchantment createEnchantment() {
           return new AncestralMaskEnchantment(owner);
        } 

       @Override
       public void chooseTarget() {
           int last;
           do{
               System.out.println("What creature do you want to target :");
               System.out.println("[1]" + "Player creatures");
               System.out.println("[2]" + "Adversary creatures");
               last=CardGame.instance.getScanner().nextInt();
           }while(last <1 || last > 2);
           if(last==1){
               int i=0, j;
               List <Creature> playercreature=CardGame.instance.getCurrentPlayer().getCreatures();
               List <Creature> plc = new ArrayList(playercreature);
               for(Creature c: plc){
                   if(!(c.targetable()))
                      c.remove();
               }
               do{
                   for(Creature c: plc){
                       System.out.println(plc.get(i));
                   }
                   System.out.println("[0] to end selection");
                   j=CardGame.instance.getScanner().nextInt();
                   if(j != 0 && j<= plc.size() && !plc.isEmpty()){
                       target=plc.get(j);
                   }
                   else 
                       target=null;
               }while(j != 0);
           }
           else{
                int i=0, j;
                        List <Creature> adversarycreature=CardGame.instance.getCurrentAdversary().getCreatures();
                        List <Creature> adc= new ArrayList(adversarycreature);
                        for(Creature c: adc){
                           if(!(c.targetable())){
                           c.remove();
                           }
                         }
                        do{
                          for(Creature c: adc){
                               System.out.println(adc.get(i));
                           }
                            System.out.println("[0] to end selection");
                           j=CardGame.instance.getScanner().nextInt();
                           if(j != 0 && j<= adc.size() && !adc.isEmpty()){
                               target=adc.get(j);
                           }
                           else 
                               target=null;
                       }while(j != 0);  
           }
       }

       @Override
       public Object getTarget() {
           return target;
       }
    }
 private class AncestralMaskEnchantment extends AbstractEnchantment{

     AncestralMaskEnchantment(Player owner){
         super(owner);
     }
/*devo usare i triggers la creatura target prende (+2/+2)*numero incantesimi in gioco */

     
        @Override
        public String name() {
           return "Ancestral Mask";
        }
     
 }

    @Override
    public Effect getEffect(Player owner) {
        return new AncestralMaskEffect(owner, this);
    }

    @Override
    public String name() {
        return "Ancestral Mask";
    }

    @Override
    public String type() {
        return "Enchantment";
    }

    @Override
    public String ruleText() {
        return "Enchanted creature gets +2/+2 for each other enchantment on the battlefield";
    }

    @Override
    public String toString() {
       return name() + " (" + type() + ") [" + ruleText() + "]";
    }
    
    @Override
    public boolean isInstant() {
        return false;
    }

}
