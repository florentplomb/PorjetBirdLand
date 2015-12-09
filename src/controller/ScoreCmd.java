/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import DataBaseManager.DataBaseController;
import model.Player;
import view.ScoreView;

/**
 * 
 *@author Florent Plomb <plombf at gmail.com>
 */
class ScoreCmd extends Command{

    public ScoreCmd() {
       
    }

    @Override
    public boolean execute(Player player) {
         clearOutputString();
        if (getSecondWord() == null) {
              new ScoreView();
            appendToOutputString("Score table");
            return false;
        } else {
            appendToOutputString("I cannot quit that...");
            return false;
        }
    }
}
