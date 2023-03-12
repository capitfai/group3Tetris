/*
 * Final Project
 *
 * Winter 2023
 */
package view;

import java.awt.*;
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

import java.util.Collections;

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
public class RedPanel extends JPanel implements PropertyChangeListener {
    /**
     * This represents the color of the panel.
     */
    private static final Color COLOR = Color.WHITE;

    /**
     * This object represents a board object from package model.
     */
    private Board myBoard = new Board();

    /**
     * Variable tells whether or not game is over.
     */
    protected static boolean gameOver;

    /**
     * Contains current tetris piece.
     */
    private TetrisPiece myTetrisPiece;

    /**
     * Contains all tetris pieces.
     */
    private Rectangle2D[] myGamePieces;

    /**
     * Contains specific piece and color they will represent.
     */
    private Map<String, Color> myPieceToColor;

    /**
     * List that holds all the compounded frozen blocks.
     */
    private List<Block[]> myFrozenBlocks;

    /**
     * Holds boolean if game has been started or not.
     */
    private boolean pressToStart;

    /**
     * Holds size (height) of frozen blocks currently on the Board.
     */
    private int theSize;


    /**
     * This constructor sets the layout, background color, and dimensions
     * of the panel.
     */
    public RedPanel() {
        setLayout(new BorderLayout());
        setBackground(COLOR);
        setPreferredSize(new Dimension(myBoard.getWidth() * 25, myBoard.getHeight() * 25));

        myGamePieces = new Rectangle2D[4];
        myPieceToColor = new TreeMap<>();

        myFrozenBlocks = new LinkedList<Block[]>();

        for (int h = 0; h < myBoard.getHeight(); h++) {
            myFrozenBlocks.add(new Block[myBoard.getWidth()]);
        }

        myPieceToColor.put("I", Color.CYAN);
        myPieceToColor.put("J", Color.BLUE);
        myPieceToColor.put("L", Color.ORANGE);
        myPieceToColor.put("O", Color.YELLOW);
        myPieceToColor.put("S", Color.GREEN);
        myPieceToColor.put("T", Color.PINK);
        myPieceToColor.put("Z", Color.RED);

        gameOver = false;

        theSize = 0;

        for (int i = 0; i < 4; i++) {
            myGamePieces[i] = new Rectangle2D.Double(0, 0, 0, 0);
        }

        pressToStart = true;

    }

    /**
     * Draws and displays info and running game in panel.
     *
     * @param theGraphics the <code>Graphics</code> object to protect
     */
    @Override
    public void paintComponent(final Graphics theGraphics) {

        super.paintComponent(theGraphics);
        final Graphics2D g2d = (Graphics2D) theGraphics;

        // for better graphics display
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        if (pressToStart) {
            g2d.setPaint(new Color(90, 50, 90));
            g2d.fillRect(25, 25, myBoard.getWidth() * 25 - 50, myBoard.getHeight() * 25 - 50);

            g2d.setPaint(Color.BLACK);
            g2d.setFont(new Font("Arial", Font.BOLD, 20));
            g2d.drawString("Press 1 to start!", 50, 250);

            g2d.setPaint(Color.BLACK);
            g2d.setFont(new Font("Arial", Font.ITALIC, 15));
            g2d.drawString("SCORING GUIDE:", 50, 275);
            g2d.drawString("+4 points when a piece", 50, 295);
            g2d.drawString("freezes in place.", 50, 315);
            g2d.drawString("+1 level (n) each 5 lines ", 50, 335);
            g2d.drawString("cleared.", 50, 355);
            g2d.drawString("+  40(n) = 1 line", 50, 375);
            g2d.drawString("+ 100(n) = 2 lines", 50, 395);
            g2d.drawString("+ 300(n) = 3 lines", 50, 415);
            g2d.drawString("+1200(n) = 4 lines", 50, 435);

            pressToStart = false;
        } else
            for (int row = 0; row < myBoard.getHeight(); row++) {
                for (int col = 0; col < myBoard.getWidth(); col++) {

                    g2d.setPaint(Color.BLACK);
                    g2d.draw(new Rectangle2D.Double(col * 25, row * 25,
                            25, 25));

                }

                for (int i = theSize - 1; i >= 0; i--) {
                    final Block[] myRow = myFrozenBlocks.get(i);

                    for (int j = 0; j < myRow.length; j++) {

                        if (myRow[j] != null) {
                            g2d.setColor(myPieceToColor.get(myRow[j].name()));
                            g2d.fillRect((j) * 25, (-i + 19) * 25, 25, 25);
                        }
                    }

                }

                if (myTetrisPiece != null && !gameOver) {
                    for (int i = 0; i < 4; i++) {
                        g2d.setColor(myPieceToColor.get(myTetrisPiece.name()));
                        g2d.fill(myGamePieces[i]);
                    }
                }

        }

        if (gameOver) {
            g2d.setPaint(Color.BLACK);
            g2d.setFont(new Font("Arial", Font.BOLD, 30));
            g2d.drawString("GAME OVER!", 25, 250);
        }
    }

    /**
     * Listens for different piece behaviors and animates the pieces on the Board.
     *
     * @param theEvt A PropertyChangeEvent object describing the event source
     *          and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent theEvt) {

        if (theEvt.getPropertyName().equals(myBoard.PROPERTY_MOVE)
                || theEvt.getPropertyName().equals(myBoard.PROPERTY_ROTATED)) {
            MovableTetrisPiece temp = (MovableTetrisPiece) theEvt.getNewValue();

            myTetrisPiece = temp.getTetrisPiece();
            Rotation myRotation = temp.getRotation();
            Point myPoint = temp.getPosition();
            int[][] myCoords = myTetrisPiece.getPointsByRotation(temp.getRotation());


            for (int i = 0; i < 4; i++) {
                myGamePieces[i].setFrame(((myPoint.x() + myCoords[i][0])) * 25, (-(myPoint.y() + myCoords[i][1]) + 19) * 25, 25, 25);
            }
        } else if (theEvt.getPropertyName().equals(myBoard.PROPERTY_FREEZE)) {

            myFrozenBlocks = (List<Block[]>) theEvt.getNewValue();

            if (!gameOver) {
                theSize = findSize(myFrozenBlocks); // find the height of rows that contain frozen blocks to draw on board
            }
        } else if (theEvt.getPropertyName().equals(myBoard.PROPERTY_CLEAR)) {
            final List<Integer> completeRows = (List<Integer>) theEvt.getNewValue();

            for (Integer x : completeRows) {
                final Block[] myRow = myFrozenBlocks.get(x);

                myFrozenBlocks.set(x, myRow);
            }
        } else if (theEvt.getPropertyName().equals(myBoard.PROPERTY_OVER)) {
            gameOver = true;
        }

        repaint();


    }

    /**
     * This method finds the Height of the rows containing Frozen Blocks.
     *
     * @param myFrozen The List of Block arrays to find the Height of frozen blocks.
     * @return The Highest row containing Frozen Blocks.
     */
    private int findSize(List<Block[]> myFrozen)
    {
        int tester = 0;

        for (Block[] row : myFrozen)
        {
            int count = 0;

            for (int i = 0; i < 10; i++)
            {
                if (row[i] != null)
                {

                    count++;
                }
            }

            if (count > 0)
            {
                tester++;
            }
        }
        return tester;
    }
}
