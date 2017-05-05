/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

import cardgame.visitor.Visitable;

/**
 *
 * @author atorsell
 */
public interface Permanent extends Visitable {//Aggiunta interfaccia Visitable per pattern Visitor.

    String name();

    void insert();

    void remove();

    /**
     * 
     * @return true se il permanente è bersagliabile, false altrimenti.
     */
    boolean targetable();

    /**
     * 
     * @param p fa passare il permanente dal proprietario al giocatore p.
     */
    void changeOwner(Player p);
}
