/*
 * Final Project
 *
 * Winter 2023
 */
package view;

import model.Board;

/**
 * This is the root program driver that opens the GUI.
 *
 * @author Avreen Kaur
 * @author Faith Capito
 * @author Sullivan Seamus Lucier-Benson
 * @author Josh Chang
 *
 * @version Winter 2023
 */
public final class Application
{
    private Application()
    {

    }

    /**
     * The main function called by the operating system.
     *
     * @param theArgs The array of options called by the OS.
     */
    public static void main(final String [] theArgs)
    {
        new Window(new Board());
    }

}