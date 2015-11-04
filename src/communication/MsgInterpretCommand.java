package communication;

import java.io.Serializable;
import model.Model;
import view.GameListener;

/**
 * This class defines messages that are used to update gameEngines through the
 * interpretCommand method
 *
 */
public class MsgInterpretCommand implements Message, Serializable {

    private static final long serialVersionUID = 1L;
    String commandName;

    public MsgInterpretCommand(String commandName) {
        this.commandName = commandName;
    }

    public void actionPerformed(Model model, GameListener l) {
        model.interpretCommand(commandName);
    }
}
