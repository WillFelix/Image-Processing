package q6;

import ninja.ImageSensei;

public class NinjaImageQ6 extends ImageSensei {
	
	public static final int PIXEL_ENABLE = 1;
	public static final int PIXEL_DISABLE = 0;
	
	public int[][] dilatation(int[][] image, int[][] mask) {
		int rows = image.length;
		int cols = image[0].length;
		int[][] result = new int[rows][cols];
		
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				result[i][j] = applyMask(mask, image, i, j);
			}
		}
		
		return result;
	}
	
	private int applyMask(int[][] mask, int[][] image, int i, int j) {
		int result = image[i][j];
		int mRows = mask.length;
		int mCols = mask[0].length;
		int center = mRows / 2;
		if (mRows % 2 == 0) center--;
		
		for (int k = 0, cr = center; k < mRows; k++, cr--) {
			for (int k2 = 0, cc = center; k2 < mCols; k2++, cc--) {
				
				if (mask[k][k2] == NinjaImageQ6.PIXEL_ENABLE) {
					if (checkBounds(image, i - cr, j - cc)) {
						result = image[i - cr][j - cc] >= result ? image[i - cr][j - cc] : result;
					}
				}
				
			}
		}
		
		return result;
	}

}