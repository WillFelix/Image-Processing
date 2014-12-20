package ninja;

public class NinjaAnalyser {
	public static final int POSITIVE = 0;
	public static final int NEGATIVE = 255;
	
	public static final int TRUE_POSITIVE = 0;
	public static final int TRUE_NEGATIVE = 1;
	public static final int FALSE_POSITIVE = 2;
	public static final int FALSE_NEGATIVE = 3;
	
	public int totalPixels(int[][] image) {
		int rows = image.length;
		int cols = image[0].length;
		
		return rows * cols;
	}
	
	public int[] matrixConfusion(int[][] original, int[][] modified) {
		int[] confusion = new int[4];
		int rows = original.length;
		int cols = original[0].length;
		
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				
				if (original[i][j] == POSITIVE && modified[i][j] == POSITIVE) {
					confusion[TRUE_POSITIVE] += 1;
				} else if (original[i][j] == NEGATIVE && modified[i][j] == NEGATIVE) {
					confusion[TRUE_NEGATIVE] += 1;
				} else if (original[i][j] == POSITIVE && modified[i][j] == NEGATIVE) {
					confusion[FALSE_NEGATIVE] += 1;
				} else if (original[i][j] == NEGATIVE && modified[i][j] == POSITIVE) {
					confusion[FALSE_POSITIVE] += 1;
				}
				
			}
		}
		
		return confusion;
	}
	
	public void imageMetrics(int[][] original, PhotoNinja ninja) {
		int[] confusion = matrixConfusion(original, ninja.getPixels());
		double TP = confusion[TRUE_POSITIVE];
		double TN = confusion[TRUE_NEGATIVE];
		double FP = confusion[FALSE_POSITIVE];
		double FN = confusion[FALSE_NEGATIVE];
		
		double P = TP + FN;
		double N = TN + FP;
		
		double accuracy = (TP + TN)/(P + N);
		double precision = TP/( TP + FP);
		double recall = TP / P;
		double specificity = TN / N;
		double fMeasure = ((2 * recall * precision) / (recall + precision)) * 100;
		
		double NRFP = FP / (FP + TN);
		double NRFN = FN / (FN + TP);
		double negativeRateMetric = (NRFN + NRFP) / 2;
		
		ninja.setTP(TP);
		ninja.setTN(TN);
		ninja.setFP(FP);
		ninja.setFN(FN);
		ninja.setAccuracy(accuracy);
		ninja.setPrecision(precision);
		ninja.setRecall(recall);
		ninja.setSpecificity(specificity);
		ninja.setfMeasure(fMeasure);
		ninja.setNegativeRateMetric(negativeRateMetric);
	}
	
}
