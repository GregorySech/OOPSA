/*
 * DEVO FARE UNTAP DELLE ENCHANTED CREATURE E CAMBIARE OWNER NELLE ENCHANTED CREATURE NON CONTROLLATE DALL'OWNER DI ABDUCTION
 */
package cardgame.cards;


import cardgame.AbstractEnchantment;
import cardgame.AbstractEnchantmentCardEffect;
import cardgame.Card;
import cardgame.CardGame;
import cardgame.Creature;
import cardgame.Effect;
import cardgame.Enchantment;
import cardgame.Player;
import cardgame.TriggerAction;
import cardgame.Triggers;
import java.util.List;

/**
 *
 * @author Elena
 */
public class Abduction implements Card{
    
    private class AbductionEffect extends AbstractEnchantmentCardEffect{
        
        public AbductionEffect(Player p, Card c){
            super(p,c);
        }

        @Override
        protected Enchantment createEnchantment() {
            return new AbductionEnchantment(owner);
        }
        
        @Override
        public void resolve() {
            /* come faccio a capire se una creatura è enchanted? */            
            List<Creature> creatures = CardGame.instance.getCurrentAdversary().getCreatures();
            for( Creature c: creatures )
                c.untap();
            creatures = CardGame.instance.getCurrentPlayer().getCreatures();
            for( Creature c: creatures )
                c.untap();
        }
    }
    
    private class AbductionEnchantment extends AbstractEnchantment{

        public AbductionEnchantment(Player owner){
            super(owner);
        }
        
        private final TriggerAction controlAction = new TriggerAction() {
            @Override
            public void execute(Object args) {
                /* come faccio a capire se una creatura è enchanted? */
                if (args != null && args instanceof Creature) {
                    Creature c = (Creature) args;
                    if(!owner.getCreatures().contains(c)){
                        System.out.println("Abduction: "+owner.name()+" controls adversary's enchanted creature "+c.name());
                        c.getCreatureDecoratorHead().changeOwner(owner);
                    }
                }
            }
        };
        
        @Override
        public String name() {
            return "Abduction";
        }
        
        @Override
        public void insert() {
            /*CONTROLLARE SE è GIUSTO!*/
            super.insert();
            CardGame.instance.getTriggers().register(Triggers.ENTER_CREATURE_FILTER, controlAction);
        }

        @Override
        public void remove() {
            /*CONTROLLARE SE è GIUSTO!*/
            super.remove();
            CardGame.instance.getTriggers().deregister(controlAction);
        }
    }

    @Override
    public Effect getEffect(Player owner) {
        return new AbductionEffect(owner,this);
    }

    @Override
    public String name() {
        return "Abduction";
    }

    @Override
    public String type() {
        return "Enchantment";
    }

    @Override
    public String ruleText() {
        return "When Abduction comes into play, untap enchanted creature. You control enchanted creature.";
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
