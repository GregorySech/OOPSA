/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

import java.util.List;

/**
 *
 * @author atorsell
 */
public interface Creature extends Permanent {

    boolean tap();

    boolean untap();

    boolean isTapped();

    void attack();

    void defend(Creature c);

    void inflictDamage(int dmg);

    void resetDamage();

    int getDamageLeft();

    void setDamageLeft(int dmg);

    int getPower();

    int getToughness();

    // returns all the effects associated to this creature
    List<Effect> effects();

    // returns only the effects that can be played currently
    // depending on state, e.g., tapped/untapped
    List<Effect> avaliableEffects();

    /*Comportamenti aggiunti necessari per l'implementazione delle carte*/
 /*Ritorna il primo decoratore (sempre presente). Necessario per assicurare comportamento
    polimorfico*/
    Creature getCreatureDecoratorHead();

    void addCreatureDecorator(CreatureDecorator cd);

    Creature removeCreatureDecorator(CreatureDecorator cd);

    boolean isDefender();

    boolean targetable();
}
