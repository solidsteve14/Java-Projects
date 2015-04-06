/**
 * 
 */
package epidemic.progpracticum.view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import epidemic.progpracticum.model.SimulationWorld;

/**
 * @author svb140
 *
 */
public class StatsPanel extends JPanel {
    private JButton myGenerateWorld;
    private JLabel myInfectedHumans;
    private JLabel myHealthyHumans;
    private JLabel myInfectedBirds;
    private JLabel myHealthyBirds;
    private JLabel myNumberOfDays;
    private WorldPanel myWorldPanel;
    private SimulationWorld myWorld;
    
    public StatsPanel(WorldPanel aWorldPanel) {
        super(new GridLayout(16,1));
        myWorldPanel = aWorldPanel;
        myWorld = myWorldPanel.getMyWorld();
        myGenerateWorld = new JButton("Generate World");
        myGenerateWorld.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent anEvent) {
                myWorld.makeAllPoints();
                myWorld.makeEntities();
                myWorldPanel.setEntities(myWorld.getEntities());
                myWorldPanel.repaint();
            }
        });
        
          
        JLabel environmentCounts = new JLabel("  Environment Counts:         ");
        JLabel humanCount = new JLabel("  Human Counts:");
        myInfectedHumans = new JLabel("  Infected: " + 0);
        myHealthyHumans = new JLabel ("  Healthy: " + 0);
        
        JLabel birdCount = new JLabel("  Bird Counts:");
        myInfectedBirds = new JLabel("  Infected: " + 0);
        myHealthyBirds = new JLabel ("  Healthy: " + 0);
        
        JLabel numberOfDays = new JLabel("  Number of Days:");
        myNumberOfDays = new JLabel("      " + 0);
        
        add(myGenerateWorld);
        add(Box.createRigidArea(new Dimension(100, 20)));
        add(environmentCounts);
        add(Box.createRigidArea(new Dimension(100, 20)));
        add(humanCount);
        add(myInfectedHumans);
        add(myHealthyHumans);
        add(Box.createRigidArea(new Dimension(100, 20)));
        add(birdCount);
        add(myInfectedBirds);
        add(myHealthyBirds);
        add(Box.createRigidArea(new Dimension(100, 20)));
        add(numberOfDays);
        add(myNumberOfDays);
        add(Box.createRigidArea(new Dimension(100, 20)));
        add(Box.createRigidArea(new Dimension(100, 20)));

    }
    
    public JButton getMyGenerateWorld() {
        return myGenerateWorld;
    }
    
}
