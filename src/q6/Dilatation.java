package q6;

import java.io.IOException;

import q1.NinjaImageQ1;

/**
 * Implemente um método para dilatar uma imagem de acordo com um elemento
 * estruturante
 * 
 */
public class Dilatation {
	public static NinjaImageQ6 ninja = new NinjaImageQ6();
	public static NinjaImageQ1 ninjaW = new NinjaImageQ1();
	
	public static void main(String[] args) throws IOException {
		int[][] image = ninja.readImage("imgs/original/Binarization/GT/H01.pgm");
		int[][] mask = {{1,0,0,0,0},{0,1,0,0,0},{0,0,1,0,0}, {0,0,0,1,0}, {0,0,0,0,1}};
		
//		image = ninjaW.negative(image);
		image = ninja.dilatation(image, mask);
		
		ninja.saveImage(image, true);
	}

}