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
public interface Effect {
    // pays for effect and places it in the stack
    boolean play();
    
    // resolves the effect
    void resolve();
}
