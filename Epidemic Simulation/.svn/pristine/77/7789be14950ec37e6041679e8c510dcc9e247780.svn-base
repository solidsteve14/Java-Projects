/**
 * 
 */
package epidemic.progpracticum.model;

import java.awt.Point;

/**
 * @author solid_000
 *
 */
public class Bird extends AbstractEntity {

    public Bird(char aSpecies, boolean aSick, int aDayNum, Point aLocation) {
        super(aSpecies, aSick, aDayNum, aLocation);
    }

    @Override
    public void move() {
        final Point location = super.getMyLocation();
        final int moves = SimParams.GENERATOR.nextInt(3);
        for (int i = 0; i < moves; i++) {
            final int move = SimParams.GENERATOR.nextInt(4);
            if (move == 0) {
                location.x -= SimParams.HUMAN_DIM; //move left
            } else if (move == 1) {
                location.x += SimParams.HUMAN_DIM; //move right
            } else if (move == 2) {
                location.y += SimParams.HUMAN_DIM; //move up
            } else if (move == 3) {
                location.y -= SimParams.HUMAN_DIM; //move down
            }
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
