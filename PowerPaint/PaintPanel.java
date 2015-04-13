/*
 * TCSS 305
 */

package seven.progpracticum;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.util.ArrayList;
import java.util.List;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.image.BufferedImage;

import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JToggleButton;
import javax.swing.SwingUtilities;

/**
 * The JPanel upon which the text will be painted.
 * 
 * @author svb140
 * @version 1
 */
@SuppressWarnings("serial")
public class PaintPanel extends JPanel {
  
    /** A ToolKit. */
    private static final Toolkit KIT = Toolkit.getDefaultToolkit();
    
    /** The Dimension of the screen. */
    private static final Dimension SCREEN_SIZE = KIT.getScreenSize();
    
    /** The width of the screen. */
    private static final int SCREEN_WIDTH = SCREEN_SIZE.width/2;
    
    /** The height of the screen. */
    private static final int SCREEN_HEIGHT = SCREEN_SIZE.height/2;
    
    /** The default size for this JPanel. */
    private static final Dimension DEFUALT_SIZE = new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT);
    
    /** 
     * The start point for paths to draw.
     */
    private Point myFromPoint;
    /**
     * The end point for paths to draw.
     */
    private Point myToPoint;
    /** 
     * The adapter to use mouse input.
     */
    private MouseDoodleAdapter myAdapter;
    /**
     * The current path to draw.
     */
    private GeneralPath myPath;
    /**
     * The current color to draw.
     */
    private Color myColor;
    /**
     * The current width to draw.
     */
    private BasicStroke myWidth;
    /**
     * List of all paths drawn.
     */
    private List<Shape> myPaths;
    /**
     * List of all colors for each path drawn.
     */
    private List<Color> myColors;
    /**
     * List of each width for each path drawn.
     */
    private List<BasicStroke> myWidths;
    /**
     * Menu bar to listen for events on.
     */
    private MenuBar myMenuBar;
    private ToolBar myToolBar;
    
    private BufferedImage myImg;

  /**
   * Construct this JPanel.
   * @param aMenu the menu bar to listen for events on.
   * 
   */
    public PaintPanel(final MenuBar aMenu, final ToolBar aToolBar) {
        super();
        myMenuBar = aMenu;
        myToolBar = aToolBar;
        createPanel();
    }
    
    public void addImage(BufferedImage anImage) {
        myImg = anImage;
    }
    
    /**
     * Adds the required elements to the panel.
     */
    private void createPanel() {
        final ActionListener strokeChange = new StrokeChangeListener();
        myMenuBar.addStrokeChangeListener(strokeChange);
        setPreferredSize(DEFUALT_SIZE);
        setBackground(Color.WHITE);
        myAdapter = new MouseDoodleAdapter();
        addMouseListener(myAdapter);
        addMouseMotionListener(myAdapter);
        myPath = new GeneralPath();
        myColor = new Color(0, 0, 0);
        myWidth = new BasicStroke(1);
        myPaths = new ArrayList<Shape>();
        myColors = new ArrayList<Color>();
        myWidths = new ArrayList<BasicStroke>();
    }
    
    /**
     * Gets the current color to draw.
     * @return myColor the current pen color.
     */
    public Color getColor() {
        return myColor;
    }
    
    /**
     * Sets the color to draw to the given color.
     * @param aColor the color to set.
     */
    public void setColor(Color aColor) {
        myColor = aColor;
        
    }
    
    /**
     * Sets the panel width to the given width.
     * @param aWidth the width to set.
     */
    public void setWidth(BasicStroke aWidth) {
        myWidth = aWidth;
    }
    
    /**
     * Gets the list of shapes drawn.
     * @return myPaths the list of shapes drawn.
     */
    public List<Shape> getShapes() {
        return myPaths;
    }
    
    /**
     * Gets the list of widths for each shape drawn.
     * @return myWidths the list of widths for each shape drawn.
     */
    public List<BasicStroke> getWidths() {
        return myWidths;
    }
    
    /**
     * Gets the list of colors for each shape drawn.
     * @return myColors the list of colors for each shape drawn.
     */
    public List<Color> getColors() {
        return myColors;
    }
    
    public void resetPoints() {
        myFromPoint = null;
        myToPoint = null;
    }
        
    
    /** {@inheritDoc} */
    @Override
    public void paintComponent(final Graphics aGraphics) {
        super.paintComponent(aGraphics);      
        final Graphics2D g2d = (Graphics2D) aGraphics;
        g2d.drawImage(myImg, 0, 0, null);
        if (myFromPoint != null) {
            for (int i = 0; i < myPaths.size(); i++) {
                g2d.setColor(myColors.get(i));
                g2d.setStroke(myWidths.get(i));
                g2d.draw(myPaths.get(i));
            }
            g2d.setStroke(myWidth);
            g2d.setColor(myColor);
            if (myToolBar.drawLine()) {
                g2d.drawLine(myFromPoint.x,myFromPoint.y, myToPoint.x, myToPoint.y);
            }
            g2d.draw(myPath);
        }
    }
    
    /**
     * The adapter to listen for mouse input.
     * @author svb140
     * @version 1
     *
     */
    private class MouseDoodleAdapter extends MouseAdapter {
        
        @Override 
        public void mousePressed(MouseEvent anEvent) {
            if (SwingUtilities.isLeftMouseButton(anEvent)) {
                myFromPoint = anEvent.getPoint();
                myToPoint = anEvent.getPoint();
            }
        }
        
        @Override
        public void mouseDragged(MouseEvent anEvent) {
            if (SwingUtilities.isLeftMouseButton(anEvent)) {
                myToPoint = anEvent.getPoint();
                
                if (!myToolBar.drawLine()) {
                    myPath.append(new Line2D.Double(myFromPoint, myToPoint), true);
                    myFromPoint = myToPoint; 
                }
                repaint();
            }
            
        }
        
        @Override
        public void mouseReleased(MouseEvent anEvent) {
            if (SwingUtilities.isLeftMouseButton(anEvent)) {
                if (myToolBar.drawLine()) {
                    Line2D line = new Line2D.Double(myFromPoint.x,myFromPoint.y, myToPoint.x, myToPoint.y);
                    myPaths.add(line);
                } else {
                    myPaths.add((Shape) new GeneralPath(myPath));
                }
                myColors.add(myColor);
                myWidths.add(myWidth);
                myPath.reset();
                repaint();
            }
        }
    }
    
    /**
     * The listener to listen for events involving changing stroke width of pen.
     * @author svb140
     * @version 1
     *
     */
    private class StrokeChangeListener implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent anEvent) {
            final Object source = anEvent.getSource();
            if (source.getClass().getName().equals("javax.swing.JRadioButtonMenuItem")) {
                final JRadioButtonMenuItem origin = (JRadioButtonMenuItem) source;
                if (origin.getName().equals("thin")) {
                    myWidth = new BasicStroke(1);                 
                } else if (origin.getName().equals("regular")) {
                    myWidth = new BasicStroke(3);                   
                } else if (origin.getName().equals("thick")) {
                    myWidth = new BasicStroke(5);  
                }
            } 
        }

    }

}
