/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import DataBaseManager.DataBaseController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.Game;

/**
 * Implementaion of the Login View
 *
 * @author Florent Plomb <plombf at gmail.com>
 */
public class LoginView extends JDialog {

    private JPanel container = new JPanel();
    private JFormattedTextField jtf = new JFormattedTextField();
    private JButton b = new JButton("OK");
    private JLabel text4user;
    private JLabel alreadyRegister;
    private JLabel welcome;
    private String playerName;

    /**
     * Allow to display the login window and manage the login, the user choice a
     * name wich is checked if not already exist in the database
     */
    public LoginView() {

        this.setModal(true);
        this.text4user = new JLabel("Enter your name.");

        this.alreadyRegister = new JLabel("This name alraedy exists please choose another one.");

        this.alreadyRegister.setForeground(Color.red);
        this.alreadyRegister.setVisible(false);

        this.setTitle("Login");
        this.setSize(300, 300);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        this.setLocationRelativeTo(null);
        container.setBackground(Color.white);
        container.setLayout(new BorderLayout());
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

        @Override
        public void actionPerformed(ActionEvent e) {

            try {
                boolean sucess = true;
                if (!jtf.getText().isEmpty()) {
                    sucess = DataBaseController.checkIfexist(jtf.getText().toUpperCase());
                }
                if (!sucess) {
               
                    playerName = jtf.getText();
                    close();
               // start(playerName);

                } else {
                    alreadyRegister.setVisible(true);

                }
            } catch (Exception ex) {
                System.out.println(ex);
            }

        }
    }

    private void close() {
        this.dispose();
    }

    private void start(String playerName) {
        Game game = new Game(playerName);
    }

}
