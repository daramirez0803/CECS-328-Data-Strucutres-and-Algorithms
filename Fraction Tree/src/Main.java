/**
 * Daniel Ramirez 018706595
 * CSULB
 * 
 */
import java.io.IOException;
import java.math.BigInteger;

public class Main {

	public static void main (String []args) throws IOException
	{
		FractionTree test = new FractionTree();
		FileHandler read = new FileHandler();
		
		String[] inputs = read.readFile(); //string array to hold the given values read from input.txt
		
		BigInteger m = new BigInteger(inputs[0]); //first number to be the numerator
		BigInteger n = new BigInteger(inputs[1]); //second number to be denominator
	
		read.writeFile(test.runFractionTree(m, n)); //run writeFile with input runFractionTree, which will return the desired output
		
	}
}
