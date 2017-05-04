/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.cardFactories;

import cardgame.Card;
import cardgame.cards.BoilingEarth;
import cardgame.cards.CalmingVerse;
import cardgame.cards.DayOfJudgment;
import cardgame.cards.FalsePeace;
import cardgame.cards.Fatigue;
import cardgame.cards.SavorTheMoment;
import cardgame.cards.VolcanicHammer;
import cardgame.cards.WorldAtWar;

/**
 *
 * @author Elena
 */
public class SorceryFactory implements CardFactory{
    
    @Override
    public Card getCard(String sorcery){

        if(sorcery == null)
            return null;
        if(sorcery.equalsIgnoreCase("Boiling Earth"))
            return new BoilingEarth();
        if(sorcery.equalsIgnoreCase("Calming Verse"))
            return new CalmingVerse();
        if(sorcery.equalsIgnoreCase("Day of Judgment"))
            return new DayOfJudgment();
        if(sorcery.equalsIgnoreCase("False Peace"))
            return new FalsePeace();
        if(sorcery.equalsIgnoreCase("Fatigue"))
            return new Fatigue();
        if(sorcery.equalsIgnoreCase("Savor the Moment"))
            return new SavorTheMoment();
        if(sorcery.equalsIgnoreCase("Volcanic Hammer"))
            return new VolcanicHammer();
        if(sorcery.equalsIgnoreCase("World at War"))
            return new WorldAtWar();

        return null;
    }
}
