package cardgame.cards;

import cardgame.AbstractEnchantment;
import cardgame.AbstractEnchantmentCardEffect;
import cardgame.Card;
import cardgame.CardGame;
import cardgame.Creature;
import cardgame.Effect;
import cardgame.Enchantment;
import cardgame.Player;
import cardgame.SingleTargetEffect;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Elena
 */
public class Abduction implements Card {

    private Creature target;

    private class AbductionEffect extends AbstractEnchantmentCardEffect implements SingleTargetEffect {

        public AbductionEffect(Player p, Card c) {
            super(p, c);
        }

        @Override
        protected Enchantment createEnchantment() {
            return new AbductionEnchantment(owner);
        }

        @Override
        public boolean play() {
            chooseTarget();
            return super.play();
        }

        private void chooseCreature(Player p) {

            List<Creature> plC = new ArrayList();

            for (Creature c : p.getCreatures()) {
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
            System.out.println("Abduction targetting phase : ");
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

    private class AbductionEnchantment extends AbstractEnchantment {

        private Player originalTargetOwner = owner;

        public AbductionEnchantment(Player owner) {
            super(owner);
        }

        @Override
        public void insert() {
            if (target != null) {
                if (!owner.getCreatures().contains(target)) {
                    originalTargetOwner = CardGame.instance.getRival(owner);
                    target.changeOwner(owner);
                }
                target.untap();
                System.out.println(target.name() + " is untapped");
            }
            super.insert();
        }

        @Override
        public void remove() {
            /*creatura target torna del suo owner originale*/
            target.resetDamage();
            target.changeOwner(originalTargetOwner);
            super.remove();
        }

        @Override
        public String name() {
            return "Abduction";
        }
    }

    @Override
    public Effect getEffect(Player owner) {
        return new AbductionEffect(owner, this);
    }

    @Override
    public String name() {
        return "Abduction";
    }

    @Override
    public String type() {
        return "Enchantment";
    }

    @Override
    public String ruleText() {
        return "When Abduction comes into play, untap enchanted creature. You control enchanted creature. When enchanted creature i put into a graveyard, return that creature to play under it's owner's control";
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
