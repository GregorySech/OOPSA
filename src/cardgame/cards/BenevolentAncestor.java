/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.cards;

import cardgame.AbstractCreature;
import cardgame.AbstractCreatureCardEffect;
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
 * @author denny
 *
 *
 *
 *
 *
 *
 *
 * /* NON ANCORA COMPLETATO
 */
public class BenevolentAncestor implements Card {

    private class BenevolentAncestorEffect extends AbstractCreatureCardEffect {

        BenevolentAncestorEffect(Player p, Card c) {
            super(p, c);
        }

        @Override
        protected Creature createCreature() {
            Creature basicCreature = new BenevolentAncestorCreature(owner);
            return basicCreature.getCreatureDecoratorHead();
        }
    }

    private class BenevolentAncestorCreature extends AbstractCreature implements SingleTargetEffect {

        private Creature target = null;
        private Player targetp = null;

        ArrayList<Effect> effects = new ArrayList<>();

        TriggerAction ProtectFromOneDamage;

        BenevolentAncestorCreature(Player owner) {
            super(owner);
        }

        @Override
        public void attack() {
            /*throw new UnsupportedOperationException("This creature can't attack");*/
            System.out.println("This creature can't attack");
        }

        @Override
        public void chooseTarget() {
            int last;
            System.out.println("Benevolent Ancestor targeting phase : ");
            do {
                System.out.println("Choose the character to target :");
                System.out.println("[1]" + "A Player");
                System.out.println("[2]" + " A Creature");
                last = CardGame.instance.getScanner().nextInt();
            } while (last < 1 || last > 2);
            if (last == 1) {
                do {
                    System.out.println("Choose the target :");
                    System.out.println("[1] Current player" + owner.name());
                    System.out.println("[2] Adversary player" + CardGame.instance.getRival(owner).name());
                    last = CardGame.instance.getScanner().nextInt();
                } while (last < 1 || last > 2);
                if (last == 1) {
                    targetp = owner;
                } else {
                    targetp = CardGame.instance.getRival(owner);
                }
            } else {
                System.out.println("Choose the owner of the creature you want to target:");
                do {
                    System.out.println("[1]" + "Player's creatures");
                    System.out.println("[2]" + "Adversary's creature");
                    last = CardGame.instance.getScanner().nextInt();
                } while (last < 1 || last > 2);
                if (last == 1) {
                    int i = 0, j;
                    List<Creature> playercreature = owner.getCreatures();
                    for (Creature c : playercreature) {
                        System.out.println(Integer.toString(i + 1) + ") " + playercreature.get(i));
                    }
                    j = CardGame.instance.getScanner().nextInt();
                    if (j <= playercreature.size() && !playercreature.isEmpty()) {
                        target = playercreature.get(j - 1);
                    } else {
                        target = null;
                    }
                } else {
                    int i = 0, j;
                    List<Creature> adversarycreature = CardGame.instance.getRival(owner).getCreatures();
                    for (Creature c : adversarycreature) {
                        System.out.println(Integer.toString(i + 1) + ") " + adversarycreature.get(i));
                    }
                    j = CardGame.instance.getScanner().nextInt();
                    if (j <= adversarycreature.size() && !adversarycreature.isEmpty()) {
                        target = adversarycreature.get(j - 1);
                    } else {
                        target = null;
                    }
                }
            }
        }

        @Override
        public Object getTarget() {
            return target;
        }

        @Override
        public boolean play() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        /*NON SO*/

        @Override
        public void resolve() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        /*NON SO*/

        private class BenevolentAncestorTrigger implements TriggerAction {

            Player designatedCaster;

            private BenevolentAncestorTrigger(Player owner) {
                designatedCaster = owner;
            }

            @Override
            public void execute(Object args) {
                if (targetp == null && target != null) {
                    target.addCreatureDecorator(new ChangePowerToughnessCreatureDecorator(target, 0, 1));
                } /*andra' via alla fine del turno? e se viene danneggiato alla fine del turno non devo togliere nulla?*/ else if (targetp != null && target == null) /*non esistono decoratori per player vero?*/ {
                    targetp.heal(1);
                }
                /*se prende danno non devo fare target.damage(1), ma se non prende danno allora si*/
            }

        }

        @Override
        public void insert() {
            ProtectFromOneDamage = new BenevolentAncestorTrigger(owner);
            CardGame.instance.getTriggers().register(Triggers.EFFECT_CASTED, ProtectFromOneDamage);
            super.insert();
        }

        @Override
        public void remove() {
            CardGame.instance.getTriggers().deregister(ProtectFromOneDamage);
            super.remove();
        }

        @Override
        public int getPower() {
            return 0;
        }

        @Override
        public int getToughness() {
            return 4;
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
            return "Benevolent Ancestor";
        }

        @Override
        public boolean targetable() {
            return true;
        }

        @Override
        public boolean isDefender() {
            return true;
        }

    }

    @Override
    public Effect getEffect(Player owner) {
        return new BenevolentAncestorEffect(owner, this);
    }

    @Override
    public String name() {
        return "Benevolent Ancestor";
    }

    @Override
    public String type() {
        return "Creature";
    }

    @Override
    public String ruleText() {
        return "Defender (This creature can't attack.) "
                + "Prevent the next 1 damage that would be dealt to target creature or player this turn";
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
