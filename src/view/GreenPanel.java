package view;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Map;
import java.util.TreeMap;
import javax.swing.*;
import model.Board;
import model.TetrisPiece;
import model.MovableTetrisPiece;
import model.Point;

/**
 * This object represents a green panel of the GUI.
 *
 * @author Avreen Kaur
 * @version Winter 2023
 */
public class GreenPanel extends JPanel implements PropertyChangeListener
{
    /**
     * This represents the color of the panel.
     */
    private static final Color COLOR = new Color(200,2,100);

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
    private Board myBoard = new Board();

    private TetrisPiece myTetrisPiece;

    Rectangle2D[] myGamePieces = new Rectangle2D[4];

    Map<String, Color> myPieceToColor;


    /**
     * This constructor sets the layout, background color, and dimensions
     * of the panel.
     */
    public GreenPanel()
    {
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

        for(int i = 0; i < 4; i++)
        {
            myGamePieces[i] = new Rectangle2D.Double(0,0,0,0);
        }
    }


    @Override
    public void propertyChange(PropertyChangeEvent theEvt) {


        if (theEvt.getPropertyName().equals(myBoard.PROPERTY_CHANGE)) {

            TetrisPiece temp = (TetrisPiece) theEvt.getNewValue();

            myTetrisPiece = temp;

            Point[] myBlock = myTetrisPiece.getPoints();

            for (int i = 0; i < 4; i++) {
                myGamePieces[i].setFrame((myBlock[i].x() + 1)* 25, (myBlock[i].y() + 1)* 25, 25, 25);
            }
        }

        repaint();

    }

    @Override
    public void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        final Graphics2D g2d = (Graphics2D) theGraphics;

        // for better graphics display
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setPaint(Color.BLACK);
        g2d.setFont(new Font("Arial", Font.BOLD, 20));
        g2d.drawString("Next Piece!", 25, 25);

        if(myTetrisPiece != null) {
            for (int i = 0; i < 4; i++) {
                g2d.setColor(myPieceToColor.get(myTetrisPiece.name()));
                g2d.fill(myGamePieces[i]);
            }
        }
    }


}