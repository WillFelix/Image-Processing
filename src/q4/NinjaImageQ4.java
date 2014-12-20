package q4;

import ninja.ImageSensei;

public class NinjaImageQ4 extends ImageSensei {
	
	public int[][] binarization(int[][] image, double pct) {
		int rows = image.length;
		int cols = image[0].length;
		int window = cols / 8;
		int fs = 127 * window;
		int[][] result = new int[rows][cols];
		
		System.out.print("Thresholding image...");
		for (int i = 0; i < rows; i++) {
			
			if (i % 2 == 0) {
				
				for (int j = 0; j < cols; j++) {
					double percent = (fs/window) * pct;
					int pixel = image[i][j];
					if (j < cols - 1){
						pixel = image[i][j + 1];
					} else if (j == cols - 1 && i < rows - 1){
						pixel = image[i + 1][0];
					}
					
					fs = fs - (fs / window) + pixel;
					result[i][j] = image[i][j] < percent ? 0 : 255;
				}
				
			} else {
				
				for (int j = cols - 1; j >= 0; j--) {
					double percent = (fs/window) * pct;
					int pixel = image[i][j];
					if (j < cols - 1){
						pixel = image[i][j + 1];
					} else if (j == cols - 1 && i < rows - 1){
						pixel = image[i + 1][0];
					}
					
					fs = fs - (fs / window) + pixel;
					result[i][j] = image[i][j] < percent ? 0 : 255;
				}
				
			}
			
		}
		
		
		System.out.println("\nFinished...");
		
		return result;
	}

}