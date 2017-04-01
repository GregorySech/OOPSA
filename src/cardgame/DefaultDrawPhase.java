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
public class DefaultDrawPhase implements Phase {
    @Override
    public void execute() {
        Player currentPlayer = CardGame.instance.getCurrentPlayer();
        
        System.out.println(currentPlayer.name() + ": draw phase");
        
        CardGame.instance.getTriggers().trigger(Triggers.DRAW_FILTER);
        currentPlayer.draw();
        
        while(currentPlayer.getHand().size() > currentPlayer.getMaxHandSize())
            currentPlayer.selectDiscard();
    }
}
