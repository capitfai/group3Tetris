/*
 * Final Project
 *
 * Winter 2023
 */
package view;

import model.Board;
import model.Rotation;
import model.TetrisPiece;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

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
    private static final Color COLOR = Color.WHITE;

    /**
     * Official UW purple to fill the pieces.
     */
    private static final Color UW_PURPLE = new Color(51, 0, 111);

    /**
     * Official UW gold to outline the pieces.
     */
    private static final Color UW_GOLD = new Color(232, 211, 162);

    /**
     * This represents the width of the panel.
     */
    private static final int WIDTH_DIM = 150;

    /**
     * This represents the length of the panel.
     */
    private static final int LENGTH_DIM = 150;

    /**
     * Contains number of rectangles in each piece.
     */
    private static final int PIECE_BLOCKS = 4;

    /**
     * Sets frame values.
     */
    private static final int SET_FRAME = 25;

    /**
     * X-coordinate of text.
     */
    private static final int TEXT_X = 20;

    /**
     * Y-coordinate of text.
     */
    private static final int TEXT_Y = 30;

    /**
     * Offset value for calculating piece points.
     */
    private static final int PIECE_OFFSET = 18;

    /**
     * Another offset value for calculating piece points.
     */
    private static final int ANOTHER_PIECE_OFFSET = 22;

    /**
     * Sets rectangle to be drawn to give a look of border.
     */
    private static final int BORDER_OFFSET = 5;

    /**
     * Sizes rectangle to draw inner fill of border.
     */
    private static final int BORDER_DRAW_OFFSET = 10;

    /**
     * This object is a board object from the model package.
     */
    private final Board myBoard;

    /**
     * Holds the shape of specific tetris piece to be drawn.
     */
    private final Rectangle2D[] myGamePieces;

    /**
     * Holds color of specific tetris piece to be drawn.
     */
    private final Map<String, Color> myPieceToColor;

    /**
     * Holds the tetris piece that will next be played.
     */
    private TetrisPiece myTetrisPiece;

    /**
     * Imported font for gameplay info.
     */
    private Font pixelMplus;

    /**
     * This constructor sets the layout, background color, and dimensions
     * of the panel.
     */
    public GreenPanel()
    {
        myBoard = new Board();
        myGamePieces = new Rectangle2D[PIECE_BLOCKS];

        setLayout(new BorderLayout());
        setBackground(COLOR);
        setPreferredSize(new Dimension(WIDTH_DIM, LENGTH_DIM));

        myPieceToColor = new TreeMap<>();

        myPieceToColor.put("I", UW_PURPLE);
        myPieceToColor.put("J", UW_PURPLE);
        myPieceToColor.put("L", UW_PURPLE);
        myPieceToColor.put("O", UW_PURPLE);
        myPieceToColor.put("S", UW_PURPLE);
        myPieceToColor.put("T", UW_PURPLE);
        myPieceToColor.put("Z", UW_PURPLE);

        try
        {
            pixelMplus = Font.createFont(Font.TRUETYPE_FONT,
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
    }

    /**
     * Listens for when next piece in sequence has changed and displays on panel.
     *
     * @param theEvt A PropertyChangeEvent object describing the event source
     *          and the property that has changed.
     */
    @Override
    public void propertyChange(final PropertyChangeEvent theEvt)
    {

        if (theEvt.getPropertyName().equals(myBoard.PROPERTY_CHANGE))
        {

            myTetrisPiece = (TetrisPiece) theEvt.getNewValue();

            final int[][] coords = myTetrisPiece.getPointsByRotation(Rotation.NONE);

            for (int i = 0; i < PIECE_BLOCKS; i++)
            {

                myGamePieces[i].setFrame((2 + coords[i][0] - 1) * SET_FRAME,
                        (-(PIECE_OFFSET + coords[i][1]) + ANOTHER_PIECE_OFFSET)
                                * SET_FRAME, SET_FRAME, SET_FRAME);

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
    public void paintComponent(final Graphics theGraphics)
    {
        super.paintComponent(theGraphics);
        final Graphics2D g2d = (Graphics2D) theGraphics;

        // for better graphics display
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setPaint(Color.BLACK);
        g2d.fillRect(BORDER_OFFSET, BORDER_OFFSET,
                WIDTH_DIM - BORDER_DRAW_OFFSET, LENGTH_DIM - BORDER_DRAW_OFFSET);

        g2d.setPaint(Color.WHITE);
        g2d.setFont(pixelMplus.deriveFont(20f));
        g2d.drawString("Next Piece!", TEXT_X, TEXT_Y);

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


}