package controller;

import model.Player;
import model.Room;

/**
 * Implementation of *Go* command.
 */
public class GoCmd extends Command {

    public GoCmd() {
    }

    /*
     * Goes to the direction which is entered as the second word. If there is no
     * exit for that direction, prints an error message. Returns always *false*.
     */
    public boolean execute(Player player) {
        clearOutputString();
        if (hasSecondWord()) {
            String direction = getSecondWord();
            Room nextRoom = player.getCurrentRoom().getExit(direction);
            player.setMove(1);
            if (nextRoom == null) {
                appendToOutputString("There is no door!");
            } else {
                player.addPreviousRoom(player.getCurrentRoom());
                player.setCurrentRoom(nextRoom);
                appendToOutputString("You are " + nextRoom.getDescription() + ".\n");
                int i = 0;
                //Waits for a while
                while (i < 100000) {
                    i++;
                }
                appendToOutputString("Exits:" + nextRoom.getExits().keySet());
            }

        } else {
            // if there is no second word, we don't know where to go...
            appendToOutputString("Go where?");
        }
        return false;
    }
}
