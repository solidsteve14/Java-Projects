import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.*;

/**
 * This is a simple renderer.
 * 
 * @author John Mayer, Srdjan Stojcic
 * @version 01.28.2014
 */

@SuppressWarnings("serial")
public class PaintPanel458 extends JPanel implements KeyListener {
	
	private static JFrame frame; // the frame to resize (pack) when dimensions are changed
	
    private int width;
    private int height;
    private int imageSize;
    private int[] pixels;  
    private int[] color;	// Keeps track of rgb values of the color to be used
    private Range[] coords; // Keeps track of pixel coordinates
    private double[][] ctm; // Current transformation matrix
    private double[][] itm; // Interactive transformation matrix
    private double[][] projection;
    private double[][] lookat;
    private double[][] zbuffer;
    private double[][] scanbuffer;
    private boolean frustum = false; //true is frustum false is orthographic
    private boolean force_frustum = false;
    private boolean force_ortho = false;
    private String path; // keeps track of the path
    
    void drawPixel(int x, int y, int r, int g, int b) {
        pixels[(height-y-1)*width*3+x*3] = r;
        pixels[(height-y-1)*width*3+x*3+1] = g;
        pixels[(height-y-1)*width*3+x*3+2] = b;                
    }
    
    void createImage() {    
    	// Get file path name unless we have it already
    	if(path == null) {
	    	Scanner pathScanner = new Scanner(System.in);
	        System.out.println("Please enter the file path:");
	        path = pathScanner.nextLine();
	        pathScanner.close();
    	}
        try {
			Scanner fileScanner = new Scanner(new File(path));
			Scanner lineScanner;
			String command;
			color = new int[3];
			

			
			// Scan all lines for commands and handle them accordingly
			while(fileScanner.hasNextLine()) {
				lineScanner = new Scanner(fileScanner.nextLine());
				command = lineScanner.next();
				switch(command) {
					case "DIM": 
						setDimensions(lineScanner.nextInt(), lineScanner.nextInt());
						doBackground();
						break;
					case "RGB":
						color[0] = Math.round(lineScanner.nextFloat() * 255);
						color[1] = Math.round(lineScanner.nextFloat() * 255);
						color[2] = Math.round(lineScanner.nextFloat() * 255);
						break;
					case "LINE":
						drawLine(lineScanner.nextFloat(), 
								lineScanner.nextFloat(),
								lineScanner.nextFloat(), 
								lineScanner.nextFloat(),
								lineScanner.nextFloat(),
								lineScanner.nextFloat());
						break;
					case "TRI":
						drawTriangle(lineScanner.nextFloat(), 
								lineScanner.nextFloat(),
								lineScanner.nextFloat(),
								lineScanner.nextFloat(),
								lineScanner.nextFloat(), 
								lineScanner.nextFloat(),
								lineScanner.nextFloat(),
								lineScanner.nextFloat(),
								lineScanner.nextFloat());
						break;
					case "LOAD_IDENTITY_MATRIX":
						ctm = Matrix.identity(4);
						break;
					case "TRANSLATE":
						ctm = Matrix.translate(ctm, 
								lineScanner.nextFloat(), 
								lineScanner.nextFloat(), 
								lineScanner.nextFloat());
						break;
					case "ROTATEX":
						ctm = Matrix.rotateX(ctm, lineScanner.nextFloat());
						break;
					case "ROTATEY":
						ctm = Matrix.rotateY(ctm, lineScanner.nextFloat());
						break;
					case "ROTATEZ":
						ctm = Matrix.rotateZ(ctm, lineScanner.nextFloat());
						break;	
					case "SCALE":
						ctm = Matrix.scale(ctm, 
								lineScanner.nextFloat(), 
								lineScanner.nextFloat(), 
								lineScanner.nextFloat());
						break;	
					case "WIREFRAME_CUBE":
						drawWireframeCube();
						break;
					case "SOLID_CUBE":
						drawSolidCube();
						break;
					case "LOOKAT":
						lookat = Matrix.makeCamera(lineScanner.nextFloat(), lineScanner.nextFloat(), lineScanner.nextFloat(), 
													lineScanner.nextFloat(), lineScanner.nextFloat(), lineScanner.nextFloat(), 
													lineScanner.nextFloat(), lineScanner.nextFloat(), lineScanner.nextFloat());
						break;
					case "FRUSTUM":
						if (!force_ortho) {
							projection = Matrix.makeFrustum(lineScanner.nextFloat(), lineScanner.nextFloat(), lineScanner.nextFloat(), 
													lineScanner.nextFloat(), lineScanner.nextFloat(), lineScanner.nextFloat());
							frustum = true;
						} else {
							projection = Matrix.makeOrtho(lineScanner.nextFloat(), lineScanner.nextFloat(), lineScanner.nextFloat(), 
									lineScanner.nextFloat(), lineScanner.nextFloat(), lineScanner.nextFloat());
							frustum = true;
						}
						
						break;
					case "ORTHO":
						if (!force_frustum) {
							projection = Matrix.makeOrtho(lineScanner.nextFloat(), lineScanner.nextFloat(), lineScanner.nextFloat(), 
													lineScanner.nextFloat(), lineScanner.nextFloat(), lineScanner.nextFloat());
							frustum = true;
						} else {
							projection = Matrix.makeFrustum(lineScanner.nextFloat(), lineScanner.nextFloat(), lineScanner.nextFloat(), 
									lineScanner.nextFloat(), lineScanner.nextFloat(), lineScanner.nextFloat());
							frustum = true;
						}
						break;						
				}
			}
			fileScanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("File was not found.");
			frame.dispose();
			e.printStackTrace();
		}
    }
    
    /**
     * Draws a Wireframe Cube.
     */
    private void drawWireframeCube() {
		drawLine(0.5f, 0.5f, 0.5f, 0.5f, -0.5f, 0.5f);
		drawLine(0.5f, -0.5f, 0.5f, -0.5f, -0.5f, 0.5f);
		drawLine(-0.5f, -0.5f, 0.5f, -0.5f, 0.5f, 0.5f);
		drawLine(-0.5f, 0.5f, 0.5f, 0.5f, 0.5f, 0.5f);
		drawLine(0.5f, 0.5f, -0.5f, 0.5f, -0.5f, -0.5f);
		drawLine(0.5f, -0.5f, -0.5f, -0.5f, -0.5f, -0.5f);
		drawLine(-0.5f, -0.5f, -0.5f, -0.5f, 0.5f, -0.5f);
		drawLine(-0.5f, 0.5f, -0.5f, 0.5f, 0.5f, -0.5f);
		drawLine(0.5f, 0.5f, 0.5f, 0.5f, 0.5f, -0.5f);
		drawLine(0.5f, -0.5f, 0.5f, 0.5f, -0.5f, -0.5f);
		drawLine(-0.5f, -0.5f, 0.5f, -0.5f, -0.5f, -0.5f);
		drawLine(-0.5f, 0.5f, 0.5f, -0.5f, 0.5f, -0.5f);
	}
    
    /**
     * Draws a Solid Cube.
     */
    public void drawSolidCube() {
    	//front
    	drawTriangle(-0.5f, -0.5f, -0.5f, -0.5f,  0.5f, -0.5f,  0.5f,  0.5f, -0.5f);
    	drawTriangle(-0.5f, -0.5f, -0.5f,  0.5f, -0.5f, -0.5f,  0.5f,  0.5f, -0.5f);   	
    	//left
    	drawTriangle(-0.5f, -0.5f, -0.5f, -0.5f, -0.5f,  0.5f, -0.5f,  0.5f,  0.5f);  	
    	drawTriangle(-0.5f, -0.5f, -0.5f, -0.5f,  0.5f, -0.5f, -0.5f,  0.5f,  0.5f);
    	//bottom
    	drawTriangle(-0.5f, -0.5f, -0.5f,  0.5f, -0.5f, -0.5f, 0.5f, -0.5f, 0.5f);
    	drawTriangle(-0.5f, -0.5f, -0.5f, -0.5f, -0.5f,  0.5f, 0.5f, -0.5f, 0.5f);
    	//right
    	drawTriangle(0.5f, -0.5f, -0.5f, 0.5f, -0.5f,  0.5f,  0.5f, 0.5f, 0.5f);
    	drawTriangle(0.5f, -0.5f, -0.5f, 0.5f,  0.5f, -0.5f,  0.5f, 0.5f, 0.5f);
    	//top
    	drawTriangle(-0.5f, 0.5f, -0.5f, -0.5f, 0.5f,  0.5f, 0.5f, 0.5f, 0.5f);
    	drawTriangle(-0.5f, 0.5f, -0.5f,  0.5f, 0.5f, -0.5f, 0.5f, 0.5f, 0.5f);
    	//back
    	drawTriangle(-0.5f, -0.5f, 0.5f, -0.5f, 0.5f,  0.5f,  0.5f,  0.5f, 0.5f);
    	drawTriangle(-0.5f, -0.5f, 0.5f,  0.5f, -0.5f, 0.5f,  0.5f,  0.5f, 0.5f);
    }

    /**
     * Draws the background.
     */
	private void doBackground() {
		zbuffer = new double[height][width];
		scanbuffer = new double[height][width];
        for (int x = 0; x < width; x++) {
           for (int y = 0; y < height; y++) {
               drawPixel(x, y, 255,255,255);
               if(frustum) {
            	   zbuffer[y][x] = 10;
                   scanbuffer[y][x] = 10;
               } else {
            	   zbuffer[y][x] = -10;
                   scanbuffer[y][x] = -10;
               }
               

           }
        }
    }
    
    /**
     * Draws a triangle with the given coordinates.
     * 
     * @param sx0 X coordinate of the first point.
     * @param sy0 Y coordinate of the first point.
     * @param sz0 Z coordinate of the first point.
     * @param sx1 X coordinate of the second point.
     * @param sy1 Y coordinate of the second point.
     * @param sz1 Z coordinate of the second point.
     * @param sx2 X coordinate of the third point.
     * @param sy2 Y coordinate of the third point.
     * @param sz2 Z coordinate of the third point.
     */
    private void drawTriangle(float sx0, float sy0, float sz0, 
    		float sx1, float sy1, float sz1, 
    		float sx2, float sy2, float sz2) {
    	double[] point0 = transformPoint(sx0, sy0, sz0);
    	double[] point1 = transformPoint(sx1, sy1, sz1);
    	double[] point2 = transformPoint(sx2, sy2, sz2);
    	
		// initialize array to keep track of all pixel positions
    	coords = new Range[height];
    	float w0 = (float)point0[3];
    	int x0 = worldToScreenX((float)point0[0]/w0);
    	int y0 = worldToScreenY((float)point0[1]/w0);
    	double z0 = (float)point0[2];
    	float w1 = (float)point1[3];
    	int x1 = worldToScreenX((float)point1[0]/w1);
    	int y1 = worldToScreenY((float)point1[1]/w1);
    	double z1 = (float)point1[2];
    	float w2 = (float)point2[3];
    	int x2 = worldToScreenX((float)point2[0]/w2);
    	int y2 = worldToScreenY((float)point2[1]/w2);
    	double z2 = (float)point2[2];
    	
    	
		drawLine(x0, y0, z0, x1, y1, z1, true);
		drawLine(x0, y0, z0, x2, y2, z2, true);
		drawLine(x1, y1, z1, x2, y2, z2, true);
		
		int largest_y = findLargestNum(y0, y1, y2);	
		int smallest_y = findSmallestNum(y0, y1, y2);	
		
		for (int i = smallest_y; i <= largest_y; i++) {
			// only draw the lines that we've covered/initialized
			if (coords[i] != null) {
				z1 = scanbuffer[i][coords[i].min];
				z2 = scanbuffer[i][coords[i].max];
				drawLine(coords[i].min, i, z1, coords[i].max, i, z2, false);
			}	
		}	
	}


    /**
     * Finds and returns the largest number out of the three numbers given.
     * 
     * @param sy0 The first number.
     * @param sy1 The second number.
     * @param sy2 The third number.
     * @return The largest number of the three given numbers.
     */
	private int findLargestNum(int sy0, int sy1, int sy2) {
		int largest;
		
		if (sy0 > sy1) {
			largest = Math.max(sy0, sy2);
		} else {
			largest = Math.max(sy1, sy2);
		}
		return largest;
	}

	/**
	 * Finds and returns the largest number out of the three numbers given.
	 * 
	 * @param sy0 The first number.
	 * @param sy1 The second number.
	 * @param sy2 The third number.
	 * @return The largest number of the three given numbers.
	 */
	private int findSmallestNum(int sy0, int sy1, int sy2) {
		int smallest;
		
		if (sy0 < sy1) {
			smallest = Math.min(sy0, sy2);
		} else {
			smallest = Math.min(sy1, sy2);
		}
		return smallest;
	}

	/**
     * Draws a line given the coordinates.
     * 
     * @param sx1 X coordinate of the first point.
     * @param sy1 Y coordinate of the first point.
     * @param sz1 Z coordinate of the first point.
     * @param sx2 X coordinate of the second point.
     * @param sy2 Y coordinate of the second point.
     * @param sz2 Z coordinate of the second point.
     */
    private void drawLine(float sx1, float sy1, float sz1, float sx2, float sy2, float sz2) {
    	double[] point1 = transformPoint(sx1, sy1, sz1);
    	double[] point2 = transformPoint(sx2, sy2, sz2);
    	
    	float w1 = (float)point1[3];
    	int x1 = worldToScreenX((float)point1[0]/w1);
    	int y1 = worldToScreenY((float)point1[1]/w1);
    	double z1 = (float)point1[2]/w1;
    	
    	float w2 = (float)point2[3];
    	int x2 = worldToScreenX((float)point2[0]/w2);
    	int y2 = worldToScreenY((float)point2[1]/w2);
    	double z2 = (float)point2[2]/w2;

    	drawLine(x1, y1, z1, x2, y2, z2, false);
	}
    
    private double[] transformPoint(float x, float y, float z) {
    	double[] vector = {x, y, z, 1};
    	return Matrix.multiply(projection, Matrix.multiply(lookat, Matrix.multiply(itm, Matrix.multiply(ctm, vector))));
    }
    
    /**
     * Draws a line given the coordinates if triangle = false and
     * it just keeps the coordinates of where each pixel would be drawn when
     * triangle = true (in this case it doesn't draw the pixels, just keeps track
     * of their locations).
     * 
     * @param sx1 X coordinate of the first point.
     * @param sy1 Y coordinate of the first point.
     * @param sx2 X coordinate of the second point.
     * @param sy2 Y coordinate of the second point.
     * @param triangle Decides whether the method should draw the pixels (false) or just
     * 				   keep track of them (true);
     */
    private void drawLine(int sx1, int sy1, double sz1, int sx2, int sy2, double sz2, boolean triangle) {
    	float slope;
    	// avoids division by 0 and sets appropriate slope value
		if (sx2 - sx1 == 0) {
			slope = Float.MAX_VALUE;
		} else {
			slope = (float) (sy2 - sy1) / (sx2 - sx1); 
		}
		
		int start, end;
		double startz, endz, currentz, dz;
		if (slope >= -1 && slope <= 1) {
			float y;
			// choose start and end points
			if (sx1 < sx2) {
				start = sx1;
				end = sx2;
				startz = sz1;
				endz = sz2;				
				y = sy1;
				
			} else {
				start = sx2;
				end = sx1;
				startz = sz2;
				endz = sz1;
				y = sy2;
			}
			
			dz = (endz-startz)/(end-start);
			currentz = startz;
			
			for(int x = start; x <= end; x++) {			
				int newY = Math.round(y);
				
				if (newY >= height) {
					newY = height - 1;
				}
				
				if (triangle) {
					// just keep track of where pixels would be placed
					if (coords[newY] == null) {
						coords[newY] = new Range(x,x);
					} else {
						if (coords[newY].min > x) {
							coords[newY].min = x;
						} else if (coords[newY].max < x) {
							coords[newY].max = x;
						}	
					}
				} else {
					if(frustum) {
						if (currentz < zbuffer[newY][x]) {
							zbuffer[newY][x] = currentz;
							drawPixel(x, newY, color[0], color[1], color[2]);
						}
					} else {
						if (currentz > zbuffer[newY][x]) {
							zbuffer[newY][x] = currentz;
							drawPixel(x, newY, color[0], color[1], color[2]);
						}
					}
				}
				scanbuffer[newY][x] = currentz;
				y += slope;
				currentz += dz;
			}
		} else if (slope > 1  || slope < -1) {
			
			float x;
			// choose start and end points
			if (sy1 < sy2) {
				start = sy1;
				end = sy2;
				startz = sz1;
				endz = sz2;	
				x = sx1;
			} else {
				start = sy2;
				end = sy1;
				startz = sz2;
				endz = sz1;	
				x = sx2;
			}
			
			dz = (endz-startz)/(end-start);
			currentz = startz;
						
			for(int y = start; y <= end; y++) {
				
				int newX = Math.round(x);
				if (newX >= width) {
					newX = width - 1;
				}
				
				if (triangle) {
					// keep track of where the pixels would be placed
					if (coords[y] == null) {
						coords[y] = new Range(newX, newX);
					} else {
						if (coords[y].min > newX) {
							coords[y].min = newX;
						} else if (coords[y].max < newX) {
							coords[y].max = newX;
						}
					}
				} else {
					if (frustum) {
						if (currentz < zbuffer[y][newX]) {
							zbuffer[y][newX] = currentz;
							drawPixel(newX, y, color[0], color[1], color[2]);
						}
					} else {
						if (currentz > zbuffer[y][newX]) {
							zbuffer[y][newX] = currentz;
							drawPixel(newX, y, color[0], color[1], color[2]);
						}
					}
				}
				scanbuffer[y][newX] = currentz;
				x += 1/slope;
				currentz += dz;
			}
		} 
    }
    
    
    /**
     * Converts an X world coordinate to a screen coordinate.
     * 
     * @param x_world Coordinate to convert.
     * @return The screen coordinate of the given world coordinate as an int.
     */
    private int worldToScreenX(float x_world) {
    	return Math.round((width-1.0f) * (x_world + 1.0f) / 2.0f);
    }
    
    /**
     * Converts a Y world coordinate to a screen coordinate.
     * 
     * @param y_world Coordinate to convert.
     * @return The screen coordinate of the given world coordinate as an int.
     */
    private int worldToScreenY(float y_world) {
    	return Math.round((height-1.0f) * (y_world + 1.0f) / 2.0f);
    }
   
    /**
     * Sets the dimensions of the panel to the given size.
     *
     * @param width The width.
     * @param height The height.
     */
	private void setDimensions(int width, int height) {
    	this.width = width;
    	this.height = height;
    	imageSize = width * height;
    	pixels = new int[imageSize * 3];
    	Dimension newDim = new Dimension(width,height);
    	setPreferredSize(newDim);
    	frame.pack();
	}

	public PaintPanel458() {
		setFocusable(true);
		addKeyListener(this);
		ctm = Matrix.identity(4);
		itm = Matrix.identity(4);
		projection = Matrix.identity(4);
		lookat = Matrix.identity(4);
		createImage();
    }
    
    public static void main(String args[]) {
        frame = new JFrame("Paint Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new PaintPanel458());
        frame.pack();      
        frame.setLocationRelativeTo( null );
        frame.setVisible( true );
    }

    public void paintComponent(Graphics g) {
        createImage();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        WritableRaster raster = image.getRaster();
        raster.setPixels(0, 0, width, height, pixels);        
        g.drawImage(image, 0, 0, null);
    }

	public void keyReleased(KeyEvent e) {
   
   }

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP) {
            itm = Matrix.rotateX(itm, -3);
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
        	itm = Matrix.rotateX(itm, 3);     
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
        	itm = Matrix.rotateY(itm, -3);
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {  
        	itm = Matrix.rotateY(itm, 3);
        } else if (e.getKeyCode() == KeyEvent.VK_P) {  
        	force_frustum = true;
        	force_ortho = false;
        } else if (e.getKeyCode() == KeyEvent.VK_O) {  
        	force_frustum = false;
        	force_ortho = true;
        }    
        repaint(); 

	}
	
	public void keyTyped(KeyEvent arg0) {}
	
	/**
	 * Keeps track of the X ranges for lines that fill the triangles.
	 */
	private class Range {
		private int min;
	    private int max;
		
	    Range(int mi, int ma) {
	        min = mi;
	        max = ma;
	    }
	}
}
