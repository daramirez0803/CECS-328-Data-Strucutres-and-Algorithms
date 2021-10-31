/**
 * Daniel Ramirez (018706595)
 */
import java.io.IOException;
import java.util.ArrayList;
import java.math.BigInteger;

public class Main {

	public static void main (String []args) throws IOException
	{
		FileHandler handler = new FileHandler();
		handler.readFile();
		
		BigInteger[] testInts = handler.getEasilyDividable();
		ArrayList<BigInteger> testAB = handler.getAAndB();
		
		gcdTester myTester = new gcdTester(testInts, testAB);
		myTester.runTest();
		
		handler.writeFile(myTester.getNumList(), myTester.getNumListScore());
	}
}
