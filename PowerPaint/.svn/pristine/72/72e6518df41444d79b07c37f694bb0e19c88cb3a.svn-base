/**
 * 
 */
package seven.progpracticum;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBoxMenuItem;

/**
 * @author svb140
 * @version 1
 *
 */
public class ChangeColorListener implements ActionListener {
    /** The panel that is to have its color changed.
     * 
     */
    private PaintPanel myPanel;
    /** The color of the pen.
     * 
     */
    private Color myColor;
    /** The check box to apply red color to the pen.
     * 
     */
    private JCheckBoxMenuItem myRed;
    /** The check box to apply green color to the pen.
     * 
     */
    private JCheckBoxMenuItem myGreen;
    /** The check box to apply blue color to the pen.
     * 
     */
    private JCheckBoxMenuItem myBlue;
    /**
     * Creates a listener to listen for checkboxes to change the color of the pen.
     * @param aPanel the panel of the gui.
     * @param aRed the checkbox to add red.
     * @param aGreen the checkbox to add green.
     * @param aBlue the checkbox to add blue.
     */
    public ChangeColorListener(final PaintPanel aPanel, final JCheckBoxMenuItem aRed, 
                               final JCheckBoxMenuItem aGreen, final JCheckBoxMenuItem aBlue) {
        myPanel = aPanel;
        myColor = myPanel.getColor();
        myRed = aRed;
        myGreen = aGreen;
        myBlue = aBlue;
    }
    
    @Override
    public void actionPerformed(ActionEvent anEvent) {
        final Object source = anEvent.getSource();
        if (source.getClass().getName().equals(
                                           "seven.progpracticum.StayOpenCheckBoxMenuItem")) {
            final JCheckBoxMenuItem origin = (StayOpenCheckBoxMenuItem) source;
            int red = myColor.getRed();
            int green = myColor.getGreen();
            int blue = myColor.getBlue();
            
            if (myRed.isSelected()) {
                red = 255;
            } else {
                red = 0;
            }                  
            if (myGreen.isSelected()) {
                green = 255;
            } else {
                green = 0;
            }                  
            if (myBlue.isSelected()) {
                blue = 255;
            } else {
                blue = 0;
            }    
            myPanel.setColor(new Color(red, green, blue));
        } 
    }

}
