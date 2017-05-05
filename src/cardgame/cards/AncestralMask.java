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
import cardgame.CreatureDecorator;
import cardgame.SingleTargetEffect;
import cardgame.TriggerAction;
import cardgame.Triggers;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jona
 */
public class AncestralMask implements Card {

    private class AncestralMaskEffect extends AbstractEnchantmentCardEffect implements SingleTargetEffect { //L'effetto della carta è a bersaglio singolo.

        private Creature target;

        AncestralMaskEffect(Player p, Card c) {
            super(p, c);
        }

        @Override
        protected Enchantment createEnchantment() {
            return new AncestralMaskEnchantment(owner, target);
        }

        @Override
        public boolean play() {
            chooseTarget();
            return super.play();
        }


        @Override
        public void chooseTarget() {
            int last;
            do {
                System.out.println("What creature do you want to target :");
                System.out.println("[1]" + "Player creatures");
                System.out.println("[2]" + "Adversary creatures");
                last = CardGame.instance.getScanner().nextInt();
            } while (last < 1 || last > 2);
            if (last == 1) {
                target = owner.targetCreature();
            } else {
                target = (CardGame.instance.getRival(owner)).targetCreature();
            }
        }

    }

    private class AncestralMaskDecorator extends CreatureDecorator {

        private int counterEnchantment;
        private TriggerAction plus;
        private TriggerAction minus;

        public AncestralMaskDecorator(Creature decoratedCreature) {
            super(decoratedCreature);

            counterEnchantment = CardGame.instance.getCurrentPlayer().getEnchantments().size()
                    + CardGame.instance.getCurrentAdversary().getEnchantments().size() - 2;

            plus = new TriggerAction() {
                @Override
                public void execute(Object args) {
                    counterEnchantment++;
                }
            };

            minus = new TriggerAction() {
                @Override
                public void execute(Object args) {
                    counterEnchantment--;
                }
            };

            CardGame.instance.getTriggers().register(Triggers.ENTER_ENCHANTMENT_FILTER, plus);

            CardGame.instance.getTriggers().register(Triggers.EXIT_ENCHANTMENT_FILTER, minus);

        }

        void deactivate() {
            CardGame.instance.getTriggers().deregister(plus);
            CardGame.instance.getTriggers().deregister(minus);
        }

        @Override
        public int getPower() {
            return super.getPower() + 2 * counterEnchantment;
        }

        @Override
        public int getToughness() {
            return super.getToughness() + 2 * counterEnchantment;
        }

    }

    private class AncestralMaskEnchantment extends AbstractEnchantment {

        Creature target;
        AncestralMaskDecorator decorator;

        AncestralMaskEnchantment(Player owner, Creature target) {
            super(owner);
            this.target = target;
        }

        @Override
        public void insert() {
            if (target != null) {
                decorator = new AncestralMaskDecorator(target);
                target.addCreatureDecorator(decorator);
            }
            super.insert();
        }

        @Override
        public void remove() {
            if (target != null) {
                target.removeCreatureDecorator(decorator);
                decorator.deactivate();
            }
            super.remove();
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
