/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.Player;
import model.item.Alarm;
import model.item.Fixed;
import model.item.Item;

/**
 * Implementation of *Use* Command.
 * @author Florent Plomb <plombf at gmail.com>
 */
public class UseCmd extends Command {

    /**
     * Allow the player to use the fixed object present in the rooms,
     * @param player the current player
     * @return always false
     */
    @Override
    public boolean execute(Player player) {
        clearOutputString();

        if (hasSecondWord()) {

            String nameItem = getSecondWord();
            Item item = player.getCurrentRoom().getItem(nameItem);

            if (item == null) {
                appendToOutputString("This item is not in the room!");

            } else {

                if (player.getCurrentRoom().getItem(nameItem) instanceof Fixed) {
                    Fixed itemFixed = (Fixed) item;
                    if (itemFixed instanceof Alarm) {
                        Alarm.use();

                    }
                } else {
                    appendToOutputString("You can use this object, try to take it");
                }
            }

        } else {
            appendToOutputString("Please specify the item that you want to take!");

        }
        return false;
    }

}
