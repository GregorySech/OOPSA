/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.cardFactories;

import cardgame.Card;
import cardgame.CardGame;
import java.util.ArrayList;

/**
 *
 * @author Elena
 */
public abstract class DeckFactory {

    private static int chooseCard(CardFactory f,ArrayList<Card> deck,int counter){
            
            int i=0;
            System.out.println(" ([0] to end selection)");
            
            ArrayList<String> avaibleCreatures = new ArrayList<>(f.getAvaibleCards());
            for(String s : avaibleCreatures){
                System.out.println("["+(++i)+"] "+ s);
            }
            
            i=1;
            while(i!=0 && counter<20){
                i = CardGame.instance.getScanner().nextInt();
                if(i<0 || i>f.getAvaibleCards().size())
                    System.out.println("Index not supported");
                else{
                    if(i!=0){
                        System.out.println(f.getAvaibleCards().get(i-1)+" added");
                        deck.add(f.getCard(f.getAvaibleCards().get(i-1)));
                        counter++;
                        System.out.println("You have choosen "+counter+"/20 cards");
                    }
                }
            }
            return counter;
    }
    
    public static void createDeck(ArrayList<Card> deck, int p){
        System.out.println("Player "+p+" create your deck!");
        int counter =0;
        while(counter<20){
            System.out.print("- Choose your creatures ");
            counter = chooseCard(new CreatureFactory(),deck,counter);
            if(counter<20){
                System.out.print("- Choose your enchantments ");
                counter = chooseCard(new EnchantmentFactory(),deck,counter);
            }
            if(counter<20){
                System.out.print("- Choose your sorceries ");
                counter = chooseCard(new SorceryFactory(),deck,counter);
            }
            if(counter<20){
                System.out.print("- Choose your instants ");
                counter = chooseCard(new InstantFactory(),deck,counter);
            }
        }
        int i =0;
        System.out.println("Here is your deck:");
        for(Card c : deck){
            System.out.println("["+(++i)+"] "+ c.name());
        }
    }
}
