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
 * @author Elena
 */
public class AggressiveUrge implements Card {

    private class AggressiveUrgeEffect extends AbstractCardEffect implements SingleTargetEffect {

        private Creature target;
        private AggressiveUrgeTriggerAction deactivator;
        private AggressiveUrgeDecorator decoratore;

        public AggressiveUrgeEffect(Player p, Card c) {
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
                decoratore = new AggressiveUrgeDecorator(target);
                deactivator = new AggressiveUrgeTriggerAction(decoratore);
                target.addCreatureDecorator(decoratore);
                target.setDamageLeft(target.getDamageLeft() + 1);
                Triggers t = CardGame.instance.getTriggers();
                t.register(Triggers.END_FILTER, deactivator);
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
            System.out.println("Aggressive Urge targetting phase : ");
            do {
                System.out.println("Whose creature do you want to target?");

                System.out.println("[1]" + owner.name() + "\'s creature :");
                for (Creature c : (owner).getCreatures()) {
                    if (c.targetable()) {
                        System.out.println("- " + c.toString());
                    }
                }

                System.out.println("[2]" + CardGame.instance.getRival(owner).name() + "\'s creature :");
                for (Creature c : CardGame.instance.getRival(owner).getCreatures()) {
                    if (c.targetable()) {
                        System.out.println("- " + c.toString());
                    }
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

    private class AggressiveUrgeTriggerAction implements TriggerAction {

        private AggressiveUrgeDecorator d;

        public AggressiveUrgeTriggerAction(AggressiveUrgeDecorator decoratore) {
            d = decoratore;
        }

        @Override
        public void execute(Object args) {
            d.removeCreatureDecorator(d);
        }

    }

    private class AggressiveUrgeDecorator extends ChangePowerToughnessCreatureDecorator {

        public AggressiveUrgeDecorator(Creature decoratore) {
            super(decoratore, 1, 1);
        }

    }

    @Override
    public Effect getEffect(Player owner) {
        return new AggressiveUrgeEffect(owner, this);
    }

    @Override
    public String name() {
        return "Aggressive Urge";
    }

    @Override
    public String type() {
        return "Instant";
    }

    @Override
    public String ruleText() {
        return "Target creatur gets +1/+1 until end of turn";
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
