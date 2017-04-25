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

/**
 *
 * @author jona
 */
public class AetherBarrier implements Card {
    
    private class AetherBarrierEffect extends AbstractEnchantmentCardEffect{
        
        AetherBarrierEffect(Player p, Card c){
            super(p, c);
        }

        @Override
        protected Enchantment createEnchantment() {
           return new AetherBarrierEnchantment(owner);
        }
        
    }
    
    private class AetherBarrierEnchantment extends AbstractEnchantment{
        AetherBarrierEnchantment(Player owner){
            super(owner);
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
        return "Whenever a player plays a creature spell that player sacrifices a permanent unless he or she plays";
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
