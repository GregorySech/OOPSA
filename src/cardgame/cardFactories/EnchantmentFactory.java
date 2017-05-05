/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.cardFactories;

import cardgame.Card;
import cardgame.cards.Abduction;
import cardgame.cards.AetherBarrier;
import cardgame.cards.AetherFlash;
import cardgame.cards.AncestralMask;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Elena
 */
public class EnchantmentFactory implements CardFactory{
    
    private List<String> enchantments;
    
    public EnchantmentFactory(){
        enchantments = new ArrayList<>();
        enchantments.add(0,"Abduction");
        enchantments.add(1,"Aether Barrier");
        enchantments.add(2,"Aether Flash");
        enchantments.add(3,"Ancestral Mask");
    }
    
    @Override
    public List<String> getAvaibleCards() {
        return enchantments;
    }

    @Override
    public Card getCard(String enchantment) {

        if(enchantment == null)
            return null;
        if(enchantment.equalsIgnoreCase("Abduction"))
            return new Abduction();
        if(enchantment.equalsIgnoreCase("Aether Barrier"))
            return new AetherBarrier();
        if(enchantment.equalsIgnoreCase("Aether Flash"))
            return new AetherFlash();
        if(enchantment.equalsIgnoreCase("Ancestral Mask"))
            return new AncestralMask();

        return null;
    }

}
