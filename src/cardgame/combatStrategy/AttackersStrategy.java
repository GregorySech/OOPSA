/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.combatStrategy;

import cardgame.Creature;
import java.util.List;

/**
 *
 * @author gregory
 */
public interface AttackersStrategy {
        List<Creature> attackSubPhase();
        AttackersStrategy getFirst();
}
