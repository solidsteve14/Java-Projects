/**
 * 
 */
package epidemic.progpracticum.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.util.List;

import javax.swing.JPanel;

import epidemic.progpracticum.model.AbstractEntity;
import epidemic.progpracticum.model.SimParams;
import epidemic.progpracticum.model.SimulationWorld;

/**
 * Used for drawing the humans and birds for simulation at after each update.
 * @author svb140
 *
 */
public class WorldPanel extends JPanel {
    private List<AbstractEntity> myEntities;
    private SimulationWorld myWorld;
    
    public WorldPanel(SimulationWorld aWorld) {
        super();
        myWorld = aWorld;
        setPreferredSize(new Dimension(SimParams.PANEL_PIX_WIDTH, SimParams.PANEL_PIX_HEIGHT));
        setBackground(Color.WHITE);
        myEntities = myWorld.getEntities();
        
    }
       
    public SimulationWorld getMyWorld() {
        return myWorld;
    }
    
    public void setEntities(List<AbstractEntity> aList) {
        myEntities = aList;
    }

    @Override
    public void paintComponent(final Graphics aGraphics) {
        super.paintComponent(aGraphics);
        final Graphics2D g2d = (Graphics2D) aGraphics; 
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        drawEntities(g2d);
    }
    
    private void drawEntities(final Graphics2D aGraphics) {
        for (AbstractEntity entity : myEntities) {
            final int x = entity.getMyLocation().x;
            final int y = entity.getMyLocation().y;
            if (entity.getMySpecies() == 'h') {
                if (entity.isSick()) {
                    aGraphics.setColor(Color.red);
                } else {                    
                    aGraphics.setColor(Color.green);
                }
                final Ellipse2D human = new Ellipse2D.Double(x, y, SimParams.HUMAN_DIM, SimParams.HUMAN_DIM);
                aGraphics.fill(human);
            } else {
                if (entity.isSick()) {
                    aGraphics.setColor(Color.orange);
                } else {
                    aGraphics.setColor(Color.gray);
                }
                final Ellipse2D bird = new Ellipse2D.Double(x, y, SimParams.BIRD_DIM, SimParams.BIRD_DIM);
                aGraphics.fill(bird);
            }
        }
    }
    

}
