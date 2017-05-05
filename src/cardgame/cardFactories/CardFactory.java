/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.cardFactories;

import cardgame.Card;
import java.util.List;

/**
 *
 * @author Elena
 */
public interface CardFactory {
    
    public Card getCard(String card);
    public List<String> getAvaibleCards();
    
}
