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

    private static int chooseCreature(CreatureFactory f,ArrayList<Card> deck,int counter){
            
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
                    case 1: deck.add(f.getCard("Argothian Enchantress"));
                            counter++;
                            break;
                    case 2: deck.add(f.getCard("Benevolent Ancestor"));
                            counter++;
                            break;
                    case 3: deck.add(f.getCard("Bronze Sable"));
                            counter++;
                            break;
                    case 4: deck.add(f.getCard("Norwood Ranger"));
                            counter++;
                            break;
                    default : System.out.println("Index not supported");
                }
                if(i!=0)
                    System.out.println("You have choosen "+counter+"/20 cards");
            }
            return counter;
    }
    private static int chooseEnchantment(EnchantmentFactory f,ArrayList<Card> deck,int counter){
            
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
                    case 1: deck.add(f.getCard("Abduction"));
                            counter++;
                            break;
                    case 2: deck.add(f.getCard("Aether Barrier"));
                            counter++;
                            break;
                    case 3: deck.add(f.getCard("Aether Flash"));
                            counter++;
                            break;
                    case 4: deck.add(f.getCard("Ancestral Mask"));
                            counter++;
                            break;
                    default : System.out.println("Index not supported");
                }
                if(i!=0)
                    System.out.println("You have choosen "+counter+"/20 cards");
            }
            return counter;
    }
    private static int chooseSorcery(SorceryFactory f,ArrayList<Card> deck,int counter){
            
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
                    case 1: deck.add(f.getCard("Boiling Earth"));
                            counter++;
                            break;
                    case 2: deck.add(f.getCard("Calming Verse"));
                            counter++;
                            break;
                    case 3: deck.add(f.getCard("Day of Judgment"));
                            counter++;
                            break;
                    case 4: deck.add(f.getCard("False Peace"));
                            counter++;
                            break;
                    case 5: deck.add(f.getCard("Fatigue"));
                            counter++;
                            break;
                    case 6: deck.add(f.getCard("Savor the Moment"));
                            counter++;
                            break;
                    case 7: deck.add(f.getCard("Volcanic Hammer"));
                            counter++;
                            break;
                    case 8: deck.add(f.getCard("World at War"));
                            counter++;
                            break;
                    default : System.out.println("Index not supported");
                }
                if(i!=0)
                    System.out.println("You have choosen "+counter+"/20 cards");
            }
            return counter;
    }
    private static int chooseInstant(InstantFactory f,ArrayList<Card> deck,int counter){
            
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
                    case 1: deck.add(f.getCard("Afflict"));
                            counter++;
                            break;
                    case 2: deck.add(f.getCard("Aggressive Urge"));
                            counter++;
                            break;
                    case 3: deck.add(f.getCard("Aura Blast"));
                            counter++;
                            break;
                    case 4: deck.add(f.getCard("Cancel"));
                            counter++;
                            break;
                    case 5: deck.add(f.getCard("Darkness"));
                            counter++;
                            break;
                    case 6: deck.add(f.getCard("Deflection"));
                            counter++;
                            break;
                    default : System.out.println("Index not supported");
                }
                if(i!=0)
                    System.out.println("You have choosen "+counter+"/20 cards");
            }
            return counter;
    }
    
    public static void createDeck(ArrayList<Card> deck, int p){
        System.out.println("Player "+p+" create your deck!");
        int counter =0;
        while(counter<20){
            counter = chooseCreature(new CreatureFactory(),deck,counter);
            if(counter<20)
                counter = chooseEnchantment(new EnchantmentFactory(),deck,counter);
            if(counter<20)
                counter = chooseSorcery(new SorceryFactory(),deck,counter);
            if(counter<20)
                counter = chooseInstant(new InstantFactory(),deck,counter);
        }
        int i =0;
        System.out.println("Here is your deck:");
        for(Card c : deck){
            System.out.println("["+(++i)+"] "+ c.name());
        }
    }
}
