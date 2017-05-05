package cardgame;

import cardgame.combatStrategy.AttackersStrategy;
import cardgame.combatStrategy.DamageStrategy;
import cardgame.combatStrategy.DefendersStrategy;

/**
 * Definito un sottotipo di Phase per la Combat dato che ospita una
 * Implementazione non pienamente utilizzata del pattern Strategy tuttavia
 * DamageStrategy è decorabile. Le altre strategy non sono pronte per essere decorate.
 *
 * @author Gregory Sech
 */
public interface CombatPhase extends Phase {

    //Strategy della sottofase dei danni.
    DamageStrategy getDamageStrategy();

    void setDamageStrategy(DamageStrategy s);

    //Strategy della sottofase degli attaccanti.
    AttackersStrategy getAttackersStrategy();

    void setAttackersStrategy(AttackersStrategy as);

    //Strategy della sottofase dei difensori.
    DefendersStrategy getDefendersStrategy();

    void setDefendersStrategy(DefendersStrategy ds);
}
