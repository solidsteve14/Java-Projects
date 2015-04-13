/**
 * This class multiplies and rotates matrices.
 * 
 * @author Srdjan Stojcic
 * @version 1.28.2014
 */
public class Matrix {

    /**
     * This method returns a nxn identity matrix
     * 
     * @param n length and width of the matrix
     * @return A nxn identity matrix
     */
    public static double[][] identity(int n) {
        double[][] result = new double[n][n];
        for (int i = 0; i < n; i++) {
        	result[i][i] = 1;
        }
        return result;
    }

    /**
     * Multiplies two matrices and returns the resulting matrix.
     * 
     * @param matrix1 First matrix to multiply.
     * @param matrix2 Second matrix to multiply.
     * @return The resulting matrix after the multiplication.
     */
    public static double[][] multiply(double[][] matrix1, double[][] matrix2) {
    	double[][] result = null;
    	
    	int aHeight = matrix1.length;
        int aWidth = matrix1[0].length;
        int bHeight = matrix2.length;
        int bWidth = matrix1[0].length;
        
        if (aWidth == bHeight) {
        	result = new double[aHeight][bWidth];
            for (int i = 0; i < aHeight; i++) {
            	for (int j = 0; j < bWidth; j++) {
            		for (int k = 0; k < aWidth; k++) {
            			result[i][j] += (matrix1[i][k] * matrix2[k][j]);
            		}    
            	}       
            }       
        }
        return result;
    }
    
    /**
     * This method multiplies a matrix with a vector (or a point)
     * 
     * @param matrix The matrix.
     * @param vector The vector/point.
     * @return The resulting vector/point.
     */
    public static double[] multiply(double[][] matrix, double[] vector) {
    	double[] result = null;
    	
        int matrixHeight = matrix.length;
        int matrixWidth = matrix[0].length;
        
        if (vector.length == matrixWidth) {
        	result = new double[matrixHeight];
            for (int i = 0; i < matrixHeight; i++) {
            	for (int j = 0; j < matrixWidth; j++) {
            		result[i] += (matrix[i][j] * vector[j]);
            	}
            }           
        }
        return result;
    }
    
	public static double[][] makeCamera(double eyeX, double eyeY, double eyeZ, double atX, double atY, double atZ, double upX, double upY, double upZ) {
		double[][] result = identity(4);
		Vec4 eye = new Vec4(eyeX, eyeY, eyeZ, 0);
		Vec4 at = new Vec4(atX, atY, atZ, 0);
		Vec4 up = new Vec4(upX, upY, upZ, 0);
		
		Vec4 n = eye.subtract(at);
		n.normalize();
		Vec4 u = up.crossWith(n);
		u.normalize();
		Vec4 v = n.crossWith(u);
		v.normalize();
		
		result[0][0] = u.getX();
		result[0][1] = u.getY();
		result[0][2] = u.getZ();
		result[0][3] = u.getW();
		
		result[1][0] = v.getX();
		result[1][1] = v.getY();
		result[1][2] = v.getZ();
		result[1][3] = v.getW();
		
		result[2][0] = n.getX();
		result[2][1] = n.getY();
		result[2][2] = n.getZ();
		result[2][3] = n.getW();
		
		return Matrix.translate(result, -eye.getX(), -eye.getY(), -eye.getZ());		
	}
	
	//FRUSTUM left right bottom top near far
	public static double[][] makeFrustum(double l, double r, double b, double t, double n, double f) {
		double[][] result = identity(4);
		result[0][0] = (2*n)/(r-l);
		result[0][2] = (r+l)/(r-l);	
		result[1][1] = (2*n)/(t-b);
		result[1][2] = (t+b)/(t-b);
		result[2][2] = (-(f+n))/(f-n);
		result[2][3] = (-2*f*n)/(f-n);
		result[3][2] = -1;
		result[3][3] = 0;
		
		return result;
	}
	
	//ORTHO left right bottom top near far
	public static double[][] makeOrtho(double l, double r, double b, double t, double n, double f) {
		double[][] result = identity(4);
		result[0][0] = (2)/(r-l);
		result[0][3] = (r+l)/(r-l);
		result[1][1] = (2)/(t-b);
		result[1][3] = (t+b)/(t-b);
		result[2][2] = (-2)/(f-n);
		result[2][3] = (f+n)/(f-n);
		return result;
	}
    
    public static double[][] rotateX(double[][] matrix, double degree) {
    	double[][] rotationMatrix = new double[4][4];
    	double angle = Math.toRadians(degree);
    	rotationMatrix[0][0] = 1;
    	rotationMatrix[1][1] = Math.cos(angle);
    	rotationMatrix[1][2] = -1 * Math.sin(angle);
    	rotationMatrix[2][1] = Math.sin(angle);
    	rotationMatrix[2][2] = Math.cos(angle);
    	rotationMatrix[3][3] = 1;
    	
    	return Matrix.multiply(rotationMatrix, matrix);
    }
    
    public static double[][] rotateY(double[][] matrix, double degree) {
    	double[][] rotationMatrix = new double[4][4];
    	double angle = Math.toRadians(degree);
    	rotationMatrix[0][0] = Math.cos(angle);
    	rotationMatrix[0][2] = Math.sin(angle);
    	rotationMatrix[1][1] = 1;
    	rotationMatrix[2][0] = - 1 * Math.sin(angle);
    	rotationMatrix[2][2] = Math.cos(angle);
    	rotationMatrix[3][3] = 1;
    	
    	return Matrix.multiply(rotationMatrix, matrix);
    }
    
    public static double[][] rotateZ(double[][] matrix, double degree) {
    	double[][] rotationMatrix = new double[4][4];
    	double angle = Math.toRadians(degree);
    	rotationMatrix[0][0] = Math.cos(angle);
    	rotationMatrix[0][1] = - 1 * Math.sin(angle);
    	rotationMatrix[1][0] = Math.sin(angle);
    	rotationMatrix[1][1] = Math.cos(angle);
    	rotationMatrix[2][2] = 1;
    	rotationMatrix[3][3] = 1;
    	
    	return Matrix.multiply(rotationMatrix, matrix);
    }
    
    public static double[][] scale(double[][] matrix, double x, double y, double z) {
    	double[][] scalingMatrix = new double[4][4];
    	scalingMatrix[0][0] = x;
    	scalingMatrix[1][1] = y;
    	scalingMatrix[2][2] = z;
    	scalingMatrix[3][3] = 1;
    	
    	return Matrix.multiply(scalingMatrix, matrix);
    }

    public static double[][] translate(double[][] matrix, double dx, double dy, double dz) {
    	double[][] translateMatrix = Matrix.identity(4);
    	translateMatrix[0][3] = dx;
    	translateMatrix[1][3] = dy;
    	translateMatrix[2][3] = dz;
    	
    	return Matrix.multiply(translateMatrix, matrix);
    }
}
