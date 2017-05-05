/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.combatStrategy;

import cardgame.CardGame;
import cardgame.Creature;
import cardgame.Player;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author denny
 */
public class DefaultDefendersStrategy implements DefendersStrategy {

    public Map<Creature, List<Creature>> defenceSubPhase(List<Creature> attackers) {
        Creature t;

        Map<Creature, List<Creature>> atkDef = new HashMap<>();
        List<Creature> def = new ArrayList<>();

        Player adversaryPlayer = CardGame.instance.getCurrentAdversary();
        List<Creature> field = new ArrayList<>(adversaryPlayer.getCreatures());
        if (!attackers.isEmpty()) {
            System.out.println(adversaryPlayer.name() + ": Choose defenders");
            Scanner reader = CardGame.instance.getScanner();

            for (Creature a : attackers) {
                System.out.println("Attacker : " + a.name());
                int idx;
                do {
                    System.out.println(adversaryPlayer.name() + " Choose a card or press 0");

                    for (int i = 0; i != field.size(); ++i) {
                        System.out.println(Integer.toString(i + 1) + ") " + field.get(i));
                    }
                    idx = reader.nextInt();

                    if (idx > 0 && idx <= field.size()) {
                        t = field.remove(idx - 1);
                        def.add(t);
                        t.defend(a);
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
        }
        return atkDef;
    }

    @Override
    public DefendersStrategy getFirst() {
        return this;
    }

}
