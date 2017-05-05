/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.cards;

import cardgame.AbstractCreature;
import cardgame.AbstractCreatureCardEffect;
import cardgame.AbstractEffect;
import cardgame.Card;
import cardgame.CardGame;
import cardgame.Creature;
import cardgame.CreatureDecorator;
import cardgame.Effect;
import cardgame.Enchantment;
import cardgame.Player;
import cardgame.SingleTargetEffect;
import cardgame.TriggerAction;
import cardgame.Triggers;
import cardgame.playerDamageStrategy.InflictDamageStrategy;
import cardgame.playerDamageStrategy.StrategyDecorator;
import cardgame.visitor.Visitable;
import cardgame.visitor.Visitor;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author denny
 *
 *
 *
 *
 *
 *
 *
 * /* NON ANCORA COMPLETATO
 */
public class BenevolentAncestor implements Card {

    private class BenevolentAncestorEffect extends AbstractCreatureCardEffect {

        BenevolentAncestorEffect(Player p, Card c) {
            super(p, c);
        }

        @Override
        protected Creature createCreature() {
            Creature basicCreature = new BenevolentAncestorCreature(owner);
            return basicCreature;
        }
    }

    private class BenevolentAncestorActiveEffect extends AbstractEffect implements SingleTargetEffect {

        private Visitable t;
        private Player owner;
        private Visitor vis;
        private Creature ancestor;
        public BenevolentAncestorActiveEffect(Player p, Creature ancestor) {
            owner = p;
            this.ancestor = ancestor;
            vis = new Visitor() {
                @Override
                public void visit(Creature c) {
                    CreatureDecorator preventDec = new CreatureDecorator(c) {
                        int danni = 1;

                        @Override
                        public void inflictDamage(int dmg) {
                            if (dmg > 0 && danni > 0) {
                                danni = 0;
                                dmg--;
                            }
                            super.inflictDamage(dmg);
                        }
                    };
                    c.addCreatureDecorator(preventDec);
                    CardGame.instance.getTriggers().register(Triggers.END_FILTER, new TriggerAction() {
                        CreatureDecorator decoratore;
                        Creature myC;

                        @Override
                        public void execute(Object args) {
                            myC.removeCreatureDecorator(decoratore);
                            CardGame.instance.getTriggers().deregister(this);
                        }

                        public TriggerAction init(Creature c, CreatureDecorator dec) {
                            this.myC = c;
                            this.decoratore = dec;
                            return this;
                        }
                    }.init(c, preventDec));
                }

                @Override
                public void visit(Player p) {
                    InflictDamageStrategy str;
                    str = p.getInflictDamageStrategy();
                    StrategyDecorator preventDec = new StrategyDecorator(str) {
                        int danni = 1;

                        @Override
                        public void inflictDamage(int dmg) {
                            if (dmg > 0 && danni > 0) {
                                danni = 0;
                                dmg--;
                            }
                            super.inflictDamage(dmg);
                        }
                    };
                    str.getHeadStrategy().decorateStrategy(preventDec);
                    CardGame.instance.getTriggers().register(Triggers.END_FILTER, new TriggerAction() {
                        InflictDamageStrategy s;
                        StrategyDecorator strDec;

                        @Override
                        public void execute(Object args) {
                            s.getHeadStrategy().removeDecoratorStrategy(strDec);
                            CardGame.instance.getTriggers().deregister(this);
                        }

                        TriggerAction init(InflictDamageStrategy strat, StrategyDecorator sdec) {
                            this.s = strat;
                            this.strDec = sdec;
                            return this;
                        }
                    });

                }

                @Override
                public void visit(Enchantment e) {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            };
            
            
        }

        @Override
        public String toString() {
            return "tap : Prevent 1 damage to a target creature or player.";
        }
        
        @Override
        public Object getTarget() {
            return t;
        }

        @Override
        public boolean play() {
            chooseTarget();
            ancestor.tap();
            return super.play();

        }

        @Override
        public void resolve() {
            if (t != null) {
                t.accept(vis);
            }
        }

        @Override
        public void chooseTarget() {
            int last;
            System.out.println("Benevolent Ancestor targeting phase : ");
            do {
                System.out.println("Choose the character to target :");
                System.out.println("[1]" + "A Player");
                System.out.println("[2]" + " A Creature");
                last = CardGame.instance.getScanner().nextInt();
            } while (last < 1 || last > 2);
            if (last == 1) {
                do {
                    System.out.println("Choose the target :");
                    System.out.println("[1]" + owner.name());
                    System.out.println("[2]" + CardGame.instance.getRival(owner).name());
                    last = CardGame.instance.getScanner().nextInt();
                } while (last < 1 || last > 2);
                if (last == 1) {
                    t = owner;
                } else {
                    t = CardGame.instance.getRival(owner);
                }
            } else {
                System.out.println("Choose the owner of the creature you want to target:");
                do {
                    System.out.println("[1]" + owner + "'s creatures");
                    System.out.println("[2]" + CardGame.instance.getRival(owner) + "'s creature");
                    last = CardGame.instance.getScanner().nextInt();
                } while (last < 1 || last > 2);
                if (last == 1) {
                    int i = 0, j;
                    List<Creature> playercreature = owner.getCreatures();
                    for (Creature c : playercreature) {
                        System.out.println(Integer.toString(i + 1) + ") " + playercreature.get(i));
                    }
                    j = CardGame.instance.getScanner().nextInt();
                    if (j <= playercreature.size() && !playercreature.isEmpty()) {
                        t = playercreature.get(j - 1);
                    }
                } else {
                    int i = 0, j;
                    List<Creature> adversarycreature = CardGame.instance.getRival(owner).getCreatures();
                    for (Creature c : adversarycreature) {
                        System.out.println(Integer.toString(i + 1) + ") " + adversarycreature.get(i));
                    }
                    j = CardGame.instance.getScanner().nextInt();
                    if (j <= adversarycreature.size() && !adversarycreature.isEmpty()) {
                        t = adversarycreature.get(j - 1);
                    }
                }
            }
        }
    }

    private class BenevolentAncestorCreature extends AbstractCreature {

        ArrayList<Effect> effects = new ArrayList<>();

        TriggerAction ProtectFromOneDamage;

        BenevolentAncestorCreature(Player owner) {
            super(owner);
            effects.add(new BenevolentAncestorActiveEffect(owner, this));
        }

        @Override
        public void attack() {
            /*throw new UnsupportedOperationException("This creature can't attack");*/
            System.out.println("This creature can't attack");
        }

        /*NON SO*/
        private class BenevolentAncestorTrigger implements TriggerAction {

            Player designatedCaster;

            private BenevolentAncestorTrigger(Player owner) {
                designatedCaster = owner;
            }

            @Override
            public void execute(Object args) {

            }

        }

        @Override
        public int getPower() {
            return 0;
        }

        @Override
        public int getToughness() {
            return 4;
        }

        @Override
        public List<Effect> effects() {
            return effects;
        }

        @Override
        public List<Effect> avaliableEffects() {
            return isTapped() ? new ArrayList<Effect>() : effects;
        }

        @Override
        public String name() {
            return "Benevolent Ancestor";
        }

        @Override
        public boolean targetable() {
            return true;
        }

        @Override
        public boolean isDefender() {
            return true;
        }

    }

    @Override
    public Effect getEffect(Player owner) {
        return new BenevolentAncestorEffect(owner, this);
    }

    @Override
    public String name() {
        return "Benevolent Ancestor";
    }

    @Override
    public String type() {
        return "Creature";
    }

    @Override
    public String ruleText() {
        return "Defender (This creature can't attack.) "
                + "Prevent the next 1 damage that would be dealt to target creature or player this turn";
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
