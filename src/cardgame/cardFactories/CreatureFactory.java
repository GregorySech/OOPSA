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

/**
 *
 * @author Elena
 */
public class CreatureFactory implements CardFactory{

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
