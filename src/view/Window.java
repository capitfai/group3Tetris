package view;

import model.Board;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;

/**
 * This object represents the frame of the GUI.
 *
 * @author Avreen Kaur
 * @version Winter 2023
 */
public class Window extends JFrame
{
    // FIELDS

    /**
     * This represents the name of the frame.
     */
    private static final String NAME = "Tetris!";

    /**
     * This represents the width of the frame.
     */
    private static final int WIDTH_DIM = 600;

    /**
     * This represents the length of the frame.
     */
    private static final int LENGTH_DIM = 600;

    /**
     * Milliseconds between each call to timer.
     */
    private static final int TIMER_DELAY = 1000;

    /**
     * This is a red panel object.
     */
    private RedPanel myRed = new RedPanel();

    /**
     * This is a blue panel object.
     */
    private BluePanel myBlue = new BluePanel();

    /**
     * This is a green panel object.
     */
    private GreenPanel myGreen = new GreenPanel();

    /**
     * This is the frame.
     */
    private JFrame myWindow;

    /**
     * File menu at top of window.
     */
    private FileMenu myFileMenu = new FileMenu(); // TEAM A

    /**
     * Timer that controls how game functions.
     */
    protected final Timer myTimer;

    /**
     *
     */
    private final Board myBoard;

    // CONSTRUCTORS

    /**
     * This constructor sets the name, layout, and visibility of the frame.
     * It also adds the 3 (red, blue, and green) panels to the frame.
     */
    public Window(final Board theBoard)
    {
        super();
        myBoard = theBoard;

        myWindow = new JFrame(NAME);
        myWindow.setLayout(new BorderLayout());

        myBlue.getPanel().add(myGreen.getPanel(), BorderLayout.NORTH);
        myRed.getPanel().add(myBlue.getPanel(), BorderLayout.EAST);

        myWindow.add(myFileMenu, BorderLayout.NORTH);
        myWindow.add(myRed.getPanel(), BorderLayout.CENTER);

        myWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myWindow.setSize(new Dimension(WIDTH_DIM, LENGTH_DIM));
        myWindow.setVisible(true);

        myTimer = new Timer(TIMER_DELAY, null);
        myTimer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                theBoard.step();
            }
        });

        addKeyListener(new BoardKeyListener());
        setFocusable(true);
        requestFocus();

    }

    class BoardKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(final KeyEvent theEvent) {
            if (theEvent.getKeyCode() == KeyEvent.VK_A
                    || theEvent.getKeyCode() == KeyEvent.VK_LEFT) {
                myBoard.left();
            } else if (theEvent.getKeyCode() == KeyEvent.VK_D
                    || theEvent.getKeyCode() == KeyEvent.VK_RIGHT) {
                myBoard.right();
            } else if (theEvent.getKeyCode() == KeyEvent.VK_W
                    || theEvent.getKeyCode() == KeyEvent.VK_UP) {
                myBoard.rotateCW();
            } else if (theEvent.getKeyCode() == KeyEvent.VK_S
                    || theEvent.getKeyCode() == KeyEvent.VK_DOWN) {
                myBoard.down();
            } else if (theEvent.getKeyCode() == KeyEvent.VK_SPACE) {
                myBoard.drop();
            }
        }
    }
}