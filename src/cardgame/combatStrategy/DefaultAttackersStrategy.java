/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.combatStrategy;

import cardgame.CardGame;
import cardgame.Creature;
import cardgame.Triggers;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author gregory
 */
public class DefaultAttackersStrategy implements AttackersStrategy {

    public List<Creature> attackSubPhase() {//Fase di deafault dlla scelta degli attaccanti
        List<Creature> attackers = new LinkedList<>();
        List<Creature> possible = new ArrayList<>();
        Creature t;
        Scanner reader = CardGame.instance.getScanner();
        int i, lastChoice;
        for (Creature c : CardGame.instance.getCurrentPlayer().getCreatures()) {
            if (!c.isTapped() && !c.isDefender()) {
                possible.add(c);
            }
        }

        if (!possible.isEmpty()) {
            System.out.println("[" + CardGame.instance.getCurrentPlayer().name() + "] choose your attackers:");
            do {
                i = 0;
                for (Creature c : possible) {
                    System.out.println("[" + (++i) + "]" + c);
                }
                System.out.println("[0] to end selection");
                lastChoice = reader.nextInt();
                if (lastChoice != 0 && lastChoice <= possible.size()) {
                    t = possible.remove(lastChoice - 1);
                    t.attack();
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
        CardGame.instance.getTriggers().trigger(Triggers.ATTACKER_FILTER, attackers);
        return attackers;
    }

    @Override
    public AttackersStrategy getFirst() {
        return this;
    }

}
