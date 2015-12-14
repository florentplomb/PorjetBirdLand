/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import model.Player;
import java.util.Set;


/**
 * Implementai
 *@author Florent Plomb <plombf at gmail.com>
 */
public class LookCmd extends Command{

    public LookCmd() {
    }
    
      public boolean execute(Player player) {
         
        clearOutputString();     
        appendToOutputString("You are +" +player.getCurrentRoom().getDescription());
         

        return false;
    }

}
