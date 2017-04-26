/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.cards;

import cardgame.AbstractCardEffect;
import cardgame.Card;
import cardgame.CardGame;
import cardgame.Effect;
import cardgame.Enchantment;
import cardgame.Player;
import cardgame.SingleTargetEffect;
import java.util.List;

/**
 *
 * @author denny
 */
public class AuraBlast implements Card {

    private class AuraBlastEffect extends AbstractCardEffect implements SingleTargetEffect{

        private Enchantment target;
        public AuraBlastEffect(Player p, Card c){
            super(p,c);
        }
        @Override
        public boolean play(){
            chooseTarget();
            return super.play();
        }

        @Override
        public void resolve(){
            if(target != null){
                   (target).remove();
                    owner.draw();
            }
            else {
                   owner.draw();
            } 
            
        }

        @Override
        public void chooseTarget() {
            int last;
            System.out.println("Aura Blast targeting phase : ");
            do{
                System.out.println("Choose the owner of the enchantment to target:");
                System.out.println("[1]" + "Player's enchantment");
                System.out.println("[2]" +"Adversary's enchantment");
                last= CardGame.instance.getScanner().nextInt();
            }while(last<1 || last >2);
            
            
            if(last==1){
                int i =0,j; 
                List <Enchantment> playerEnchantment=owner.getEnchantments();
                do{
                    System.out.println("Choose the enchantment to target:");
                    for(Enchantment c: playerEnchantment){
                        System.out.println(Integer.toString(i + 1) + ") " + playerEnchantment.get(i));
                    }
                    j=CardGame.instance.getScanner().nextInt();
                    if(j<= playerEnchantment.size()&& !playerEnchantment.isEmpty()){
                        if(playerEnchantment.get(j--).targetable()){
                            target=playerEnchantment.get(j--);}
                        else{
                            System.out.println("This enchantment can't be targeted"); 
                            j=-1;
                        }
                    }
                    else 
                        target=null; 
                } while(j==-1);
            }
            else{
                int i=0, j;
                List <Enchantment> adversaryEnchantment=CardGame.instance.getRival(owner).getEnchantments();
                do{
                    System.out.println("Choose the enchantment to target:");
                    for(Enchantment c: adversaryEnchantment){
                        System.out.println(Integer.toString(i + 1) + ") " + adversaryEnchantment.get(i));
                    }
                    j=CardGame.instance.getScanner().nextInt();
                    if(j<= adversaryEnchantment.size() && !adversaryEnchantment.isEmpty()){
                        if(adversaryEnchantment.get(j--).targetable()){
                            target=adversaryEnchantment.get(j--);}
                        else{
                            System.out.println("This enchantment can't be targeted"); 
                            j=-1;
                        }
                    }
                    else {
                        target=null;}
                } while(j==-1);
            }
                
            
        }
         

        @Override
        public Object getTarget() {
            return target;
        }
    }
    @Override
    public Effect getEffect(Player owner) {
        return new AuraBlastEffect(owner, this);
    }

    @Override
    public String name() {
        return "Aura Blast";
    }

    @Override
    public String type() {
        return "Instant";
    }

    @Override
    public String ruleText() {
        return "Destroy target enchantment.\n" + "Draw a card.";
    }

    @Override
    public boolean isInstant() {
        return true;
    }

    @Override
    public String toString() {
        return name() + " (" + type() + ") [" + ruleText() + "]";
    }
}
