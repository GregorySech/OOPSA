/* MANCA DA GESTIRE / SISTEMARE
 * - IL FATTO DI ESSERE ISTANTANEO 
 * - IL DECORATORE
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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Elena
 */
public class AggressiveUrge implements Card {

    private class AggressiveUrgeEffect extends AbstractCardEffect implements SingleTargetEffect {

        private Creature target;

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
            /* NON SO COSA METTERE COME PRIMO ARGOMENTO DEL DECORATORE CPTCD */
 /* poi visto che è un istantaneo andrebbe rimosso alla fine del turno, come si fa? */
            target.addCreatureDecorator(new ChangePowerToughnessCreatureDecorator(target, 1, 1));
        }

        private void chooseCreature(Player p) {

            List<Creature> playerCreature = p.getCreatures();
            List<Creature> plC = new ArrayList(playerCreature);

            for (Iterator<Creature> c = plC.iterator(); c.hasNext();) {
                Creature cr = c.next();
                if (!cr.targetable()) {
                    c.remove();
                }
            }

            int choose, i;
            i = 0;
            do {
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

        @Override
        public Object getTarget() {
            return target;
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
