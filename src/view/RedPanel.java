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
     * Contains header font size.
     */
    private static final int HEADER_SIZE = 15;

    /**
     * Y-coordinate for header.
     */
    private static final int HEADER_Y = 275;

    /**
     * Stroke thickness of tetris pieces.
     */
    private static final int STROKE = 1;

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

    protected Font pixelMplus;

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
            pixelMplus = Font.createFont(Font.TRUETYPE_FONT,
                    new File("PixelMplus12-Bold.ttf"));
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("PixelMplus12-Bold.ttf")));
        }
        catch (IOException | FontFormatException e) {
        }

        for (int i = 0; i < PIECE_BLOCKS; i++)
        {
            myGamePieces[i] = new Rectangle2D.Double(0, 0, 0, 0);
        }

        for (int h = 0; h < myBoard.getHeight(); h++)
        {
            myFrozenBlocks.add(new Block[myBoard.getWidth()]);
        }
        myPieceToColor.put("I", Color.CYAN);
        myPieceToColor.put("J", Color.BLUE);
        myPieceToColor.put("L", Color.ORANGE);
        myPieceToColor.put("O", Color.YELLOW);
        myPieceToColor.put("S", Color.GREEN);
        myPieceToColor.put("T", Color.PINK);
        myPieceToColor.put("Z", Color.RED);
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
//            g2d.setPaint(Color.BLACK);
//            g2d.fillRect(25, 25, myBoard.getWidth() * 25 - 50, myBoard.getHeight() * 25 - 50);

            g2d.setPaint(Color.RED);
            g2d.setFont(pixelMplus.deriveFont(35f));
            g2d.drawString("T E T R I S", (myBoard.getWidth() * 25) / 7, (myBoard.getHeight() * 25) / 4);

            g2d.setPaint(Color.WHITE);
            g2d.setFont(pixelMplus.deriveFont(20f));
            g2d.drawString("Press 1 to start!", (myBoard.getWidth() * 25) / 6, 250);

            g2d.setFont(pixelMplus.deriveFont(13f));
            g2d.drawString("SCORING GUIDE:", (myBoard.getWidth() * 25) / 3, 275);
            g2d.drawString("+4 points when a piece", TEXT_X, 295);
            g2d.drawString("freezes in place.", TEXT_X, 315);
            g2d.drawString("+1 level (n) each 5 lines ", TEXT_X, 335);
            g2d.drawString("cleared.", TEXT_X, 355);
            g2d.drawString("+  40(n) = 1 line", TEXT_X, 375);
            g2d.drawString("+ 100(n) = 2 lines", TEXT_X, 395);
            g2d.drawString("+ 300(n) = 3 lines", TEXT_X, 415);
            g2d.drawString("+1200(n) = 4 lines", TEXT_X, 435);

            myPressToStart = false;

        }
        else
            for (int row = 0; row < myBoard.getHeight(); row++)
            {
                for (int col = 0; col < myBoard.getWidth(); col++)
                {


                    g2d.setPaint(Color.WHITE);
                    g2d.setStroke(new BasicStroke(1/2));
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
                            g2d.setColor(myPieceToColor.get(blockRow[j].name()));
                            g2d.fillRect(j * BOARD_OFFSET, (-i + 19) * BOARD_OFFSET, BOARD_OFFSET, BOARD_OFFSET);
                            g2d.setColor(Color.WHITE);
                            g2d.setStroke(new BasicStroke(1));
                            g2d.drawRect(j * BOARD_OFFSET, (-i + 19) * BOARD_OFFSET, BOARD_OFFSET, BOARD_OFFSET);
                        }
                    }

                }

                if (myTetrisPiece != null)
                {
                    for (int i = 0; i < PIECE_BLOCKS; i++)
                    {
                        g2d.setColor(myPieceToColor.get(myTetrisPiece.name()));
                        g2d.fill(myGamePieces[i]);
                        g2d.setColor(Color.WHITE);
                        g2d.setStroke(new BasicStroke(1));
                        g2d.draw(myGamePieces[i]);
                    }
                }

            }

        if (myGameOver)
        {
            g2d.setPaint(Color.RED);
            g2d.setFont(pixelMplus.deriveFont(30f));
            g2d.drawString("GAME OVER!", (myBoard.getWidth() * 25) / 4, (myBoard.getHeight() * 25) / 2);
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
                        (-(point.y() + coords[i][1]) + 19) * BOARD_OFFSET, BOARD_OFFSET, BOARD_OFFSET);
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
