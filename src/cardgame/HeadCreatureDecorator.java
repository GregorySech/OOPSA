/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

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
    public void addCreatureDecorator(CreatureDecorator cd) {
        cd.setDecoratedCreature(this.getDecoratedCreature());
        this.setDecoratedCreature(cd);
    }

    @Override
    public void removeCreatureDecorator(CreatureDecorator cd) {
        Creature next = this.getDecoratedCreature();
        CreatureDecorator prec = this;
        boolean flag = false;
        boolean found = false;
        while(!flag && !found){
            if(next instanceof CreatureDecorator){
                if(next == cd)
                   found = true;
                else{
                    prec = ((CreatureDecorator)next);
                    next = ((CreatureDecorator)next).getDecoratedCreature();
                }
            }else
                flag = true;
        }
        if(found){
            prec.setDecoratedCreature(((CreatureDecorator)next).getDecoratedCreature());
            ((CreatureDecorator)next).setDecoratedCreature(null);
        }
    }
    
}
