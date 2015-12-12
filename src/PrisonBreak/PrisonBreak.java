package PrisonBreak;

import DataBaseManager.DataBaseController;
import model.Game;
import view.LoginView;
import view.ScoreView;

public class PrisonBreak {

    //The game started from the method
    public static void main(String[] args) {
       
        LoginView login = new LoginView();
        String playerName = login.getPlayerName();
        Game myGame = new Game(playerName);
    }
}
