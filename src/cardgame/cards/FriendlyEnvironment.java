/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.cards;

import cardgame.Card;
import cardgame.Effect;
import cardgame.Enchantment;
import cardgame.Player;
import cardgame.AbstractEnchantmentCardEffect;
import cardgame.AbstractEnchantment;
import cardgame.CardGame;
import cardgame.Creature;
import cardgame.TriggerAction;
import cardgame.Triggers;


/**
 *
 * @author atorsell
 */
public class FriendlyEnvironment implements Card {
    private class FriendlyEnvironmentEffect extends AbstractEnchantmentCardEffect {
        public FriendlyEnvironmentEffect(Player p, Card c) { super(p,c); }
        @Override
        protected Enchantment createEnchantment() { return new FriendlyEnvironmentEnchantment(owner); }
    }
    @Override
    public Effect getEffect(Player p) { return new FriendlyEnvironmentEffect(p,this); }
    
    private class FriendlyEnvironmentEnchantment extends AbstractEnchantment {
        public FriendlyEnvironmentEnchantment(Player owner) {
            super(owner);
        }
        
        private final TriggerAction GreetingAction = new TriggerAction() {
                @Override
                public void execute(Object args) {
                    if (args != null  && args instanceof Creature) {
                        Creature c = (Creature)args;
                        System.out.println( name() + " says: \"Welcome " + c.name() +"!\"" );
                    }
                }
            };
        
        @Override
        public void insert() {
            super.insert();
            CardGame.instance.getTriggers().register(Triggers.ENTER_CREATURE_FILTER, GreetingAction);
        }
        
        @Override
        public void remove() {
            super.remove();
            CardGame.instance.getTriggers().deregister(GreetingAction);
        }
        
        @Override
        public String name() { return "Friendly Environment"; }
    }
    
    
    @Override
    public String name() { return "Friendly Environment"; }
    @Override
    public String type() { return "Enchantment"; }
    @Override
    public String ruleText() { return "Whenever a creature enters the game " + name() + " welcomes it"; }
    @Override
    public String toString() { return name() + " (" + type() + ") [" + ruleText() +"]";}
    @Override
    public boolean isInstant() { return false; }
}
