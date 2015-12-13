package PrisonBreak;

import controller.GameParms;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import model.Game;
import view.LoginView;

public class PrisonBreak {

    //The game started from the method
    public static void main(String[] args) {
        try {
            DataBaseManager.DataBaseController.getConnection();
        } catch (ExceptionInInitializerError e) {
            JOptionPane.showMessageDialog(new JFrame(), "Connecion data base failed. Connect it and try again.", "Connection failed",
                    JOptionPane.ERROR_MESSAGE);
            GameParms.mobileApp = true;
        }

        LoginView login = new LoginView();
        Game game = new Game(login.getPlayerName());

    }
}
