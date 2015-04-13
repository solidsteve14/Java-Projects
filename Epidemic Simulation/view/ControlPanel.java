/**
 * 
 */
package epidemic.progpracticum.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

import epidemic.progpracticum.model.SimulationWorld;

/**
 * @author svb140
 *
 */
public class ControlPanel extends JPanel {
    private JButton myStart;
    private JButton myStop;
    private JButton myStep;
    private JButton myReset;
    private Timer myTimer;
    private SimulationWorld myWorld;
    
    public ControlPanel(Timer aTimer, SimulationWorld aWorld) {
        super();
        myTimer = aTimer;
        myWorld = aWorld;
        
        myStart = new JButton("Start");
        myStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent anEvent) {
                myTimer.start();
            }
        });
        
        myStop = new JButton("Stop");
        myStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent anEvent) {
                myTimer.stop();
            }
        });
        
        myStep = new JButton("Step");
        myStep.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent anEvent) {
                myTimer.stop();
                myWorld.step();
            }
        });
        
        myReset = new JButton("Reset");
        myReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent anEvent) {
                myTimer.stop();
                myWorld.reset();
            }
        });
        
        add(myStart);
        add(myStop);
        add(myStep);
        add(myReset);
    }
    
}
