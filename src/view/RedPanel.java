/*
 * Final Project
 *
 * Winter 2023
 */
package view;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.awt.geom.Rectangle2D;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import javax.swing.*;
import model.Board;
import model.Point;
import model.TetrisPiece;
import model.MovableTetrisPiece;
import model.Rotation;
import model.Block;


/**
 * This object represents a red panel of the GUI.
 *
 * @author Avreen Kaur
 * @author Faith Capito
 * @author Sullivan Seamus Lucier-Benson
 * @author Josh Chang
 *
 * @version Winter 2023
 */
public class RedPanel extends JPanel implements PropertyChangeListener
{

    /**
     * This represents the color of the panel.
     */
    private static final Color COLOR = Color.BLACK;

    /**
     * Official UW purple to fill the pieces.
     */
    private static final Color UW_PURPLE = new Color(51, 0, 111);

    /**
     * Official UW gold to outline the pieces.
     */
    private static final Color UW_GOLD = new Color(232, 211, 162);

    /**
     * X-coordinate for text placement.
     */
    private static final int TEXT_X = 50;

    /**
     * Pixel offset for Board.
     */
    private static final int BOARD_OFFSET = 25;

    /**
     * Contains number of rectangles in each piece.
     */
    private static final int PIECE_BLOCKS = 4;

    /**
     * Sets text size according to panel.
     */
    private static final int TEXT_SIZING = 25;

    /**
     * Y-coordinate of subheader text.
     */
    private static final int SUBHEADING_Y = 250;

    /**
     * Y-coordinate of first instruction line.
     */
    private static final int FIRST_LINE_Y = 275;

    /**
     * Y-coordinate of second instruction line.
     */
    private static final int SECOND_LINE_Y = 295;

    /**
     * Y-coordinate of third instruction line.
     */
    private static final int THIRD_LINE_Y = 315;

    /**
     * Y-coordinate of fourth instruction line.
     */
    private static final int FOURTH_LINE_Y = 335;

    /**
     * Y-coordinate of fifth instruction line.
     */
    private static final int FIFTH_LINE_Y = 355;

    /**
     * Y-coordinate of sixth instruction line.
     */
    private static final int SIXTH_LINE_Y = 375;

    /**
     * Y-coordinate of seventh instruction line.
     */
    private static final int SEVENTH_LINE_Y = 395;

    /**
     * Y-coordinate of eighth instruction line.
     */
    private static final int EIGHTH_LINE_Y = 415;

    /**
     * Y-coordinate of ninth instruction line.
     */
    private static final int NINTH_LINE_Y = 435;


    /**
     * This object represents a board object from package model.
     */
    private final Board myBoard;

    /**
     * Contains specific piece and color they will represent.
     */
    private final Map<String, Color> myPieceToColor;

    /**
     * List that holds all the compounded frozen blocks.
     */
    private List<Block[]> myFrozenBlocks;

    /**
     * Contains all tetris pieces.
     */
    private final Rectangle2D[] myGamePieces;

    /**
     * Variable tells whether game is over.
     */
    private boolean myGameOver;

    /**
     * Contains current tetris piece.
     */
    private TetrisPiece myTetrisPiece;

    /**
     * Holds boolean if game has been started or not.
     */
    private boolean myPressToStart;

    /**
     * Imported font for all text shown in game.
     */
    private Font myFont;

    /**
     * This constructor sets the layout, background color, and dimensions
     * of the panel.
     */
    public RedPanel()
    {
        myBoard = new Board();
        setLayout(new BorderLayout());
        setBackground(COLOR);
        setPreferredSize(new Dimension(myBoard.getWidth() * BOARD_OFFSET,
                myBoard.getHeight() * BOARD_OFFSET));
        myGamePieces = new Rectangle2D[PIECE_BLOCKS];
        myPieceToColor = new TreeMap<>();
        myFrozenBlocks = new LinkedList<Block[]>();

        myGameOver = false;
        myPressToStart = true;

        try
        {
            myFont = Font.createFont(Font.TRUETYPE_FONT,
                    new File("PixelMplus12-Bold.ttf"));
            final GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT,
                    new File("PixelMplus12-Bold.ttf")));
        }
        catch (final IOException | FontFormatException e)
        {
        }

        for (int i = 0; i < PIECE_BLOCKS; i++)
        {
            myGamePieces[i] = new Rectangle2D.Double(0, 0, 0, 0);
        }

        for (int h = 0; h < myBoard.getHeight(); h++)
        {
            myFrozenBlocks.add(new Block[myBoard.getWidth()]);
        }
        myPieceToColor.put("I", UW_PURPLE);
        myPieceToColor.put("J", UW_PURPLE);
        myPieceToColor.put("L", UW_PURPLE);
        myPieceToColor.put("O", UW_PURPLE);
        myPieceToColor.put("S", UW_PURPLE);
        myPieceToColor.put("T", UW_PURPLE);
        myPieceToColor.put("Z", UW_PURPLE);
    }

    /**
     * Draws and displays info and running game in panel.
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

        if (myPressToStart)
        {

            final int gameTitleSpacing = 7; // offsets title according to game board
            final int yOffset = 4; // sets title quarter down from top of board
            final int textXOffset = 6; // offsets text to be centered
            final int scoringX = 3; // offsets scoring guide text to be centered

            g2d.setPaint(UW_PURPLE);
            g2d.setFont(myFont.deriveFont(35f));
            g2d.drawString("T E T R I S", (myBoard.getWidth() * TEXT_SIZING)
                            / gameTitleSpacing, (myBoard.getHeight() * TEXT_SIZING)
                            / yOffset);

            g2d.setPaint(Color.WHITE);
            g2d.setFont(myFont.deriveFont(20f));
            g2d.drawString("Press 1 to start!", (myBoard.getWidth() * TEXT_SIZING)
                    / textXOffset, SUBHEADING_Y);

            g2d.setFont(myFont.deriveFont(13f));
            g2d.drawString("SCORING GUIDE:", (myBoard.getWidth() * TEXT_SIZING) / scoringX, FIRST_LINE_Y);
            g2d.drawString("+4 points when a piece", TEXT_X, SECOND_LINE_Y);
            g2d.drawString("freezes in place.", TEXT_X, THIRD_LINE_Y);
            g2d.drawString("+1 level (n) each 5 lines ", TEXT_X, FOURTH_LINE_Y);
            g2d.drawString("cleared.", TEXT_X, FIFTH_LINE_Y);
            g2d.drawString("+  40(n) = 1 line", TEXT_X, SIXTH_LINE_Y);
            g2d.drawString("+ 100(n) = 2 lines", TEXT_X, SEVENTH_LINE_Y);
            g2d.drawString("+ 300(n) = 3 lines", TEXT_X, EIGHTH_LINE_Y);
            g2d.drawString("+1200(n) = 4 lines", TEXT_X, NINTH_LINE_Y);

            myPressToStart = false;

        }
        else
            for (int row = 0; row < myBoard.getHeight(); row++)
            {
                for (int col = 0; col < myBoard.getWidth(); col++)
                {


                    g2d.setPaint(Color.WHITE);
                    g2d.setStroke(new BasicStroke(1 / 2));
                    g2d.draw(new Rectangle2D.Double(col * BOARD_OFFSET, row * BOARD_OFFSET,
                            BOARD_OFFSET, BOARD_OFFSET));

                }

                for (int i = myFrozenBlocks.size() - 1; i >= 0; i--)
                {
                    final Block[] blockRow = myFrozenBlocks.get(i);

                    for (int j = 0; j < blockRow.length; j++)
                    {

                        if (blockRow[j] != null)
                        {
                            final int transpose = 19;

                            g2d.setColor(myPieceToColor.get(blockRow[j].name()));
                            g2d.fillRect(j * BOARD_OFFSET, (-i + transpose) * BOARD_OFFSET,
                                    BOARD_OFFSET, BOARD_OFFSET);
                            g2d.setColor(UW_GOLD);
                            g2d.setStroke(new BasicStroke(1));
                            g2d.drawRect(j * BOARD_OFFSET, (-i + transpose) * BOARD_OFFSET,
                                    BOARD_OFFSET, BOARD_OFFSET);
                        }
                    }

                }

                if (myTetrisPiece != null)
                {
                    for (int i = 0; i < PIECE_BLOCKS; i++)
                    {
                        g2d.setColor(myPieceToColor.get(myTetrisPiece.name()));
                        g2d.fill(myGamePieces[i]);
                        g2d.setColor(UW_GOLD);
                        g2d.setStroke(new BasicStroke(1));
                        g2d.draw(myGamePieces[i]);
                    }
                }

            }

        if (myGameOver)
        {
            g2d.setPaint(Color.RED);
            g2d.setFont(myFont.deriveFont(30f));
            g2d.drawString("GAME OVER!", (myBoard.getWidth() * BOARD_OFFSET) / PIECE_BLOCKS,
                    (myBoard.getHeight() * BOARD_OFFSET) / 2);
        }
    }

    /**
     * Listens for different piece behaviors and animates the pieces on the Board.
     *
     * @param theEvt A PropertyChangeEvent object describing the event source
     *          and the property that has changed.
     */
    @Override
    public void propertyChange(final PropertyChangeEvent theEvt)
    {

        final int transpose = 19;

        if (theEvt.getPropertyName().equals(myBoard.PROPERTY_MOVE)
                || theEvt.getPropertyName().equals(myBoard.PROPERTY_ROTATED))
        {
            final MovableTetrisPiece temp = (MovableTetrisPiece) theEvt.getNewValue();

            myTetrisPiece = temp.getTetrisPiece();
            final Rotation rotation = temp.getRotation();
            final Point point = temp.getPosition();
            final int[][] coords = myTetrisPiece.getPointsByRotation(rotation);


            for (int i = 0; i < PIECE_BLOCKS; i++)
            {
                myGamePieces[i].setFrame(((point.x() + coords[i][0])) * BOARD_OFFSET,
                        (-(point.y() + coords[i][1]) + transpose) * BOARD_OFFSET, BOARD_OFFSET,
                        BOARD_OFFSET);
            }
        }
        else if (theEvt.getPropertyName().equals(myBoard.PROPERTY_FREEZE))
        {
            myFrozenBlocks = (List<Block[]>) theEvt.getNewValue();
        }
        else if (theEvt.getPropertyName().equals(myBoard.PROPERTY_CLEAR))
        {

            final List<Integer> completeRows = (List<Integer>) theEvt.getNewValue();

            for (Integer x : completeRows)
            {
                final Block[] rows = myFrozenBlocks.get(x);

                myFrozenBlocks.set(x, rows);
            }
        }
        else if (theEvt.getPropertyName().equals(myBoard.PROPERTY_OVER))
        {
            myGameOver = true;

        }

        repaint();


    }

}
