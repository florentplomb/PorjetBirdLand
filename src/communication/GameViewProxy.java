package communication;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Model;
import view.GameListener;

/**
 * This class is used to communicate with remote GamelListeneners It opens
 * communication sockets and starts listening for incoming messages
 *
 */
public class GameViewProxy extends Thread implements GameListener {

    ServerSocket serverSocket;
    Socket socket;
    ObjectOutputStream out;
    ObjectInputStream in;
    Model engine;

    public GameViewProxy(Model engine) {
        this.engine = engine;
    }

    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(4443);
            socket = serverSocket.accept();

            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());

            Message m;
            while (true) {
                m = (Message) in.readObject();
                m.actionPerformed(engine, this);
            }
        } catch (Exception ex) {
            Logger.getLogger(GameViewProxy.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void gameStateModified(String roomName) {
        try {
            out.writeObject(new MsgGameStateModified(roomName));
        } catch (IOException ex) {
            Logger.getLogger(GameViewProxy.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
