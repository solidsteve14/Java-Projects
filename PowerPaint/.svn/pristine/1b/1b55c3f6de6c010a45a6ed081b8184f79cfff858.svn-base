/**
 * 
 */
package seven.progpracticum;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * @author svb140
 * @version 1
 *
 */
public class MenuBar extends JMenuBar {
    /**
     * The file menu.
     */
    private JMenu myFileMenu;
    /**
     * The options menu.
     */
    private JMenu myOptionsMenu;
    /**
     * The button to draw thin line.
     */
    private JRadioButtonMenuItem myThinLine;
    /**
     * The button to draw a reguar line.
     */
    private JRadioButtonMenuItem myRegularLine;
    /**
     * The button to draw a thick line.
     */
    private JRadioButtonMenuItem myThickLine;
    /**
     * The button to add red to pen color.
     */
    private JCheckBoxMenuItem myRed;
    /**
     * The button to add green to pen color.
     */
    private JCheckBoxMenuItem myGreen;
    /**
     * The button to add blue to the pen color.
     */
    private JCheckBoxMenuItem myBlue;
    /**
     * The help menu.
     */
    private JMenu myHelpMenu;
    /**
     * The panel to make changes to.
     */
    private PaintPanel myPanel;
    /**
     * The toolbar to sync with new action.
     */
    private ToolBar myToolBar;
    
    private JMenuItem mySaveFile;
    private JMenuItem myOpenFile;
    private FileAction myFileAction;
    private JFileChooser myFilePicker;
    private BufferedImage myImage;
    
    /**
     * Creates a menuBar and adds all submenu items.
     * @param aToolBar the toolbar to sync.
     */
    public MenuBar(final ToolBar aToolBar) {
        super();
        myToolBar = aToolBar;
        myFileMenu = new JMenu("File");
        myOptionsMenu = new JMenu("Options");
        myHelpMenu = new JMenu("Help");
        
        myFileMenu.setMnemonic(KeyEvent.VK_F);
        myOptionsMenu.setMnemonic(KeyEvent.VK_O);
        myHelpMenu.setMnemonic(KeyEvent.VK_H);
        
        myFileAction = new FileAction();
        setup();       
    }
    
    /**
     * Sets the paint panel to change to the given panel.
     * @param aPanel the panel to make changes to on events.
     */
    public void setPaintPanel(PaintPanel aPanel) {
        myPanel = aPanel;
        
        final ActionListener changeColorListener = 
                new ChangeColorListener(myPanel, myRed, myGreen, myBlue);
        myRed.addActionListener(changeColorListener);
        myGreen.addActionListener(changeColorListener);
        myBlue.addActionListener(changeColorListener);
        
    }
   
    /**
     * Sets up the menubar.
     */
    private void setup() {
        setupMyFileMenu();
        setupMyOptionsMenu();
        
        add(myHelpMenu);
        final JMenuItem about = myHelpMenu.add("About");
    }
    
    /**
     * Sets up the file menu.
     */
    private void setupMyFileMenu() {
        add(myFileMenu);
        final JMenuItem newFile = myFileMenu.add(new AbstractAction("New") {
            public void actionPerformed(ActionEvent anEvent) {
                myPanel.getShapes().clear();
                myPanel.getWidths().clear();
                myPanel.getColors().clear();
                myPanel.setColor(Color.black);
                myPanel.setWidth(new BasicStroke(1));
                myPanel.resetPoints();
                myRed.setSelected(false);
                myGreen.setSelected(false);
                myBlue.setSelected(false);
                myThinLine.setSelected(true);
                myToolBar.getWhiteButton().setSelected(true);
                myToolBar.getDrawLine().setSelected(false);
                myToolBar.setDrawLine(false);
                myPanel.repaint();
            }
        });
        newFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        myOpenFile = myFileMenu.add("Open");
        mySaveFile = myFileMenu.add("Save");
        mySaveFile.addActionListener(myFileAction);
        myOpenFile.addActionListener(myFileAction);
        
        mySaveFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        //saveFile.setEnabled(false);
                
        final JMenu printSubMenu = new JMenu("Print");
        final JMenuItem print = printSubMenu.add("Print");
        final JMenuItem printPreview = printSubMenu.add("Print preview...");
        myFileMenu.add(printSubMenu);
        
        myFileMenu.addSeparator(); 
        
        final JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent anEvent) {
                System.exit(0);
            }
        });  
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
        myFileMenu.add(exit);

    }
    
    /**
     * Sets up the options menu.
     */
    private void setupMyOptionsMenu() {
        add(myOptionsMenu);
        final JMenu lineWidthMenu = new JMenu("Line Width");  
        myOptionsMenu.add(lineWidthMenu);       
        myOptionsMenu.addSeparator();
        
        myThinLine = new JRadioButtonMenuItem("1");
        myRegularLine = new JRadioButtonMenuItem("2");
        myThickLine = new JRadioButtonMenuItem("3");
        
        myThinLine.setName("thin");
        myRegularLine.setName("regular");
        myThickLine.setName("thick");
        
        final ButtonGroup lineWidths = new ButtonGroup();
        lineWidths.add(myThinLine);
        lineWidths.add(myRegularLine);
        lineWidths.add(myThickLine);
        
        myThinLine.setSelected(true);
       
        lineWidthMenu.add(myThinLine);
        lineWidthMenu.add(myRegularLine);
        lineWidthMenu.add(myThickLine);
        
        final JMenu lineColorMenu = new JMenu("Line Color");
        myOptionsMenu.add(lineColorMenu);
        
        myRed = new StayOpenCheckBoxMenuItem("R");
        myGreen = new StayOpenCheckBoxMenuItem("G");
        myBlue = new StayOpenCheckBoxMenuItem("B");
        
        myRed.setName("red");
        myGreen.setName("green");
        myBlue.setName("blue");
        
        lineColorMenu.add(myRed);
        lineColorMenu.add(myGreen);
        lineColorMenu.add(myBlue);
    }
    
    /**
     * Adds the given listener to listen for events on the menu bar for line width.
     * @param aListener the listener to listen for events about line width.
     */
    public void addStrokeChangeListener(ActionListener aListener) {
        myThinLine.addActionListener(aListener);
        myRegularLine.addActionListener(aListener);
        myThickLine.addActionListener(aListener);
    }
    
    /**
     * @author svb140
     *
     */
    public class FileAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent anEvent) {
            if (myFilePicker == null) {
                myFilePicker = new JFileChooser();
            }            
            final Object source = anEvent.getSource();
            myFilePicker.resetChoosableFileFilters();
            
            int select = -1;           
            if (source == mySaveFile) {                
                myFilePicker.setFileFilter(new FileNameExtensionFilter(
                                                       "GIF", "*.gif", "gif"));
                myFilePicker.setFileFilter(new FileNameExtensionFilter(
                                                       "JPEG", "*.jpg", "jpg"));
                myFilePicker.setFileFilter(new FileNameExtensionFilter(
                                                       "PNG", "*.png", "png"));
                myFilePicker.setFileFilter(new FileNameExtensionFilter(
                                                       "Bitmap", "*.bmp", "bmp"));
                select = myFilePicker.showSaveDialog(null);
            } else if (source == myOpenFile) {
                myFilePicker.setFileFilter(new FileNameExtensionFilter("Image Files",
                                                           "GIF", "JPEG", "PNG", "Bitmap"));
                select = myFilePicker.showOpenDialog(null);
            }
            File result;
            
            if (select == myFilePicker.APPROVE_OPTION) {
                result = myFilePicker.getSelectedFile();
                if (result == null) {
                    return;
                }
                if (source == mySaveFile) {
                   String extension = myFilePicker.getFileFilter().getDescription().substring(0,3);
                   saveImage(extension, result.getPath());
                } else if (source == myOpenFile) {
                    openImage(result);
                }
            }
            
        }
               
    }
    
    /**
     * Saves the current image to the give path.
     */
    public void saveImage(String aType, String aPath) {
        myImage = new BufferedImage(myPanel.getWidth(),
                                    myPanel.getHeight(), BufferedImage.TYPE_INT_RGB);
        final Graphics2D g = myImage.createGraphics();
        myPanel.paintAll(g);   
        try {
            ImageIO.write(myImage, aType, new File(aPath + "." + aType));
        } catch (IOException e) {
            System.out.println("Could not print file");
        }
    }
    
    /**
     * Opens the selected file.
     * @param aFile the file to open.
     */
    public void openImage(final File aFile) {
        myImage = null;
        try {
            myImage = ImageIO.read(aFile);
        } catch (final IOException e) {
            e.printStackTrace();
        }
        myPanel.addImage(myImage);
    }
    
}
