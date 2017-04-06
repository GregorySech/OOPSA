/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

import java.util.Deque;
import java.util.EnumMap;

/**
 *
 * @author atorsell
 */
public class DefaultPhaseManager implements PhaseManager {
    private final EnumMap<Phases, Deque<Phase> > phases;
    private Phases currentPhaseIdx=Phases.NULL;
    
    public DefaultPhaseManager(EnumMap<Phases, Deque<Phase> > p) {phases=p;}
    
    
    @Override
    public Phases currentPhase() { return currentPhaseIdx; }
    
    @Override
    public Phases nextPhase() { 
        currentPhaseIdx = currentPhaseIdx.next();
        return currentPhase();
    }
}
