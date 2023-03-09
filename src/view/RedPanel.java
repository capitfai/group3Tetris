package view;

import java.awt.*;
import java.util.Map;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.*;
import model.Board;
import model.Point;
import model.TetrisPiece;
import model.MovableTetrisPiece;
import java.util.TreeMap;
import model.Rotation;

/**
 * This object represents a red panel of the GUI.
 *
 * @author Avreen Kaur
 * @version Winter 2023
 */
public class RedPanel extends JPanel implements PropertyChangeListener {
    /**
     * This represents the color of the panel.
     */
    private static final Color COLOR = Color.WHITE;

    /**
     * This represents the width of the panel.
     */
    private static final int WIDTH_DIM = 100;

    /**
     * This represents the length of the panel.
     */
    private static final int LENGTH_DIM = 100;

    /**
     * This object represents a board object from package model.
     */
    private Board myBoard = new Board();

    private boolean gameOver;

    private TetrisPiece myTetrisPiece;

    Rectangle2D[] myGamePieces;

    Map<String, Color> myPieceToColor;

    String [][] myGrid;


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
        myGrid = new String[10][20];

        myPieceToColor.put("I", Color.CYAN);
        myPieceToColor.put("J", Color.BLUE);
        myPieceToColor.put("L", Color.ORANGE);
        myPieceToColor.put("O", Color.YELLOW);
        myPieceToColor.put("S", Color.GREEN);
        myPieceToColor.put("T", Color.PINK);
        myPieceToColor.put("Z", Color.RED);

        gameOver = false;

        for (int i = 0; i < 4; i++) {
            myGamePieces[i] = new Rectangle2D.Double(0, 0, 0, 0);
        }

    }

    @Override
    public void paintComponent(final Graphics theGraphics) {

        super.paintComponent(theGraphics);
        final Graphics2D g2d = (Graphics2D) theGraphics;

        // for better graphics display
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        for (int row = 0; row < myBoard.getHeight(); row++) {
            for (int col = 0; col < myBoard.getWidth(); col++) {

                g2d.setPaint(Color.BLACK);
                g2d.draw(new Rectangle2D.Double(col * 25, row * 25,
                        25, 25));

                if(myGrid[col][row] != null)
                {
                    g2d.setPaint(myPieceToColor.get(myGrid[col][row]));
                    g2d.fillRect(col * 25, row * 25 , 25, 25);
                }
            }

            if (myTetrisPiece != null) {
                for (int i = 0; i < 4; i++) {
                    g2d.setColor(myPieceToColor.get(myTetrisPiece.name()));
                    g2d.fill(myGamePieces[i]);
                }
            }
        }

        if(gameOver)
        {
            g2d.setPaint(Color.BLACK);
            g2d.setFont(new Font("Arial", Font.BOLD, 30));
            g2d.drawString("GAME OVER!", 25, 250);

            // stop timer?
            // make game status not in progress?
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent theEvt) {

        if (theEvt.getPropertyName().equals(myBoard.PROPERTY_MOVE))
        {
            MovableTetrisPiece temp = (MovableTetrisPiece) theEvt.getOldValue();
            Point myP = (Point) theEvt.getNewValue();

            System.out.println(myP);

            myTetrisPiece = temp.getTetrisPiece();
            System.out.println(myTetrisPiece.toString());
            Point[] myBlock = myTetrisPiece.getPoints();

            for (int i = 0; i < 4; i++) {
                // shifts each block in the TetrisPiece to new position
                myGamePieces[i].setFrame(((myBlock[i].x() + myP.x())) * 25, (myBlock[i].y() + myP.y()) * 25, 25, 25);
            }
        }

        else if(theEvt.getPropertyName().equals(myBoard.PROPERTY_ROTATED))
        {
            System.out.println("WORK.... PLEASE");

            MovableTetrisPiece temp = (MovableTetrisPiece) theEvt.getOldValue();
            Rotation myRotation = (Rotation) theEvt.getNewValue();

            System.out.println(myRotation);
        }

        else if(theEvt.getPropertyName().equals(myBoard.PROPERTY_FREEZE))
        {
            MovableTetrisPiece myTemp = (MovableTetrisPiece) theEvt.getOldValue();
            TetrisPiece myTetrisPiece = myTemp.getTetrisPiece();
            Point myPoint = (Point) theEvt.getNewValue();
            Point[] myBlock = myTetrisPiece.getPoints();

            for(int i = 0; i < 4; i++)
            {
                if(myPoint.y() + myBlock[i].y() == -1)
                {
                    myGrid[myPoint.x() + myBlock[i].x()][0] = myTetrisPiece.name();
                    // so that the last piece before the game is over is added to the grid
                }

                else
                {
                    myGrid[myPoint.x() + myBlock[i].x()][myPoint.y() + myBlock[i].y()] = myTetrisPiece.name();
                    // adding to grid the frozen block so that the user can see it in the GUI
                }
            }
        }

        else if(theEvt.getPropertyName().equals(myBoard.PROPERTY_CLEAR))
        {
            MovableTetrisPiece myTemp = (MovableTetrisPiece) theEvt.getOldValue();
            myTetrisPiece = myTemp.getTetrisPiece();
            int rowChange = (int) theEvt.getNewValue();

            int count = 0;

            for(int row = 0; row < myBoard.getHeight(); row++)
            {
                for(int col = 0; col < myBoard.getWidth(); col++)
                {
                    if(myGrid[col][row] != null)
                    {
                        count++; // does every row have a block?
                    }
                }

                if(count >= myBoard.getWidth())
                {
                    for(int k = 0; k < 10; k++)
                    {
                        myGrid[k][row] = null; // it does??? CLEAR
                    }

                }

                count = 0; // reset count in order to check next row
            }

            // now make sure all rows above the cleared rows drop by 1

        }

        else if(theEvt.getPropertyName().equals(myBoard.PROPERTY_OVER))
        {
            gameOver = true;
        }

        repaint();

    }
}
