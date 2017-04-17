/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/**
 *
 * @author atorsell
 */
public class CardStack implements Iterable<Effect> {
    private final ArrayDeque<Effect> stack = new ArrayDeque<>();
    
    public Iterator<Effect> iterator() { return stack.iterator(); }
    
    public void add(Effect e) { 
        stack.push(e); 
    }
    
    public void remove(Effect e) { stack.remove(e); }
    
    public void resolve() {
        while(!stack.isEmpty()) { 
            Effect e = stack.pop();
            
            System.out.println("Stack: resolving " + e);
            
            e.resolve(); 
        }
    }
    
    public void fill(int playerID){
        // alternate in placing effect until bith players pass
        int numberPasses=0;
        
        int responsePlayerIdx = (playerID)%2;
        while (numberPasses<2) {
            if (playAvailableEffect(CardGame.instance.getPlayer(responsePlayerIdx),false))
                numberPasses=0;
            else ++numberPasses;
            
            responsePlayerIdx = (responsePlayerIdx+1)%2;
        }
        
        CardGame.instance.getStack().resolve();
    }
        // looks for all playable effects from cards in hand and creatures in play
    // and asks player for which one to play
    // includes creatures and sorceries only if isMain is true
    public boolean playAvailableEffect(Player activePlayer, boolean isMain) {
        //collect and display available effects...
        ArrayList<Effect> availableEffects = new ArrayList<>();
        Scanner reader = CardGame.instance.getScanner();

        //...cards first
        System.out.println(activePlayer.name() + " select card/effect to play, 0 to pass");
        int i=0;
        for( Card c:activePlayer.getHand() ) {
            if ( isMain || c.isInstant() ) {
                availableEffects.add( c.getEffect(activePlayer) );
                System.out.println(Integer.toString(i+1)+") " + c );
                ++i;
            }
        }
        
        //...creature effects last
        for ( Creature c:activePlayer.getCreatures()) {
            for (Effect e:c.avaliableEffects()) {
                availableEffects.add(e);
                System.out.println(Integer.toString(i+1)+") " + c.name() + 
                    " ["+ e + "]" );
                ++i;
            }
        }
        
        //get user choice and play it
        int idx= reader.nextInt()-1;
        if (idx<0 || idx>=availableEffects.size()) return false;

        availableEffects.get(idx).play();
        return true;
    }
}
