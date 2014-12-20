package q3;

import java.io.IOException;

/**
 * Implemente um método que remova os ruídos da imagem ruido.pgm. Ao final indique qual
 * técnica usou para isso.
 * 
 * 
 * ==> FILTRO DA MEDIANA
 * 
 */
public class RemoveNoise {
	public static NinjaImageQ3 ninja = new NinjaImageQ3();
	
	public static void main(String[] args) throws IOException {
		int[][] image = ninja.readImage("imgs/original/ruido.pgm");
		int dimension = 4;

		image = ninja.removeNoise(image, dimension);
		
		ninja.saveImage(image, true);
	}

}