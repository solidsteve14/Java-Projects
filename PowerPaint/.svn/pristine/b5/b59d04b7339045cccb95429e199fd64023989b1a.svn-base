/**
 * 
 */
package seven.progpracticum;

import java.awt.Dimension;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;

/**
 * The toolbar to hold buttons for changing panel background.
 * @author svb140
 * @version 1
 */
public class ToolBar extends JToolBar {
    /**
     * Serial Version of toolbar.
     */
    private static final long serialVersionUID = 1L;
    /**
     * The button to change the background color to red.
     */
    private JToggleButton myRed;
    /**
     * The button to change the background color to  blue.
     */
    private JToggleButton myBlue;
    /**
     * The button to change the background color to yellow.
     */
    private JToggleButton myYellow;
    /**
     * The button to change the background color to white.
     */
    private JToggleButton myWhite;
    /**
     * The group for all the background changing buttons.
     */
    private ButtonGroup myColors;
    
    private JToggleButton myLine;
    private boolean myDrawLine;
    
    /**
     * Creates a toolbar to hold the buttons for changing the background color.
     */
    public ToolBar() {
        myDrawLine = false;
        addButtons();
    }
    
    /**
     * Adds the buttons to the toolbar.
     */
    private void addButtons() {
        myRed = new JToggleButton(new ImageIcon("red-ball.gif"));
        myBlue = new JToggleButton(new ImageIcon("blue-ball.gif"));
        myYellow = new JToggleButton(new ImageIcon("yellow-ball.gif"));
        myWhite = new JToggleButton(new ImageIcon("whiteball.gif"));
        myLine = new JToggleButton("/");
        
        myRed.setMnemonic(KeyEvent.VK_R);
        myBlue.setMnemonic(KeyEvent.VK_B);
        myYellow.setMnemonic(KeyEvent.VK_Y);
        myWhite.setMnemonic(KeyEvent.VK_W);
        
        final Dimension size = myRed.getSize();
        myBlue.setPreferredSize(size);
        myYellow.setPreferredSize(size);
        myWhite.setPreferredSize(size);
        myLine.setPreferredSize(size);
        
        myRed.setName("red");
        myBlue.setName("blue");
        myYellow.setName("yellow");
        myWhite.setName("white");
        myLine.setName("line");
        
        myRed.setToolTipText("Red");
        myBlue.setToolTipText("Blue");
        myYellow.setToolTipText("Yellow");
        myWhite.setToolTipText("White");
        myLine.setToolTipText("draw line");
        myColors = new ButtonGroup();
        myColors.add(myRed);
        myColors.add(myBlue);
        myColors.add(myYellow);
        myColors.add(myWhite);
        
        add(myRed);
        add(myBlue);
        add(myYellow);
        add(myWhite);
        add(myLine);
    }
    
    /**
     * Adds the given listener to the toolbar buttons.
     * @param aListener the listener to listen for events on toolbar.
     */
    public void addColorListener(ItemListener aListener) {
        myRed.addItemListener(aListener);
        myBlue.addItemListener(aListener);
        myYellow.addItemListener(aListener);
        myWhite.addItemListener(aListener);
        myLine.addItemListener(aListener);
    }
    
    /**
     * Gets the button to change background color to white.
     * @return myWhite the button to change background color to white.
     */
    public JToggleButton getWhiteButton() {
        return myWhite;
    }
    
    /**
     * Gets the button to change background color to red.
     * @return myRed the button to change background color to red.
     */
    public JToggleButton getRedButton() {
        return myRed;
    }
    
    /**
     * Gets the button to change background color to blue.
     * @return myBlue the button to change background color to blue.
     */
    public JToggleButton getBlueButton() {
        return myBlue;
    }
    
    /**
     * Gets the button to change background color to yellow.
     * @return myYellow the button to change background color to yellow.
     */
    public JToggleButton getYellowButton() {
        return myYellow;
    }
    
    public JToggleButton getDrawLine() {
        return myLine;
    }
    
    public boolean drawLine() {
        return myDrawLine;
    }
    
    public void setDrawLine(boolean flag) {
        myDrawLine = flag;
    }

}
