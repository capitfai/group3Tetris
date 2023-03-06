package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.*;
import model.Board;

/**
 * This class represents the next piece panel as a drawing.
 *
 * @author Avreen Kaur
 * @author Sullivan
 * @author Josh
 *
 * @version Winter 2023
 */
public class DrawPiecePanel extends JPanel implements PropertyChangeListener
{
    /**
     * This object represents a circle on the drawn panel.
     */
    private Ellipse2D myPiece;

    /**
     * This object represents the Board object.
     */
    private Board myBoard;

    /**
     * This constructor initializes the board and ellipse.
     */
    public DrawPiecePanel()
    {
        myPiece = new Ellipse2D.Double(0, 0, 0, 0);
        myBoard = new Board();
    }

    @Override
    public void paintComponent(final Graphics theGraphics)
    {
        super.paintComponent(theGraphics);
        final Graphics2D g2d = (Graphics2D) theGraphics;

        // for better graphics display
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);

        setBackground(Color.GREEN);
        g2d.setPaint(Color.ORANGE);
        g2d.fill(myPiece);

    }

    @Override
    public void propertyChange(final PropertyChangeEvent theEvt)
    {
        if (theEvt.getPropertyName() == myBoard.getMovePropertyPieceChange())
        {
            myPiece.setFrame(0, 0, 30, 30);
            repaint();
        }
    }
}