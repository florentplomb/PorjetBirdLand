/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBaseManager;


//import java.sql.*;
import controller.GameParms;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.HashMap;
import java.util.ListIterator;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import model.Player;

//import ch.modele.Question;
//import ch.modele.Answer;
//import java.util.ArrayList;
/**
 *
 * @author Florent Plomb <plombf at gmail.com>
 */
public class DataBaseController {

    /**
     * DB Connection data
     */
    private static final String url = GameParms.url;

    private static final String userName = GameParms.userName;

    private static final String password = GameParms.password;
    /**
     * System out style :)
     */
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    /**
     * Random question engin
     */
    private static ListIterator<Integer> itrTabRdm = createItr();

    private static final int nbQuestion = getNumberofQuestion();
             // Chargement du driver odbc une fois pour toute

    static {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    
public static void getConnection(){
        final String url = "jdbc:derby://localhost:1527/quizz";

     final String userName = "quizz";

   final String password = "1234";
   
    Connection con = null;
   
            try {
            // Connection � la base de donn�es
            con = DriverManager.getConnection(url, userName, password);
            Statement requete = con.createStatement();}
            catch (Exception e){
                JOptionPane.showMessageDialog(new JFrame(), "Connecion data base failed", "DataBase",
        JOptionPane.ERROR_MESSAGE);
                
                System.exit(0);
            }
           
    
}

    private static ListIterator<Integer> createItr() {

        ArrayList<Integer> randomNumber = createTabRdm();
        ListIterator<Integer> itr = randomNumber.listIterator();
        return itr;
    }

    private static ArrayList<Integer> createTabRdm() {
        ArrayList<Integer> tabNumbers = new ArrayList<Integer>();
        int x = 0;
        while (x < nbQuestion) {
            tabNumbers.add(x + 1);
            x++;
        }
        Collections.shuffle(tabNumbers);
        return tabNumbers;
    }

    public static Question getQuestion() {

        Question q = new Question();
        if (!itrTabRdm.hasNext()) {
            itrTabRdm = createItr();
        }

        Connection con = null;

        try {
            // Connection à la base de données

            con = DriverManager.getConnection(url, userName, password);
            Statement requete = con.createStatement();
            ResultSet QuestionSet = requete.executeQuery("SELECT QUESTION.TITLE AS Qtitle, ANSWER.TITLE AS Atitle,"
                    + " ISCORRECT FROM QUIZZ.QUESTION INNER JOIN QUIZZ.ANSWER ON QUESTION.ID = QUESTION_ID WHERE QUESTION.ID =" + itrTabRdm.next() + "");
            // Parcours de l'ensemble de résultats
            HashMap<String, Integer> answers = new HashMap<String, Integer>();
            QuestionSet.next();
            q.setTitle(QuestionSet.getString("Qtitle"));
            do {
                answers.put(QuestionSet.getString("Atitle"), QuestionSet.getInt("ISCORRECT"));
            } while (QuestionSet.next());
            q.setAnswers(answers);
            itrTabRdm.remove();

        } catch (Exception e) {
         //   System.out.println(e.getMessage());
           
            JOptionPane.showMessageDialog(new JFrame(), "Connecion data base failed", "DataBase",
        JOptionPane.ERROR_MESSAGE);

        }
        // fermeture de la connection à la base de donnée ainsi que de toutes 
        //les ressources qui lui sont associées ! (ResultSet, Statement)
        try {
            con.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return q;
    }

    private static Integer getNumberofQuestion() {
        Connection con = null;
        int nbrQuestion = 1;
        try {
            con = DriverManager.getConnection(url, userName, password);
            Statement requete = con.createStatement();
            ResultSet countQuestion = requete.executeQuery("SELECT COUNT(" + 1 + ") FROM question");

            countQuestion.next();
            nbrQuestion = countQuestion.getInt(1);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            con.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return nbrQuestion;
    }

    public static boolean insertNamePlayer(String name) {
        Connection con = null;
        boolean dejaInscrit = false;

        try {
            // Connection � la base de donn�es
            con = DriverManager.getConnection(url, userName, password);
            Statement requete = con.createStatement();
            // Rappel : Comme l'id est un champ auto incr�ment� il NE FAUT PAS le d�finir ;-)
          
            ResultSet ensembleResultats = requete.executeQuery("SELECT * FROM GAME");
            // Parcours de l'ensemble de r�sultats
            
            while (ensembleResultats.next() && !dejaInscrit) {
                if (name.equals(ensembleResultats.getString("PLAYER"))) {
                    dejaInscrit = true;
                }

            }

            try {
                con.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return dejaInscrit;
    }
    
       public static void insertDataPlayer(Player player) {
          Connection con = null;

        try {
            // Connection � la base de donn�es
            con = DriverManager.getConnection(url, userName, password);
            Statement requete = con.createStatement();
            
             int nbPlayerAdd = requete.executeUpdate(
                        "INSERT INTO GAME"
                        + "(PLAYER,MOVE,POINT) VALUES "
                        + "('" + player.getName().toUpperCase() + "',"+ player.getMove() + "," + player.getPoint() + ")",
                        Statement.RETURN_GENERATED_KEYS);
                ResultSet ensembleTuplesAjoutes = requete.getGeneratedKeys();
                Integer playerName = null;
                        
           
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            con.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(new JFrame(), "Connecion data base failed", "DataBase",
        JOptionPane.ERROR_MESSAGE);
          //  System.out.println(e.getMessage());
        }
    }
public static ArrayList<Player> getPlayerBD() {

        ArrayList<Player> players = new ArrayList<Player>();


        Connection con = null;

        try {
            // Connection � la base de donn�es
            con = DriverManager.getConnection(url, userName, password);
            Statement requete = con.createStatement();
            ResultSet ensembleResultats = requete.executeQuery("SELECT * FROM GAME ORDER BY MOVE ");
            // Parcours de l'ensemble de r�sultats
            while (ensembleResultats.next()) {
                Player player = new Player(
                        ensembleResultats.getString("Player"),
                        ensembleResultats.getInt("Point"),
                        ensembleResultats.getInt("Move"));
                players.add(player);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            players = null;
        }

        // fermeture de la connection � la base de donn�e ainsi que de toutes 
        //les ressources qui lui sont associ�es ! (ResultSet, Statement)
        try {
            con.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(new JFrame(), "Connecion data base failed", "DataBase",
        JOptionPane.ERROR_MESSAGE);
          //  System.out.println(e.getMessage());
        }

        return players;
    }


}
