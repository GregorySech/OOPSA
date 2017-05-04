/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

import cardgame.visitor.Visitor;

/**
 *
 * @author Elena
 */
public class HeadCreatureDecorator extends CreatureDecorator {

    public HeadCreatureDecorator(Creature c) {
        super(c);
    }

    @Override
    public Creature getCreatureDecoratorHead() {
        return this;
    }

    @Override
    public int getPower() {
        return decoratedCreature.getPower() < 0 ? 0 : decoratedCreature.getPower();
    }

    @Override
    public void addCreatureDecorator(CreatureDecorator cd) {
        cd.decoratedCreature = this.decoratedCreature;
        this.decoratedCreature = cd;
    }

    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void accept(Visitor cpv) {
        cpv.visit(this);
    }
    
}
