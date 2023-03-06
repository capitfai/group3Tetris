package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.*;
import model.Board;
import model.Point;

/**
 * This object represents the Board as a drawing.
 *
 * @author Avreen Kaur
 * @author Sullivan
 * @author Josh
 *
 * @version Winter 2023
 */
public class DrawBoardPanel extends JPanel implements PropertyChangeListener
{

    /**
     * This is a Board object of the model package.
     */
    private Board myBoard;

    /**
     * This is the Elipse that will be on the panel.
     */
    private Ellipse2D myPiece;

    /**
     * This keeps track of the point location of myPiece.
     */
    private Point myPoint;

    /**
     * This constructor initializes the Board object
     * and point location of the Ellipse.
     */
    public DrawBoardPanel()
    {
        myBoard = new Board();
        myPiece = new Ellipse2D.Double(0, 0, 0, 0);
    }


    @Override
    public void propertyChange(final PropertyChangeEvent theEvt)
    {
        if (theEvt.getPropertyName() == myBoard.getMovePropertyName()
                || theEvt.getPropertyName() == myBoard.getMovePropertyClear()
                || theEvt.getPropertyName() == myBoard.getMovePropertyRotated())
        {

            final Point loc = (Point) theEvt.getNewValue();

            System.out.println(loc);

            myPiece.setFrame(loc.x() * 25, loc.y() * 25, 25, 25);

            repaint();
        }

    }

    @Override
    public void paintComponent(final Graphics theGraphics)
    {
        super.paintComponent(theGraphics);
        final Graphics2D g2d = (Graphics2D) theGraphics;

        // for better graphics display
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setPaint(Color.BLACK);
        for (int row = 0; row < myBoard.getHeight(); row++)
        {
            for (int col = 0; col < myBoard.getWidth(); col++)
            {
                g2d.draw(new Rectangle2D.Double(col * 25, row * 25,
                        25, 25));
            }
        }

        g2d.setPaint(Color.GREEN);
        g2d.fill(myPiece);
    }
}
