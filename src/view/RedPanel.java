package view;

import java.awt.*;
import java.util.*;
import java.awt.geom.Ellipse2D;
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

    private Rectangle2D[] myGamePieces;

    private Map<String, Color> myPieceToColor;

    private String [][] myGrid;

    private boolean pressToStart;


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

        pressToStart = true;

    }

    @Override
    public void paintComponent(final Graphics theGraphics) {

        super.paintComponent(theGraphics);
        final Graphics2D g2d = (Graphics2D) theGraphics;

        // for better graphics display
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        if(pressToStart)
        {
            g2d.setPaint(new Color(90,50,90));
            g2d.fillRect(25,25, myBoard.getWidth() * 25 - 50, myBoard.getHeight() * 25 - 50);

            g2d.setPaint(Color.BLACK);
            g2d.setFont(new Font("Arial", Font.BOLD, 20));
            g2d.drawString("Press 1 to start!", 50, 250);

            g2d.setPaint(Color.BLACK);
            g2d.setFont(new Font("Arial", Font.ITALIC, 15));
            g2d.drawString("SCORING GUIDE:", 50, 275);
            g2d.drawString("+4 points when a piece", 50, 295);
            g2d.drawString("freezes in place.", 50, 315);
            g2d.drawString("+1 level (n) each 5 lines ", 50, 335);
            g2d.drawString("cleared.", 50,355);
            g2d.drawString("+  40(n) = 1 line", 50,375);
            g2d.drawString("+ 100(n) = 2 lines", 50,395);
            g2d.drawString("+ 300(n) = 3 lines", 50,415);
            g2d.drawString("+1200(n) = 4 lines", 50,435);




            pressToStart = false;
        }

        else
        {
        for (int row = 0; row < myBoard.getHeight(); row++) {
            for (int col = 0; col < myBoard.getWidth(); col++) {

                g2d.setPaint(Color.BLACK);
                g2d.draw(new Rectangle2D.Double(col * 25, row * 25,
                        25, 25));

                if (myGrid[col][row] != null) {
                    g2d.setPaint(myPieceToColor.get(myGrid[col][row]));
                    g2d.fillRect(col * 25, row * 25, 25, 25);
                }
            }

            if (myTetrisPiece != null) {
                for (int i = 0; i < 4; i++) {
                    g2d.setColor(myPieceToColor.get(myTetrisPiece.name()));
                    g2d.fill(myGamePieces[i]);
                }
            }
        }
        }

        if(gameOver)
        {
            g2d.setPaint(Color.BLACK);
            g2d.setFont(new Font("Arial", Font.BOLD, 30));
            g2d.drawString("GAME OVER!", 25, 250);
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent theEvt) {

        if (theEvt.getPropertyName().equals(myBoard.PROPERTY_MOVE))
        {
            MovableTetrisPiece temp = (MovableTetrisPiece) theEvt.getOldValue();
            Point myP = (Point) theEvt.getNewValue();

            myTetrisPiece = temp.getTetrisPiece();
            Point[] myBlock = myTetrisPiece.getPoints();

            int[][] myCoords = myTetrisPiece.getPointsByRotation(temp.getRotation());

            for (int i = 0; i < 4; i++) {
                // shifts each block in the TetrisPiece to new position;
                // myP.x() == myCoords[i][0]
                // myP.y() == myCoords[i][1]
                myGamePieces[i].setFrame((( myP.x() + myCoords[i][0])) * 25, ( myP.y() + myCoords[i][1]) * 25, 25, 25);
            }
        }

        else if(theEvt.getPropertyName().equals(myBoard.PROPERTY_ROTATED))
        {
            MovableTetrisPiece temp = (MovableTetrisPiece) theEvt.getOldValue();

            myTetrisPiece = temp.getTetrisPiece();
            Rotation myRotation = (Rotation) theEvt.getNewValue();
            Point myPoint = temp.getPosition();
            int[][] myCoords = myTetrisPiece.getPointsByRotation(temp.getRotation());


            for (int i = 0; i < 4; i++) {
                myGamePieces[i].setFrame((( myPoint.x() + myCoords[i][0])) * 25, ( myPoint.y() + myCoords[i][1]) * 25, 25, 25);
            }


        }

        else if(theEvt.getPropertyName().equals(myBoard.PROPERTY_FREEZE))
        {
            MovableTetrisPiece myTemp = (MovableTetrisPiece) theEvt.getOldValue();

            myTetrisPiece = myTemp.getTetrisPiece();
            Point myPoint = (Point) theEvt.getNewValue();
            Point[] myBlock = myTetrisPiece.getPoints();

            int[][] myCoords = myTetrisPiece.getPointsByRotation(myTemp.getRotation());

            for(int i = 0; i < 4; i++)
            {
                if(myPoint.y() + myBlock[i].y() != -1) {
                    // adding to grid the frozen block so that the user can see it in the GUI
                    myGrid[myPoint.x() + myCoords[i][0]][myPoint.y() + myCoords[i][1]] = myTetrisPiece.name();
                }

                // else do nothing, don't draw frozen blocks that are out of GUI
            }
        }

        else if(theEvt.getPropertyName().equals(myBoard.PROPERTY_CLEAR))
        {
//            MovableTetrisPiece myTemp = (MovableTetrisPiece) theEvt.getOldValue();
//            myTetrisPiece = myTemp.getTetrisPiece();
//
//            int max = 0;
//
//            final List<Integer> completeRows = (List<Integer>) theEvt.getNewValue();
//
//            for(int row = 0; row < completeRows.size(); row++)
//            {
//                for(int col = 0; col < myBoard.getWidth(); col++)
//                {
//                    System.out.println(completeRows.get(row));
//
//                    if(max < completeRows.get(row))
//                    {
//                        max = completeRows.get(row);
//                    }
//                    myGrid[col][completeRows.get(row)] = null;
//                }
//            }
//
//            for(int row = max; row > 0; row--)
//            {
//                for(int col = 0; col < 10; col++)
//                {
//                    myGrid[col][row] = myGrid[col][row-1];
//                }
//            }

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
                    for(int i = row; i > 0; i--)
                    {
                        for(int j = 0; j < 10; j++)
                        {
                            myGrid[j][i] = myGrid[j][i-1];
                            myGrid[j][i - 1] = null;

                        }
                    }
                    // now bring all rows above down by amount ????
                }
                count = 0; // reset count in order to check next row
            }
        }

        else if(theEvt.getPropertyName().equals(myBoard.PROPERTY_OVER))
        {
            gameOver = true;
        }

        repaint();

    }


    private void printMe(String [][] theGrid)
    {
        for(int row = 0; row < 20; row++)
        {
            for(int col = 0; col < 10; col++)
            {
                System.out.print(theGrid[col][row] + " ");
            }
            System.out.println();
        }
    }

}
