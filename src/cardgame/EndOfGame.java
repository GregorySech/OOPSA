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
public class EndOfGame extends RuntimeException {
    public EndOfGame() {}

    public EndOfGame(String msg) {
        super(msg);
    }
}
