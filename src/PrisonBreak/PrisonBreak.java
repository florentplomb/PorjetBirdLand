package PrisonBreak;

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
            JOptionPane.showMessageDialog(new JFrame(), "Connecion data base failed.", "Connection failed",
                    JOptionPane.ERROR_MESSAGE);              
                        System.exit(0);
                    
        }

        LoginView login = new LoginView();
        Game game = new Game(login.getPlayerName());

    }
}
