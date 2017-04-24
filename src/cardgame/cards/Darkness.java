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
import cardgame.CreatureDecorator;
import cardgame.Effect;
import cardgame.Player;
import cardgame.TriggerAction;
import cardgame.Triggers;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gregory
 */
public class Darkness implements Card {

    private class DarknessEffect extends AbstractCardEffect {

        private List<DarknessDecorator> decorators;
        private ActivateDarknessTriggerAction activator;
        private DeactivateDarknessTriggerAction deactivator;

        public DarknessEffect(Player p, Card c) {
            super(p, c);
            decorators = new ArrayList<>();
            activator = new ActivateDarknessTriggerAction(decorators);
            deactivator = new DeactivateDarknessTriggerAction(decorators);

        }

        @Override
        public void resolve() {
            Triggers tr = CardGame.instance.getTriggers();
            tr.register(Triggers.START_DAMAGE_SUBPHASE_FILTER, activator);
            tr.register(Triggers.END_DAMAGE_SUBPHASE_FILTER, deactivator);
            tr.register(Triggers.END_FILTER, new ExpireDarknessTriggerAction(activator, deactivator));
        }

    }

    private class ActivateDarknessTriggerAction implements TriggerAction {

        private List<DarknessDecorator> l;

        public ActivateDarknessTriggerAction(List<DarknessDecorator> l) {
            this.l = l;
        }

        @Override
        public void execute(Object args) {
            DarknessDecorator dec;
            for (Creature c : CardGame.instance.getCurrentPlayer().getCreatures()) {
                dec = new DarknessDecorator(c);
                l.add(dec);
                c.addCreatureDecorator(dec);
            }
            for (Creature c : CardGame.instance.getCurrentAdversary().getCreatures()) {
                dec = new DarknessDecorator(c);
                l.add(dec);
                c.addCreatureDecorator(dec);
            }
        }

    }

    private class DeactivateDarknessTriggerAction implements TriggerAction {

        private List<DarknessDecorator> l;

        public DeactivateDarknessTriggerAction(List<DarknessDecorator> l) {
            this.l = l;
        }

        @Override
        public void execute(Object args) {
            for (DarknessDecorator dd : l) {
                dd.removeCreatureDecorator(dd);
            }
        }

    }

    private class ExpireDarknessTriggerAction implements TriggerAction {

        private ActivateDarknessTriggerAction activator;
        private DeactivateDarknessTriggerAction deactivator;

        public ExpireDarknessTriggerAction(ActivateDarknessTriggerAction ac, DeactivateDarknessTriggerAction dc) {
            this.activator = ac;
            this.deactivator = dc;
        }

        @Override
        public void execute(Object args) {
            CardGame.instance.getTriggers().deregister(activator);
            CardGame.instance.getTriggers().deregister(deactivator);
            CardGame.instance.getTriggers().deregister(this);
        }

    }

    private class DarknessDecorator extends CreatureDecorator {

        public DarknessDecorator(Creature decoratedCreature) {
            super(decoratedCreature);
        }

        @Override
        public int getPower() {
            return 0;
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
