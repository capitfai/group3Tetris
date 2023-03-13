/*
 * Final Project
 *
 * Winter 2023
 */
package view;

import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.*;
import model.Board;

/**
 * This is program is a JMenuBar containing a file menu for a tetris game.
 *
 * @author Avreen Kaur
 * @author Faith Capito
 * @author Sullivan Seamus Lucier-Benson
 * @author Josh Chang
 *
 * @version Winter 2023
 */
public class FileMenu extends JMenuBar implements PropertyChangeListener
{

    /**
     * The text message that appears when the button "About" is called.
     */
    private static final String TEXT_ABOUT_MSG = "Winter 2023: By Avreen, Faith, "
            + "Josh and Sullivan\nFont imported from public site:"
            + "\nhttps://itouhiro.hatenablog.com/entry/20130602/font";

    /**
     * The represents the width of the About file menu item.
     */
    private static final int ABOUT_WIDTH = 300;

    /**
     * The represents the height of the About file menu item.
     */
    private static final int ABOUT_HEIGHT = 300;

    /**
     * The represents the width of the text.
     */
    private static final int TEXT_WIDTH = 150;

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
     * This represents the Tetris game board.
     */
    private Board myBoard = new Board();

    /**
     * This constructor constructs the file menu bar.
     */
    public FileMenu()
    {
        super();
        buildComponents();
        addEvents();
        myGameState = false;
    }

    /**
     * Sets up the new JMenu and JMenuItems for the new JMenuBar.
     */
    private void buildComponents()
    {
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
    private void addEvents()
    {
        //Sets the text area field for the "About" menu item,
        // to show a description of this program
        myAbout.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(final ActionEvent theE)
            {
                final JFrame about = new JFrame();
                final JTextArea text = new JTextArea(TEXT_ABOUT_MSG);
                text.setEditable(false);
                text.setSize(TEXT_WIDTH, TEXT_HEIGHT);
                about.setLocationRelativeTo(null);
                about.setVisible(true);
                about.setSize(ABOUT_WIDTH , ABOUT_HEIGHT);
                about.add(text);
            }
        });
        //
        myExit.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(final ActionEvent theE)
            {
                System.exit(1);
            }
        });
        //
        myNewGame.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(final ActionEvent theE)
            {
                if (myGameState)
                {
                    new Window(new Board());
                    myGameState = false;
                    myNewGame.setEnabled(true);
                }
                else
                {
                    //Disable myNewGame JMenuItem, if game is already in progress
                    JOptionPane.showMessageDialog(null,
                            "Game is already in progress."
                                    + " Finish the current game before starting a new one.",
                            "Cannot Start New Game", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }

    /**
     * Listens to whether or not the game is over.
     *
     * @param theEvt A PropertyChangeEvent object describing the event source
     *          and the property that has changed.
     */
    @Override
    public void propertyChange(final PropertyChangeEvent theEvt)
    {
        if (theEvt.getPropertyName().equals(myBoard.PROPERTY_OVER))
        {
            myGameState = true;
        }
    }
}
