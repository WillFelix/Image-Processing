package q1;

import java.io.IOException;
import java.util.Scanner;

/**
 * Faça um programa que lê uma imagem e:
 * 
 * a) Altera o contraste: o usuário informa um valor real entre 0 e 2
 * b) Altera o brilho: o usuário informa um valor inteiro entre -255 a + 255
 * c) Negativa a imagem
 * d) Aplica a mudança de escala do histograma: o usuário informa o valor mínimo e máximo
 *    da nova escala onde o mínimo não pode ser menor do que 0 e o máximo não pode ser maior do que 255
 * e) Aplica a equalização do histograma
 * 
 */
public class Main {
	public static NinjaImageQ1 ninja = new NinjaImageQ1();

	public static void main(String[] args) throws IOException {
		Scanner input = new Scanner(System.in);
		int op = 6;
		
		do {
			int[][] image = ninja.readImage("imgs/original/lena.pgm");
			System.out.println("Aspectos Avançados em Computação");
			System.out.println("---------------------------------------");
			System.out.println("");
			System.out.println("1. Contraste");
			System.out.println("2. Brilho");
			System.out.println("3. Negativo");
			System.out.println("4. Mudança de Escala");
			System.out.println("5. Equalizar");
			System.out.println("6. Sair");
			System.out.print("Escolha uma opção: ");
			op = input.nextInt();
	
			switch (op) {
			case 1:
				double cval = 0.0;
				System.out.print("Insira um valor real entre 0 e 2: ");
				cval = input.nextDouble();
				
				ninja.contrast(cval, image);
				break;
			case 2:
				int bval = 0;
				System.out.print("Insira um valor inteiro entre -255 e 255: ");
				bval = input.nextInt();
				
				ninja.bright(bval, image);
				break;
			case 3:
				ninja.negative(image);
				
				break;
			case 4:
				int gmin = 0, gmax = 0;
				System.out.println("Insira dois valores inteiros, sendo o primeiro menor que o segundo e que estejam entre 0 e 255");
				gmin = input.nextInt();
				gmax = input.nextInt();
				
				ninja.changeScale(image, gmin, gmax);
				
				break;
			case 5:
				ninja.equalize(image);
				
				break;
	
			default:
				break;
			}
	
			ninja.saveImage(image, true);
		} while(op != 6);
		input.close();
	}

}
