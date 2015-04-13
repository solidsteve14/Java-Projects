/*
 * TCSS 305
 */

package seven.progpracticum;

import java.awt.EventQueue;

/**
 * Creates and runs the GUI.
 * 
 * @author svb140
 * @version 1
 */
public final class GUIMain {
  
  /**
   * Private constructor to inhibit instantiation.
   */
    private GUIMain() {
        throw new IllegalStateException();
    }

  /**
   * Start point for the program.
   * 
   * @param aRgs command line arguments - ignored
   */
    public static void main(final String... aRgs) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GUI(); // create the graphical user interface
            }
        });
    }

}
