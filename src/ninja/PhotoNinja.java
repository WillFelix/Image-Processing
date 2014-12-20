package ninja;

public class PhotoNinja {

	private String name;
	private String path;
	private int[][] pixels;

	private double TP;
	private double TN;
	private double FP;
	private double FN;

	private double pct;
	private double accuracy;
	private double precision;
	private double recall;
	private double specificity;
	private double fMeasure;
	private double negativeRateMetric;

	public PhotoNinja(String name, String path, int[][] pixels) {
		this.name = name;
		this.path = path;
		this.pixels = pixels;
	}

	public void printMetrics() {
		System.out.println(	"\nTP: " + TP
						  + "\nFP: " + FP
						  + "\nTN: " + TN
						  + "\nFN: " + FN);

		System.out.println("Accuracy: " + accuracy);
		System.out.println("Precision: " + precision);
		System.out.println("Recall: " + recall);
		System.out.println("Specificity: " + specificity);
		System.out.println("F-Measure: " + fMeasure);
		System.out.println("NRM: " + negativeRateMetric);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public int[][] getPixels() {
		return pixels;
	}

	public void setPixels(int[][] pixels) {
		this.pixels = pixels;
	}

	public double getTP() {
		return TP;
	}

	public void setTP(double tP) {
		TP = tP;
	}

	public double getTN() {
		return TN;
	}

	public void setTN(double tN) {
		TN = tN;
	}

	public double getFP() {
		return FP;
	}

	public void setFP(double fP) {
		FP = fP;
	}

	public double getFN() {
		return FN;
	}

	public void setFN(double fN) {
		FN = fN;
	}

	public double getPct() {
		return pct;
	}

	public void setPct(double pct) {
		this.pct = pct;
	}

	public double getAccuracy() {
		return accuracy;
	}

	public void setAccuracy(double accuracy) {
		this.accuracy = accuracy;
	}

	public double getPrecision() {
		return precision;
	}

	public void setPrecision(double precision) {
		this.precision = precision;
	}

	public double getRecall() {
		return recall;
	}

	public void setRecall(double recall) {
		this.recall = recall;
	}

	public double getSpecificity() {
		return specificity;
	}

	public void setSpecificity(double specificity) {
		this.specificity = specificity;
	}

	public double getfMeasure() {
		return fMeasure;
	}

	public void setfMeasure(double fMeasure) {
		this.fMeasure = fMeasure;
	}

	public double getNegativeRateMetric() {
		return negativeRateMetric;
	}

	public void setNegativeRateMetric(double negativeRateMetric) {
		this.negativeRateMetric = negativeRateMetric;
	}

}