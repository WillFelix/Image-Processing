package ninja;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ImageSensei extends NinjaAnalyser {
	// Image's Header
	private String header = "";

	// Image's Name
	private String name = "";

	// Max Tone
	public int Lmax;

	// Image's Dimension
	public int width;
	public int height;
	
	
	public static final int BLACK = 0;
	public static final int WHITE = 255;
	
	public void reset(){
		header = null;
		width = 0;
		height = 0;
		Lmax = 0;
	}
	
	/**
	 * Read and print the image's matrix
	 * 
	 * @param path
	 * @throws IOException
	 */
	public void readAndPrintImage(String path) throws IOException {
		File f = new File(path);
		BufferedReader reader = new BufferedReader(new FileReader(f));

		String line = null;

		do {
			line = reader.readLine();
			if (line != null) {
				System.out.println(line);
			}
		} while (line != null);

		reader.close();
	}
	
	/**
	 * Read the Image passed for parameter. In this method is set the header,
	 * dimensions and Lmax of the image.
	 * 
	 * @param path
	 * @throws IOException
	 */
	public List<PhotoNinja> readImages(String path) throws IOException {
		File f = new File(path);
		List<PhotoNinja> images = new ArrayList<PhotoNinja>();
		
		if (f.isDirectory()) {
			File[] files = f.listFiles();
			
			for (int i = 0; i < files.length; i++) {
				int[][] image = readImage(files[i].getPath());

				reset();
				PhotoNinja photo = new PhotoNinja(files[i].getName(), files[i].getPath(), image);
				images.add(photo);
			}
			
			if (!images.isEmpty()) {
				Collections.sort(images, new Comparator<PhotoNinja>() {
					@Override
					public int compare(PhotoNinja o1, PhotoNinja o2) {
						return o1.getName().compareTo(o2.getName());
					}
				});
			}
				
		}
		
		return images;
	}

	/**
	 * Read the Image passed for parameter. In this method is set the header,
	 * dimensions and Lmax of the image.
	 * 
	 * @param path
	 * @throws IOException
	 */
	public int[][] readImage(String path) throws IOException {
		System.out.println("Processando..");
		
		String[] vName = path.split("/");
		name = vName[vName.length - 1];

		File f = new File(path);
		BufferedReader reader = new BufferedReader(new FileReader(f));

		int i = 0, j = 0;
		boolean headerOK = false;
		String line = null;
		int[][] matrix = null;

		do {
			line = reader.readLine();

			// Define the file header
			if (line != null && !headerOK) {
				
				String[] vLine = line.split(" ");
				if (isLMax(vLine)) {
					headerOK = true;
				}

				header += line + "\n";
				
				if (headerOK) header+= "\n";
			}

			if (line != null && !line.contains("#") && !line.isEmpty()) {

				String[] vLine = line.split(" ");

				// Get the Matrix's Dimensions
				if (isDimension(vLine) && width == 0 && height == 0) {

					setDimensions(vLine);
					matrix = new int[height][width];

				} else {

					// Check if just has number on line
					if (justHasNumber(vLine) && !isLMax(vLine) && width > 0 && height > 0) {

						for (int index = 0; index < vLine.length; index++) {
							if (j == width) {
								j = 0;
								i++;
							}
							if (i == height)
								break;
							
							matrix[i][j] = Integer.parseInt(vLine[index]);	
							j++;

						}

					}

				}
			}

		} while (line != null);

		reader.close();

		return matrix;
	}

	/**
	 * Save all modifications of a image.
	 * 
	 * @throws IOException
	 */
	public void saveImage(int[][] image, boolean showImage) throws IOException {
		FileWriter fw = new FileWriter("imgs/modified/ninja_" + name);
		BufferedWriter bw = new BufferedWriter(fw);

		StringBuilder content = new StringBuilder();
		content.append(header);
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				content.append(image[i][j]);
				content.append(" ");
			}
			content.append("\n");
		}

		bw.write(content.toString());
		bw.close();
		
		reset();
		
		if (showImage)
			Runtime.getRuntime().exec("eog imgs/modified/ninja_" + name);
	}
	

	/**
	 * Check if the line passed is the match to the Lmax
	 * 
	 * @param vLine
	 *            - File's line as array
	 * @return
	 */
	private boolean isLMax(String[] vLine) {
		if (vLine != null && vLine.length == 1) {
			try {
				Lmax = Integer.parseInt(vLine[0]);
				return true;
			} catch (Exception e) {
				return false;
			}
		}

		return false;
	}

	/**
	 * Check if the line is the match to the dimensions
	 * 
	 * @param vLine
	 * @return
	 */
	private boolean isDimension(String[] vLine) {
		if (vLine != null && vLine.length == 2)
			return true;

		return false;
	}

	/**
	 * Set the dimensions
	 * 
	 * @param vLine
	 */
	private void setDimensions(String[] vLine) {
		width = Integer.parseInt(vLine[0]);
		height = Integer.parseInt(vLine[1]);
	}

	/**
	 * Check if the line has just numbers.
	 * 
	 * @param vLine
	 * @return
	 */
	private boolean justHasNumber(String[] vLine) {
		try {
			for (int i = 0; i < vLine.length; i++) {
				Integer.parseInt(vLine[i]);
			}

			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * Return the histogram
	 * 
	 * @return
	 */
	public int[] imHist(int[][] image) {
		int[] h = new int[256];

		// Check how many instances of each number is present on matrix
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				int count = h[image[i][j]]; 
				h[image[i][j]] = ++count;
			}
		}

		return h;
	}
	
	public void printImage(int[][] image){
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				System.out.println("matrix[" + i + "][" + j + "]: " + image[i][j]);
			}
		}
	}

	public void printArray(int[] imHist) {
		for (int i = 0; i < imHist.length; i++) {
			System.out.println(imHist[i]);
		}
	}
	
	public int cropPixel(int pixel){
		if (pixel > 255) {
			pixel = 255;
		} else if (pixel < 0) {
			pixel = 0;
		}
		
		return pixel;
	}
	
	public boolean checkBounds(int[][] image, int i, int j) {
		int rows = image.length;
		int cols = image[0].length;
		
		if (i < 0 || j < 0 || i >= rows || j >= cols) {
			return false;
		}
		
		return true;
	}
	
	public int[][] projections(int[][] image, boolean isHorizontal) {
		int rows = image.length;
		int cols = image[0].length;
		int[][] image2 = new int[rows][cols];
		
		if (isHorizontal) {
			
			int[] hist = new int[rows];
			for (int i = 0; i < rows; i++) {
				int count = 0;
				for (int j = 0; j < cols; j++) {
					if (image[i][j] == BLACK) {
						
						hist[i] = ++count;
						
					}
				}
			}
			
			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < cols; j++) {
					if (j <= hist[i])
						image2[i][j] = BLACK;
					else
						image2[i][j] = WHITE;
				}
			}
			printArray(hist);

		} else {
			
			int[] hist = new int[cols];
			for (int i = 0; i < cols; i++) {
				int count = 0;
				for (int j = 0; j < rows; j++) {
					if (image[j][i] == BLACK) {
						
						hist[i] = ++count;
						
					}
				}
			}
			
			for (int i = 0; i < cols; i++) {
				for (int j = 0; j < rows; j++) {
					if (j <= hist[i])
						image2[j][i] = BLACK;
					else
						image2[j][i] = WHITE;
				}
			}
			
			printArray(hist);
		}
		
		return image2;
	}

	public static void exportCSV(List<PhotoNinja> wellners, boolean openReport) {
		try {
			String fileName = "report.csv";
			FileWriter writer = new FileWriter(fileName);
			
			writer.append("Imagem,Param(Porcentagem),TP,TN,FP,FN,Accuracy,Precision,Recall,Specificity,F-Measure,NRM\n");
			
			for (PhotoNinja w : wellners) {
				writer.append(w.getName() + ",");
				writer.append(w.getPct() + ",");
				writer.append(w.getTP() + ",");
				writer.append(w.getTN() + ",");
				writer.append(w.getFP() + ",");
				writer.append(w.getFN() + ",");
				writer.append(w.getAccuracy() + ",");
				writer.append(w.getPrecision() + ",");
				writer.append(w.getRecall() + ",");
				writer.append(w.getSpecificity() + ",");
				writer.append(w.getfMeasure() + ",");
				writer.append(w.getNegativeRateMetric() + "\n");
			}
			
			writer.flush();
			writer.close();
			
			if (openReport)
				Runtime.getRuntime().exec("loffice -calc " + fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		 
	}
	
}
