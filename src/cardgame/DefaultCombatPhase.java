/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 *
 * @author atorsell
 */
public class DefaultCombatPhase implements Phase {
    /**
     * Asks the CurrentPlayer which creature will start the attack. 
     * Next there is a stack fill started by the enemy of CurrentPlayer.
     * @return list of attackers chosen by the currentPlayer. 
     */
    protected List<Creature> attackSubPhase(){
        List<Creature> attackers = new LinkedList<>();
        List<Creature> possible = new ArrayList<>(CardGame.instance.getCurrentPlayer().getCreatures());
        Scanner reader = CardGame.instance.getScanner();
        int i, lastChoice = 0;
        for(Creature c : possible)
            if(c.isTapped())
                possible.remove(c);
        System.out.println("["+CardGame.instance.getCurrentPlayer().name()
                + "]"
                + " choose your attackers:");
        do{
            i = 0;
            lastChoice = 0;
            
            for(Creature c : possible){
                System.out.println("["+(++i)+"]"+c.toString());
            }
            
            System.out.println("[0] to end selection");
            lastChoice = reader.nextInt();
            if(lastChoice != 0)
                attackers.add(possible.remove(lastChoice-1));
        }while(lastChoice!=0);
        if(attackers.size()>0){
            CardGame.instance.getStack().fill(CardGame.instance.getPlayerID(CardGame.instance.getCurrentAdversary()));
            for(Creature a : attackers){
                if(!CardGame.instance.getCurrentPlayer().getCreatures().contains(a))
                    attackers.remove(a);
            }
        }
        return attackers;
    }
    
    /**
     * Asks the enemy of CurrentPlayer if and how the attackers will be blocked.
     * Next there is a stack fill started by CurrentPlayer.
     * @param attackers list of attackers.
     * @return Map(attacker, defenders) chosen by the enemy of CurrentPlayer.
     */
    protected Map<Creature, List<Creature>> defenceSubPhase(List<Creature> attackers){
        return null;
    }
    /**
     * 
     * @param battles Map(attacker, defenders) each entry is a single battle. 
     * a battle with no defenders will be creature vs player.
     */
    protected void damageSubPhase(Map<Creature, List<Creature>> battles){
        Map<Creature, Integer> damage = new TreeMap<>();
        for(Map.Entry<Creature, List<Creature>> entry : battles.entrySet()){
            int damagecreature=0,attack, damagedefensor=0;
            Creature mycreature=entry.getKey();
            List<Creature> mylist=entry.getValue();
            attack=mycreature.getPower();
            //adv damage
           if(mylist.isEmpty()){
                System.out.println("Inflicting damage to adversary");
                Player adversary=CardGame.instance.getCurrentAdversary();
                adversary.inflictDamage(mycreature.getPower());
           }
            //creature damage
            for (Creature c: mylist) {
               damagecreature= damagecreature +c.getPower();
               System.out.println("Damage creature:"+damagecreature);
               System.out.println("Toughness creature:"+ (mycreature.getToughness()-damagecreature));
            }
            damage.put(mycreature, damagecreature);
            
           //defensor damage
        
         Iterator <Creature> c= mylist.iterator();
         while(c.hasNext() && mycreature.getPower()>0 ){
             if(mycreature.getPower() > c.next().getToughness()){
                   damagedefensor=damagedefensor+mycreature.getPower();
                  /* aggiornare l'attack della creatura mycreature*/
                  attack=attack-c.next().getToughness();
               }
             else {
                damagedefensor=damagedefensor+mycreature.getPower()-c.next().getToughness();
                 /*aggiornare l'attack della creatura*/
                 attack=attack-c.next().getToughness();
             }
            damage.put(c.next(), damagedefensor);
         }
        }

       for(Map.Entry<Creature,Integer> e : damage.entrySet()){
           Creature a=e.getKey();
           Integer d=e.getValue();
           a.inflictDamage(d);
       }
      
      }
    
    @Override
    public void execute() {
        Player currentPlayer = CardGame.instance.getCurrentPlayer();
        
        System.out.println(currentPlayer.name() + ": combat phase");
        
        CardGame.instance.getTriggers().trigger(Triggers.COMBAT_FILTER);
        damageSubPhase(defenceSubPhase(attackSubPhase()));
    }
}
