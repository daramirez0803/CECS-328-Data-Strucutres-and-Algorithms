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
	private int numOfGraph;
	private int dimOfGraph;
	private int counter = 0;
	private String graphInstr;
	private Maze[] mazes;

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
		      
		      numOfGraph = Integer.parseInt(myReader.nextLine());
		      dimOfGraph = Integer.parseInt(myReader.nextLine());
		      mazes = new Maze[numOfGraph];
		      
		      while (myReader.hasNextLine()) {
		    	  while (counter < numOfGraph) {
		    		  graphInstr = "";
		    		  for (int i = 0; i < dimOfGraph; i++) {
		    			  graphInstr += myReader.nextLine();
		    		  }
		    		  myReader.nextLine();
		    		  mazes[counter] = new Maze(dimOfGraph, graphInstr);
		    		  counter++;
		    	  }
		      }
		      myReader.close();
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		
	}
	
	public Maze[] getMazes() {
		return mazes;
	}
	
	public int getNumberOfMazes() {
		return numOfGraph;
	}

	/**
	 * 
	 * @param f the fraction that is to be written to output.txt
	 * @throws IOException
	 */
	public void writeFile(int[] f) throws IOException 
	{
	    BufferedWriter writer = new BufferedWriter(new FileWriter(canonicalPath + "/output.txt"));
	    writer.write(Integer.toString(f[0]));
	    writer.write(" ");
	    writer.write(Integer.toString(f[1]));
	    
	    writer.close();
	}

}