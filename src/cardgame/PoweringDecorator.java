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
public class PoweringDecorator extends CreatureDecorator{
   
    private int powerAdded;
    private int toughnessAdded;
    
    public PoweringDecorator(Creature decoratore,int powerAdded,int toughnessAdded){
        super(decoratore);
        this.powerAdded=powerAdded;
        this.toughnessAdded=toughnessAdded;
    }
    
    @Override
    public int getPower(){ return c.getPower()+powerAdded; }
    
    @Override
    public int getToughness(){ return c.getToughness()+toughnessAdded; }
    
    
}
