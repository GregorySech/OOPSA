/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

import cardgame.cards.AggressiveUrge;
import cardgame.cards.ArgothianEnchantress;
import cardgame.cards.BronzeSable;
import cardgame.cards.WorldAtWar;
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
        
        for(int i = 0; i < 5; i++){
            deck1.add(new WorldAtWar());
            deck1.add(new BronzeSable());
            deck1.add(new ArgothianEnchantress());
            deck1.add(new AggressiveUrge());
        }
        //createDeck(deck1,1);
        instance.getPlayer(0).setDeck(deck1.iterator());
        //createDeck(deck2,2);
        instance.getPlayer(1).setDeck(deck1.iterator());

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
    
    
    
    
    /* Deck creation */
    
    private static int chooseCreature(Factory f,ArrayList<Card> deck,int counter){
            
            int i;
            System.out.println(" - Choose your creatures ([0] to end selection)");
            System.out.println("[1] Argothian Enchantress");
            System.out.println("[2] Benevolent Ancestor");
            System.out.println("[3] Bronze Sable");
            System.out.println("[4] Norwood Ranger");
            i=1;
            while(i!=0 && counter<20){
                i = CardGame.instance.getScanner().nextInt();
                switch(i){
                    case 0: break;
                    case 1: deck.add(f.getCreatureFactory().getCreature("Argothian Enchantress"));
                            counter++;
                            break;
                    case 2: deck.add(f.getCreatureFactory().getCreature("Benevolent Ancestor"));
                            counter++;
                            break;
                    case 3: deck.add(f.getCreatureFactory().getCreature("Bronze Sable"));
                            counter++;
                            break;
                    case 4: deck.add(f.getCreatureFactory().getCreature("Norwood Ranger"));
                            counter++;
                            break;
                    default : System.out.println("Index not supported");
                }
                if(i!=0)
                    System.out.println("You have choosen "+counter+"/20 cards");
            }
            return counter;
    }
    private static int chooseEnchantment(Factory f,ArrayList<Card> deck,int counter){
            
            int i;
            System.out.println(" - Choose your enchantments ([0] to end selection)");
            System.out.println("[1] Abduction");
            System.out.println("[2] Aether Barrier");
            System.out.println("[3] Aether Flash");
            System.out.println("[4] Ancestral Mask");
            i=1;
            while(i!=0 && counter<20){
                i = CardGame.instance.getScanner().nextInt();
                switch(i){
                    case 0: break;
                    case 1: deck.add(f.getEnchantmentFactory().getEnchantment("Abduction"));
                            counter++;
                            break;
                    case 2: deck.add(f.getEnchantmentFactory().getEnchantment("Aether Barrier"));
                            counter++;
                            break;
                    case 3: deck.add(f.getEnchantmentFactory().getEnchantment("Aether Flash"));
                            counter++;
                            break;
                    case 4: deck.add(f.getEnchantmentFactory().getEnchantment("Ancestral Mask"));
                            counter++;
                            break;
                    default : System.out.println("Index not supported");
                }
                if(i!=0)
                    System.out.println("You have choosen "+counter+"/20 cards");
            }
            return counter;
    }
    private static int chooseSorcery(Factory f,ArrayList<Card> deck,int counter){
            
            int i;
            System.out.println(" - Choose your sorceries ([0] to end selection)");
            System.out.println("[1] Boiling Earth");
            System.out.println("[2] Calming Verse");
            System.out.println("[3] Day of Judgment");
            System.out.println("[4] False Peace");
            System.out.println("[5] Fatigue");
            System.out.println("[6] Savor the Moment");
            System.out.println("[7] Volcanic Hammer");
            System.out.println("[8] World at War");
            i=1;
            while(i!=0 && counter<20){
                i = CardGame.instance.getScanner().nextInt();
                switch(i){
                    case 0: break;
                    case 1: deck.add(f.getSorceryFactory().getSorcery("Boiling Earth"));
                            counter++;
                            break;
                    case 2: deck.add(f.getSorceryFactory().getSorcery("Calming Verse"));
                            counter++;
                            break;
                    case 3: deck.add(f.getSorceryFactory().getSorcery("Day of Judgment"));
                            counter++;
                            break;
                    case 4: deck.add(f.getSorceryFactory().getSorcery("False Peace"));
                            counter++;
                            break;
                    case 5: deck.add(f.getSorceryFactory().getSorcery("Fatigue"));
                            counter++;
                            break;
                    case 6: deck.add(f.getSorceryFactory().getSorcery("Savor the Moment"));
                            counter++;
                            break;
                    case 7: deck.add(f.getSorceryFactory().getSorcery("Volcanic Hammer"));
                            counter++;
                            break;
                    case 8: deck.add(f.getSorceryFactory().getSorcery("World at War"));
                            counter++;
                            break;
                    default : System.out.println("Index not supported");
                }
                if(i!=0)
                    System.out.println("You have choosen "+counter+"/20 cards");
            }
            return counter;
    }
    private static int chooseInstant(Factory f,ArrayList<Card> deck,int counter){
            
            int i;
            System.out.println(" - Choose your instants ([0] to end selection)");
            System.out.println("[1] Afflict");
            System.out.println("[2] Aggressive");
            System.out.println("[3] Aura Blast");
            System.out.println("[4] Cancel");
            System.out.println("[5] Darkness");
            System.out.println("[6] Deflection");
            i=1;
            while(i!=0 && counter<20){
                i = CardGame.instance.getScanner().nextInt();
                switch(i){
                    case 0: break;
                    case 1: deck.add(f.getInstantFactory().getInstant("Afflict"));
                            counter++;
                            break;
                    case 2: deck.add(f.getInstantFactory().getInstant("Aggressive Urge"));
                            counter++;
                            break;
                    case 3: deck.add(f.getInstantFactory().getInstant("Aura Blast"));
                            counter++;
                            break;
                    case 4: deck.add(f.getInstantFactory().getInstant("Cancel"));
                            counter++;
                            break;
                    case 5: deck.add(f.getInstantFactory().getInstant("Darkness"));
                            counter++;
                            break;
                    case 6: deck.add(f.getInstantFactory().getInstant("Deflection"));
                            counter++;
                            break;
                    default : System.out.println("Index not supported");
                }
                if(i!=0)
                    System.out.println("You have choosen "+counter+"/20 cards");
            }
            return counter;
    }
    private static void createDeck(ArrayList<Card> deck, int p){
        System.out.println("Player "+p+" create your deck!");
        int counter =0;
        Factory f = new Factory();
        while(counter<20){
            counter = chooseCreature(f,deck,counter);
            if(counter<20)
                counter = chooseEnchantment(f,deck,counter);
            if(counter<20)
                counter = chooseSorcery(f,deck,counter);
            if(counter<20)
                counter = chooseInstant(f,deck,counter);
        }
        int i =0;
        System.out.println("Here is your deck:");
        for(Card c : deck){
            System.out.println("["+(++i)+"] "+ c.name());
        }
    }
}
