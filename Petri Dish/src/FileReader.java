import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.Scanner;

public class FileReader {
	
	private static String canonicalPath;
	private BufferedReader buffer;
	
	public FileReader() 
	{
		
	}
	
	public Shape fileToShape() throws IOException {
		
		try {
			canonicalPath = new File(".").getCanonicalPath();
		} catch (IOException e) {
			System.out.println("IOException Occured" + e.getMessage());
		}

		InputStream in = new FileInputStream(canonicalPath + "/input.txt");
		Reader reader = new InputStreamReader(in);
		buffer = new BufferedReader(reader);


		return readToShape(buffer);
		
	}
	
	private static Shape readToShape(Reader reader) throws IOException
	{
		Shape newShape = new Shape();
		int r;
		int row = 0;
		while ( (r = reader.read()) != -1)
		{
			char currentChar = (char) r;
			
			if (currentChar == 10)
			{
				newShape.addRowBelow();
				row++;
			}
			else if (currentChar == 13)
			{
				//System.out.println("Carriage");
				//row++;
			} 
			else
				newShape.insert(row, currentChar);
		}
		return newShape;
	}
	
	public void shapeToWrite(Shape s) throws IOException 
	{
	    BufferedWriter writer = new BufferedWriter(new FileWriter(canonicalPath + "/output.txt"));
	    for (int row = 0; row < s.get2DArrayList().size(); row++)
	    {
	    	for (int col = 0; col < s.get2DArrayList().get(row).size(); col++)
	    	{
	    		writer.write(s.get2DArrayList().get(row).get(col));
	    	}
	    	if (row != s.get2DArrayList().size() - 1)
	    		writer.newLine();
	    }
	    writer.close();
	}

}
