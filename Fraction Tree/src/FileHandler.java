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

public class FileHandler {
	
	private static String canonicalPath;
	private String[] strArray = new String[2];	
	
	private int counter = 0;

	/**
	 * 
	 * @return an array of Strings that has the string values (will be 2) read from the input.txt file
	 * @throws IOException
	 */
	public String[] readFile() throws IOException 
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
		      while (myReader.hasNextLine()) {
		        strArray[counter] = myReader.nextLine();
		        System.out.println(strArray[counter]);
		        counter++;
		      }
		      myReader.close();
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }

		return strArray;
		
	}

	/**
	 * 
	 * @param f the fraction that is to be written to output.txt
	 * @throws IOException
	 */
	public void writeFile(Fraction f) throws IOException 
	{
	    BufferedWriter writer = new BufferedWriter(new FileWriter(canonicalPath + "/output.txt"));
	    writer.write(f.getNumerator().toString());
	    writer.newLine();
	    writer.write(f.getDenominator().toString());
	    
	    writer.close();
	}

}