package view;

import java.awt.*;
import javax.swing.*;
import model.Board;

/**
 * This object represents a green panel of the GUI.
 *
 * @author Avreen Kaur
 * @version Winter 2023
 */
public class GreenPanel extends JPanel
{
    /**
     * This represents the color of the panel.
     */
    private static final Color COLOR = Color.GREEN;

    /**
     * This represents the width of the panel.
     */
    private static final int WIDTH_DIM = 150;

    /**
     * This represents the length of the panel.
     */
    private static final int LENGTH_DIM = 150;

    /**
     * This is the panel.
     */
    private JPanel myPanel = new JPanel();

    /**
     * This object is a board object from the model package.
     */
    private Board myBoard = new Board();


    /**
     * This constructor sets the layout, background color, and dimensions
     * of the panel.
     */
    public GreenPanel()
    {
        myPanel.setLayout(new BorderLayout());
        myPanel.setBackground(COLOR);
        myPanel.setPreferredSize(new Dimension(WIDTH_DIM, LENGTH_DIM));
    }

    /**
     * This method returns the panel.
     *
     * @return myPanel The panel to return.
     */
    public JPanel getPanel()
    {
        return myPanel;
    }

    /**
     * This method adds a JPanel to the JPanel of
     * the Green Panel object.
     *
     * @param thePanel The panel being added to Green Panel Object
     */
    public void add(final JPanel thePanel)
    {
        myPanel.add(thePanel);
    }

}