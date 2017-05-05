/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.combatStrategy;

import cardgame.Creature;
import java.util.List;
import java.util.Map;

/**
 *
 * @author gregory
 */
public interface DefendersStrategy { //Strategy sottofase difensori

    Map<Creature, List<Creature>> defenceSubPhase(List<Creature> attackers);

    DefendersStrategy getFirst();
}
