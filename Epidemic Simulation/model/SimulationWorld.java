/**
 * 
 */
package epidemic.progpracticum.model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import epidemic.progpracticum.view.ControlPanel;
import epidemic.progpracticum.view.SliderPanel;
import epidemic.progpracticum.view.StatsPanel;
import epidemic.progpracticum.view.WorldPanel;

/**
 * @author svb140
 *
 */
public class SimulationWorld {

    private int myHumanCount;
    private int mySickHumans;
    private int myHealthyHumans;
    
    private int myBirdCount;
    private int mySickBirds;
    private int myHealthyBirds;
    
    private int myInitialPercentSick;
    private int myInitialSickBirds;
    private SliderPanel mySliderPanel;
    
    private List<Point> myAllPoints;
    private List<AbstractEntity> myEntities;
    
    private WorldPanel myWorldPanel;
    private StatsPanel myStatsPanel;
    
    public SimulationWorld(final SliderPanel aSliderPanel) {
        mySliderPanel = aSliderPanel;
        makeAllPoints();
        makeEntities();
    }
   
    public void makeAllPoints() {
        myAllPoints = new ArrayList<Point>();
        for (int i = 0; i < SimParams.PANEL_PIX_WIDTH; i += SimParams.HUMAN_DIM) {
            for (int j = 0; j < SimParams.PANEL_PIX_HEIGHT; j += SimParams.HUMAN_DIM) {
                myAllPoints.add(new Point(i, j));
            }
        }
    }
    
    public void makeEntities() {
        myHumanCount = mySliderPanel.getHumans();
        myBirdCount = mySliderPanel.getBirds();
        myInitialPercentSick = mySliderPanel.getPercentSick();
        myInitialSickBirds = (int) Math.ceil(((double)myBirdCount) * myInitialPercentSick / 100.0);
        System.out.println(myInitialSickBirds);
        myHealthyHumans = mySliderPanel.getHumans();
        
        myEntities = new ArrayList<AbstractEntity>();
        for (int i = 0; i < myHumanCount; i++) {
            final int index = SimParams.GENERATOR.nextInt(myAllPoints.size() - 1);
            final Point location = myAllPoints.get(index);
            final AbstractEntity human =  new Human('h', false, 0, location);
            
            myEntities.add(human);
            myAllPoints.remove(location);
        }
        
        for (int i = 0; i < myBirdCount; i++) {
            final int index = SimParams.GENERATOR.nextInt(myAllPoints.size() - 1);
            final Point location = myAllPoints.get(index);
            final AbstractEntity bird =  new Bird('b', false, 0, location);
            if (i < myInitialSickBirds) {
                bird.setMyIsSick(true);
            }
            myEntities.add(bird);
            myAllPoints.remove(location);
        }
    }
    
    public void setMyWorldPanel(WorldPanel aWorldPanel) {
        myWorldPanel = aWorldPanel;
    }
    
    public void setMyStatsPanel(StatsPanel aStatsPanel) {
        myStatsPanel = aStatsPanel;
    }
    
    public void step() {
        moveAll();
        myWorldPanel.repaint();
        updateInfected();
    }
    
    public void reset() {
        myEntities = new ArrayList<AbstractEntity>();
        mySliderPanel.reset();
        myStatsPanel.getMyGenerateWorld().doClick();
    }
    
    private void updateInfected() {
        for (AbstractEntity entity1 : myEntities) {
            for (AbstractEntity entity2: myEntities) {
                if (entity1.getMyLocation().equals(entity2.getMyLocation())) {
                    if (!entity1.isSick() && entity2.isSick()) {
                        final int spread = SimParams.GENERATOR.nextInt(2);
                        if (spread == 0) {
                            entity1.setMyIsSick(true);
                        }
                    }
                }
            }
        }
    }
    
    private void moveAll() {
        for (AbstractEntity entity : myEntities) {
            entity.move();
        }
    }
    
    public List<AbstractEntity> getEntities() {
        return myEntities;
    }
}
