/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.damageStrategy;

/**
 *
 * @author gregory
 */
public abstract class StrategyDecorator implements InflictDamageStrategy {

    protected InflictDamageStrategy decoratedStrategy;

    public StrategyDecorator(InflictDamageStrategy decoratedStrategy) {
        this.decoratedStrategy = decoratedStrategy;
    }

    @Override
    public void inflictDamage(int dmg) {
        decoratedStrategy.inflictDamage(dmg);
    }

    @Override
    public InflictDamageStrategy getHeadStrategy() {
        return decoratedStrategy.getHeadStrategy();
    }

    @Override
    public void decorateStrategy(StrategyDecorator dec) {
        getHeadStrategy().decorateStrategy(dec);
    }

    @Override
    public InflictDamageStrategy removeDecoratorStrategy(StrategyDecorator dec) {
        if (this == dec) {
            return decoratedStrategy;
        } else {
            decoratedStrategy = decoratedStrategy.removeDecoratorStrategy(dec);
            return this;
        }
    }

}
