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
import cardgame.Phases;
import cardgame.Player;
import cardgame.SingleTargetEffect;
import cardgame.SkipPhase;

/**
 *
 * @author gsech
 */
public class FalsePeace implements Card {

    private class FalsePeaceEffect extends AbstractCardEffect implements SingleTargetEffect {

        private Player target;

        public FalsePeaceEffect(Player p, Card c) {
            super(p, c);
        }

        @Override
        public boolean play() {
            chooseTarget();
            return super.play();
        }

        @Override
        public void resolve() {
            target.setPhase(Phases.COMBAT, new SkipPhase(Phases.COMBAT));
        }

        @Override
        public void chooseTarget() {
            int last;
            System.out.println("False Peace targetting phase :");
            do {
                System.out.println("Choose the target :");
                System.out.println("[1] " + CardGame.instance.getCurrentPlayer().name());
                System.out.println("[2] " + CardGame.instance.getCurrentAdversary().name());
                last = CardGame.instance.getScanner().nextInt();
            } while (last < 1 || last > 2);
        }

        @Override
        public Object getTarget() {
            return target;
        }

    }

    @Override
    public Effect getEffect(Player owner) {
        return new FalsePeaceEffect(owner, this);
    }

    @Override
    public String name() {
        return "False Peace";
    }

    @Override
    public String type() {
        return "Sorcery";
    }

    @Override
    public String ruleText() {
        return "Target player skips his next combat phase";
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
