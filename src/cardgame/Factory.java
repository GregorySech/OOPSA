package cardgame;

import cardgame.cards.*;

/**
 *
 * @author Elena
 */
public class Factory{
    
    public CreatureFactory getCreatureFactory(){ return new CreatureFactory(); }
    public EnchantmentFactory getEnchantmentFactory(){ return new EnchantmentFactory(); }
    public SorceryFactory getSorceryFactory(){ return new SorceryFactory(); }
    public InstantFactory getInstantFactory(){ return new InstantFactory(); }
    
    public class CreatureFactory extends AbstractFactory{

        @Override
        public Card getCreature(String creature) {

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
    
    public class EnchantmentFactory extends AbstractFactory{

        @Override
        public Card getEnchantment(String enchantment) {

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
    
    public class SorceryFactory extends AbstractFactory{
    
        @Override
        public Card getSorcery(String sorcery){
            
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
    
    public class InstantFactory extends AbstractFactory{
    
        @Override
        public Card getInstant(String instant){
            
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
}
