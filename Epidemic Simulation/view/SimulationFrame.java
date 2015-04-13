/**
 * 
 */
package epidemic.progpracticum.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.Timer;

import epidemic.progpracticum.model.SimParams;
import epidemic.progpracticum.model.SimulationWorld;

/**
 * @author solid_000
 *
 */
public class SimulationFrame extends JFrame{
    private SliderPanel mySliderPanel;
    private WorldPanel myWorldPanel;
    private StatsPanel myStatsPanel;
    private ControlPanel myControlPanel;
    private SimulationWorld mySimulationWorld;
    
    private Timer myTimer;

    
    public SimulationFrame() {
        super();
        myTimer = new Timer(SimParams.ANIMATION_STEP_TIME, new StepListener());
        mySliderPanel = new SliderPanel();              
        
        mySimulationWorld = new SimulationWorld(mySliderPanel);       
        myWorldPanel = new WorldPanel(mySimulationWorld);
        myControlPanel = new ControlPanel(myTimer, mySimulationWorld);
        myStatsPanel = new StatsPanel(myWorldPanel);
        mySimulationWorld.setMyWorldPanel(myWorldPanel);
        mySimulationWorld.setMyStatsPanel(myStatsPanel);
        
        
        
        
        add(mySliderPanel, BorderLayout.NORTH);
        add(myWorldPanel, BorderLayout.WEST);
        add(myStatsPanel, BorderLayout.EAST);
        add(myControlPanel, BorderLayout.SOUTH);
        setResizable(false);
        pack();
        setVisible(true);
    }
    
    public class StepListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent aTimerTick) {
            mySimulationWorld.step();           
        }        
    }
    
}


