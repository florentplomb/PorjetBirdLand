package iphone;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Set;
import model.Model;
import model.Player;
import view.GameListener;

public class IOSGameViewProxy implements GameListener {

    private static boolean quit = false;
    private static PrintStream out = null;
    private static BufferedReader in = null;
    Model engine;
    Player player;

    // Constructor
    public IOSGameViewProxy(Model engine, Player p) throws InterruptedException {
        this.engine = engine;
        this.player = p;
        engine.addGameListener(this);
        ServerSocket connectionServer = null;
        Socket clientSession = null;
        int port = 4444;

        //  final int time = 10;
        final ServiceAnnouncer service = new ServiceAnnouncer();

        // Register service on that server socket
        System.out.println("Registering service...");
        service.registerService(port);

        while (!quit) {
            try {
                System.out.println("\nNow accepting connections...");
                connectionServer = new ServerSocket(4444);
                clientSession = connectionServer.accept();
                System.out.println("Connection was accepted");
                out = new PrintStream(clientSession.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(clientSession.getInputStream()));

                System.out.println("Initial room is " + player.getCurrentRoom().getDescription() + " with exits: " + code(player.getCurrentRoom().getExits()));
                out.println(player.getCurrentRoom().getDescription() + "/" + code(player.getCurrentRoom().getExits()) + "/" + player.getCurrentRoom().getImageName());

                String command;
                while ((command = in.readLine()) != null) {
                    System.out.println("Received command " + command);
                    engine.interpretCommand(command);
                }

                System.out.println("Reading loop ended");
                out.close();
                in.close();
                clientSession.close();
                connectionServer.close();
            } catch (IOException e) {
                System.out.println(e);
            }
        }

    }

    public void gameStateModified(String roomName, String mapName) {
        if (out != null) {
            System.out.println("Initial room is " + player.getCurrentRoom().getDescription() + " with exits: " + code(player.getCurrentRoom().getExits()) + " " + player.getCurrentRoom().getImageName());
            out.println(player.getCurrentRoom().getDescription() + "/" + code(player.getCurrentRoom().getExits()) + "/" + player.getCurrentRoom().getImageName());
        }
    }

    private String code(HashMap exits) {
        @SuppressWarnings("unchecked")
        Set<String> set = exits.keySet();
        String code = "";
        for (String s : set) {
            code = code + s + ",";
        }
        return code;
    }

    public void gameStateModified(String roomImageName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
