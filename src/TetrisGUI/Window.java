package TetrisGUI;
import java.awt.*;
import javax.swing.*;

/**
 * This object represents the frame of the GUI.
 *
 * @author Avreen Kaur
 * @version Winter 2023
 */
public class Window extends JFrame {

    // FIELDS

    /**
     * This represents the name of the frame.
     */
    private static final String NAME = "Tetris!";

    /**
     * This represents the width of the frame.
     */
    private static final int WIDTH_DIM = 600;

    /**
     * This represents the length of the frame.
     */
    private static final int LENGTH_DIM = 600;

    /**
     * This is a red panel object.
     */
    private RedPanel myRed = new RedPanel();

    /**
     * This is a blue panel object.
     */
    private BluePanel myBlue = new BluePanel();

    /**
     * This is a green panel object.
     */
    private GreenPanel myGreen = new GreenPanel();

    /**
     * This is the frame.
     */
    private JFrame myWindow;

    // private fileMenuPanel myButtonPanel = new fileMenuPanel(); // TEAM A

    // CONSTRUCTORS

    /**
     * This constructor sets the name, layout, and visibility of the frame.
     * It also adds the 3 (red, blue, and green) panels to the frame.
     */
    public Window()
    {
        myWindow = new JFrame(NAME);
        myWindow.setLayout(new BorderLayout());

        myBlue.getPanel().add(myGreen.getPanel(), BorderLayout.NORTH);
        myRed.getPanel().add(myBlue.getPanel(), BorderLayout.EAST);

        // myWindow.add(myButtonPanel, BorderLayout.NORTH); // CODE FROM TEAM A
        myWindow.add(myRed.getPanel(), BorderLayout.CENTER);

        myWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myWindow.setSize(new Dimension(WIDTH_DIM, LENGTH_DIM));
        myWindow.setVisible(true);
    }
}