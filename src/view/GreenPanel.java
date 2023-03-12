/*
 * Final Project
 *
 * Winter 2023
 */
package view;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Map;
import java.util.TreeMap;
import javax.swing.*;
import model.Board;
import model.MovableTetrisPiece;
import model.Point;
import model.TetrisPiece;
import model.Rotation;

/**
 * This object represents a panel holding the next tetris piece
 * in the current game.
 *
 * @author Avreen Kaur
 * @author Faith Capito
 * @author Sullivan Seamus Lucier-Benson
 * @author Josh Chang
 *
 * @version Winter 2023
 */
public class GreenPanel extends JPanel implements PropertyChangeListener
{
    /**
     * This represents the color of the panel.
     */
    private static final Color COLOR = new Color(190, 120, 150);

    /**
     * This represents the width of the panel.
     */
    private static final int WIDTH_DIM = 150;

    /**
     * This represents the length of the panel.
     */
    private static final int LENGTH_DIM = 150;

    /**
     * This object is a board object from the model package.
     */
    private Board myBoard;

    /**
     * Holds the shape of specific tetris piece to be drawn.
     */
    private Rectangle2D[] myGamePieces;

    /**
     * Holds color of specific tetris piece to be drawn.
     */
    private Map<String, Color> myPieceToColor;

    /**
     * Holds the tetris piece that will next be played.
     */
    private TetrisPiece myTetrisPiece;


    /**
     * This constructor sets the layout, background color, and dimensions
     * of the panel.
     */
    public GreenPanel()
    {
        myBoard = new Board();
        myGamePieces = new Rectangle2D[4];

        setLayout(new BorderLayout());
        setBackground(COLOR);
        setPreferredSize(new Dimension(WIDTH_DIM, LENGTH_DIM));

        myPieceToColor = new TreeMap<>();

        myPieceToColor.put("I", Color.CYAN);
        myPieceToColor.put("J", Color.BLUE);
        myPieceToColor.put("L", Color.ORANGE);
        myPieceToColor.put("O", Color.YELLOW);
        myPieceToColor.put("S", Color.GREEN);
        myPieceToColor.put("T", Color.PINK);
        myPieceToColor.put("Z", Color.RED);

        for (int i = 0; i < 4; i++)
        {
            myGamePieces[i] = new Rectangle2D.Double(0, 0, 0, 0);
        }
    }

    /**
     * Listens for when next piece in sequence has changed and displays on panel.
     *
     * @param theEvt A PropertyChangeEvent object describing the event source
     *          and the property that has changed.
     */
    @Override
    public void propertyChange(final PropertyChangeEvent theEvt) {

            if (theEvt.getPropertyName().equals(myBoard.PROPERTY_CHANGE)) {

                    MovableTetrisPiece temp = (MovableTetrisPiece) theEvt.getOldValue();

                    myTetrisPiece = (TetrisPiece) theEvt.getNewValue();

                    Point myP = temp.getPosition();

                    Point[] myBlock = myTetrisPiece.getPoints();

                    int[][] myCoords = myTetrisPiece.getPointsByRotation(Rotation.NONE);


                    for (int i = 0; i < 4; i++) {
                        // shifts each block in the TetrisPiece to new position;
                        // myP.x() == myCoords[i][0]
                        // myP.y() == myCoords[i][1]

                        myGamePieces[i].setFrame((2 + myCoords[i][0] - 1) * 25, (-(18 + myCoords[i][1])+22) * 25, 25, 25);
                    }
            }

        repaint();

    }

    /**
     * Draws the piece next in sequence.
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

        g2d.setPaint(Color.BLACK);
        g2d.setFont(new Font("Arial", Font.BOLD, 20));
        g2d.drawString("Next Piece!", 20, 25);

        if (myTetrisPiece != null) {
            for (int i = 0; i < 4; i++) {
                g2d.setColor(myPieceToColor.get(myTetrisPiece.name()));
                g2d.fill(myGamePieces[i]);
            }
        }
    }


}