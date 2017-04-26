/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

/**
 *
 * @author Elena
 * MANCANO FACTORY PER TUTTO TRANNE CREATURE
 */
public class FactoryProducer {
    
    public static AbstractFactory getFactory(String choice){
        
        if(choice.equalsIgnoreCase("Creature"))
            return new CreatureFactory();
        /*if(choice.equalsIgnoreCase("Enchantment"))
            return new EnchantmentFactory();
        if(choice.equalsIgnoreCase("Sorcery"))
            return new SorceryFactory();
        if(choice.equalsIgnoreCase("Instant"))
            return new InstantFactory();*/
        
        return null;
    }
    
}
