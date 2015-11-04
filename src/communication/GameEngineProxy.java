package communication;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Model;
import view.GameListener;

/**
 * This class is used to communicate with remote Game Engine It opens
 * communication sockets and starts listening for incoming messages
 *
 */
public class GameEngineProxy extends Thread implements Model {

    Socket socket;
    ObjectOutputStream out;
    ObjectInputStream in;
    GameListener listener;

    @Override
    public void run() {
        try {
            socket = new Socket("localhost", 4443);
            in = new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());
            Message m;
            while (true) {
                m = (Message) in.readObject();
                m.actionPerformed(this, listener);
            }
        } catch (Exception ex) {
            Logger.getLogger(GameEngineProxy.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void interpretCommand(String commandLine) {
        try {
            out.writeObject(new MsgInterpretCommand(commandLine));
        } catch (IOException ex) {
            Logger.getLogger(GameEngineProxy.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addGameListener(GameListener gl) {
        listener = gl;
        try {
            out.writeObject(new MsgAddGameListener());
        } catch (IOException ex) {
            Logger.getLogger(GameEngineProxy.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
