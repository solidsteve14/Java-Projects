/**
 * 
 */
package seven.progpracticum;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

/**
 * @author svb140
 * @version 1
 *
 */
public class PopupMenu extends JPopupMenu {
    /**
     * The menu item to change the background color to red.
     */
    private JMenuItem myRed;
    /**
     * The menu item to change the background color to blue.
     */
    private JMenuItem myBlue;
    /**
     * The menu item to change the background color to yellow.
     */
    private JMenuItem myYellow;
    /**
     * The menu item to change the background color to white.
     */
    private JMenuItem myWhite;
    /**
     * The toolbar to sync the buttons with.
     */
    private ToolBar myToolBar;
    
    /**
     * Creates a popup menu and syncs actions with the given toolbar.
     * @param aToolBar the toolbar to sync actionw with.
     */
    public PopupMenu(final ToolBar aToolBar) {
        myToolBar = aToolBar;
        addMenuItems();

    }
    
    /**
     * Adds all menu items and their attributes to the popup menu.
     */
    private void addMenuItems() {
        myRed = new JMenuItem("red", new ImageIcon("red-ball.gif"));
        myBlue = new JMenuItem("blue", new ImageIcon("blue-ball.gif"));
        myYellow = new JMenuItem("yellow", new ImageIcon("yellow-ball.gif"));
        myWhite = new JMenuItem("white", new ImageIcon("whiteball.gif"));
        
        myRed.setName("red");
        myBlue.setName("blue");
        myYellow.setName("yellow");
        myWhite.setName("white");
        
        final ActionListener popupListener = new PopupColorListener();
        myRed.addActionListener(popupListener);
        myBlue.addActionListener(popupListener);
        myYellow.addActionListener(popupListener);
        myWhite.addActionListener(popupListener);
        
        add(myRed);
        add(myBlue);
        add(myYellow);
        add(myWhite);
    }
    
    /**
     * Listener for popup menu item events to change background color.
     * @author svb140
     * @version 1
     * 
     */
    private class PopupColorListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent anEvent) {
            final Object source = anEvent.getSource();          
            if (source == myRed) {
                myToolBar.getRedButton().setSelected(true);
            } else if (source == myBlue) {
                myToolBar.getBlueButton().setSelected(true);
            } else if (source == myYellow) {
                myToolBar.getYellowButton().setSelected(true);
            } else if (source == myWhite) {
                myToolBar.getWhiteButton().setSelected(true);
            }
            
        }

    }
}
