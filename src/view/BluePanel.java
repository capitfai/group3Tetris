/*
 * Final Project
 *
 * Winter 2023
 */
package view;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import model.Board;

/**
 * This object represents the panel that holds the score and level info
 * of the current game.
 *
 * @author Avreen Kaur
 * @author Faith Capito
 * @author Sullivan Seamus Lucier-Benson
 * @author Josh Chang
 *
 * @version Winter 2023
 */
public class BluePanel extends JPanel implements PropertyChangeListener
{
    /**
     * This represents the color of the panel.
     */
    private static final Color COLOR = new Color(150, 100, 150);

    /**
     * This represents the width of the panel.
     */
    private static final int WIDTH_DIM = 150;

    /**
     * This represents the length of the panel.
     */
    private static final int LENGTH_DIM = 100;

    /**
     * Reference of current Board being played on.
     */
    private Board myBoard;

    /**
     * Contains user's score in current game.
     */
    private int myScore;

    /**
     * Contains lines cleared in game.
     */
    private int myLines;

    /**
     * Keeps track of current level.
     */
    private int myLevel;

    /**
     * Tracks next level to be achieved.
     */
    private int myNextLevel;

    /**
     * Contains timer delay of how fast to run.
     */
    private int myDelay;

    /**
     * List holding amount of lines have been cleared on the Board.
     */
    private List<Integer> mySize;

    /**
     * This constructor sets the layout, background color, and dimensions
     * of the panel.
     */
    public BluePanel()
    {
        setLayout(new BorderLayout());
        setBackground(COLOR);
        setPreferredSize(new Dimension(WIDTH_DIM, LENGTH_DIM));
        myBoard = new Board();

        mySize = new ArrayList<>();

        myScore = 0;
        myLines = 0;
        myLevel = 1;
        myNextLevel = 5;

        myDelay = 1000;
    }

    /**
     * Lists out the controls, levels and lines cleared in panel.
     *
     * @param theGraphics the <code>Graphics</code> object to protect
     */
    @Override
    public void paintComponent(final Graphics theGraphics)
    {
        super.paintComponent(theGraphics);
        final Graphics2D g2d = (Graphics2D) theGraphics;

        // for better graphics display
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setPaint(Color.BLACK);
        g2d.setFont(new Font("Italic", Font.ITALIC, 25));
        g2d.drawString("Controls:", 25, 175);

        g2d.setFont(new Font("Bold", Font.BOLD, 10));

        g2d.drawString("W,w, UP  CW", 10, 200);
        g2d.drawString("E,e, CCW", 10, 215);
        g2d.drawString("D,d, >, RIGHT", 10, 230);
        g2d.drawString("A,a, <, LEFT ", 10, 245);
        g2d.drawString("S,s, |, DOWN ", 10, 260);
        g2d.drawString("Space, DROP ", 10, 275);
        g2d.drawString("P, p, Pause", 10, 290);
        g2d.drawString("U,u, Unpause", 10, 305);
        g2d.drawString("2, End Game", 10, 320);

        g2d.drawString("SCORE: " + myScore, 10, 335);
        g2d.drawString("LINES CLEARED: " + myLines, 10, 350);
        g2d.drawString("LEVEL: " + myLevel, 10, 365);
        g2d.drawString("NEXT LEVEL IN : " + myNextLevel + " LINES.", 10, 380);

    }

    /**
     * Listens to the events happening on the board to calculate the level and
     * current score of game.
     *
     * @param theEvt A PropertyChangeEvent object describing the event source
     *          and the property that has changed.
     */
    @Override
    public void propertyChange(final PropertyChangeEvent theEvt)
    {
        if(theEvt.getPropertyName().equals(myBoard.PROPERTY_FREEZE))
        {
            myScore += 4;
        }

        if(theEvt.getPropertyName().equals(myBoard.PROPERTY_CLEAR))
        {
            mySize =  (List<Integer>) theEvt.getNewValue();

            int tempSize = mySize.size();

            myLines += tempSize;

            int tempMyLines = myLines;

            int countLevels = 0;

            while(tempMyLines >= 0)
            {
                tempMyLines = tempMyLines - 5;
                countLevels++;
            }

            myNextLevel = ((myLines % 5 - 5) * - 1); // single statement?

            myLevel = countLevels;

            if (tempSize == 1)
            {
                myScore += myLevel * 40;
            }

            else if (tempSize == 2)
            {
                myScore += myLevel * 100;
            }

            else if (tempSize == 3)
            {
                myScore += myLevel * 300;
            }

            else if (tempSize == 4)
            {
                myScore += myLevel * 1200;
            }

            Window.myTimer.setDelay(myDelay - myLevel * 100);
        }

        repaint();
    }
}