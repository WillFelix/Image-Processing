package q1;

import ninja.ImageSensei;

public class NinjaImageQ1 extends ImageSensei {
	
	// Image's Scale
	private int fmin;
	private int fmax;

	/**
	 * Calculate the fMin
	 */
	private void calculateFmin(int[][] matrix) {
		fmin = 255;

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (matrix[i][j] < fmin) {
					fmin = matrix[i][j];
				}
			}
		}
	}

	/**
	 * Calculate the fMax
	 */
	private void calculateFmax(int
			[][] matrix) {
		fmax = 0;

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (matrix[i][j] > fmax)
					fmax = matrix[i][j];
			}
		}

	}

	/**
	 * Change the Image's Scale
	 * 
	 * @param gmin
	 * @param gmax
	 */
	public void changeScale(int[][] image, int gmin, int gmax) {
		calculateFmin(image);
		calculateFmax(image);
		
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				
				System.out.println("((" + gmax + " - " +  gmin + ") / (" + fmax + " - " + fmin + ")) * (" + image[i][j] + " - " + fmin  +") + " + gmin);
				image[i][j] = ((gmax - gmin) / (fmax - fmin)) * (image[i][j] - fmin) + gmin;

			}
		}
	}

	/**
	 * Transform the image in a negative image
	 */
	public int[][] negative(int[][] image) {
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {

				image[i][j] = Math.abs(Lmax - image[i][j]);

			}
		}
		
		return image;
	}

	/**
	 * Change the image contrast
	 * 
	 * @param val
	 */
	public void contrast(double val, int[][] image) {
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {

				image[i][j] = (int) (image[i][j] * val);
				if (image[i][j] < 0)
					image[i][j] = 0;
				if (image[i][j] > 255)
					image[i][j] = 255;
			}
		}
	}

	/**
	 * Change the image bright
	 * 
	 * @param val
	 */
	public void bright(int val, int[][] image) {
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {

				image[i][j] = image[i][j] + val;
				if (image[i][j] < 0)
					image[i][j] = 0;
				if (image[i][j] > 255)
					image[i][j] = 255;
			}
		}
	}

	public void equalize(int[][] image) {
		int h[] = imHist(image);
		
		printArray(h);
//		double hist[] = new double[256];
//		double g[] = new double[256];
//		int f[] = new int[256];
//		int pixelsAmount = height * width;
//		
//		// hist(k) = h(k) / n
//		for (int i = 0; i < h.length; i++) {
//			hist[i] = ((double) h[i]) / pixelsAmount;
//		}
//		
//		// g(k) = hist(i), onde tem-se um somatorio de i até Lmax
//		for (int k = 0; k < g.length; k++) {
//			for (int i = k; i >= 0; i--) {
//				g[k] += hist[i];
//			}
//		}
//		
//		// f(k) = g(k) * Lmax
//		for (int k = 0; k < g.length; k++) {
//			f[k] = Integer.parseInt(String.valueOf(Math.round(g[k] * Lmax)));
//		}
//		
//		for (int i = 0; i < height; i++) {
//			for (int j = 0; j < width; j++) {
//				image[i][j] = f[image[i][j]];
//			}
//		}
	}
	
}
