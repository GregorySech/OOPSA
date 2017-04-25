/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.cards;

import cardgame.AbstractCardEffect;
import cardgame.Card;
import cardgame.CardGame;
import cardgame.Creature;
import cardgame.Effect;
import cardgame.Player;
import cardgame.SingleTargetEffect;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author jona
 */
public class VolcanicHammer implements Card {

    private class VolcanicHammerEffect extends AbstractCardEffect implements SingleTargetEffect {

        private Object target;

        public VolcanicHammerEffect(Player p, Card c) {
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
                if (target instanceof Player) {
                    ((Player) target).inflictDamage(3);
                } else {
                    ((Creature) target).inflictDamage(3);
                }
            }
        }

        private void chooseCreature(Player p) {
            int i = 0, j;
            List<Creature> playercreature = p.getCreatures();
            List<Creature> plc = new ArrayList(playercreature);
            Creature cr = null;
            for (Iterator<Creature> it = plc.iterator(); it.hasNext();) {
                cr = it.next();
                if (!(cr.targetable())) {
                    it.remove();
                }
            }
            do {
                i = 0;
                for (Creature c : plc) {
                    System.out.println("[" + (++i) + "]" + c);
                }
                System.out.println("[0] to end selection");
                j = CardGame.instance.getScanner().nextInt();
                if (j != 0 && j <= plc.size()) {
                    target = plc.get(j - 1);
                } else {
                    target = null;
                }
            } while (j < 0 || j > plc.size());
        }

        @Override
        public void chooseTarget() {
            int last;
            System.out.println("Volcanic Hammer targetting phase : ");
            do {
                System.out.println("What do you want to target :");
                System.out.println("[1]" + "A Player");
                System.out.println("[2]" + "A Creature");
                last = CardGame.instance.getScanner().nextInt();
                if (last == 1) {
                    do {
                        
                        System.out.println("[1]" + owner.name());
                        System.out.println("[2]" + CardGame.instance.getRival(owner).name());
                        last = CardGame.instance.getScanner().nextInt();
                    } while (last < 1 || last > 2);
                    if (last == 1) {
                        target = owner; 
                    } else {
                        target = CardGame.instance.getRival(owner);
                    }
                } else {
                    System.out.println("Whom creature do you want to target:");
                    do {
                        System.out.println("[1]" + owner.name() +"\'s creature");
                        System.out.println("[2]" + CardGame.instance.getRival(owner).name() +"\'s creature");
                        last = CardGame.instance.getScanner().nextInt();
                    } while (last < 1 || last > 2);
                    if (last == 1) {
                        chooseCreature(owner);
                    } else {
                        chooseCreature(CardGame.instance.getRival(owner));
                    }
                }

            } while (last < 1 || last > 2);

        }

        @Override
        public Object getTarget() {
            return target;
        }
    }

    @Override
    public Effect getEffect(Player owner) {
        return new VolcanicHammerEffect(owner, this);
    }

    @Override
    public String name() {
        return "Volcanic Hammer";
    }

    @Override
    public String type() {
        return "Sorcery";
    }

    @Override
    public String ruleText() {
        return "Target player or creature gets 3 damage";
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
