/**
 * Daniel Ramirez 018706595
 */
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;

public class Main {
	
	
	public static void main (String [] args) throws IOException {
		
		FileHandler myHandler = new FileHandler();
		myHandler.readFile();
		Pads testPad = new Pads(myHandler.getInputs());
		testPad.findPaths();
		myHandler.writeFile(testPad.getTotalPaths());
	}
}
