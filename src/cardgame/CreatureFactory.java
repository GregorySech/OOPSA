/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

import cardgame.cards.*;

/**
 *
 * @author Elena
 * DISCOMMENTARE BENEVOLENT ANCHESTOR
 */
public class CreatureFactory extends AbstractFactory {

    @Override
    public Card getCreature(String creature) {
        
        if(creature == null)
            return null;
        if(creature.equalsIgnoreCase("Bronze Sable"))
            return new BronzeSable();
        if(creature.equalsIgnoreCase("Norwood Ranger"))
            return new NorwoodRanger();
        if(creature.equalsIgnoreCase("Argothian Enchantress"))
            return new ArgothianEnchantress();
        /*if(creature.equalsIgnoreCase("Benevolent Ancestor"))
            return new BenevolentAnchestor();*/
        
        return null;
    }
    
}
