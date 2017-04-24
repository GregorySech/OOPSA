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
import cardgame.Player;
import java.util.List;

/**
 *
 * @author gsech
 */
public class DayOfJudgment implements Card{

    private class DayOfJudgmentEffect extends AbstractCardEffect {

        public DayOfJudgmentEffect(Player p, Card c){
            super(p, c);
        }
        @Override
        public void resolve() {
            List<Creature> creaturesPlayer = CardGame.instance.getCurrentPlayer().getCreatures();
            List<Creature> creaturesAdversary = CardGame.instance.getCurrentAdversary().getCreatures();
            for(Creature c : creaturesPlayer)
                c.getCreatureDecoratorHead().remove();
            for(Creature c: creaturesAdversary)
                c.getCreatureDecoratorHead().remove();
        }
    }
    @Override
    public Effect getEffect(Player owner) {
        return new DayOfJudgmentEffect(owner, this);
    }

    @Override
    public String name() {
        return "Day of Judgment";
    }

    @Override
    public String type() {
        return "Sorcery";
    }

    @Override
    public String ruleText() {
        return "Destroy all creatures";
    }

    @Override
    public boolean isInstant() {
        return false;
    }
    
    @Override 
    public String toString() { return name() + " (" + type() + ") [" + ruleText() +"]";}

    
}
