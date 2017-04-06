/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

import java.util.ArrayDeque;
import java.util.ArrayList;

/**
 *
 * @author atorsell
 */
public class Triggers {
    private class Entry { 
        public int filter; 
        public TriggerAction action;
        public Entry(int f, TriggerAction a) { filter=f; action=a; }
        
    }
    
    ArrayList<Triggers.Entry> actions = new ArrayList<Triggers.Entry>();
    
    
    public void register(int phaseTrigger, TriggerAction a) {
        actions.add(new Triggers.Entry(phaseTrigger, a));
    }
    
    public void deregister(TriggerAction a) {
        for(int i=actions.size(); i>=0; --i) {
            if (a.equals(actions.get(i))) actions.remove(i);
        }
    }
    
    
    public void trigger(int event) { trigger(event, null); }
    public void trigger(int event, Object args) {
        ArrayDeque<Triggers.Entry> triggerableActions = new ArrayDeque<>();
        
        for (Triggers.Entry p:actions) {
            if ((p.filter & event)!=0) triggerableActions.push(p);
        }
        
        //execute last-inserted-first
        while (!triggerableActions.isEmpty()) {
            Triggers.Entry entry=triggerableActions.pop();
            entry.action.execute(args);
        }
    }
    
    public static final int DRAW_FILTER=1;
    public static final int UNTAP_FILTER=2;
    public static final int COMBAT_FILTER=4;
    public static final int MAIN_FILTER=8;
    public static final int END_FILTER=16;
    public static final int ENTER_CREATURE_FILTER=32;
    public static final int EXIT_CREATURE_FILTER=64;
    public static final int ENTER_ENCHANTMENT_FILTER=128;
    public static final int EXIT_ENCHANTMENT_FILTER=256;
    
}
