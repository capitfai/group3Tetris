package view;

import java.awt.*;
import javax.swing.*;
import model.Board;

/**
 * This object represents a red panel of the GUI.
 *
 * @author Avreen Kaur
 * @version Winter 2023
 */
public class RedPanel extends JPanel
{
    /**
     * This represents the color of the panel.
     */
    private static final Color COLOR = Color.RED;

    /**
     * This represents the width of the panel.
     */
    private static final int WIDTH_DIM = 100;

    /**
     * This represents the length of the panel.
     */
    private static final int LENGTH_DIM = 100;

    /**
     * This is the panel.
     */
    private JPanel myPanel = new JPanel();

    /**
     * This object represents a board object from package model.
     */
    private Board myBoard = new Board();

    /**
     * This is the drawBoardPanel object which represents the board
     * of the game as a drawing.
     */
    private DrawBoardPanel myDraw = new DrawBoardPanel();

    /**
     * This constructor sets the layout, background color, and dimensions
     * of the panel.
     */
    public RedPanel()
    {
        myPanel.setLayout(new BorderLayout());
        myPanel.setBackground(COLOR);
        myPanel.setPreferredSize(new Dimension(WIDTH_DIM, LENGTH_DIM));
        // myPanel.add(myDraw);
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
     * the Red Panel object.
     *
     * @param thePanel The panel being added to Red Panel Object
     */
    public void add(final JPanel thePanel)
    {
        myPanel.add(thePanel);
    }



}
