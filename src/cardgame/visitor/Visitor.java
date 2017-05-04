/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.visitor;

import cardgame.Creature;
import cardgame.Enchantment;
import cardgame.Player;

/**
 *
 * @author Gregory Sech
 */
public interface Visitor {

    void visit(Creature c);

    void visit(Player p);

    void visit(Enchantment e);
}
