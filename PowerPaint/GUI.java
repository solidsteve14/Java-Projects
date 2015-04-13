package seven.progpracticum;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JFrame;
import javax.swing.JToggleButton;

/**
 * The Graphical User Interface for this example program.
 * 
 * @author svb140
 * @version 1
 */
public class GUI {

    /** 
     * represents a frame.
     */
    private final JFrame myGuiFrame;

    /**
     * represents a top-level menu.
     */
    private final MenuBar myMenuBar;
    
    /** 
     * represents a toolbar.
     */
    private final ToolBar myToolBar;
    
    /**
     * represents a panel.
     */
    private final PaintPanel myPanel;
    
    /**
     * Popup menu from right click.
     */
    private final PopupMenu myPopup;

    /**
     * Construct the GUI.
     */
    public GUI() {
        myGuiFrame = new JFrame("Steven's Doodle Engine");   
        myToolBar = new ToolBar();
        myMenuBar = new MenuBar(myToolBar);
        myPanel = new PaintPanel(myMenuBar, myToolBar);
        myMenuBar.setPaintPanel(myPanel);
        myPopup = new PopupMenu(myToolBar);
        createGUI();
    }
    
    /**
     * Adds pieces of GUI to the frame.
     */
    private void createGUI() {
        final PanelColorListener colorListener = new PanelColorListener();
        myToolBar.getWhiteButton().setSelected(true);
       
        myGuiFrame.setJMenuBar(myMenuBar);
        myGuiFrame.add(myToolBar, BorderLayout.NORTH);
        myGuiFrame.add(myPanel, BorderLayout.CENTER);
        
        myPanel.setComponentPopupMenu(myPopup);
        myGuiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myGuiFrame.pack();
        myGuiFrame.setLocationRelativeTo(null);
        myGuiFrame.setVisible(true);
        myToolBar.addColorListener(colorListener);
    }
    
    /**
     * Listener to listen for events involving changing the background color.
     * @author svb140
     * @version 1
     *
     */
    private class PanelColorListener implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent anEvent) {
            final Object source = anEvent.getItemSelectable();
            if (source.getClass().getName().equals("javax.swing.JToggleButton")) {
                final JToggleButton origin = (JToggleButton) source;
                if (origin.getName().equals("red")) {
                    myPanel.setBackground(Color.red);                  
                } else if (origin.getName().equals("blue")) {
                    myPanel.setBackground(Color.blue);                  
                } else if (origin.getName().equals("yellow")) {
                    myPanel.setBackground(Color.yellow); 
                } else if (origin.getName().equals("white")) {
                    myPanel.setBackground(Color.white);
                } else if (origin.getName().equals("line")) {
                    if (myToolBar.getDrawLine().isSelected()) {
                        myToolBar.setDrawLine(true);
                    } else {
                        myToolBar.setDrawLine(false);
                    }
                }
            }
        }        
    }
}
