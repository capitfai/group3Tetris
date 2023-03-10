package view;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.*;

/**
 * This object represents a blue panel of the GUI.
 *
 * @author Avreen Kaur
 * @version Winter 2023
 */
public class BluePanel extends JPanel implements PropertyChangeListener
{
    /**
     * This represents the color of the panel.
     */
    private static final Color COLOR = new Color(150,100,150);

    /**
     * This represents the width of the panel.
     */
    private static final int WIDTH_DIM = 150;

    /**
     * This represents the length of the panel.
     */
    private static final int LENGTH_DIM = 100;

    /**
     * This constructor sets the layout, background color, and dimensions
     * of the panel.
     */
    public BluePanel()
    {
        setLayout(new BorderLayout());
        setBackground(COLOR);
        setPreferredSize(new Dimension(WIDTH_DIM, LENGTH_DIM));
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
        g2d.setFont(new Font("Arial", Font.ITALIC, 25));
        g2d.drawString("Controls:",25, 175);

        g2d.setFont(new Font("Arial", Font.BOLD, 20));

        g2d.drawString("D,d, >, RIGHT",10, 200);
        g2d.drawString("A,a, <, LEFT ",10, 225);
        g2d.drawString("S,s, |, DOWN ",10, 250);
        g2d.drawString("Space, DROP ",10, 275);
        g2d.drawString("P, p, Pause",10, 300);
        g2d.drawString("U,u, Unpause",10, 325);






    }


    @Override
    public void propertyChange(PropertyChangeEvent theEvt)
    {
        repaint();
    }
}