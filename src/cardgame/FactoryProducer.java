package cardgame;

/**
 *
 * @author Elena
 */
public class FactoryProducer {
    
    Factory fact = new Factory();
    
    public AbstractFactory getFactory(String choice){
        
        if(choice.equalsIgnoreCase("Creature"))
            return fact.getCreatureFactory();
        if(choice.equalsIgnoreCase("Enchantment"))
            return fact.getEnchantmentFactory();
        if(choice.equalsIgnoreCase("Sorcery"))
            return fact.getSorceryFactory();
        if(choice.equalsIgnoreCase("Instant"))
            return fact.getInstantFactory();
        
        return null;
    }
    
}
