package view;

import DataBaseManager.Question;
import DataBaseManager.DataBaseController;
import controller.ClimbCmd;
import controller.GameParms;
import javax.swing.*;
import java.awt.*;
import static java.awt.BorderLayout.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.swing.border.Border;
import model.GameEngine;
import model.Player;
import model.item.Alarm;

/**
 * Implementaion of the QuizzUserInterface This class allow to play a quizz
 * against the guardian when we try to esapet
 *
 * @author Florent Plomb <plombf at gmail.com>
 */
public class QuizzUserInterface extends JFrame implements ActionListener {

    private JButton one, two, three, for4;
    private HashMap<Integer, Integer> answers;
    private JTextArea score, desire;
    private String currentScore, printQ;
    private int countScore, cpt;
    private double x, y, gvWidth;
    private GameEngine ge;
    private Player player;
    private boolean loose;
    private JPanel mainPanel, questionPanel;

    public QuizzUserInterface(GameEngine ge, Player p) {
        this.cpt = 1;
        this.ge = ge;
        this.loose = false;
        this.player = p;
        this.answers = new HashMap<Integer, Integer>();
        this.currentScore = "Question" + (cpt) + "/3" + "\n" + "Score :" + countScore + "\n Your Total Score : " + (player.getPoint() + countScore);
        this.countScore = 0;
        this.setTitle("QUIZZ");
        this.setLocationRelativeTo(null);
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setVisible(true);
        //Container pane = getContentPane();
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        this.setLocation(((int) x) + 600, ((int) y));
        questionPanel = new JPanel();
        questionPanel.setLayout((new FlowLayout()));

        desire = new JTextArea(printQ);
        desire.setLineWrap(true);
        score = new JTextArea(currentScore);
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        score.setBorder(BorderFactory.createCompoundBorder(border,
                BorderFactory.createEmptyBorder()));

        this.one = new JButton("1");
        this.two = new JButton("2");
        this.three = new JButton("3");
        this.for4 = new JButton("4");

        if (GameParms.mobileApp) {

        } else {

        }

        this.newQuestion();

        one.addActionListener(this);
        two.addActionListener(this);
        three.addActionListener(this);
        for4.addActionListener(this);
        mainPanel.add(desire, NORTH);
        mainPanel.add(score, CENTER);
        mainPanel.add(questionPanel, SOUTH);
        questionPanel.add(one);
        questionPanel.add(two);
        questionPanel.add(three);
        questionPanel.add(for4);
        setContentPane(mainPanel);

    }

//    public void setPositionQuizz(GameEngine ge) {
//        Point pGameView = ge.getGameView().getPanel().getLocationOnScreen();
//        x = pGameView.getX();
//        y = pGameView.getY();
//        gvWidth = ge.getGameView().getPanel().getSize().getWidth();
//    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == one && answers.get(1) == 1) {
            this.win();
        } else if (source == two && answers.get(2) == 1) {
            this.win();
        } else if (source == three && answers.get(3) == 1) {
            this.win();
        } else if (source == for4 && answers.get(4) == 1) {
            this.win();
        } else {
            this.loose();
        }

    }

    private void win() {
        countScore++;
        Border border = BorderFactory.createLineBorder(Color.GREEN);
        score.setBorder(border);
        this.setScore();
        this.newQuestion();

    }

    private void loose() {

        this.loose = true;
        this.newQuestion();
        Border border = BorderFactory.createLineBorder(Color.RED);
        score.setBorder(border);

    }

    private void setScore() {
        cpt++;
        score.setText("Question : " + (cpt) + "/3" + "\n" + "Score : " + countScore + "\n Your Total Score : " + (player.getPoint() + countScore));
    }

    private void newQuestion() {
        if (cpt > 3 || loose) {
            if (loose) {
                String txt;
                Icon icon;
                if (Alarm.getState() == true) {
                    ScoreView sc = new ScoreView(player);
                    icon = new ImageIcon(getClass().getResource("/images/backToCell.jpg"));
                    txt = "GAME OVER";
                    JOptionPane optionPane = new JOptionPane(null, JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE, icon);
                    JDialog dialog = optionPane.createDialog(txt);
                    dialog.setModal(true);
                    dialog.setVisible(true);

                    System.exit(0);
                } else {
                    txt = "You loose... ALARM!!!!";
                    icon = new ImageIcon(getClass().getResource("/images/alarm.jpg"));
                    JOptionPane optionPane = new JOptionPane(null, JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE, icon);
                    JDialog dialog = optionPane.createDialog(txt);
                    dialog.setModal(true);
                    dialog.setVisible(true);

                    Alarm.use();
                    this.ge.alarm();
                    this.ge.setGV(true);
                }

            } else {
//                JOptionPane.showMessageDialog(null, "You win the quizz you can continues",
//                        "Quizz won", JOptionPane.PLAIN_MESSAGE, null);
                this.ge.setGV(true);

            }
            this.player.addPoint(countScore);
            this.dispose();

        }
        
         Question q = null;

        if (GameParms.mobileApp) {

           q = getQuestionMobile();
        } else{
           q = DataBaseController.getQuestion();
        }

      

        String printQ = q.getTitle() + "\n";

        int cptAnswer = 0;
        for (Map.Entry<String, Integer> entry : q.getAnswers().entrySet()) {
            cptAnswer++;
            printQ += cptAnswer + ") " + entry.getKey() + "\n";
            answers.put(cptAnswer, entry.getValue());
            if (entry.getValue() == 1) {
                // result.setAnswer(entry.getKey());
            }
        }
        desire.setText(printQ);
    }

    private Question getQuestionMobile() {
        Question q = new Question();

        HashMap<String, Integer> answers = new HashMap<String, Integer>();

        q.setTitle("Quellle est la première émission de télé-réalité française ?");

        answers.put("Koh-Lanta", 0);
        answers.put("Secret Story", 0);
        answers.put("Loft Story", 1);
        answers.put("Les anges", 0);

        q.setAnswers(answers);

        return q;

    }

}
