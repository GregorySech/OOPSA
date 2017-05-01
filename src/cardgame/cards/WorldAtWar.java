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
import cardgame.PhaseManager;
import cardgame.Phases;
import cardgame.Player;
import cardgame.TriggerAction;
import cardgame.Triggers;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

/**
 *
 * @author denny
 */
public class WorldAtWar implements Card {

    List<Creature> lastAttackers;
    private final TriggerAction trig = new TriggerAction() {
        @Override
        public void execute(Object args) {
            if (args != null) {
                if (args instanceof List) {
                    lastAttackers = (List<Creature>) args;
                }
            }
        }
    };

    private class WorldAtWarEffect extends AbstractCardEffect {

        List<Creature> attackers;

        public WorldAtWarEffect(Player p, Card c, List<Creature> attackers) {
            super(p, c);
            this.attackers = attackers;
        }

        @Override
        public void resolve() {
            WorldAtWarManager wawm = new WorldAtWarManager(owner, attackers);
            owner.setPhaseManager(wawm);
        }
    }

    private class WorldAtWarManager implements PhaseManager {

        Deque<Phases> phases;
        Phases current;
        Player p;
        List<Creature> lastAttackers;

        public WorldAtWarManager(Player p, List<Creature> attackers) {
            phases = new ArrayDeque<>();
            phases.push(Phases.COMBAT);
            phases.push(Phases.MAIN);
            current = Phases.MAIN;
            this.p = p;
            this.lastAttackers = attackers;
        }

        @Override
        public Phases currentPhase() {
            return current;
        }

        @Override
        public Phases nextPhase() {
            current = phases.pop();
            Phases retp = currentPhase();
            if (retp == Phases.COMBAT && lastAttackers != null) {
                for (Creature c : lastAttackers) {
                    c.untap();
                }
            }
            if (phases.isEmpty()) {
                p.removePhaseManager(this);
            }
            return retp;
        }
    }

    @Override
    public Effect getEffect(Player owner) {
        return new WorldAtWarEffect(owner, this, lastAttackers);
    }

    public WorldAtWar() {
        super();
        CardGame.instance.getTriggers().register(Triggers.ATTACKER_FILTER, trig);
    }

    @Override
    public String name() {
        return "World At War";
    }

    @Override
    public String type() {
        return "Sorcery";
    }

    @Override
    public String ruleText() {
        return "After the first post combat main phase this turn, there is an additional combat phase followed by an additional main phase. At the beginning of that combat, untap all the creatures that attacked this turn.";
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
