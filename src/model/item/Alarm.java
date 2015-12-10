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
    
     private static boolean state = false;
    
       private Alarm(String nom, String description) {
        super(nom, description);
    }

    private static Alarm instance = new Alarm("Alarm","It is the alarm of the prison" );

    public static boolean getState(){
        return Alarm.state;
    }
    public static Alarm getInstance() {
      return instance;
   }
    
     public static void use() {
         state = !state;
         
   }
            

    @Override
    public void action() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
