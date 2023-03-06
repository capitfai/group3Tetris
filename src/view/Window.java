package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;
import model.BoardControls;

/**
 * This object represents the frame of the GUI.
 *
 * @author Avreen Kaur
 * @author Faith
 *
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
    private static final int WIDTH_DIM = 400;

    /**
     * This represents the length of the frame.
     */
    private static final int LENGTH_DIM = 550;

    /**
     * Milliseconds between each call to timer.
     */
    private static final int TIMER_DELAY = 1000;

    /**
     * Timer that controls how game functions.
     */
    protected final Timer myTimer;

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
    private FileMenu myFileMenu = new FileMenu();

    /**
     * This represents a Board Object of the model package.
     */
    private final BoardControls myBoard;

    /**
     * This is the drawBoardPanel object which represents the board
     * of the game as a drawing in Red Panel.
     */
    private final DrawBoardPanel myBoardPanel = new DrawBoardPanel();

    /**
     * This is the drawPiecePanel object which represents the next piece
     * of the game as a drawing in Green Panel.
     */
    private final DrawPiecePanel myPiecePanel = new DrawPiecePanel();

    // CONSTRUCTORS

    /**
     * This constructor sets the name, layout, and visibility of the frame.
     * It also adds the 3 (red, blue, and green) panels to the frame.
     */
    public Window(final BoardControls theBoard)
    {
        super();
        myBoard = theBoard;
        myBoard.newGame();

        myWindow = new JFrame(NAME);
        myWindow.setLayout(new BorderLayout());

        myRed.add(myBoardPanel);
        myGreen.add(myPiecePanel);

        myBlue.getPanel().add(myGreen.getPanel(), BorderLayout.NORTH);
        myRed.getPanel().add(myBlue.getPanel(), BorderLayout.EAST);

        myWindow.add(myFileMenu, BorderLayout.NORTH);
        myWindow.add(myRed.getPanel(), BorderLayout.CENTER);

        myWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myWindow.setSize(new Dimension(WIDTH_DIM, LENGTH_DIM));
        // myWindow.setResizable(false);
        myWindow.setVisible(true);

        myTimer = new Timer(TIMER_DELAY, null);
        myTimer.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(final ActionEvent theEve)
            {
                myBoard.step();
            }
        });

        myBoard.addPropertyChangeListener(myBoardPanel);
        myBoard.addPropertyChangeListener(myPiecePanel);

        myWindow.addKeyListener(new BoardKeyListener());
        myWindow.setFocusable(true);
        myWindow.requestFocus();

    }

    class BoardKeyListener extends KeyAdapter
    {
        @Override
        public void keyPressed(final KeyEvent theEvent)
        {
            if (theEvent.getKeyCode() == KeyEvent.VK_A
                    || theEvent.getKeyCode() == KeyEvent.VK_LEFT)
            {
                myBoard.left();
            }

            else if (theEvent.getKeyCode() == KeyEvent.VK_D
                    || theEvent.getKeyCode() == KeyEvent.VK_RIGHT)
            {
                myBoard.right();
            }

            else if (theEvent.getKeyCode() == KeyEvent.VK_W
                    || theEvent.getKeyCode() == KeyEvent.VK_UP)
            {
                myBoard.rotateCW();
            }

            else if (theEvent.getKeyCode() == KeyEvent.VK_S
                    || theEvent.getKeyCode() == KeyEvent.VK_DOWN)
            {
                myBoard.down();
            }

            else if (theEvent.getKeyCode() == KeyEvent.VK_SPACE)
            {
                myBoard.drop();
            }
        }
    }
}