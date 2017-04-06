/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.interfaces;

import cardgame.time.Phases;

/**
 *
 * @author atorsell
 */
public interface PhaseManager {
    Phases currentPhase();
    Phases nextPhase(); 
}
