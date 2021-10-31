/**
 * Daniel Ramirez 018706595
 * CSULB
 * File that handles the reading of input.txt and the writing to output.txt
 */
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.math.BigInteger;

public class FileHandler {
	
	private static String canonicalPath;
	private BigInteger bigOne = new BigInteger("1");
	private ArrayList<BigInteger> inputs = new ArrayList<BigInteger>();

	/**
	 * 
	 * @return an array of Strings that has the string values (will be 2) read from the input.txt file
	 * @throws IOException
	 */
	public void readFile() throws IOException 
	{
		try 
		{
			canonicalPath = new File(".").getCanonicalPath();
		} 
		catch (IOException e) 
		{
			System.out.println("IOException Occured" + e.getMessage());
		}

		try {
		      File myObj = new File(canonicalPath + "/input.txt");
		      Scanner myReader = new Scanner(myObj);
		      
		      
		      while (myReader.hasNext()) {
		    	  inputs.add(new BigInteger(myReader.next()));
		    
		      }
		      myReader.close();
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		
	}
	
	public ArrayList<BigInteger> getInputs() {
		return inputs;
	}
	

	public void writeFile(ArrayList<BigInteger> outputs) throws IOException 
	{
	    BufferedWriter writer = new BufferedWriter(new FileWriter(canonicalPath + "/output.txt"));

	    for (int i = 0; i < outputs.size(); i++) {
	    	if (i != 0 && outputs.get(i).equals(bigOne)) 
	    		writer.newLine();
	    	writer.write(outputs.get(i).toString() + " ");
	    }

	    
	    writer.close();
	}
	

}