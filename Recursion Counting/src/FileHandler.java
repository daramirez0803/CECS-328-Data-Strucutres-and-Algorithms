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
	
	private int n;
	private int counter = 0;
	
	private BigInteger[] easilyDividable;
	
	private ArrayList<BigInteger> aAndB = new ArrayList<BigInteger>();

	public BigInteger[] getEasilyDividable()
	{
		return easilyDividable;
	}

	public ArrayList<BigInteger> getAAndB()
	{
		return aAndB;
	}
	
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
		      
		      n = myReader.nextInt();
		      easilyDividable = new BigInteger[n];
		      while (myReader.hasNext()) {
		    	  if (counter < n)
		    	  {
		    		  easilyDividable[counter] = new BigInteger(myReader.next());
		    		  counter++;
		    	  }
		    	  else
		    	  {
		    		  aAndB.add(new BigInteger(myReader.next()));
		    	  }
		    		  

		      }
		      myReader.close();
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
	}
	
	public void writeFile(BigInteger[] easilyDiv, int[] score) throws IOException 
	{
	    BufferedWriter writer = new BufferedWriter(new FileWriter(canonicalPath + "/output.txt"));
	    
	    for (int i = 0; i < easilyDiv.length; i++)
	    {
	    	writer.write(easilyDiv[i] + " " + score[i]);
	    	
	    	if (i + 1 < easilyDiv.length)
	    		writer.newLine();
	    }
	    
	    writer.close();
	}
}