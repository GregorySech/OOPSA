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
import cardgame.Player;
import cardgame.TriggerAction;
import cardgame.Triggers;

/**
 *
 * @author gsech
 */
public class AetherFlash implements Card {

    private class AetherFlashEffect extends AbstractEnchantmentCardEffect {

        AetherFlashEffect(Player p, Card c) {
            super(p, c);
        }

        @Override
        protected Enchantment createEnchantment() {
            return new AetherFlashEnchantment(owner);
        }
    }

    private class AetherFlashEnchantment extends AbstractEnchantment {

        AetherFlashEnchantment(Player owner) {
            super(owner);
        }

        private final TriggerAction damageAction = new TriggerAction() {//Classe anonima con la logica dell'effetto di Aether Flash
            @Override
            public void execute(Object args) {
                if (args != null && args instanceof Creature) {
                    Creature c = (Creature) args;
                    System.out.println("AetherFlash: 2 damages to the freshly summoned creature " + c.name());
                    c.inflictDamage(2);
                }
            }
        };

        @Override
        public void insert() {
            CardGame.instance.getTriggers().register(Triggers.ENTER_CREATURE_FILTER, damageAction);

            super.insert();
        }

        @Override
        public void remove() {
            CardGame.instance.getTriggers().deregister(damageAction);
            super.remove();
        }

        @Override
        public String name() {
            return "Aether Flash";
        }

    }

    @Override
    public Effect getEffect(Player owner) {
        return new AetherFlashEffect(owner, this);
    }

    @Override
    public String name() {
        return "Aether Flash";
    }

    @Override
    public String type() {
        return "Enchantment";
    }

    @Override
    public String ruleText() {
        return "Whenever a creature comes into play, AEther Flash deals 2 damage to it.";
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
