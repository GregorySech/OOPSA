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
import cardgame.SkipPhase;
import cardgame.TriggerAction;
import cardgame.Triggers;

/**
 *
 * @author denny
 */
public class WorldAtWar implements Card {

    private class WorldAtWarEffect extends AbstractCardEffect {
        
        private final TriggerAction trig = new TriggerAction() {
            @Override
            public void execute(Object args) {
                /*SE NON HO CAPITO MALE QUI CI VA LA PARTE IN CUI PRENDO QUELLI CHE HANNO ATTACCATO*/
            }
        };

        public WorldAtWarEffect(Player p, Card c) {
            super(p, c);
        }

        @Override
        public void resolve() {
            owner.setPhase(Phases.END, new SkipPhase(Phases.END));
            owner.setPhase(Phases.NULL, new SkipPhase(Phases.NULL));
            owner.setPhase(Phases.DRAW, new SkipPhase(Phases.DRAW));
            owner.setPhase(Phases.UNTAP, new SkipPhase(Phases.UNTAP));
            Triggers t= CardGame.instance.getTriggers();
            t.register(Triggers.ATTACKER_FILTER, trig);
            
        }
    }

    @Override
    public Effect getEffect(Player owner) {
        return new WorldAtWarEffect(owner, this);
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