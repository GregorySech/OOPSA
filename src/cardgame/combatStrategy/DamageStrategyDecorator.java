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
 * Decoratore della strategy della sottofase dei danni.
 * @author gregory
 */
public class DamageStrategyDecorator implements DamageStrategy {

    protected DamageStrategy ds;

    @Override
    public void damageSubPhase(Map<Creature, List<Creature>> battles) {
        ds.damageSubPhase(battles);
    }

    @Override
    public DamageStrategy getFirst() {
        return ds.getFirst();
    }

    @Override
    public void decorate(DamageStrategyDecorator dsd) {
        getFirst().decorate(dsd);
    }

    @Override
    public DamageStrategy removeDecorator(DamageStrategyDecorator dsd) {
        if(this == dsd)
            return ds;
        else{
            ds = ds.removeDecorator(dsd);
            return this;
        }
    }
    
    
    
    
}
