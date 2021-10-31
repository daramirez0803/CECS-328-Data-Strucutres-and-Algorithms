/**
 * Daniel Ramirez #018706595
 * Assignment #4
 */
import java.io.IOException;

public class Main {
	
	public static void main (String [] args) throws IOException {

		FileHandler myFile = new FileHandler();
		myFile.readFile();
		MazeSimilarity mazeTest = new MazeSimilarity(myFile.getNumberOfMazes(), myFile.getMazes());
		
		int[] test = mazeTest.getLeastSimilar();
		System.out.println(test[0] + " " + test[1]);
		
		myFile.writeFile(test);
	}
}
