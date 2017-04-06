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
public enum Phases {
    DRAW ("draw"), UNTAP ("untap"), COMBAT ("combat"), MAIN ("main"), END ("end"), NULL ("null");
    private static final Phases[] vals = values();
    private final String name;
    Phases(String n) { this.name=n; }
    public String getName() { return name; }
    public Phases next() { return vals[(this.ordinal()+1) % vals.length]; }
    public Phases prev() { return vals[(this.ordinal()+vals.length-1) % vals.length]; }
    public int getIdx() { return this.ordinal(); }
    public static Phases idx(int i) { return vals[i]; }
    
}
