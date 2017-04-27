/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.playerDamageStrategy;



/**
 *
 * @author gregory
 */
public interface InflictDamageStrategy {
    void inflictDamage(int dmg);
    InflictDamageStrategy getHeadStrategy();
    void decorateStrategy(StrategyDecorator dec);
    InflictDamageStrategy removeDecoratorStrategy(StrategyDecorator dec);
}
