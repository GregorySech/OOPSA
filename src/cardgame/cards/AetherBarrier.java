/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.cards;

import cardgame.AbstractEnchantment;
import cardgame.AbstractEnchantmentCardEffect;
import cardgame.Card;
import cardgame.CardGame;
import cardgame.Creature;
import cardgame.Effect;
import cardgame.Enchantment;
import cardgame.Permanent;
import cardgame.Player;
import cardgame.TriggerAction;
import static cardgame.Triggers.EFFECT_CASTED;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jona
 */
public class AetherBarrier implements Card {
    
    private class AetherBarrierEffect extends AbstractEnchantmentCardEffect {
        
        AetherBarrierEffect(Player p, Card c){
            super(p, c);
        }

        @Override
        protected Enchantment createEnchantment() {
           return new AetherBarrierEnchantment(owner);
        }
         
    }
    
    private class AetherBarrierEnchantment extends AbstractEnchantment  {
       private Permanent target;
       TriggerAction D;
        AetherBarrierEnchantment(Player owner){
            super(owner);
        }
        
        
       private void choosePermanent(Player p) {
            int i = 0, j;
            List<Permanent> plP = new ArrayList();
            List<Creature> c= p.getCreatures();
            List<Enchantment> e= p.getEnchantments();

            for (Creature ci : c) {
                plP.add(ci);
            }
            for (Enchantment ei : e) {
                plP.add(ei);
            }
            
            do {
                i = 0;
                for (Permanent pe : plP) {
                    System.out.println("[" + (++i) + "]" + c);
                }
                System.out.println("[0] to end selection");
                j = CardGame.instance.getScanner().nextInt();
                if (j != 0 && j <= plP.size()) {
                    target = plP.get(j - 1);
                } else {
                    target = null;
                }
            } while (j < 0 || j > plP.size());
        }

      
        
        private class AetherBarrierTrigger implements TriggerAction{
        
            Player p;
            
            private AetherBarrierTrigger (Player owner){
                p= owner;
            }
         
            @Override
            public void execute(Object args) {
                if(args instanceof AbstractEnchantmentCardEffect){
                      AbstractEnchantmentCardEffect enchantment = (AbstractEnchantmentCardEffect) args;
                    if (enchantment.getOwner() == p) {
                        choosePermanent(p);
                      }
                }
            }
           
        }
        @Override
        public void insert() {
            super.insert();
            CardGame.instance.getTriggers().register(EFFECT_CASTED, D);
        }

        @Override
        public void remove() {
            super.remove();
            CardGame.instance.getTriggers().deregister(D);
        }
            @Override
            public String name() {
                return "Aether Barrier";
            }
    }
        
   
    
    @Override
    public Effect getEffect(Player owner) {
        return new AetherBarrierEffect(owner, this);
    }

    @Override
    public String name() {
        return "Aether Barrier";
    }

    @Override
    public String type() {
        return "Enchantment";
    }

    @Override
    public String ruleText() {
        return "Whenever a player plays a creature spell that player sacrifices a permanent";
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
