/**
 * Your name. Final project Autumn 2013
 */

package epidemic.progpracticum.view;

import java.awt.EventQueue;

import javax.swing.JFrame;

/**
 * Simulation Driver class.
 * @author Monika
 * @version Autumn 2013
 */
public final class SimulationMain {
    
    /**
     * Private constructor - guards against instantiation.
     */
    private SimulationMain() {
        
    }
    
    /**
     * Main method of a simulation driver.
     * @param aRgs command line arguments, assumes none
     */
    public static void main(String[] aRgs) {
        
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                final SimulationFrame frame = new SimulationFrame();
                frame.setTitle("CSS305 Epidemic Simulation");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}
