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
public abstract class DefaultInflictDamageStrategy implements InflictDamageStrategy {

    StrategyDecorator head;

    public DefaultInflictDamageStrategy() {
        head = new StrategyDecorator(this) {
            @Override
            public InflictDamageStrategy getHeadStrategy() {
                return this;
            }

            @Override
            public void decorateStrategy(StrategyDecorator dec) {
                dec.decoratedStrategy = this.decoratedStrategy;
                this.decoratedStrategy = dec;
            }

            @Override
            public InflictDamageStrategy removeDecoratorStrategy(StrategyDecorator dec) {
                this.decoratedStrategy = decoratedStrategy.removeDecoratorStrategy(dec);
                return this;
            }

        };
    }


    @Override
    public InflictDamageStrategy getHeadStrategy() {
        return head;
    }

    @Override
    public void decorateStrategy(StrategyDecorator dec) {
        getHeadStrategy().decorateStrategy(dec);
    }

    @Override
    public InflictDamageStrategy removeDecoratorStrategy(StrategyDecorator dec) {
        return this;
    }

}
