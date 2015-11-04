package communication;

import model.Model;
import view.GameListener;

/**
 * This interface defines the Messages sent between a GameEngineProxy and a
 * gameViewProxy
 *
 */
public interface Message {

    // actionPerformed defines the action that are to be performed on the model or on the GameListener
    public void actionPerformed(Model m, GameListener l);
}
