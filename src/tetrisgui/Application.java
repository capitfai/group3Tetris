package tetrisgui;
/**
 * This is the root program driver that opens the GUI.
 *
 * @author Avreen Kaur
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
        new Window();
    }

}