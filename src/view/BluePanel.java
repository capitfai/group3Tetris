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
     * Constant for initial value of nextLevel.
     */
    private static final int NEXT_LEVEL = 5;

    /**
     * Constant for initial value of score.
     */
    private static final int DEFAULT_SCORE = 0;

    /**
     * Constant for initial value of lines cleared.
     */
    private static final int DEFAULT_LINES = 0;

    /**
     * Constant for first level.
     */
    private static final int DEFAULT_LEVEL = 1;

    /**
     * Constant for how many milliseconds timer should delay.
     */
    private static final int TIMER_DELAY = 1000;

    /**
     * Size of header text.
     */
    private static final int TITLE_SIZE = 25;

    /**
     * Size of body text.
     */
    private static final int BODY_SIZE = 10;

    /**
     * X-coordinate of header text.
     */
    private static final int TITLE_X = 25;

    /**
     * Y-coordinate of header text.
     */
    private static final int TITLE_Y = 175;

    /**
     * X-coordinate of info text.
     */
    private static final int TEXT_X = 10;

    /**
     * Y-coordinate of rotate (clockwise) controls.
     */
    private static final int CW_Y = 200;

    /**
     * Y-coordinate of rotate (counter-clockwise) controls.
     */
    private static final int CCW_Y = 215;

    /**
     * Y-coordinate of right controls.
     */
    private static final int RIGHT_Y = 230;

    /**
     * Y-coordinate of left controls.
     */
    private static final int LEFT_Y = 245;

    /**
     * Y-coordinate of left controls.
     */
    private static final int DOWN_Y = 260;

    /**
     * Y-coordinate of drop control.
     */
    private static final int DROP_Y = 275;

    /**
     * Y-coordinate of pause control.
     */
    private static final int PAUSE_Y = 290;

    /**
     * Y-coordinate of unpause control.
     */
    private static final int UNPAUSE_Y = 305;

    /**
     * Y-coordinate of end-game control.
     */
    private static final int END_Y = 320;

    /**
     * Y-coordinate of score.
     */
    private static final int SCORE_Y = 335;

    /**
     * Y-coordinate of lines cleared text.
     */
    private static final int LINES_CLEAR = 350;

    /**
     * Y-coordinate of current level text.
     */
    private static final int LEVEL_Y = 365;

    /**
     * Y-coordinate of next level text.
     */
    private static final int NEXT_LEVEL_Y = 380;

    /**
     * Base amount of points scored when piece is placed.
     */
    private static final int PIECE_POINTS = 4;

    /**
     * Points scored when one row is cleared.
     */
    private static final int ONE_ROW_POINTS = 40;

    /**
     * Points scored when two rows are cleared.
     */
    private static final int TWO_ROW_POINTS = 100;

    /**
     * Points scored when three rows are cleared.
     */
    private static final int THREE_ROW_POINTS = 300;

    /**
     * Points scored when four rows are cleared.
     */
    private static final int FOUR_ROW_POINTS = 1200;

    /**
     * One row cleared from Board.
     */
    private static final int ONE_ROW_CLEARED = 1;

    /**
     * Two rows cleared from Board.
     */
    private static final int TWO_ROWS_CLEARED = 2;

    /**
     * Three rows cleared from Board.
     */
    private static final int THREE_ROWS_CLEARED = 3;

    /**
     * Four rows cleared from Board.
     */
    private static final int FOUR_ROWS_CLEARED = 4;


    /**
     * Used to reset timer delay to go faster.
     */
    private static final int TIMER_ADJUST = 100;

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

        myScore = DEFAULT_SCORE;
        myLines = DEFAULT_LINES;
        myLevel = DEFAULT_LEVEL;
        myNextLevel = NEXT_LEVEL;

        myDelay = TIMER_DELAY;
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
        g2d.setFont(new Font("Italic", Font.ITALIC, TITLE_SIZE));
        g2d.drawString("Controls:", TITLE_X, TITLE_Y);

        g2d.setFont(new Font("Bold", Font.BOLD, BODY_SIZE));

        g2d.drawString("W, w, ^  CW", TEXT_X, CW_Y);
        g2d.drawString("E, e, CCW", TEXT_X, CCW_Y);
        g2d.drawString("D, d, >, RIGHT", TEXT_X, RIGHT_Y);
        g2d.drawString("A, a, <, LEFT ", TEXT_X, LEFT_Y);
        g2d.drawString("S, s, |, DOWN ", TEXT_X, DOWN_Y);
        g2d.drawString("Space, DROP ", TEXT_X, DROP_Y);
        g2d.drawString("P, p, Pause", TEXT_X, PAUSE_Y);
        g2d.drawString("U, u, Unpause", TEXT_X, UNPAUSE_Y);
        g2d.drawString("2, End Game", TEXT_X, END_Y);

        g2d.drawString("SCORE: " + myScore, TEXT_X, SCORE_Y);
        g2d.drawString("LINES CLEARED: " + myLines, TEXT_X, LINES_CLEAR);
        g2d.drawString("LEVEL: " + myLevel, TEXT_X, LEVEL_Y);
        g2d.drawString("NEXT LEVEL IN : " + myNextLevel + " LINES.", TEXT_X, NEXT_LEVEL_Y);

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
        if (theEvt.getPropertyName().equals(myBoard.PROPERTY_FREEZE))
        {
            myScore += PIECE_POINTS;
        }

        if (theEvt.getPropertyName().equals(myBoard.PROPERTY_CLEAR))
        {
            mySize =  (List<Integer>) theEvt.getNewValue();

            final int tempSize = mySize.size();

            myLines += tempSize;

            int tempMyLines = myLines;

            int countLevels = 0;

            while (tempMyLines >= 0)
            {
                tempMyLines = tempMyLines - NEXT_LEVEL;
                countLevels++;
            }

            myNextLevel = (myLines % NEXT_LEVEL - NEXT_LEVEL) * -1; // single statement?

            myLevel = countLevels;

            if (tempSize == ONE_ROW_CLEARED)
            {
                myScore += myLevel * ONE_ROW_POINTS;
            }

            else if (tempSize == TWO_ROWS_CLEARED)
            {
                myScore += myLevel * TWO_ROW_POINTS;
            }

            else if (tempSize == THREE_ROWS_CLEARED)
            {
                myScore += myLevel * THREE_ROW_POINTS;
            }

            else if (tempSize == FOUR_ROWS_CLEARED)
            {
                myScore += myLevel * FOUR_ROW_POINTS;
            }

            Window.myTimer.setDelay(myDelay - myLevel * TIMER_ADJUST);
        }

        repaint();
    }
}