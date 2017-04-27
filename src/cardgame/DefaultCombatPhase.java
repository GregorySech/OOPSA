/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

import cardgame.combatStrategy.AttackersStrategy;
import cardgame.combatStrategy.DamageStrategy;
import cardgame.combatStrategy.DefaultAttackersStrategy;
import cardgame.combatStrategy.DefaultDamageStrategy;
import cardgame.combatStrategy.DefaultDefendersStrategy;
import cardgame.combatStrategy.DefendersStrategy;
import java.util.List;
import java.util.Map;
/**
 *
 * @author atorsell
 */
public class DefaultCombatPhase implements Phase {

    private DamageStrategy ds;
    private AttackersStrategy as;
    private DefendersStrategy defs;

    public DefaultCombatPhase() {
        ds = new DefaultDamageStrategy();
        as = new DefaultAttackersStrategy();
        defs = new DefaultDefendersStrategy();
    }
    
    /**
     * Asks the CurrentPlayer which creature will start the attack. Next there
     * is a stack fill started by the enemy of CurrentPlayer.
     *
     * @return list of attackers chosen by the currentPlayer.
     */
    protected List<Creature> attackSubPhase() {
        return as.getFirst().attackSubPhase();
    }

    /**
     * Asks the enemy of CurrentPlayer if and how the attackers will be blocked.
     * Next there is a stack fill started by CurrentPlayer.
     *
     * @param attackers list of attackers.
     * @return Map(attacker, defenders) chosen by the enemy of CurrentPlayer.
     */
    protected Map<Creature, List<Creature>> defenceSubPhase(List<Creature> attackers) {
        return defs.getFirst().defenceSubPhase(attackers);
    }

    /**
     *
     * @param battles Map(attacker, defenders) each entry is a single battle. a
     * battle with no defenders will be creature vs player.
     */
    protected void damageSubPhase(Map<Creature, List<Creature>> battles) {
        ds.getFirst().damageSubPhase(battles);
    }

    public DamageStrategy getDamageStrategy() {
        return ds;
    }

    public AttackersStrategy getAttackersStrategy() {
        return as;
    }

    public DefendersStrategy getDefendersStrategy() {
        return defs;
    }

    
    @Override
    public void execute() {
        Player currentPlayer = CardGame.instance.getCurrentPlayer();

        System.out.println(currentPlayer.name() + ": combat phase");

        CardGame.instance.getTriggers().trigger(Triggers.COMBAT_FILTER);
        damageSubPhase(defenceSubPhase(attackSubPhase()));
    }
}
