/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.Player;
import model.item.Item;
import model.item.Transportable;

/**
 * Implementation of *Take* Command.
 *
 * @author Florent Plomb <plombf at gmail.com>
 */
public class TakeCmd extends Command {

    private Transportable lastTake;
    private Boolean takeOK = false;

    public TakeCmd() {
    }

    /**
     * Allows the player to take an transportable object wich is in a room, this
     * function check also is the maximum weight is not exceeded
     *
     * @param player the current player
     * @return always false
     */
    public boolean execute(Player player) {

        clearOutputString();
        if (hasSecondWord()) {
            String nameItem = getSecondWord();
            Item item = player.getCurrentRoom().getItem(nameItem);
            if (item == null) {
                appendToOutputString("This item doesn't exist in this room!");
            } else {

                if (player.getCurrentRoom().getItem(nameItem) instanceof Transportable) {

                    Transportable itemTrans = (Transportable) item;
                    lastTake = itemTrans;
                    if (itemTrans.getWEIGHT() + player.getWeightItems() > GameParams.MAX_WEIGHT) {
                        appendToOutputString("You can't carry this object ( " + itemTrans.getNAME() + " " + itemTrans.getWEIGHT() + " units ) because your maximum weight will be exceeded.");
                    } else {
                        takeOK = true;
                        player.addItem(itemTrans);
                        player.getCurrentRoom().removeItem(item.getNAME());
                        appendToOutputString("You just took the " + item.getNAME() + " !");
                    }

                } else {
                    appendToOutputString("This item is fixed you can't take it.");
                }

            }

        } else {
            appendToOutputString("Please specify the item that you want to take!");

        }
        return false;
    }

    public Transportable getLastTake() {
        return lastTake;
    }

    public boolean getTakeOk() {
        return takeOK;
    }

    public void setTakeOff() {
        takeOK = false;
    }
}
