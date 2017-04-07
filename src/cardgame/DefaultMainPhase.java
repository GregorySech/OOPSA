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
public class DefaultMainPhase implements Phase {
    
    @Override
    public void execute() {
        Player currentPlayer = CardGame.instance.getCurrentPlayer();
        int responsePlayerIdx = (CardGame.instance.getPlayer(0) == currentPlayer)?1:0;
        
        System.out.println(currentPlayer.name() + ": main phase");
        
        CardGame.instance.getTriggers().trigger(Triggers.MAIN_FILTER);
        
        CardGame.instance.getStack().fill(CardGame.instance.getPlayerID(currentPlayer));
    }
    
    

    
}
