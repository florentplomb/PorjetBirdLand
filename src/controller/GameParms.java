package controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * This class sets the global game variables , in this way it is easy to change
 * the behavior of the game from only one class. Currently there are only one
 * variable but we can imagine other variable like limit time and whatever you
 * want
 *
 * @author Florent Plomb <plombf at gmail.com>
 */
public class GameParms {

    public static int MAX_WEIGHT = 10;
    
    public static final String url = "jdbc:derby://localhost:1527/quizz";

    public static final String userName = "quizz";

    public static final String password = "1234";
    
    // si on met false , �a doit se connecter � la base de don�e
    
    public static  boolean mobileApp = true;
    
    
     public static  boolean DemoGame = false;
    
    
}
