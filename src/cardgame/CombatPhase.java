/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

import cardgame.combatStrategy.AttackersStrategy;
import cardgame.combatStrategy.DamageStrategy;
import cardgame.combatStrategy.DefendersStrategy;

/**
 * Definito un sottotipo di Phase per la Combat dato che ospita una
 * implemntazione parziale del pattern Strategy.
 * Implementazione non completa del pattern, tuttavia DamageStrategy è decorabile.
 *
 * @author Gregory Sech
 */
public interface CombatPhase extends Phase {

    DamageStrategy getDamageStrategy();

    AttackersStrategy getAttackersStrategy();

    DefendersStrategy getDefendersStrategy();
}
