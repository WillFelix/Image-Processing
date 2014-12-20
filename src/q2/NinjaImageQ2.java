package q2;

import ninja.ImageSensei;

public class NinjaImageQ2 extends ImageSensei {
	private int border; 

	// BORDER TYPES
	public static final int BORDER_CONVULATION = 1;
	public static final int BORDER_REPLICATION = 2;
	public static final int BORDER_ZERO = 3;
	public static final int BORDER_DESPISE = 4;
	public static final int BORDER_KEEP = 5;
	
	public int[][] filter(int[][] image, int border, int[][] mask, int factor) {
		this.border = border;
		
		switch (border) {
			case NinjaImageQ2.BORDER_CONVULATION:
//				image = convulationImage(image);
				break;
			case NinjaImageQ2.BORDER_REPLICATION:
				image = replicateBorder(image);
				break;
			case NinjaImageQ2.BORDER_ZERO:
				image = zeroBorder(image);
				break;
			default:
				break;
		}
		
		image = applyMask(image, mask, factor);
		return image;
	}
	
	private int[][] replicateBorder(int[][] image) {
		int rows = image.length;
		int cols = image[0].length;
		int[] up = new int[cols];
		int[] down = new int[cols];
		int[][] imgRepl = new int[rows + 2][cols + 2];
		
		
		for (int j = 0; j < cols; j++) {
			up[j] = image[0][j];
			down[j] = image[rows - 1][j];
		}
		
		
		for (int i = 0; i < rows + 2; i++) {
			for (int j = 1; j < cols + 1; j++) {
				
				if (i == 0) {
					imgRepl[i][j] = up[j - 1];
				} else if (i == rows + 1) {
					imgRepl[i][j] = down[j - 1];
				} else {
					imgRepl[i][j] = image[i - 1][j - 1];
				}
				
			}
		}
		
		
		for (int i = 0; i < rows + 2; i++) {
			imgRepl[i][0] = imgRepl[i][1];
			imgRepl[i][cols - 1] = imgRepl[i][cols - 2];
		}
		
		return imgRepl;
	}

	public int[][] zeroBorder(int[][] image) {
		int rows = image.length;
		int cols = image[0].length;
		
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (i == 0 || j == 0 || i == rows - 1 || j == cols -1){
					image[i][j] = 0;
				}
			}
		}
		
		return image;
	}
	
	private int[][] applyMask(int[][] image, int[][] mask, int factor) {
		int rows = image.length;
		int cols = image[0].length;
		int[][] imageFiltered = new int[rows][cols];
		
		for (int i = 1; i < rows - 1; i++) {
			for (int j = 1; j < cols - 1; j++) {
				
				int pixel = calculateMask(mask, image, i, j); 
				imageFiltered[i][j] = cropPixel(pixel / factor);
				
			}
			
			if (i % 200 == 0) System.out.print(".");
		}
		
		return imageFiltered;
	}

	private int calculateMask(int[][] mask, int[][] image, int i, int j) {
		int result = 0;
		int mRows = mask.length;
		int mCols = mask[0].length;
		int center = mRows / 2;
		if (mRows % 2 == 0) center--;
		
		switch (this.border) {
			case NinjaImageQ2.BORDER_DESPISE:
				for (int k = 0, cr = center; k < mRows; k++, cr--) {
					for (int k2 = 0, cc = center; k2 < mCols; k2++, cc--) {
						
						if (checkBounds(image, i - cr, j - cc)){
							result += image[i - cr][j - cc] * mask[k][k2];
						}
						
					}
				}
				
				break;
				
			case NinjaImageQ2.BORDER_KEEP:
				for (int k = 0, cr = center; k < mRows; k++, cr--) {
					for (int k2 = 0, cc = center; k2 < mCols; k2++, cc--) {
						if (i != 0 && j != 0 && i != image.length - 1 && j != image[0].length - 1) {
							result += image[i - cr][j - cc] * mask[k][k2];
						}
					}
				}
				
				break;
				
			default:
				for (int k = 0, cr = center; k < mRows; k++, cr--) {
					for (int k2 = 0, cc = center; k2 < mCols; k2++, cc--) {
						
						result += image[i - cr][j - cc] * mask[k][k2];
						
					}
				}
				break;
		}
		
		return result;
	}
}
