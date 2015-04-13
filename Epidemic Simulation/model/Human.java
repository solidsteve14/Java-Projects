/**
 * 
 */
package epidemic.progpracticum.model;

import java.awt.Point;

/**
 * @author solid_000
 *
 */
public class Human extends AbstractEntity {

    public Human(char aSpecies, boolean aSick, int aDayNum, Point aLocation) {
        super(aSpecies, aSick, aDayNum, aLocation);
    }

    @Override
    public void move() {
        final Point location = super.getMyLocation();
        final int move = SimParams.GENERATOR.nextInt(8);
        if (move == 0) {        //move left
            location.x -= SimParams.HUMAN_DIM;           
        } else if (move == 1) { //move up and left
            location.x -= SimParams.HUMAN_DIM;
            location.y += SimParams.HUMAN_DIM;           
        } else if (move == 2) { //move up
            location.y += SimParams.HUMAN_DIM;          
        } else if (move == 3) { //move up and right
            location.x += SimParams.HUMAN_DIM;
            location.y += SimParams.HUMAN_DIM;   
        } else if (move == 4) { //move right
            location.x += SimParams.HUMAN_DIM;            
        } else if (move == 5) { //move down and right
            location.x += SimParams.HUMAN_DIM;
            location.y -= SimParams.HUMAN_DIM;           
        } else if (move == 6) { //move down
            location.y -= SimParams.HUMAN_DIM;            
        } else if (move == 7) { //move down and left
            location.x -= SimParams.HUMAN_DIM;
            location.y -= SimParams.HUMAN_DIM;            
        }
        
        if (location.x < 0) {
            location.x += SimParams.PANEL_PIX_WIDTH;
        } else if (location.x >= SimParams.PANEL_PIX_WIDTH) {
            location.x -= SimParams.PANEL_PIX_WIDTH;
        }
        
        if (location.y < 0) {
            location.y += SimParams.PANEL_PIX_HEIGHT;
        } else if (location.y >= SimParams.PANEL_PIX_HEIGHT) {
            location.y -= SimParams.PANEL_PIX_HEIGHT;
        }

        super.setMyLocation(location);
        
    }

}
