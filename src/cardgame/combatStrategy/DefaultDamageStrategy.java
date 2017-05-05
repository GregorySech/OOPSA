/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.combatStrategy;

import cardgame.CardGame;
import cardgame.Creature;
import cardgame.Player;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 *
 * @author jona
 */
public class DefaultDamageStrategy implements DamageStrategy {

    private DamageStrategyDecorator head;//è decorabile serve un dummy decorator

    public DefaultDamageStrategy() {
        head = new DamageStrategyDecorator(){
            @Override
            public void decorate(DamageStrategyDecorator dsd) {
                dsd.ds = this.ds;
                this.ds = dsd;
            }
            
            DamageStrategyDecorator init(DamageStrategy ds){
                this.ds = ds;
                return this;
            }
        }.init(this);
    }

    public void damageSubPhase(Map<Creature, List<Creature>> battles) {
        Map<Creature, Integer> damage = new HashMap<>();

        for (Map.Entry<Creature, List<Creature>> entry : battles.entrySet()) {
            int damagecreature = 0, attack, damagedefensor = 0;
            Creature mycreature = entry.getKey();
            List<Creature> mylist = entry.getValue();
            attack = mycreature.getPower();
            //adv damage
            if (mylist.isEmpty()) {
                Player adversary = CardGame.instance.getCurrentAdversary();
                adversary.inflictDamage(mycreature.getPower());
                System.out.println("Inflicting damage to adversary (life left : " + adversary.getLife() + ")");
            }
            //creature damage
            for (Creature c : mylist) {
                damagecreature = damagecreature + c.getPower();
                System.out.println("Statistics : [" + c.getPower() + "/" + c.getToughness() + "]");
                System.out.println("Damage creature:" + damagecreature);
                System.out.println("Toughness creature:" + (mycreature.getToughness() - damagecreature));
            }
            damage.put(mycreature, damagecreature);

            //defensor damage
            Iterator<Creature> c = mylist.iterator();
            Creature d;
            while (c.hasNext() && mycreature.getPower() > 0) {
                d = c.next();
                if (mycreature.getPower() > d.getToughness()) {
                    damagedefensor = damagedefensor + mycreature.getPower();
                    /* aggiornare l'attack della creatura mycreature*/
                    attack = attack - d.getToughness();
                } else {
                    damagedefensor = damagedefensor + mycreature.getPower() - d.getToughness();
                    /*aggiornare l'attack della creatura*/
                    attack = attack - d.getToughness();
                }
                damage.put(d, damagedefensor);
            }
        }
        for (Map.Entry<Creature, Integer> e : damage.entrySet()) {
            Creature a = e.getKey();
            Integer d = e.getValue();
            a.inflictDamage(d);
        }
    }

    @Override
    public DamageStrategy getFirst() {
        return head;
    }

    @Override
    public void decorate(DamageStrategyDecorator dsd) {
        head.decorate(dsd);
    }

    @Override
    public DamageStrategy removeDecorator(DamageStrategyDecorator dsd) {
        return head.removeDecorator(dsd);
    }

}
