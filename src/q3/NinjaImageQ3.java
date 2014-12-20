package q3;

import ninja.ImageSensei;

public class NinjaImageQ3 extends ImageSensei {

	public int[][] removeNoise(int[][] image, int dimension) {
		int rows = image.length;
		int cols = image[0].length;
		int[][] imageFiltered = new int[rows][cols];

		int[][] mask = new int[dimension][dimension];
		mask = fillMask(mask);
		
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				
				int pixel = calculateMedian(mask, image, i, j); 
				imageFiltered[i][j] = cropPixel(pixel);
				
			}
		}
		
		return imageFiltered;
	}

	private int[][] fillMask(int[][] mask) {
		for (int i = 0; i < mask.length; i++) {
			for (int j = 0; j < mask[0].length; j++) {
				mask[i][j] = -1;
			}
		}
		
		return mask;
	}

	private int calculateMedian(int[][] mask, int[][] image, int i, int j) {
		
		// Checking margin of mask filter to using on for statement
		int marginVert = mask[0].length / 2;
		int up = marginVert;
		int down = marginVert;
		
		int marginHoriz = mask.length / 2;
		int left = marginHoriz;
		int right = marginHoriz;
		
		if (mask.length % 2 == 0)
			left--;
		if (mask[0].length % 2 == 0)
			up--;
		
		for (int r = i - up, r2 = 0; r <= i + down; r++, r2++) {
			for (int c = j - left, c2 = 0; c <= j + right; c++, c2++) {
				
				if (checkBounds(image, r, c)) {
					mask[r2][c2] = image[r][c];
				}
				
			}
		}
		
		int[] vectorMedian = sort(mask);
		int middle = vectorMedian.length / 2;
		int median = 0;
		
		if (vectorMedian.length > 0) {
			
			if (vectorMedian.length % 2 != 0 || vectorMedian.length == 1) {
				median = vectorMedian[middle];
			} else {
				median = (vectorMedian[middle-1] + vectorMedian[middle]) / 2;
			}
			
		}
	    
	    return median;
	}
	
	private int[] sort(int[][] mask) {
		int[] result = new int[mask.length * mask[0].length];
		int count = 0;
		
		// Matrix to Array
		for (int i = 0; i < mask.length; i++) {
			for (int j = 0; j < mask[0].length; j++) {
				result[count] = mask[i][j];
				count++;
			}
		}
		
		// Bubble Sort
		int aux = 0;
		for (int i = result.length - 1; i >= 1; i--) {
			for (int j = 0; j < i ; j++) {
		    	if (result[j] > result[j+1]) {
		    		aux = result[j];
		    		result[j] = result[j+1];
		    		result[j+1] = aux;
		    	}
		     }
		}
		
		// Remove each instance of number -1 
		count = 0;
		for (int i = 0; i < result.length; i++) {
			if (result[i] == -1) {
				count++;
			}
		}
		int result2[] = new int[result.length - count];
		for (int i = count,j=0; i < result.length; i++,j++) {
			result2[j] = result[i];
		}
		
		return result2;
	}
}
