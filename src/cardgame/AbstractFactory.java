/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

/**
 *
 * @author Elena
 */
public abstract class AbstractFactory {
    
    public Card getCreature(String creature){ return null; }
    public Card getEnchantment(String enchantment){ return null; }
    public Card getSorcery(String sorcery){ return null; }
    public Card getInstant(String instant){ return null; }
    
}
