/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

/**
 * Interfaccia per modellare effetti con un singolo bersaglio. Molto utile per
 * Deflection.
 *
 * @author gsech
 */
public interface SingleTargetEffect extends Effect {

    void chooseTarget();

}
