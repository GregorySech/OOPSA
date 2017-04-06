/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.interfaces;

import cardgame.Player;

/**
 *
 * @author atorsell
 */
public interface TurnManager {
    Player getCurrentPlayer();
    
    Player getCurrentAdversary();
    
    Player nextPlayer();
}
