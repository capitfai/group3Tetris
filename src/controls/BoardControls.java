package controls;

import model.TetrisPiece;

import java.beans.PropertyChangeListener;
import java.util.List;

/**
 * This interface consists of a Board's controls.
 *
 * @author Avreen Kaur
 * @version Winter 2023
 */
public interface BoardControls {

    /**
     * Get the width of the board.
     *
     * @return Width of the board.
     */
    int getWidth();

    /**
     * Get the height of the board.
     *
     * @return Height of the board.
     */
    int getHeight();

    /**
     * Resets the board for a new game.
     * This method must be called before the first game
     * and before each new game.
     */
    void newGame();

    /**
     * Sets a non random sequence of pieces to loop through.
     *
     * @param thePieces the List of non random TetrisPieces.
     */
    void setPieceSequence(List<TetrisPiece> thePieces);

    /**
     * Advances the board by one 'step'.
     *
     * This could include
     * - moving the current piece down 1 line
     * - freezing the current piece if appropriate
     * - clearing full lines as needed
     */
    void step();

    /**
     * Try to move the movable piece down.
     * Freeze the Piece in position if down tries to move into an illegal state.
     * Clear full lines.
     */
    void down();

    /**
     * Try to move the movable piece left.
     */
    void left();

    /**
     * Try to move the movable piece right.
     */
    void right();

    /**
     * Try to rotate the movable piece in the clockwise direction.
     */
    void rotateCW();

    /**
     * Try to rotate the movable piece in the counter-clockwise direction.
     */
    void rotateCCW();


    /**
     * Drop the piece until piece is set.
     */
    void drop();

    /**
     * This method adds the Property Change Listener
     * from the Property Change Support.
     *
     * @param theListener The Property Change Listener being added.
     */
    void addPropertyChangeListener(PropertyChangeListener theListener);

    /**
     * This method removes the Property Change Listener
     * from the Property Change Support.
     *
     * @param theListener The Property Change Listener being removed.
     */
    void removePropertyChangeListener(PropertyChangeListener theListener);

    /**
     * This method adds a Property Change Listener to the Property Change Support.
     *
     * @param thePropertyName The name of the Property Change Listener being added.
     * @param theListener The Property Change Listener that is added.
     */
    void addPropertyChangeListener(String thePropertyName,
                                           PropertyChangeListener theListener);

    /**
     * This method removes a Property Change Listener to the Property Change Support.
     *
     * @param thePropertyName The name of the Property Change Listener being removed.
     * @param theListener The Property Change Listener that is removed.
     */
    void removePropertyChangeListener(String thePropertyName,
                                              PropertyChangeListener theListener);

    /**
     * This method sets the status of the game to false,
     * and notifies that the game status is over.
     *
     */
    void setGameOver();


}
