/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

import cardgame.cardFactories.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Scanner;


/**
 *
 * @author atorsell
 */
public class CardGame {

    /**
     * @param args the command line arguments
     */
    
    
    public static void main(String[] args) {
        //create decks
        ArrayList<Card> deck1 = new ArrayList<>();
        ArrayList<Card> deck2 = new ArrayList<>();
        
        /*for(int i = 0; i < 5; i++){
            deck1.add(new WorldAtWar());
            deck1.add(new BronzeSable());
            deck1.add(new Afflict());
            deck1.add(new NorwoodRanger());
        }*/
        DeckFactory.createDeck(deck1,1);
        instance.getPlayer(0).setDeck(deck1.iterator());
        DeckFactory.createDeck(deck2,2);
        instance.getPlayer(1).setDeck(deck2.iterator());

        instance.run();
    }

    //Signleton and instance access
    public static final CardGame instance = new CardGame();

    //game setup 
    private CardGame() {
        turnManagerStack.push(new DefaultTurnManager(Players));

        Players[0] = new Player();
        Players[0].setName("Player 1");
        Players[0].setPhase(Phases.DRAW, new SkipPhase(Phases.DRAW));

        Players[1] = new Player();
        Players[1].setName("Player 2");
    }

    //execute game
    public void run() {
        Players[0].getDeck().shuffle();
        Players[1].getDeck().shuffle();

        for (int i = 0; i != 5; ++i) {
            Players[0].draw();
        }
        for (int i = 0; i != 5; ++i) {
            Players[1].draw();
        }

        try {
            while (true) {
                instance.nextPlayer().executeTurn();
            }
        } catch (EndOfGame e) {
            System.out.println(e.getMessage());
        }
    }

    // Player and turn management
    private final Player[] Players = new Player[2];
    private final Deque<TurnManager> turnManagerStack = new ArrayDeque<>();

    public void setTurnManager(TurnManager m) {
        turnManagerStack.push(m);
    }

    public void removeTurnManager(TurnManager m) {
        turnManagerStack.remove(m);
    }

    Player getPlayer(int i) {
        return Players[i];
    }
    public Player getRival(Player p){
        if(p == Players[0])
            return Players[1];
        else
            return Players[0];
    }

    public Player getCurrentPlayer() {
        return turnManagerStack.peek().getCurrentPlayer();
    }

    public Player getCurrentAdversary() {
        return turnManagerStack.peek().getCurrentAdversary();
    }

    Player nextPlayer() {
        return turnManagerStack.peek().nextPlayer();
    }

    public int getPlayerID(Player p) {
        return p == Players[0] ? 0 : 1;
    }

    // Stack access
    private final CardStack stack = new CardStack();

    public CardStack getStack() {
        return stack;
    }

    //Trigger access
    private final Triggers triggers = new Triggers();

    public Triggers getTriggers() {
        return triggers;
    }

    //IO resources  to be dropped in final version
    private final Scanner reader = new Scanner(System.in);

    public Scanner getScanner() {
        return reader;
    }
    
}
