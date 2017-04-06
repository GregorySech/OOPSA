/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

import cardgame.interfaces.Effect;

/**
 *
 * @author atorsell
 */
public abstract class AbstractEffect implements Effect {
    @Override
    public boolean play() { 
        CardGame.instance.getStack().add(this);
        return true;
    }
}
