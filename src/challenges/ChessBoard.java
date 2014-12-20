package challenges;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ninja.ImageSensei;
import q6.NinjaImageQ6;


public class ChessBoard extends ImageSensei {
	public static final int BLACK = 0;
	
	
	public static void main(String[] args) throws Exception {
		ChessBoard chessboard = new ChessBoard();
		chessboard.init();
	}
	
	public void init() throws IOException {
//		NinjaImageQ4 ninja = new NinjaImageQ4();
		NinjaImageQ6 dilSennin = new NinjaImageQ6();
		
		int[][] image = readImage("challenges/chessboard.pgm");
		
		int[][] mask = {{1,0,1},{1,0,1},{1,0,1}};
		int[][] dilated = dilSennin.dilatation(image, mask);
		dilated = dilSennin.dilatation(dilated, mask);

		dilated = diference(image, dilated);
		
//		image = ninja.binarization(dilated, 1);
		image = segmentation(dilated);
		
		
		saveImage(dilated, true);
	}

	private int[][] diference(int[][] image, int[][] dilated) {
		int rows = image.length;
		int cols = image[0].length;
		
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				dilated[i][j] = cropPixel(-image[i][j] + dilated[i][j]);
			}
		}
		
		return dilated;
	}

	private int[][] segmentation(int[][] image) {
		int rows = image.length;
		int cols = image[0].length;
		int rotuleAmount = 1;
		int rotule = 0;
		int map[] = new int[256];
		int[][] result = new int[rows][cols];
		List<Integer> list = new ArrayList<Integer>();
		
		result = fillImage(result);
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (image[i][j] == BLACK) {
					
					list = listNextRotules(image, i, j);
					if (list.isEmpty()) {
						
						rotule = rotuleAmount;
						map[rotule] = 0;
						rotuleAmount++;
						
					} else {
						
						Collections.sort(list);
						rotule = list.get(0);
						int aux = 0;
						for (int a = 0; a < list.size(); a++) {
							aux = list.get(a);
							while (map[aux] != 0 && map[aux] != rotule) {
								aux = map[aux];
							}
							map[aux] = rotule;
						}
						
					}
				
					result[i][j] = rotule;
					image = populateNextRotules(image, i, j, rotule);
				}
			}
		}
		
		return result;
	}

	private int[][] fillImage(int[][] result) {
		for (int i = 0; i < result.length; i++) {
			for (int j = 0; j < result[0].length; j++) {
				result[i][j] = BLACK;
			}
		}
		
		return result;
	}

	private List<Integer> listNextRotules(int[][] image, int i, int j) {
		List<Integer> list = new ArrayList<Integer>();
		
		for (int a = i - 1; a < i + 1; a++) {
			for (int b = j - 1; b < j + 1; b++) {
				
				if (checkBounds(image, a, b)) {
					if (image[a][b] > BLACK && !list.contains(image[a][b])) {
						list.add(image[a][b]);
					}
				}
				
			}
		}
		
		return list;
	}

	private int[][] populateNextRotules(int[][] image, int i, int j, int rotule) {
		for (int a = i - 1; a < i + 1; a++) {
			for (int b = j - 1; b < j + 1; b++) {
				
				if (checkBounds(image, a, b)) {
					if (image[a][b] == BLACK) {
						image[a][b] = rotule;
					}
				}
				
			}
		}
		
		return image;
	}

}
