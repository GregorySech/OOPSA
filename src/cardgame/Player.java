/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

import java.util.Iterator;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.EnumMap;
import java.util.List;
import java.util.Scanner;


/**
 *
 * @author atorsell
 */
public class Player {
    // basic properties: name, library, deck, and life
    private String name;
    public String name() {return name;}
    @Override
    public String toString() {return name();}
    public void setName(String n) {name=n;}
    
    private final Library library = new Library(this);

    public void setDeck(Iterator<Card> deck) { library.add(deck); }
    public Library getDeck() { return library; }
    
    
    
    private int life=10;
    public int getLife() {return life;}
    
    // need to attach strategy/decorator
    public void inflictDamage(int pts) {
        life -= pts;
        if (life <=0) lose("received fatal damage");
    }
    
    public void heal(int pts) { life += pts; }
            
            
    // player looses. might need strategy/decorator
    public void lose(String s) { throw new EndOfGame(name + " lost the game: "+ s); }     
    
    
    
    public Player() {
        phaseManagerStack.push(new DefaultPhaseManager(phases));
        
        phases.put(Phases.DRAW, new ArrayDeque<Phase>());
        setPhase(Phases.DRAW, new DefaultDrawPhase());
        
        phases.put(Phases.UNTAP, new ArrayDeque<Phase>());
        setPhase(Phases.UNTAP, new DefaultUntapPhase());
        
        phases.put(Phases.COMBAT, new ArrayDeque<Phase>());
        setPhase(Phases.COMBAT, new DefaultCombatPhase());
        
        phases.put(Phases.MAIN, new ArrayDeque<Phase>());
        setPhase(Phases.MAIN, new DefaultMainPhase());
        
        phases.put(Phases.END, new ArrayDeque<Phase>());
        setPhase(Phases.END, new DefaultEndPhase());
        
        phases.put(Phases.NULL, new ArrayDeque<Phase>());
    }
    
    
    
    void executeTurn() {
        System.out.println(name() + "'s turn");
        
        // print out the fields
        System.out.println("=== Field ===");
        for (int i=0; i!=2; ++i) {
            Player fieldsPlayer=CardGame.instance.getPlayer(i);
            List<Creature> creatures = fieldsPlayer.getCreatures();
            if (creatures.isEmpty()) {
                System.out.println(fieldsPlayer.name() + " has no creature in play");
            } else {
                System.out.println(fieldsPlayer.name() + "'s creatures in play:");
                for (Creature c:creatures)
                    System.out.println("  "+c);
            }
            List<Enchantment> enchantments = fieldsPlayer.getEnchantments();
            if (enchantments.isEmpty()) {
                System.out.println(fieldsPlayer.name() + " has no enchantment in play");
            } else {
                System.out.println(fieldsPlayer.name() + "'s enchantments in play:");
                for (Enchantment e:enchantments)
                    System.out.println("  "+e);
            }
        }
        System.out.println("=============");        
        
        Phase curPhase;
        while ((curPhase=nextPhase())!=null) {
            curPhase.execute();
        }
    }
    
    
    
    
    // phase management
    
    // phases maps a phaseID to a stack of phase implemenations
    // top one is active
    private final EnumMap<Phases, Deque<Phase> > phases = new EnumMap<>(Phases.class);
    public Phase getPhase(Phases p) { return phases.get(p).peek(); }
    
    // set to final because it is called in constructor
    public final void setPhase(Phases id, Phase p) { phases.get(id).push(p); }
    public void removePhase(Phases id, Phase p) { phases.get(id).remove(p); }
    
    // 
    private final Deque<PhaseManager> phaseManagerStack = new ArrayDeque<>();
    public void setPhaseManager(PhaseManager m) { phaseManagerStack.push(m); }
    public void removePhaseManager(PhaseManager m) { phaseManagerStack.remove(m); }
    Phases currentPhaseId() { return phaseManagerStack.peek().currentPhase(); }
    Phase nextPhase() { return getPhase(phaseManagerStack.peek().nextPhase()); }
 
    
    
    
    
    
     // hand management
    private final ArrayList<Card> hand = new ArrayList<>();
    private int maxHandSize=7;
    
    public List<Card> getHand() { return hand; }
    public int getMaxHandSize() { return maxHandSize; }
    public void setMaxHandSize(int size) { maxHandSize=size; }
    
    public void draw() {
        Card drawn = library.draw();
        System.out.println(name()+" draw card: " + drawn.name());
        hand.add(drawn);
    }
    
    public void selectDiscard() {
        Scanner reader = CardGame.instance.getScanner();
        
        System.out.println(name()+" Choose a card to discard");
        for(int i=0; i!=hand.size(); ++i) {
            System.out.println(Integer.toString(i+1)+") " + hand.get(i) );
        }
        int idx= reader.nextInt()-1;
        if (idx>=0 && idx<hand.size())
            hand.remove(idx);         
    }
    
    
    // Creature management
    private final ArrayList<Creature> creatures = new ArrayList<>();
    public List<Creature> getCreatures() {return creatures;}
    // destroy a creature in play
    public void destroy(Creature c) {creatures.remove(c);} 
    
    
    
    // Enchantments management
    private final ArrayList<Enchantment> enchantments = new ArrayList<>();
    public List<Enchantment> getEnchantments() {return enchantments;}
    // destroy a creature in play
    public void destroy(Enchantment c) {enchantments.remove(c);} 
}
