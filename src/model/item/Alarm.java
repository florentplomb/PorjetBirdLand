/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.item;

/**
 * Implementaion of *Alarm" model (Singelton)
 *
 * @author Bryan Cornelius
 */
public class Alarm extends Fixed {

    private static boolean state = false;

    private Alarm(String nom, String description) {
        super(nom, description);
    }

    private static Alarm instance = new Alarm("Alarm", "It is the alarm of the prison");

    /**
     *
     * @return true if the alarm is on , false is not
     */
    public static boolean getState() {
        return Alarm.state;
    }

    /**
     *
     * @return the singleton Alarm
     */
    public static Alarm getInstance() {
        return instance;
    }

    /**
     * Allow to enable or disabled the Alarm
     */
    public static void use() {
        state = !state;

    }

}
