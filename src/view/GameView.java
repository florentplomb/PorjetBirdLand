package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.*;
import model.GameEngine;

/**
 * This class implements a simple graphical user interface with a text entry
 * area, a text output area and an optional image.
 */
public class GameView implements ActionListener, GameListener {

    /*
     * Lists of Strings holding the data to be displayed.
     * The content of these lists will automatically be displayed in the GUI.
     * IMPORTANT: DO NOT MODIFY NAMES OR TYPES OF THESE 5 FIELDS!!!
     *
     */
    private ArrayList<String> myRoomItems, myPlayerItems, myPlayerStats;
    private URL roomImage, playerImage;
    private GameEngine engine;

    /**
     * Construct a UserInterface. As a parameter, a Game Engine
     * (an object processing and executing the game commands) is
     * needed.
     * 
     * @param engine  The GameEngine object implementing the game logic.
     */
    public GameView(GameEngine engine) {
        this.engine = engine;

        // These ArrayLists contains the list of items available in the room, items that the player carry and the stats of the players respectively.
        myRoomItems = new ArrayList<String>();
        myPlayerItems = new ArrayList<String>();
        myPlayerStats = new ArrayList<String>();

        roomImage = null;
        playerImage = null;

        createGUI();

        engine.addGameListener(this);
        // 'auto-invoke' method at start up since nothing has yet been displayed

    }

    /**
     * Sets the list of room items. If you want to show items of current room, you should modify this method.
     */
    private void setRoomItems() {
        myRoomItems.clear();
        myRoomItems.add("none");
    }

    /**
     * Sets the list of player items. If you want to show the items of the player, you should modify this method.
     */
    private void setPlayerItems() {
        myPlayerItems.clear();
        myPlayerItems.add("none");
    }

    /**
     * Sets the list of player states. If you want to change the status of the player, you should modify this method.
     */
    private void setPlayerStats() {
        myPlayerStats.clear();
        myPlayerStats.add("none");
    }

    /**
     * Sets the name of the room image to be displayed.
     */
    private void setRoomImage(String roomImageName) {
        roomImage = getClass().getResource(roomImageName);
    }

    /**
     * Sets the name of the player image to be displayed. If you want to change the player picture, you should modify this method.
     */
    private void setPlayerImage() {
        playerImage = getClass().getResource("/images/knight.gif");
    }

// ==================LISTENERS' METHODS==============================
    /**
     * Method triggered when eventListener is notified that an event has happened (here, event = 'enter' key typed in text field).
     * @param e action event
     */
    public void actionPerformed(ActionEvent e) {
        // no need to check the type of action at the moment.
        // there is only one possible action: text entry
        processCommand();
    }

    /**
     * A command has been entered. Read the command and do whatever is 
     * necessary to process it.
     */
    private void processCommand() {
        String input = inputBox.getText();
        inputBox.setText("");
        engine.interpretCommand(input);
    }

    /**
     * Method triggered when gameListener is notified that the game has been modified.
     * @param roomImageName the name of the image of the room to be displayed
     */
    public void gameStateModified(String roomImageName) {

        // Tests if game is over, and disables the text field if it's the case.
        if (engine.isFinished()) {
            enable(false);
        }

        // Clears the text displayed and then gets the new data in the Engine.
        clearLog();
        println(engine.getOutputString());

        // Sets fields to possibly new values.
        setRoomItems();
        setPlayerItems();
        setPlayerStats();
        setRoomImage(roomImageName);
        setPlayerImage();

        // Updates images and lists displayed in the GUI.
        // IMPORTANT: Fields of the GUI must have been set to new values before.
        updateRoomImage();
        updatePlayerImage();
        updateLists();
    }
//==============================================================================
//  << NO NEED TO READ THE FOLLOWING CODE >> << MODIFY AT YOUR OWN RISK >>
//==============================================================================
// ==================FIELDS==============================
    //variables declaration
    //background panel
    private JPanel mainPanel;
    //sub panels and their components.
    //ROOM
    private JPanel roomPanel;
    private JLabel mapLabel;
    private JPanel roomlistPanel;
    private JScrollPane listScroller3;
    private JList roomItems;
    //PLAYER
    private JPanel playerPanel;
    private JLabel meLabel;
    private JLabel testLabel;
    private JPanel statPanel;
    private JScrollPane listScroller2;
    private JList playerStats;
    private JPanel listPanel;
    private JScrollPane listScroller4;
    private JList playerItems;
    //WIZARD
    private JPanel dialogPanel;
    private JTextField inputBox;
    private JScrollPane listScroller;
    private JTextArea log;
    //Layout variable
    private GridBagConstraints c;
    private JFrame myFrame;

// ==================METHODS==============================
    /**
     * initializes all components of the GUI
     */
    private void createGUI() {
        myFrame = new JFrame("THE WORLD OF ZUUL");
        //background panel
        mainPanel = new JPanel();

        //6 containers (panels)
        roomPanel = new JPanel();
        playerPanel = new JPanel();
        dialogPanel = new JPanel();
        listPanel = new JPanel();
        roomlistPanel = new JPanel();
        statPanel = new JPanel();





        //---------------------------------------------------------------------
        //---------------------------------------------------------------------
        //subcontainers 
        //adding the various components to the subcontainers

        //this method 
        mapLabel = new JLabel();
        mapLabel.setPreferredSize(new Dimension(400, 300));
        mapLabel.setMinimumSize(new Dimension(200, 100));

        roomItems = new JList();
        listScroller3 = new JScrollPane();
        listScroller3.getViewport().add(roomItems);
        listScroller3.setPreferredSize(new Dimension(100, 80));
        listScroller3.setMinimumSize(new Dimension(100, 80));

        roomlistPanel.add(listScroller3);
        roomlistPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Items in the room"),
                BorderFactory.createEmptyBorder(1, 1, 1, 1)));

        //sets the layout variables

        c = new GridBagConstraints();
        //adds an empty border around the components
        //c.insets=new Insets (5,5,5,5);
        c.weighty = 1.0;
        c.weightx = 1.0;
        //gridx  and gridy set the relative position of the component in a grid. 0,0 is the top left position.
        //increasing x moves the component to the right increasing y moves the component down.
        c.gridx = 0;
        c.gridy = 0;
        //adds padding around the components
        //c.ipadx=2;
        //c.ipady=2;

        roomPanel.setLayout(new GridBagLayout());
        roomPanel.add(mapLabel, c);
        c.gridx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.NORTH;
        roomPanel.add(roomlistPanel, c);
        c.anchor = GridBagConstraints.CENTER;

        //-----------------------------------------------------
        meLabel = new JLabel();
//        meLabel.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("knight.gif")));
        meLabel.setPreferredSize(new Dimension(100, 100));
        meLabel.setMinimumSize(new Dimension(100, 100));

        testLabel = new JLabel();
        testLabel.setPreferredSize(new Dimension(300, 300));
        testLabel.setMinimumSize(new Dimension(300, 300));

        playerItems = new JList();
        listScroller2 = new JScrollPane();
        listScroller2.getViewport().add(playerItems);
        listScroller2.setPreferredSize(new Dimension(120, 80));
        listScroller2.setMinimumSize(new Dimension(120, 80));

        listPanel.add(listScroller2);
        listPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Items"),
                BorderFactory.createEmptyBorder(1, 1, 1, 1)));

        //caracteristics
        playerStats = new JList();
        listScroller4 = new JScrollPane();
        listScroller4.getViewport().add(playerStats);
        listScroller4.setPreferredSize(new Dimension(120, 80));
        listScroller4.setMinimumSize(new Dimension(120, 80));

        statPanel.add(listScroller4);
        statPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Stats"),
                BorderFactory.createEmptyBorder(1, 1, 1, 1)));


        playerPanel.setLayout(new GridBagLayout());

        // c.insets=new Insets (5,5,5,5);
        c.weighty = 1.0;
        c.weightx = 1.0;
        c.gridx = 0;
        c.gridy = 0;
        //c.ipadx=5;
        //c.ipady=5;
        //c.gridheight=2;
        c.fill = GridBagConstraints.NONE;
        playerPanel.add(meLabel, c);

        c.gridx = 1;
        //c.gridheight=2;


        c.fill = GridBagConstraints.HORIZONTAL;
        playerPanel.add(listPanel, c);
        c.gridx = 2;
        playerPanel.add(statPanel, c);
        c.fill = GridBagConstraints.NONE;


        //text boxes
        log = new JTextArea();
        log.setEditable(false);

        listScroller = new JScrollPane(log);
        listScroller.setPreferredSize(new Dimension(300, 100));
        listScroller.setMinimumSize(new Dimension(300, 100));

        inputBox = new JTextField(34);

        dialogPanel.setLayout(new BorderLayout());
        dialogPanel.add(listScroller, BorderLayout.NORTH);
        dialogPanel.add(inputBox, BorderLayout.CENTER);


        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 2;
        mainPanel.add(roomPanel, c);
        // c.fill=GridBagConstraints.NONE;


        //c.gridx=0;
        c.gridy = 2;
        //c.gridwidth=1;
        mainPanel.add(playerPanel, c);

        c.gridy = 3;
        //c.gridy=3;
        c.anchor = GridBagConstraints.NORTH;
        mainPanel.add(dialogPanel, c);
        c.anchor = GridBagConstraints.CENTER;

        roomPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(" ROOM "),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));

        playerPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(" PLAYER "),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));

        dialogPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(" WIZARD "),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));



        //create a combo item box with item choices




        //current position image

        myFrame.getContentPane().add(mainPanel);

        myFrame.addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });



        inputBox.addActionListener(this);


        myFrame.pack();
        myFrame.setVisible(true);
        inputBox.requestFocus();
    }

    /**
     * Udates the Jlists in the GUI with the content of the three arrayList fields.
     */
    private void updateLists() {
        String[] myList1 = new String[myRoomItems.size()];
        for (int index = 0; index < myList1.length; index++) {
            myList1[index] = myRoomItems.get(index);
        }
        roomItems.setListData(myList1);

        String[] myList2 = new String[myPlayerItems.size()];
        for (int index = 0; index < myList2.length; index++) {
            myList2[index] = myPlayerItems.get(index);
        }
        playerItems.setListData(myList2);

        String[] myList3 = new String[myPlayerStats.size()];
        for (int index = 0; index < myList3.length; index++) {
            myList3[index] = myPlayerStats.get(index);
        }
        playerStats.setListData(myList3);
    }

    /**
     * Updates the room image file in the GUI.
     */
    private void updateRoomImage() {
        if (roomImage == null) {
            System.out.println("image not found");
        } else {
            ImageIcon incon1 = new ImageIcon(roomImage);
            testLabel.setIcon(incon1);
            ImageIcon icon = new ImageIcon(roomImage);
            mapLabel.setIcon(icon);
            myFrame.pack();
        }
    }

    /**
     * Updates the player image file in the GUI.
     */
    private void updatePlayerImage() {
        if (playerImage == null) {
            System.out.println("image not found");
        } else {
            ImageIcon icon = new ImageIcon(playerImage);
            meLabel.setIcon(icon);
            myFrame.pack();
        }
    }

    /**
     * Enable or disable input in the input field.
     * @param on
     */
    public void enable(boolean on) {
        inputBox.setEditable(on);
        if (!on) {
            inputBox.getCaret().setBlinkRate(0);
        }
    }

    /**
     * Print out some text into the text area.
     * @param text
     */
    public void print(String text) {
        log.append(text);
        log.setCaretPosition(log.getDocument().getLength());
    }

    /**
     * Print out some text into the text area, followed by a line break.
     * @param text 
     */
    public void println(String text) {
        log.append(text + "\n");
        log.setCaretPosition(log.getDocument().getLength());
    }

    /**
     * Clears out the text area.
     */
    private void clearLog() {
        log.setText("");
        log.setCaretPosition(0);
    }
//==============================================================================
}
