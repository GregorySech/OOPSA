/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.cards;

import cardgame.AbstractCardEffect;
import cardgame.Card;
import cardgame.CardGame;
import cardgame.CombatPhase;
import cardgame.Creature;
import cardgame.Effect;
import cardgame.Phase;
import cardgame.Phases;
import cardgame.Player;
import cardgame.TriggerAction;
import cardgame.Triggers;
import cardgame.combatStrategy.DamageStrategyDecorator;
import java.util.List;
import java.util.Map;

/**
 *
 * @author gregory
 */
public class Darkness implements Card {

    private class DarknessEffect extends AbstractCardEffect {

        DarknessStrategyDecorator dsd;

        public DarknessEffect(Player p, Card c) {
            super(p, c);
        }

        @Override
        public void resolve() {
            dsd = new DarknessStrategyDecorator();
            Phase p = CardGame.instance.getCurrentPlayer().getPhase(Phases.COMBAT);
            if (p instanceof CombatPhase) {
                CombatPhase dcp = (CombatPhase) p;
                dcp.getDamageStrategy().decorate(dsd);
                CardGame.instance.getTriggers().register(Triggers.END_FILTER, new DarknessDeactivator(dsd));
            }
        }

    }

    private class DarknessDeactivator implements TriggerAction {

        DarknessStrategyDecorator dsd;

        public DarknessDeactivator(DarknessStrategyDecorator dsd) {
            this.dsd = dsd;
        }

        @Override
        public void execute(Object args) {
            Phase p = CardGame.instance.getCurrentPlayer().getPhase(Phases.COMBAT);
            if (p instanceof CombatPhase) {
                CombatPhase dcp = (CombatPhase) p;
                dcp.getDamageStrategy().removeDecorator(dsd);
            }
            CardGame.instance.getTriggers().deregister(this);
        }

    }

    private class DarknessStrategyDecorator extends DamageStrategyDecorator {

        @Override
        public void damageSubPhase(Map<Creature, List<Creature>> battles) {
            System.out.println("DARKNESS : No combat damages!");
        }
    }

    @Override
    public Effect getEffect(Player owner) {
        return new DarknessEffect(owner, this);
    }

    @Override
    public String name() {
        return "Darkness";
    }

    @Override
    public String type() {
        return "Instant";
    }

    @Override
    public String ruleText() {
        return "Prevent all combat damage that would be dealt this turn.";
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
