package cardgame.cardFactories;

import cardgame.Card;
import cardgame.cards.*;

/**
 *
 * @author Elena
 */
public class InstantFactory implements CardFactory{
    
    @Override
    public Card getCard(String instant){

        if(instant == null)
            return null;
        if(instant.equalsIgnoreCase("Afflict"))
            return new Afflict();
        if(instant.equalsIgnoreCase("Aggressive Urge"))
            return new AggressiveUrge();
        if(instant.equalsIgnoreCase("Aura Blast"))
            return new AuraBlast();
        if(instant.equalsIgnoreCase("Cancel"))
            return new Cancel();
        if(instant.equalsIgnoreCase("Darkness"))
            return new Darkness();
        if(instant.equalsIgnoreCase("Deflection"))
            return new Deflection();

        return null;
    }
}
