/**
 * 
 */
package epidemic.progpracticum.view;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import epidemic.progpracticum.model.SimParams;

/**
 * @author svb140
 *
 */
public class SliderPanel extends JPanel {
    private JLabel myHumans;
    private JLabel myBirds;
    private JLabel myPercentSick;
    private JSlider mySelectHumans;
    private JSlider mySelectBirds;
    private JSlider mySelectPercentSick;
   
    /**
     * 
     */
    public SliderPanel() {
        super(new GridLayout(2,3));
        createSliders();               
        myHumans = new JLabel("Select number of people: 0");
        myBirds = new JLabel("Select number of birds: 0");
        myPercentSick = new JLabel("Infected birds: 0%");
        add(myHumans);
        add(myBirds);
        add(myPercentSick);
       
        add(mySelectHumans);
        add(mySelectBirds);
        add(mySelectPercentSick);
              
    }
    
    /** Creates the sliders to select starting world population values.
     * 
     */
    private void createSliders() {
        mySelectHumans = new JSlider(JSlider.HORIZONTAL, 0, SimParams.MAX_HUMAN_NUM, 0);
        mySelectHumans.setMajorTickSpacing(100);
        mySelectHumans.setMinorTickSpacing(50);
        mySelectHumans.setPaintTicks(true);
        mySelectHumans.setPaintLabels(true);
        mySelectHumans.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent anEvent) {
                final JSlider source = (JSlider) anEvent.getSource();
                myHumans.setText("Select number of people: " + source.getValue());
            }
        });
        
        mySelectBirds = new JSlider(JSlider.HORIZONTAL, 0, SimParams.MAX_BIRD_NUM, 0);
        mySelectBirds.setMajorTickSpacing(100);
        mySelectBirds.setMinorTickSpacing(50);
        mySelectBirds.setPaintTicks(true);
        mySelectBirds.setPaintLabels(true);
        mySelectBirds.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent anEvent) {
                final JSlider source = (JSlider) anEvent.getSource();
                myBirds.setText("Select number of birds: " + source.getValue());
            }
        });
        
        mySelectPercentSick = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);
        mySelectPercentSick.setMajorTickSpacing(10);
        mySelectPercentSick.setMinorTickSpacing(5);
        mySelectPercentSick.setPaintTicks(true);
        mySelectPercentSick.setPaintLabels(true);
        mySelectPercentSick.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent anEvent) {
                final JSlider source = (JSlider) anEvent.getSource();
                myPercentSick.setText("Infected birds: " + source.getValue() + "%");
            }
        });
    }
    
    public int getHumans() {
        return mySelectHumans.getValue();
    }
    
    public int getBirds() {
        return mySelectBirds.getValue();
    }
    
    public int getPercentSick() {
        return mySelectPercentSick.getValue();
    }
    
    public void reset() {
        mySelectHumans.setValue(0);
        mySelectBirds.setValue(0);
        mySelectPercentSick.setValue(0);
        
    }
}
