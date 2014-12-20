package dithering;

import java.io.IOException;

import ninja.ImageSensei;

public class Dithering extends ImageSensei {
	
	public static void main(String[] args) throws IOException {
		Dithering de = new Dithering();
		
		int[][] image = de.readImage("imgs/original/lena.pgm");
		double limiar = 128;
		double[][] diffusion = {{0, 0.375}, {0.375, 0.25}};
		image = de.diffusionError(image, limiar, diffusion);
		
		de.saveImage(image, true);
	}
	
	public int[][] diffusionError(int[][] image, double limiar, double[][] diffusion) {
		int rows = image.length;
		int cols = image[0].length;
		int px = 0;
		
		double error = 0.0;
		for (int i = 1; i < rows; i++) {
			for (int j = 1; j < cols; j++) {
				
				px = image[i][j];
				image[i][j] = px > limiar ? 255 : 0;
				error = px - image[i][j];
				
				propagateError(diffusion, error, image, i, j);
			}
		}
		
		return image;
	}
	
	public void propagateError(double[][] diffusion, double error, int[][] image, int i, int j) {
		for (int k = 0; 			k < diffusion.length; 			k++) {
			for (int k2 = 0; 		k2 < diffusion[0].length; 		k2++) {
				
				if (checkBounds(image, i + k, j + k2)){
					Double v = diffusion[k][k2] * error;
					image[i + k][j + k2] += v.intValue();
					image[i + k][j + k2] = cropPixel(image[i + k][j + k2]);
				}
				
			}
		}

	}

}
