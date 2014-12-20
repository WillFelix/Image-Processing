package challenges;


public class HowManyCars {
	
	public static void main(String[] args) throws Exception {
//		File file = new File("challenges/video1.avi");
//		BufferedReader bf = new BufferedReader(new FileReader(file));
//		
//		String line = bf.readLine();
//		do {
//			System.out.println(line);
//		} while(line != null);
//		
//		bf.close();
		
		
		String test = "3 x 4";
		System.out.println(test.split("\\s?(?i)x\\s?")[0]);
		System.out.println(test.split("\\s?(?i)x\\s?")[1]);
		
	}

}
