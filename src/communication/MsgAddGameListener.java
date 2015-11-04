package communication;

import java.io.Serializable;
import model.Model;
import view.GameListener;

/**
 * This class defines messages that are used to add GameListeners
 * 
 */
public class MsgAddGameListener implements Message, Serializable {

    public void actionPerformed(Model m, GameListener l) {
        m.addGameListener(l);
    }
}
