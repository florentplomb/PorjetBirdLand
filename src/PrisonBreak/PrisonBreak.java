package PrisonBreak;

import model.Game;
import view.LoginView;

public class PrisonBreak {

    //The game started from the method
    public static void main(String[] args) {
       
        LoginView login = new LoginView();
        Game game = new Game(login.getPlayerName());
     
    }
}
