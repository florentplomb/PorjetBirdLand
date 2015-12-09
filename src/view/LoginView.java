/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Quizz.DataBaseController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author Florent Plomb <plombf at gmail.com>
 */
//Les imports habituels
public class LoginView extends JDialog {

    private JPanel container = new JPanel();
    private JFormattedTextField jtf = new JFormattedTextField();
    private JButton b = new JButton("OK");
    private JLabel text4user;
    private JLabel alreadyRegister;
    private JLabel welcome;
    private String playerName;

    public LoginView() {
        
        this.setModal(true);
        this.text4user = new JLabel("Enter your name");
  
         this.alreadyRegister = new JLabel("This name alraedy exist please choice an other");
        
         this.alreadyRegister.setForeground(Color.red);
         this.alreadyRegister.setVisible(false);
        
        this.setTitle("Login");
        this.setSize(300, 300);
        this.setDefaultCloseOperation(this.DO_NOTHING_ON_CLOSE);
        // this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        container.setBackground(Color.white);
        container.setLayout(new BorderLayout());
//        container.add(text4user);
//        container.add(welcome);
        JPanel top = new JPanel();
        Font police = new Font("Arial", Font.BOLD, 14);
        jtf.setFont(police);
        jtf.setPreferredSize(new Dimension(150, 30));
        jtf.setForeground(Color.BLUE);
        b.addActionListener(new BoutonListener());
        text4user.setBackground(Color.lightGray);
        top.add(jtf);
        top.add(b);
         
        top.add(text4user);
        top.add(alreadyRegister);
       
       
        
        this.setContentPane(top);
       
        
        this.setVisible(true);
    }

    /**
     * @return the playerName
     */
    public String getPlayerName() {
        return playerName;
    }

    class BoutonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            boolean sucess = DataBaseController.insertNamePlayer(jtf.getText().toUpperCase());

            if (sucess) {
                playerName = jtf.getText();
                close();
                
            } else {
                
                 alreadyRegister.setVisible(true);
            }
            }

        }
    private void close(){
        this.dispose();
    }
    
   
}
