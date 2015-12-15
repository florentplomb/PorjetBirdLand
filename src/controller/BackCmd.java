package controller;

import java.util.ArrayList;
import model.Player;
import model.Room;

/**
 * Implementation of *Back* command.
 *
 *
 * @author Bryan Cornelius
 */
public class BackCmd extends Command {

    public BackCmd() {
    }

    /**
     * Try to go to previous room if there is one. Otherwise print an error
     * message. Returns always 'false'.
     *
     * @param player the current player
     * @return always false
     */
    public boolean execute(Player player) {
        clearOutputString();

        ArrayList<Room> previousRoom = player.getPreviousRooms();
        boolean check = previousRoom.isEmpty();

        if (check == true) {
            appendToOutputString("There is no way back.");

        } else {

            Room lastRoom = previousRoom.get(previousRoom.size() - 1);
            player.setCurrentRoom(lastRoom);
            previousRoom.remove(previousRoom.size() - 1);
             player.setMove(1);
        }

        return false;
    }
}
