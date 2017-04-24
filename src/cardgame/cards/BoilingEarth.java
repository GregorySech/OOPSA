package cardgame.cards;

import cardgame.AbstractCardEffect;
import cardgame.Card;
import cardgame.CardGame;
import cardgame.Creature;
import cardgame.Effect;
import cardgame.Player;
import java.util.List;

/**
 *
 * @author Elena
 */
public class BoilingEarth implements Card {

    private class BoilingEarthEffect extends AbstractCardEffect {

        public BoilingEarthEffect(Player p, Card c){
            super(p,c);
        }
        
        @Override
        public void resolve() {
            
            List<Creature> creaturesPlayer = CardGame.instance.getCurrentPlayer().getCreatures();
            List<Creature> creaturesAdversary = CardGame.instance.getCurrentAdversary().getCreatures();
            
            for(Creature c : creaturesPlayer)
                c.getCreatureDecoratorHead().inflictDamage(1);
            for(Creature c: creaturesAdversary)
                c.getCreatureDecoratorHead().inflictDamage(1);
            
        }
    
    }
    
    @Override
    public Effect getEffect(Player owner) {
        return new BoilingEarthEffect(owner, this);
    }

    @Override
    public String name() {
        return "Boiling Earth";
    }

    @Override
    public String type() {
        return "Sorcery";
    }

    @Override
    public String ruleText() {
        return "Boiling Earth deals 1 damage to each creature";
    }

    @Override
    public boolean isInstant() {
        return false;
    }
    
    @Override
    public String toString(){
        return name() + " (" + type() + ") [" + ruleText() +"]";
    }

    
}
