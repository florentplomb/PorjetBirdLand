/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.item;

/**
 *
 * @author Bryan
 */
public class Alarm extends Fixed {
    
     private boolean state = false;
    
       private Alarm(String nom, String description) {
        super(nom, description);
    }

    private static Alarm instance = new Alarm("Alarme1","Une alarme de la prison" );

    public boolean getState(){
        return this.state;
    }
    
    public void switchOff(){
        state = false;
    }
           
    public void switchOn(){
       state = true;
    }

    @Override
    public void action() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
