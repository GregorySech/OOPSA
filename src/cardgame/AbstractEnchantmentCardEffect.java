/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

/**
 *
 * @author atorsell
 */
public abstract class AbstractEnchantmentCardEffect extends AbstractCardEffect {
    protected AbstractEnchantmentCardEffect( Player p, Card c) { super(p,c); }
    
    // deferred method that creates the creature upon resolution
    protected abstract Enchantment  createEnchantment();
    
    @Override
    public void resolve() {
        Enchantment e=createEnchantment();
        owner.getEnchantments().add(e);
        e.insert();
    }
}
