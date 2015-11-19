/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 *
 * @author Onur Erdogan
 */
public class GameViewAlert extends Thread{
  private boolean actif = true;
  private JPanel panel;

    public GameViewAlert(JPanel panel) {
        this.panel = panel;
    }
  
  @Override
  @SuppressWarnings("WaitWhileNotSynced")
  public void run() {
      while (actif) {
          try {
              System.out.println("Change de couleur");
              panel.setBackground(Color.red);
              wait(10);
              panel.setBackground(Color.white);
          } catch (InterruptedException ex) {
              Logger.getLogger(GameViewAlert.class.getName()).log(Level.SEVERE, null, ex);
          }
      }
  }
  public void start(){
      run();
  }
  public void arreter() {
    actif = false;
  }
}
