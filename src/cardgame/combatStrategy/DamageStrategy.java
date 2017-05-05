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
 * Strategy DECORABILE per la sottofase dei danni
 * @author gregory
 */
public interface DamageStrategy {

    void damageSubPhase(Map<Creature, List<Creature>> battles);

    DamageStrategy getFirst();

    void decorate(DamageStrategyDecorator dsd);

    DamageStrategy removeDecorator(DamageStrategyDecorator dsd);
}
