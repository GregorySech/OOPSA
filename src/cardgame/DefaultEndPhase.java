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
public class DefaultEndPhase implements Phase {
    public void execute() {
        Player currentPlayer = CardGame.instance.getCurrentPlayer();
        
        System.out.println(currentPlayer.name() + ": end phase");
        
        CardGame.instance.getTriggers().trigger(Triggers.END_FILTER);
        
        for(Creature c:currentPlayer.getCreatures()) {
            System.out.println("...reset damage to " + c.name());
            c.resetDamage();
        }
        
        for(Creature c:CardGame.instance.getCurrentAdversary().getCreatures()) {
            System.out.println("...reset damage to adversary creature " + c.name());
            c.resetDamage();
        }
    }
}
