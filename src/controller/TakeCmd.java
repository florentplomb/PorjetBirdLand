/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.Player;
import model.Room;

/**
 *
 * @author Florent Plomb <plombf at gmail.com>
 */
public class TakeCmd extends Command {

    public TakeCmd() {
    }

    /*
     * Goes to the direction which is entered as the second word. If there is no
     * exit for that direction, prints an error message. Returns always *false*.
     * Take a object   
     */
    public boolean execute(Player player) {

        clearOutputString();
        if (hasSecondWord()) {
            String nameItem =  getSecondWord();
          
        //    Item item = player.getCurrentRoom(). 
        }
        return false;
    }
}
