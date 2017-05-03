/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.cards;

import cardgame.AbstractCreature;
import cardgame.AbstractCreatureCardEffect;
import cardgame.AbstractEnchantmentCardEffect;
import cardgame.Card;
import cardgame.CardGame;
import cardgame.Creature;
import cardgame.Effect;
import cardgame.Player;
import cardgame.TriggerAction;
import cardgame.Triggers;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gsech
 */
public class ArgothianEnchantress implements Card {

    private class ArgothianEnchantressEffect extends AbstractCreatureCardEffect {

        ArgothianEnchantressEffect(Player p, Card c) {
            super(p, c);
        }

        @Override
        protected Creature createCreature() {
            Creature basicCreature = new ArgothianEnchantressCreature(owner);
            return basicCreature.getCreatureDecoratorHead();
        }
    }

    private class ArgothianEnchantressCreature extends AbstractCreature {

        ArrayList<Effect> effects = new ArrayList<>();

        TriggerAction DrawOnEnchantmentCast;

        ArgothianEnchantressCreature(Player owner) {
            super(owner);
        }

        private class ArgothianTrigger implements TriggerAction {

            Player designatedCaster;

            private ArgothianTrigger(Player owner) {
                designatedCaster = owner;
            }

            @Override
            public void execute(Object args) {
                if (args instanceof AbstractEnchantmentCardEffect) {
                    AbstractEnchantmentCardEffect enchantment = (AbstractEnchantmentCardEffect) args;
                    if (enchantment.getOwner() == designatedCaster) {
                        System.out.println("ArgothianEnchantress : you've casted an enchantment, draw a card!");
                        designatedCaster.draw();
                    }
                }
            }
        }

        @Override
        public void insert() {
            DrawOnEnchantmentCast = new ArgothianTrigger(owner);
            CardGame.instance.getTriggers().register(Triggers.EFFECT_CASTED, DrawOnEnchantmentCast);
            super.insert();
        }

        @Override
        public void remove() {
            CardGame.instance.getTriggers().deregister(DrawOnEnchantmentCast);
            super.remove();
        }

        @Override
        public int getPower() {
            return 0;
        }

        @Override
        public int getToughness() {
            return 1;
        }

        @Override
        public List<Effect> effects() {
            return effects;
        }

        @Override
        public List<Effect> avaliableEffects() {
            return effects;
        }

        @Override
        public String name() {
            return "Argothian Enchantress";
        }

        @Override
        public boolean targetable() {
            return false;
        }

    }

    @Override
    public Effect getEffect(Player owner) {
        return new ArgothianEnchantressEffect(owner, this);
    }

    @Override
    public String name() {
        return "Argothian Enchantress";
    }

    @Override
    public String type() {
        return "Creature";
    }

    @Override
    public String ruleText() {
        return "[Shroud] (This creature can't be the target of spells or abilities.) "
                + "Whenever you cast an enchantment spell, draw a card";
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
