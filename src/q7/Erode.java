package q7;

import java.io.IOException;

/**
 * Implemente um método para erodir uma imagem de acordo com um elemento estruturante
 * 
 */
public class Erode {
	public static NinjaImageQ7 ninja = new NinjaImageQ7();
	
	public static void main(String[] args) throws IOException {
		int[][] image = ninja.readImage("imgs/original/Binarization/GT/H01.pgm");
		int[][] mask = {{1,0,0,0,1},{0,1,0,1,0},{0,0,1,0,0}, {0,1,0,1,0}, {1,0,0,0,1}};
		
		image = ninja.erode(image, mask);
		
		ninja.saveImage(image, true);
	}

}