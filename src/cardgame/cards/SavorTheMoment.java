package cardgame.cards;

import cardgame.AbstractCardEffect;
import cardgame.Card;
import cardgame.Effect;
import cardgame.Phases;
import cardgame.Player;
import cardgame.SkipPhase;

/**
 *
 * @author Elena
 */
public class SavorTheMoment implements Card {

    private class SavorTheMomentEffect extends AbstractCardEffect {

        public SavorTheMomentEffect(Player p, Card c) {
            super(p, c);
        }

        @Override
        public void resolve() {
            owner.setPhase(Phases.NULL, new SkipPhase(Phases.NULL));
            owner.setPhase(Phases.UNTAP, new SkipPhase(Phases.UNTAP));
        }
    }

    @Override
    public Effect getEffect(Player owner) {
        return new SavorTheMomentEffect(owner, this);
    }

    @Override
    public String name() {
        return "Savor the Moment";
    }

    @Override
    public String type() {
        return "Sorcery";
    }

    @Override
    public String ruleText() {
        return "Take an extra turn after this one. Skip the untap step of that turn";
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
