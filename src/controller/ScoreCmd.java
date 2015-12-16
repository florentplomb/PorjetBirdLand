/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.Player;
import view.ScoreView;

/**
 * Implementation of *Score* Command.
 *
 * @author Florent Plomb <plombf at gmail.com>
 */
class ScoreCmd extends Command {

    public ScoreCmd() {

    }

    /**
     * Allows the players to display the score view
     *
     * @param player the current player
     * @return always false
     */

    @Override
    public boolean execute(Player player) {
        clearOutputString();
        if (getSecondWord() == null) {
            ScoreView scoreView = new ScoreView(player);
            appendToOutputString("Score table");
            return false;
        } else {
            appendToOutputString("I cannot quit something...");
            return false;
        }
    }
}
