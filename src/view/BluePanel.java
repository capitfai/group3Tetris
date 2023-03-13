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
import java.io.File;
import java.io.IOException;
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
    private static final Color COLOR = Color.WHITE;

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
     * Offsets pixels from text drawn according to width of panel.
     */
    private static final int TEXT_OFFSET = 3;

    /**
     * Constant for first level.
     */
    private static final int DEFAULT_LEVEL = 1;

    /**
     * Constant for how many milliseconds timer should delay.
     */
    private static final int TIMER_DELAY = 1000;

    /**
     * X-coordinate of header text.
     */
    private static final int TITLE_X = 30;

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
    private static final int SCORE_Y = 350;

    /**
     * Y-coordinate of lines cleared text.
     */
    private static final int LINES_CLEAR = 365;

    /**
     * Y-coordinate of current level text.
     */
    private static final int LEVEL_Y = 380;

    /**
     * Y-coordinate of next level text.
     */
    private static final int NEXT_LEVEL_Y = 395;

    /**
     * Y-coordinate of lines left until next level.
     */
    private static final int LEVEL_LINE_Y = 410;

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
     * Offsets where rectangle is drawn to capture a border.
     */
    private static final int BORDER_OFFSET = 5;

    /**
     * Offsets Y-coordinate where rectangle is drawn to capture a border.
     */
    private static final int BORDER_Y = 150;

    /**
     * Offset to get border to align with panel.
     */
    private static final int BORDER_DRAW_OFFSET = 55;

    /**
     * Centers text.
     */
    private static final int STRING_OFFSET = 5;

    /**
     * Centers longer text.
     */
    private static final int DRAW_STRING_OFFSET = 10;

    /**
     * Text font size.
     */
    private static final int TEXT_FONT_SIZE = 10;

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
     * Imported font for all text shown in game.
     */
    private Font myFont;

    /**
     * This constructor sets the layout, background color, and dimensions
     * of the panel.
     */
    public BluePanel()
    {

        setUpComponents();
        myBoard = new Board();
        setUpFont();

        mySize = new ArrayList<>();
        myScore = DEFAULT_SCORE;
        myLines = DEFAULT_LINES;
        myLevel = DEFAULT_LEVEL;
        myNextLevel = NEXT_LEVEL;

        myDelay = TIMER_DELAY;

    }

    public void setUpComponents()
    {

        setLayout(new BorderLayout());
        setBackground(COLOR);
        setPreferredSize(new Dimension(WIDTH_DIM, LENGTH_DIM));

    }

    public void setUpFont()
    {
        try
        {
            final String fileName = "PixelMplus12-Bold.ttf";
            myFont = Font.createFont(Font.TRUETYPE_FONT,
                    new File(fileName));
            final GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT,
                    new File(fileName)));
        }
        catch (final IOException | FontFormatException e)
        {
        }
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
        g2d.fillRect(BORDER_OFFSET, BORDER_Y, WIDTH_DIM - TEXT_X,
                (LENGTH_DIM * PIECE_POINTS) - BORDER_DRAW_OFFSET);

        g2d.setPaint(Color.YELLOW);
        g2d.setFont(myFont.deriveFont(20f));
        g2d.drawString("Controls:", TITLE_X, TITLE_Y);

        g2d.setPaint(Color.WHITE);
        g2d.setFont(myFont.deriveFont(13f));

        final int tenOffset = 10;
        final int nineOffset = 9;
        final int sevenOffset = 7;
        final int sixOffset = 6;
        final int fourOffset = 4;

        g2d.drawString("W, w, ^  CW", LENGTH_DIM / 2 - tenOffset, CW_Y);
        g2d.drawString("E, e, CCW", LENGTH_DIM / 2 - sixOffset, CCW_Y);
        g2d.drawString("D, d, >, RIGHT", LENGTH_DIM / TEXT_OFFSET - STRING_OFFSET, RIGHT_Y);
        g2d.drawString("A, a, <, LEFT ", LENGTH_DIM / TEXT_OFFSET - TEXT_OFFSET, LEFT_Y);
        g2d.drawString("S, s, |, DOWN ", LENGTH_DIM / TEXT_OFFSET - BORDER_OFFSET, DOWN_Y);
        g2d.drawString("Space, DROP ", LENGTH_DIM / TEXT_OFFSET + TEXT_OFFSET, DROP_Y);
        g2d.drawString("P, p, Pause", LENGTH_DIM / TEXT_OFFSET, PAUSE_Y);
        g2d.drawString("U, u, Unpause", LENGTH_DIM / fourOffset + sevenOffset, UNPAUSE_Y);
        g2d.drawString("2, End Game", LENGTH_DIM / 2 - nineOffset, END_Y);

        g2d.drawString("SCORE: " + myScore, LENGTH_DIM / 2, SCORE_Y);
        g2d.drawString("LINES CLEARED: " + myLines, LENGTH_DIM / STRING_OFFSET, LINES_CLEAR);
        g2d.drawString("LEVEL: " + myLevel, LENGTH_DIM / 2, LEVEL_Y);
        g2d.drawString("NEXT LEVEL IN :", LENGTH_DIM / BORDER_OFFSET + 2, NEXT_LEVEL_Y);
        g2d.drawString(myNextLevel + " LINES", LENGTH_DIM / 2, LEVEL_LINE_Y);

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