package q2;

import java.io.IOException;
import java.util.Scanner;

/**
 * Implemente um método que realiza uma filtragem na imagem !. Parâmetros:
 * 		
 * 		a) Imagem
 * 		b) Tipo de cuidado com a borda
 * 		c) Máscara utilizada
 * 		d) Fator multiplicador
 */
public class Filter {
	public static NinjaImageQ2 ninja = new NinjaImageQ2();
	
	public static void main(String[] args) throws IOException {
		int[][] mask;
		int border = 0, factor = 0;
		
		Scanner input = new Scanner(System.in);
		
		do {
			int[][] image = ninja.readImage("imgs/original/lena.pgm");
			System.out.println("Aspectos Avançados em Computação (Filtro)");
			System.out.println("---------------------------------------");
			System.out.println("");
			System.out.println("Primeiro você deve escolher o tipo de cuidado de borda que será usado:");
			System.out.println("\t1. Convulação");
			System.out.println("\t2. Replicar Borda");
			System.out.println("\t3. Zerar Borda");
			System.out.println("\t4. Desprezar Borda");
			System.out.println("\t5. Manter Borda");
			System.out.println("\t6. Sair do Programa");
			System.out.print("\nEscolha uma opção: ");
			
			border = input.nextInt();
			if (border == 6) {
				System.exit(0);
			}
			
			
			System.out.println("---------------------------------------");
			System.out.println("");
			System.out.println("Agora você deve informar qual a máscara que será usada:");
			System.out.print("Dimensões da máscara (linha x coluna): ");
			String dimensions = input.next();
			int height = Integer.parseInt(dimensions.split("\\s?(?i)x\\s?")[0]);
			int width = Integer.parseInt(dimensions.split("\\s?(?i)x\\s?")[1]);
			mask = new int[height][width];
			
			System.out.println("Digite os valores da máscara:");
			for (int i = 0; i < height; i++) {
				for (int j = 0; j < width; j++) {
					System.out.print("Linha " + i + ", Coluna " + j + ": ");
					mask[i][j] = input.nextInt();
				}
			}
			
			
			System.out.println("---------------------------------------");
			System.out.println("");
			System.out.println("Por fim, coloque o fator multiplicativo que será utilizado. (Digite 1 para ignorá-lo)");
			factor = input.nextInt();
			
			
			System.out.println("---------------------------------------");
			System.out.println("");
			image = ninja.filter(image, border, mask, factor);
			
			ninja.saveImage(image, true);
			input.reset();
			
		} while(border != 6);
		
		input.close();
	}

}