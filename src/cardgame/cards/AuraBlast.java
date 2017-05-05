/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.cards;

import cardgame.AbstractCardEffect;
import cardgame.Card;
import cardgame.CardGame;
import cardgame.Effect;
import cardgame.Enchantment;
import cardgame.Player;
import cardgame.SingleTargetEffect;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author denny
 */
public class AuraBlast implements Card {

    private class AuraBlastEffect extends AbstractCardEffect implements SingleTargetEffect {

        private Enchantment target;

        public AuraBlastEffect(Player p, Card c) {
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
                target.remove();
            }

            owner.draw();

        }

        private void chooseEnchantment(Player p) {
            List<Enchantment> plE = new ArrayList<>();
            for (Enchantment e : p.getEnchantments()) {
                if (e.targetable()) {
                    plE.add(e);
                }
            }
            target = null;
            int choose, i;
            do {
                i = 0;
                for (Enchantment e : plE) {
                    System.out.println("[" + (++i) + "] " + e.toString());
                }
                System.out.println("[0] to end selection");
                choose = CardGame.instance.getScanner().nextInt();
                if (choose > 0 && choose <= plE.size()) {
                    target = plE.get(choose - 1);
                }
            } while (choose < 0 || choose > plE.size());
        }

        @Override
        public void chooseTarget() {
            int last;
            System.out.println("Aura Blast targeting phase : ");
            do {
                System.out.println("Whose enchantment do you want to target?");
                System.out.println("[1]" + owner.name() + "\'s enchantment");
                for (Enchantment e : (owner.getEnchantments())) {
                    if (e.targetable()) {
                        System.out.println("- " + e.toString());
                    }
                }
                System.out.println("[2]" + CardGame.instance.getRival(owner) + "\'s enchantment");
                for (Enchantment e : CardGame.instance.getRival(owner).getEnchantments()) {
                    if (e.targetable()) {
                        System.out.println("- " + e.toString());
                    }
                }
                last = CardGame.instance.getScanner().nextInt();
            } while (last < 1 || last > 2);
            if (last == 1) {
                chooseEnchantment(owner);
            } else {
                chooseEnchantment(CardGame.instance.getRival(owner));
            }

        }

    }

    @Override
    public Effect getEffect(Player owner) {
        return new AuraBlastEffect(owner, this);
    }

    @Override
    public String name() {
        return "Aura Blast";
    }

    @Override
    public String type() {
        return "Instant";
    }

    @Override
    public String ruleText() {
        return "Destroy target enchantment. " + "Draw a card.";
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
