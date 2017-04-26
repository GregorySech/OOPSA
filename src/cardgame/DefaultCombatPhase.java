/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author atorsell
 */
public class DefaultCombatPhase implements Phase {

    /**
     * Asks the CurrentPlayer which creature will start the attack. Next there
     * is a stack fill started by the enemy of CurrentPlayer.
     *
     * @return list of attackers chosen by the currentPlayer.
     */
    protected List<Creature> attackSubPhase() {
        List<Creature> attackers = new LinkedList<>();
        List<Creature> possible = new ArrayList<>(CardGame.instance.getCurrentPlayer().getCreatures());
        Creature t;
        Scanner reader = CardGame.instance.getScanner();
        int i, lastChoice;
        for (Creature c : possible) {
            if (c.getCreatureDecoratorHead().isTapped()) {
                possible.remove(c);
            }
        }

        if (!possible.isEmpty()) {
            System.out.println("[" + CardGame.instance.getCurrentPlayer().name() + "] choose your attackers:");
            do {
                i = 0;
                for (Creature c : possible) {
                    System.out.println("[" + (++i) + "]" + c.getCreatureDecoratorHead());
                }
                System.out.println("[0] to end selection");
                lastChoice = reader.nextInt();
                if (lastChoice != 0 && lastChoice <= possible.size()) {
                    t = possible.remove(lastChoice - 1);
                    t.getCreatureDecoratorHead().attack();
                    attackers.add(t);
                }
            } while (lastChoice != 0);
        }
        CardGame.instance.getStack().fill(CardGame.instance.getPlayerID(CardGame.instance.getCurrentAdversary()));
        for (Creature a : attackers) {
            if (!CardGame.instance.getCurrentPlayer().getCreatures().contains(a)) {
                attackers.remove(a);
            }
        }
        return attackers;
    }

    /**
     * Asks the enemy of CurrentPlayer if and how the attackers will be blocked.
     * Next there is a stack fill started by CurrentPlayer.
     *
     * @param attackers list of attackers.
     * @return Map(attacker, defenders) chosen by the enemy of CurrentPlayer.
     */
    protected Map<Creature, List<Creature>> defenceSubPhase(List<Creature> attackers) {
        Creature t;

        Map<Creature, List<Creature>> atkDef = new HashMap<>();
        List<Creature> def = new ArrayList<>();

        Player adversaryPlayer = CardGame.instance.getCurrentAdversary();
        List<Creature> field = new ArrayList<>(adversaryPlayer.getCreatures());

        System.out.println(adversaryPlayer.name() + ": Choose defenders");
        Scanner reader = CardGame.instance.getScanner();

        for (Creature a : attackers) {
            System.out.println("Attacker : " + a.getCreatureDecoratorHead().name());
            int idx;
            do {
                System.out.println(adversaryPlayer.name() + " Choose a card or press 0");

                for (int i = 0; i != field.size(); ++i) {
                    System.out.println(Integer.toString(i + 1) + ") " + field.get(i).getCreatureDecoratorHead());
                }
                idx = reader.nextInt();

                if (idx > 0 && idx <= field.size()) {
                    t = field.remove(idx - 1);
                    def.add(t);
                    t.getCreatureDecoratorHead().defend(a);
                }
            } while (idx != 0);
            atkDef.put(a, def);
            def = new ArrayList<>();
        }

        CardGame.instance.getStack().fill(CardGame.instance.getPlayerID(CardGame.instance.getCurrentPlayer()));

        for (Map.Entry<Creature, List<Creature>> entry : atkDef.entrySet()) {
            if (!CardGame.instance.getCurrentPlayer().getCreatures().contains(entry.getKey())) {
                atkDef.remove(entry.getKey());
            } else {
                def = entry.getValue();
                for (Creature d : def) {
                    if (!CardGame.instance.getCurrentAdversary().getCreatures().contains(d)) {
                        def.remove(d);
                    }
                }
            }
        }
        return atkDef;
    }

    /**
     *
     * @param battles Map(attacker, defenders) each entry is a single battle. a
     * battle with no defenders will be creature vs player.
     */
    protected void damageSubPhase(Map<Creature, List<Creature>> battles) {
        Map<Creature, Integer> damage = new HashMap<>();
        CardGame.instance.getTriggers().trigger(Triggers.START_DAMAGE_SUBPHASE_FILTER);

        for (Map.Entry<Creature, List<Creature>> entry : battles.entrySet()) {
            int damagecreature = 0, attack, damagedefensor = 0;
            Creature mycreature = entry.getKey();
            List<Creature> mylist = entry.getValue();
            attack = mycreature.getCreatureDecoratorHead().getPower();
            //adv damage
            if (mylist.isEmpty()) {
                Player adversary = CardGame.instance.getCurrentAdversary();
                adversary.inflictDamage(mycreature.getCreatureDecoratorHead().getPower());
                System.out.println("Inflicting damage to adversary (life left : " + adversary.getLife() + ")");
            }
            //creature damage
            for (Creature c : mylist) {
                damagecreature = damagecreature + c.getCreatureDecoratorHead().getPower();
                System.out.println("Statistics : ["+c.getCreatureDecoratorHead().getPower()+"/"+c.getCreatureDecoratorHead().getToughness()+"]");
                System.out.println("Damage creature:" + damagecreature);
                System.out.println("Toughness creature:" + (mycreature.getCreatureDecoratorHead().getToughness() - damagecreature));
            }
            damage.put(mycreature, damagecreature);

            //defensor damage
            Iterator<Creature> c = mylist.iterator();
            Creature d;
            while (c.hasNext() && mycreature.getCreatureDecoratorHead().getPower() > 0) {
                d = c.next();
                if (mycreature.getCreatureDecoratorHead().getPower() > d.getCreatureDecoratorHead().getToughness()) {
                    damagedefensor = damagedefensor + mycreature.getCreatureDecoratorHead().getPower();
                    /* aggiornare l'attack della creatura mycreature*/
                    attack = attack - d.getCreatureDecoratorHead().getToughness();
                } else {
                    damagedefensor = damagedefensor + mycreature.getCreatureDecoratorHead().getPower() - d.getCreatureDecoratorHead().getToughness();
                    /*aggiornare l'attack della creatura*/
                    attack = attack - d.getCreatureDecoratorHead().getToughness();
                }
                damage.put(d, damagedefensor);
            }
        }
        for (Map.Entry<Creature, Integer> e : damage.entrySet()) {
            Creature a = e.getKey();
            Integer d = e.getValue();
            a.getCreatureDecoratorHead().inflictDamage(d);
        }
        CardGame.instance.getTriggers().trigger(Triggers.END_DAMAGE_SUBPHASE_FILTER);
    }

    @Override
    public void execute() {
        Player currentPlayer = CardGame.instance.getCurrentPlayer();

        System.out.println(currentPlayer.name() + ": combat phase");

        CardGame.instance.getTriggers().trigger(Triggers.COMBAT_FILTER);
        damageSubPhase(defenceSubPhase(attackSubPhase()));
    }
}
