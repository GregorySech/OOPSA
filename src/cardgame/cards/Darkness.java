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

/**
 *
 * @author gregory
 */
public class Darkness implements Card {

    private class DarknessEffect extends AbstractCardEffect {

        private class AddDarknessTriggerAction implements TriggerAction {

            @Override
            public void execute(Object args) {
                if (args instanceof Creature) {
                    Creature c = (Creature) args;
                    c.addCreatureDecorator(new DarknessDecorator(c));
                }
            }
        }

        public DarknessEffect(Player p, Card c) {
            super(p, c);
        }

        @Override
        public void resolve() {
            TriggerAction addDark = new AddDarknessTriggerAction();

            CardGame.instance.getTriggers().register(Triggers.ENTER_CREATURE_FILTER, addDark);
            CardGame.instance.getTriggers().register(Triggers.END_FILTER, new TriggerAction() {
                TriggerAction trig;

                @Override
                public void execute(Object args) {
                    CardGame.instance.getTriggers().deregister(trig);
                }

                TriggerAction init(TriggerAction t) {
                    trig = t;
                    return this;
                }
            }.init(addDark));

            for (Creature c : CardGame.instance.getCurrentPlayer().getCreatures()) {
                c.addCreatureDecorator(new DarknessDecorator(c));
            }
            for (Creature c : CardGame.instance.getCurrentAdversary().getCreatures()) {
                c.addCreatureDecorator(new DarknessDecorator(c));
            }

        }

    }

    private class DarknessDecorator extends CreatureDecorator {

        private class DarknessActivator implements TriggerAction {

            DarknessDecorator dec;

            public DarknessActivator(DarknessDecorator dec) {
                this.dec = dec;
            }

            @Override
            public void execute(Object args) {
                dec.active = true;
            }
        }

        private class DarknessDeactivator implements TriggerAction {

            DarknessDecorator dec;

            public DarknessDeactivator(DarknessDecorator dec) {
                this.dec = dec;
            }

            @Override
            public void execute(Object args) {
                dec.active = false;
            }
        }

        private class DarknessExpirator implements TriggerAction {

            DarknessDecorator dec;

            public DarknessExpirator(DarknessDecorator dec) {
                this.dec = dec;
            }

            @Override
            public void execute(Object args) {
                CardGame.instance.getTriggers().deregister(this);
                CardGame.instance.getTriggers().deregister(dec.activator);
                CardGame.instance.getTriggers().deregister(dec.deactivator);
                dec.decoratedCreature.getCreatureDecoratorHead().removeCreatureDecorator(dec);
            }

        }
        private boolean active;
        private DarknessActivator activator;
        private DarknessDeactivator deactivator;

        public DarknessDecorator(Creature decoratedCreature) {
            super(decoratedCreature);
            active = false;
            activator = new DarknessActivator(this);
            deactivator = new DarknessDeactivator(this);
            CardGame.instance.getTriggers().register(Triggers.START_DAMAGE_SUBPHASE_FILTER, activator);
            CardGame.instance.getTriggers().register(Triggers.END_DAMAGE_SUBPHASE_FILTER, deactivator);
            CardGame.instance.getTriggers().register(Triggers.END_FILTER, new DarknessExpirator(this));
        }

        @Override
        public void inflictDamage(int dmg) {
            if (!active) {
                decoratedCreature.inflictDamage(dmg);
            }
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
