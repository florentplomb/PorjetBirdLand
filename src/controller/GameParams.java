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
public class GameParams {

    public static final int MAX_WEIGHT = 10;
    
        /**
     * If true , the game don't use the random function to initialise the item's
     * room (the room is defined: blanket in cellEast1 and ladder in cellWest2)
     * and the guardian is fixed (he doesn't move) in the mainCorridorMiddle.
     *
     */
    
    private static final boolean DEMOGAME = true;

    /**
     * The url  of the database
     */
    public static final String URL = "jdbc:derby:quizz;create=true";

    /**
     * The weight of the ladder
     */
    public static final int WEIGHT_LADDER = 8;

    /**
     * The weight of the blanket
     */
    public static final int WEIGHT_BLANKET = 5;

    /**
     * The weight of the banana
     */
    public static final int WEIGHT_BANANA = 1;

    /**
     * Return the state of demo game
     * @return the boolean
     */
    public static boolean demoGame() {
        return DEMOGAME;
    }

    public GameParams() {
        
    }

    
    
}
