package tetrisgui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 * @Author Sullivan Lucier-Benson and Josh Chang
 * This is program is a JMenuBar containing a file menu for a tetris game.
 *
 */
public class FileMenu extends JMenuBar {
    private JMenu myFileMenu;
    private JMenuItem myNewGame;
    private JMenuItem myExit;
    private JMenuItem myAbout;
    private boolean myGameState;
    public FileMenu() {
        super();
        buildComponents();
        addEvents();

    }
    /**
     * Sets up the new JMenu and JMenuItems for the new JMenuBar
     */
    private void buildComponents() {
        //Creates new JMenu
        myFileMenu = new JMenu("File");

        //Creates menu items
        myNewGame = new JMenuItem("New Game");
        myExit = new JMenuItem("Exit");
        myAbout = new JMenuItem("About");

        //Adds new menu items to the new menu
        myFileMenu.add(myNewGame);
        myFileMenu.add(myAbout);
        myFileMenu.add(myExit);
        this.add(myFileMenu);

    }
    /**
     * Sets up the event listeners for newGame, exit, and about.
     */
    private void addEvents() {
        //Sets the text area field for the "About" menu item, to show a description of this program
        myAbout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                JFrame about = new JFrame();
                JTextArea text = new JTextArea("test");
                text.setEditable(false);
                text.setSize(50, 10);
                about.setLocationRelativeTo(null);
                about.setVisible(true);
                about.setSize(100, 100);
                about.add(text);
            }
        });
        //
        myExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(1);
            }
        });
        //
        myNewGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {

                if (myGameState) {
                    //Start new game
                } else {
                    //Disable myNewGame JMenuItem, if game is already in progress
                    myNewGame.setEnabled(false);
                    JOptionPane.showMessageDialog(null, "Game is already in progress. Finish the current game before starting a new one.", "Cannot Start New Game", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }
    public static void main(final String[] theArgs) {
        final JMenuBar newFileMenu = new FileMenu();
        final JFrame frame = new JFrame("Menu");
        frame.setLocationRelativeTo(null);
        frame.setSize(500,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());
        frame.setVisible(true);
        frame.setJMenuBar(newFileMenu);
    }
}
