package cardgame.cardFactories;

import cardgame.Card;
import cardgame.cards.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Elena
 */
public class InstantFactory implements CardFactory{
    
    private List<String> instants;
    
    public InstantFactory(){
        instants = new ArrayList<>();
        instants.add(0,"Afflict");
        instants.add(1,"Aggressive Urge");
        instants.add(2,"Aura Blast");
        instants.add(3,"Cancel");
        instants.add(4,"Darkness");
        instants.add(5,"Deflection");
    }
    
    @Override
    public List<String> getAvaibleCards() {
        return instants;
    }
    
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
