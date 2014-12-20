package q5;

import java.util.ArrayList;
import java.util.List;

import ninja.ImageSensei;

public class NinjaImageQ5 extends ImageSensei {

	public int[][] segmentation(int[][] image) {
		int rows = image.length;
		int cols = image[0].length;

		int aux = 0;
		int rotulo = 0;
		int qtdRotulo = 1;
		
		int[][] imglabel = new int[rows][cols];
		int[] mapa = new int[rows], novomapa = new int[rows];
		List<Integer> lista = new ArrayList<Integer>();

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				
				if (image[i][j] == BLACK) {
					lista = listarRotulosVizinhos(i, j);
					if (lista.isEmpty()) {
						
						rotulo = qtdRotulo;
						mapa[rotulo] = 0;
						qtdRotulo++;
						
					} else {
						
						lista = sort(lista);
						rotulo = lista.get(0);
						for (int a = 1; a < lista.size(); a++) {
							aux = lista.get(a);
							while (mapa[aux] != 0 && mapa[aux] != rotulo) {
								aux = mapa[aux];
							}
							
							mapa[aux] = rotulo;
						}
						
					}
					
					imglabel[i][j] = rotulo;
					RotularVizinhosSemRotulo(i, j, rotulo);
					
				}
				
			}
		}
		
		
		for(int a = 0; a < mapa.length; a++) {
			aux = mapa[a]; 
			rotulo = aux;
			
			while(aux!= 0){
				rotulo = aux; 
				aux = mapa[aux]; 
			}
			
			novomapa[a] = rotulo; 
		}
		
		
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				
				if (novomapa[imglabel[i][j]] == 0 )
					image[i][j] = imglabel[i][j];
				else
					image[i][j] = novomapa[imglabel[i][j]];
				
			}
		}
		
		return image;
	}

	private List<Integer> listarRotulosVizinhos(int i, int j) {
		
		return null;
	}

	private List<Integer> sort(List<Integer> list) {
		int temp = 0;
		for (int x=0; x < list.size(); x++) {
            for (int i=0; i < list.size()-i; i++) {
            	if (list.get(i).compareTo(list.get(i+1)) > 0) {
                    temp = list.get(i);
                    list.set(i,list.get(i+1) );
                    list.set(i+1, temp);
                }
            }
        }
		
		return list;
	}

	private void RotularVizinhosSemRotulo(int i, int j, int rotulo) {
		
	}

}