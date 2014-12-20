package q4;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import ninja.ImageSensei;
import ninja.PhotoNinja;

/**
 * Pesquise um algoritmo de binarização e codifique-o. Após a codificação:
 * 
 * a) Execute o programa utilizando as imagens disponíveis no site da professora
 * b) Avalie o algoritmo utilizando as métricas de avaliação baseadas na matriz de confusão.
 * 	  E apresente uma tabela com os resultados e as imagens resultantes.
 * c) Faça uma breve análise sobre os resultados obtidos.
 * d) Na data estipulada no site da professora apresente o algoritmo, os resultados obtidos à
 * 	  turma e entregue o relatório de binarização para a professora.
 * 
 * 
 * H01 - 0.87
 * H02 - 0.90
 * H03 - 0.75
 * H04 - 0.90
 * H05 - 0.55
 * H06 - 0.80
 * H07 - 0.80
 * H08 - 0.86
 * H09 - 1
 * H10 - 0.90
 * H11 - 0.90
 * H12 - 0.35
 * H13 - 0.78
 * H14 - 0.55
 * H15 - 0.68
 * 
 * HW1 - 0.45
 * HW2 - 0.84
 * HW3 - 0.85
 * HW4 - 0.55
 * HW5 - 0.55
 * HW6 - 0.80
 * HW7 - 0.50
 * HW8 - 0.81
 * 
 * P01 - 0.65
 * P02 - 0.70
 * P03 - 0.85
 * P04 - 0.50
 * P05 - 0.47
 * 
 */
public class Binarization {
	public static NinjaImageQ4 ninja = new NinjaImageQ4();
	
	public static void main(String[] args) throws IOException {
//		wellnerAlgorithm(true);
//		generateReport();
	}
	
	
	@SuppressWarnings("unused")
	private static void wellnerAlgorithm(boolean showImage) throws IOException {
		double pct = 0.47;
		
		int[][] image = ninja.readImage("challenges/chessboard.pgm");
		int[][] wellner = ninja.binarization(image, pct);
		ninja.saveImage(wellner, showImage);
	}
	

	@SuppressWarnings("unused")
	private static void generateReport() throws IOException {
		List<PhotoNinja> images = null, perfects = null, wellners = null;
		Scanner input = new Scanner(System.in);
		double pct = 0;
		
		// Read all images and apply the thresholding
		images = ninja.readImages("imgs/original/Binarization/img");
		wellners = new ArrayList<PhotoNinja>();
		for (PhotoNinja image : images) {
			System.out.print("\n\nDigite o pct para a imagem " + image.getName() + ": ");
			pct = input.nextDouble();
			
			int[][] wellner = ninja.binarization(image.getPixels(), pct);
			ninja.saveImage(wellner, false);
			
			image.setPixels(wellner);
			image.setPct(pct);
			wellners.add(image);
		}
		
		// Read all images on GT's folder and check all metrics in wellner's images
		perfects = ninja.readImages("imgs/original/Binarization/GT");
		for (PhotoNinja perfect : perfects) {
			for (PhotoNinja wellner : wellners) {
				
				if (perfect.getName().equals(wellner.getName())) {
					ninja.imageMetrics(perfect.getPixels(), wellner);
					break;
				}
				
			}
		}
		
		ImageSensei.exportCSV(wellners, true);
		
		input.close();
	}

}