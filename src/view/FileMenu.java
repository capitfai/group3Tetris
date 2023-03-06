package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * This is program is a JMenuBar containing a file menu for a tetris game.
 *
 * @author Sullivan Lucier-Benson
 * @author Josh Chang
 * @version Winter 2023
 *
 */
public class FileMenu extends JMenuBar {

    /**
     * The text message that appears when the button "About" is called.
     */
    private static final String TEXT_ABOUT_MSG = "Test.";

    /**
     * The represents the width of the frame.
     */
    private static final int FRAME_WIDTH = 500;

    /**
     * The represents the height of the frame.
     */
    private static final int FRAME_HEIGHT = 500;

    /**
     * The represents the width of the About file menu item.
     */
    private static final int ABOUT_WIDTH = 100;

    /**
     * The represents the height of the About file menu item.
     */
    private static final int ABOUT_HEIGHT = 100;

    /**
     * The represents the width of the text.
     */
    private static final int TEXT_WIDTH = 50;

    /**
     * The represents the height of the text.
     */
    private static final int TEXT_HEIGHT = 10;

    /**
     * This represents the File Menu Bar.
     */
    private JMenu myFileMenu;

    /**
     * This is the New Game Menu Item.
     */
    private JMenuItem myNewGame;

    /**
     * This is the Exit Menu Item.
     */
    private JMenuItem myExit;

    /**
     * This is the About Menu item.
     */
    private JMenuItem myAbout;

    /**
     * This is the game state (In progress or not).
     */
    private boolean myGameState;

    /**
     * This constructor constructs the file menu bar.
     */
    public FileMenu() {
        super();
        buildComponents();
        addEvents();

    }
    /**
     * Sets up the new JMenu and JMenuItems for the new JMenuBar.
     */
    private void buildComponents() {
        //Creates new JMenu
        myFileMenu = new JMenu("File");

        //Creates menu items
        myNewGame = new JMenuItem("New Game");
        myExit = new JMenuItem("Exit");
        myAbout = new JMenuItem("About");

        //Adds new menu items to the new menu
        myFileMenu.add(myNewGame);
        myFileMenu.add(myAbout);
        myFileMenu.add(myExit);
        this.add(myFileMenu);

    }

    /**
     * Sets up the event listeners for newGame, exit, and about.
     */
    private void addEvents() {
        //Sets the text area field for the "About" menu item,
        // to show a description of this program
        myAbout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent theE) {
                final JFrame about = new JFrame();
                final JTextArea text = new JTextArea(TEXT_ABOUT_MSG);
                text.setEditable(false);
                text.setSize(TEXT_WIDTH, TEXT_HEIGHT);
                about.setLocationRelativeTo(null);
                about.setVisible(true);
                about.setSize(ABOUT_WIDTH, ABOUT_HEIGHT);
                about.add(text);
            }
        });
        //
        myExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent theE) {
                System.exit(1);
            }
        });
        //
        myNewGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent theE) {

                if (myGameState) { // game is not in progress
                    // start new game
                } else {
                    //Disable myNewGame JMenuItem, if game is already in progress
                    myNewGame.setEnabled(false);
                    JOptionPane.showMessageDialog(null,
                            "Game is already in progress."
                                    + " Finish the current game before starting a new one.",
                            "Cannot Start New Game", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }

    /**
     *
     * @param theArgs
     */
    public static void main(final String[] theArgs) {
        final JMenuBar newFileMenu = new FileMenu();
        final JFrame frame = new JFrame("Menu");
        frame.setLocationRelativeTo(null);
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());
        frame.setVisible(true);
        frame.setJMenuBar(newFileMenu);
    }
}