package communication;

import java.io.Serializable;
import model.Model;
import view.GameListener;

/**
 * This class defines messages that are used to update gameListeners through the gameStateModified method
 * 
 */
public class MsgGameStateModified implements Message, Serializable {

    String roomName;

    public MsgGameStateModified(String roomName) {
        this.roomName = roomName;
    }

    public void actionPerformed(Model m, GameListener l) {
        if (l != null) {
            l.gameStateModified(roomName);
        }
    }
}
