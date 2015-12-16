package controller;

import model.Player;
import model.Room;
import model.item.Ladder;

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

            if (nextRoom == null) {
                appendToOutputString("There is no door!");
            } else {
                player.addPreviousRoom(player.getCurrentRoom());
                player.setCurrentRoom(nextRoom);
                player.setMove(1);
                //POUR TESTER 
                //nextRoom.addItem(new Ladder("Ladder", "Ladder", 1, true, "/images/echelle.gif"));
                appendToOutputString("You are " + nextRoom.getDescription() + "\n");
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
