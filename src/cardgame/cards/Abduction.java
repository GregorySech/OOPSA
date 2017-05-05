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
            chooseTarget(); // scelta target prima del caricamento nello stack.
            return super.play();
        }

        @Override
        public void chooseTarget() {
            int choose;
            System.out.println("Abduction targetting phase : ");
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
                target = owner.targetCreature();
            } else {
                target = CardGame.instance.getRival(owner).targetCreature();
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
            if (target != null) {//se il bersaglio è accettabile
                if (!owner.getCreatures().contains(target)) {//se non lo controllo ne cambio il bersaglio
                    originalTargetOwner = CardGame.instance.getRival(owner);
                    target.changeOwner(owner);
                }
                target.untap(); //Abduction dice che va stappata.
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
