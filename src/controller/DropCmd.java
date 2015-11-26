/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.Player;
import model.item.Item;

/**
 *
 * @author Onur Erdogan
 */
public class DropCmd extends Command{

    @Override
    public boolean execute(Player player) {
        clearOutputString();
            if (hasSecondWord()) {
                String itemName = getSecondWord();
                Item item = player.getItem(itemName);
                if ( item == null) {
                    appendToOutputString("Item not exist");
                } else {
                    player.dropItem(item);
                    //Lier l'objet à la room
                    //player.getCurrentRoom().
                    appendToOutputString("You drop " + item.toString() +" in "+player.getCurrentRoom() +".\n");
                }

            } else {
                // if there is no second word, we don't know where to go...
                appendToOutputString("drop what?");
            }
            return false;
    }
    
}
