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
 * @author denny
 */
public class Fatigue implements Card {
    private class FatigueEffect extends AbstractCardEffect implements SingleTargetEffect {

        private Player target;

        public FatigueEffect(Player p, Card c) {
            super(p, c);
        }

        @Override
        public boolean play() {
            chooseTarget();
            return super.play();
        }

        @Override
        public void resolve() {
            target.setPhase(Phases.DRAW, new SkipPhase(Phases.DRAW));
        }

        @Override
        public void chooseTarget() {
            int last;
            System.out.println("Fatigue targeting phase :");
            do {
                System.out.println("Choose the target :");
                System.out.println("[1] " + CardGame.instance.getCurrentPlayer().name());
                System.out.println("[2] " + CardGame.instance.getCurrentAdversary().name());
                last = CardGame.instance.getScanner().nextInt();
            } while (last < 1 || last > 2);
            if(last==1){
                target=CardGame.instance.getCurrentPlayer();
            }
            else{
                target=CardGame.instance.getCurrentAdversary();
            }
        }

        @Override
        public Object getTarget() {
            return target;
        }

    }

    @Override
    public Effect getEffect(Player owner) {
        return new FatigueEffect(owner, this);
    }

    @Override
    public String name() {
        return "Fatigue";
    }

    @Override
    public String type() {
        return "Sorcery";
    }

    @Override
    public String ruleText() {
        return "Target player skips his next draw phase.";
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
