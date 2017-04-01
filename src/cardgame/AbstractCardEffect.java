/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

/**
 *
 * @author atorsell
 */
public abstract class AbstractCardEffect extends AbstractEffect {
    protected Player owner;
    protected Card card;
    
    protected AbstractCardEffect(Player p, Card c) { owner=p; card=c; }
    
    public boolean play() { 
        owner.getHand().remove(card);
        return super.play();
    }
    
    public String toString() { return card.toString(); }
}
