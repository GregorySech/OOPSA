/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.cards;

import cardgame.AbstractCreature;
import cardgame.AbstractCreatureCardEffect;
import cardgame.Card;
import cardgame.CardGame;
import cardgame.Creature;
import cardgame.Effect;
import cardgame.Player;
import cardgame.SingleTargetEffect;
import cardgame.TriggerAction;
import cardgame.Triggers;
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
 * /* NON ANCORA COMPLETATO, MANCANO EXECUTE E "NON PUO ATTACCARE" */








public class BenevolentAncestor implements Card {

    private class BenevolentAncestorEffect extends AbstractCreatureCardEffect implements SingleTargetEffect {
        private Object target;
        BenevolentAncestorEffect(Player p, Card c) {
            super(p, c);
        }

        @Override
        protected Creature createCreature() {
            return new BenevolentAncestorCreature(owner);
        }

        @Override
        public void chooseTarget() {
             int last;
            System.out.println("Benevolent Ancestor targetting phase : ");
            do{
                System.out.println("What do you want to target :");
                System.out.println("[1]" + "A Player");
                System.out.println("[2]" + " A Creature");
                last= CardGame.instance.getScanner().nextInt();
                if(last==1){
                    do{
                        System.out.println("[1]" + CardGame.instance.getCurrentPlayer().name());
                        System.out.println("[2]" + CardGame.instance.getCurrentAdversary().name());
                        last= CardGame.instance.getScanner().nextInt();
                    }while(last<1 || last >2);
                    if(last==1)
                       target=CardGame.instance.getCurrentPlayer();
                    else 
                       target=CardGame.instance.getCurrentAdversary();
                }
                else {
                    System.out.println("What creature do you want to target:");
                     do{
                        System.out.println("[1]" + "Player creatures");
                        System.out.println("[2]" +"Adversary Creature");
                        last= CardGame.instance.getScanner().nextInt();
                    }while(last<1 || last >2);
                    if(last==1){
                        int i =0,j; 
                        List <Creature> playercreature=CardGame.instance.getCurrentPlayer().getCreatures();
                        do{
                           for(Creature c: playercreature){
                                System.out.println(playercreature.get(i));
                            }
                             System.out.println("[0] to end selection");
                            j=CardGame.instance.getScanner().nextInt();
                            if(j != 0 && j<= playercreature.size()&& !playercreature.isEmpty()){
                                target=playercreature.get(j);
                            }
                            else 
                                target=null;
                        }while(j != 0);   
                    }
                    else{
                        int i=0, j;
                         List <Creature> adversarycreature=CardGame.instance.getCurrentAdversary().getCreatures();
                        do{
                           for(Creature c: adversarycreature){
                                System.out.println(adversarycreature.get(i));
                            }
                             System.out.println("[0] to end selection");
                            j=CardGame.instance.getScanner().nextInt();
                            if(j != 0 && j<= adversarycreature.size() && !adversarycreature.isEmpty()){
                                target=adversarycreature.get(j);
                            }
                            else 
                                target=null;
                        }while(j != 0);   
                    }
                }
                
            }while(last < 1 || last > 2);
            
        }

        @Override
        public Object getTarget() {
            return target;
        }
    }

    private class BenevolentAncestorCreature extends AbstractCreature {

        ArrayList<Effect> effects = new ArrayList<>();

        TriggerAction ProtectFromOneDamage;

        BenevolentAncestorCreature(Player owner) {
            super(owner);
        }

        private class BenevolentAncestorTrigger implements TriggerAction {

            Player designatedCaster;

            private BenevolentAncestorTrigger(Player owner) {
                designatedCaster = owner;
            }

            @Override
            public void execute(Object args) {
                /* PARTE MANCANTE */
            }
        }

        @Override
        public void insert() {
            ProtectFromOneDamage = new BenevolentAncestorTrigger(owner);
            CardGame.instance.getTriggers().register(Triggers.EFFECT_CASTED, ProtectFromOneDamage);
            super.insert();
        }

        @Override
        public void remove() {
            CardGame.instance.getTriggers().deregister(ProtectFromOneDamage);
            super.remove(); 
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
            return effects;
        }

        @Override
        public String name() {
            return "Benevolent Ancestor";
        }

        @Override
        public boolean targetable() {
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
        return "Defender (This creature can't attack.)\n"
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

    

