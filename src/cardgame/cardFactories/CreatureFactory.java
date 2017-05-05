/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.cardFactories;

import cardgame.Card;
import cardgame.cards.ArgothianEnchantress;
import cardgame.cards.BenevolentAncestor;
import cardgame.cards.BronzeSable;
import cardgame.cards.NorwoodRanger;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Elena
 */
public class CreatureFactory implements CardFactory{

    private List<String> creatures;
    
    public CreatureFactory(){
        creatures = new ArrayList<>();
        creatures.add(0,"Argothian Enchantress");
        creatures.add(1,"Benevolent Ancestor");
        creatures.add(2,"Bronze Sable");
        creatures.add(3,"Norwood Ranger");
    }
    
    @Override
    public List<String> getAvaibleCards(){
        return creatures;
    }
    
    @Override
    public Card getCard(String creature) {

        if(creature == null)
            return null;
        if(creature.equalsIgnoreCase("Argothian Enchantress"))
            return new ArgothianEnchantress();
        if(creature.equalsIgnoreCase("Benevolent Ancestor"))
            return new BenevolentAncestor();
        if(creature.equalsIgnoreCase("Bronze Sable"))
            return new BronzeSable();
        if(creature.equalsIgnoreCase("Norwood Ranger"))
            return new NorwoodRanger();


        return null;
    }

}
