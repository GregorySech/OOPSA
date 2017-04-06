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
public class DefaultUntapPhase implements Phase {
    @Override
    public void execute() {
        Player current_player = CardGame.instance.getCurrentPlayer();
        
        System.out.println(current_player.name() + ": untap phase");
        
        CardGame.instance.getTriggers().trigger(Triggers.UNTAP_FILTER);
        
        if (current_player.getCreatures().isEmpty())
            System.out.println("...no creatures to untap");
        
        for(Creature c:current_player.getCreatures()) {
            System.out.println("...untap " + c.name());
            c.untap();
        }
    }
}
