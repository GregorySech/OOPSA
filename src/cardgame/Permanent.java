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
public interface Permanent extends Visitable {

    String name();

    void insert();

    void remove();

    boolean targetable();

    void changeOwner(Player p);
}
