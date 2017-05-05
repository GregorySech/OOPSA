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
public class Afflict implements Card {

    private class AfflictEffect extends AbstractCardEffect implements SingleTargetEffect {

        private Creature target;
        private AfflictTriggerAction deactivator;
        private AfflictDecorator di;

        public AfflictEffect(Player p, Card c) {
            super(p, c);
        }

        @Override
        public boolean play() {
            chooseTarget();
            return super.play();
        }

        @Override
        public void resolve() {
            if (target != null) {
                di = new AfflictDecorator(target);
                deactivator = new AfflictTriggerAction(di);
                Triggers t = CardGame.instance.getTriggers();
                t.register(Triggers.END_FILTER, deactivator);
                target.addCreatureDecorator(di);
                target.setDamageLeft(target.getDamageLeft() - 1);
                target.inflictDamage(0);
            }
        }

        private void chooseCreature(Player p) {

            List<Creature> playerCreature = p.getCreatures();
            List<Creature> plC = new ArrayList();

            for (Creature c : playerCreature) {
                if (c.targetable()) {
                    plC.add(c);
                }
            }

            int choose, i;
            do {
                i = 0;
                for (Creature c : plC) {
                    System.out.println("[" + (++i) + "]" + c.toString());
                }
                System.out.println("[0] to end selection");

                choose = CardGame.instance.getScanner().nextInt();
                if (choose != 0 && choose <= plC.size()) {
                    target = plC.get(choose - 1);
                } else {
                    target = null;
                }

            } while (choose < 0 || choose > plC.size());

        }

        @Override
        public void chooseTarget() {
            int choose;
            System.out.println("Afflict targetting phase : ");
            do {
                System.out.println("Whose creature do you want to target?");

                System.out.println("[1]" + owner.name() + "\'s creature :");
                for (Creature c : (owner).getCreatures()) {
                    System.out.println("- " + c.toString());
                }

                System.out.println("[2]" + CardGame.instance.getRival(owner).name() + "\'s creature :");
                for (Creature c : CardGame.instance.getRival(owner).getCreatures()) {
                    System.out.println("- " + c.toString());
                }

                choose = CardGame.instance.getScanner().nextInt();
            } while (choose != 1 && choose != 2);

            if (choose == 1) {
                chooseCreature(owner);
            } else {
                chooseCreature(CardGame.instance.getRival(owner));
            }
        }

    }

    private class AfflictTriggerAction implements TriggerAction {

        private AfflictDecorator d;

        public AfflictTriggerAction(AfflictDecorator decorator) {
            d = decorator;
        }

        @Override
        public void execute(Object args) {
            d.removeCreatureDecorator(d);
        }

    }

    private class AfflictDecorator extends ChangePowerToughnessCreatureDecorator {

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
