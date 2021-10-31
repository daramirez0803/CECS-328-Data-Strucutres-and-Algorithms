import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Tester {

	public static void main (String []args) throws IOException
	{
		FileGrid myGrid = new FileGrid();
		//myGrid.printGrid();
		//System.out.println();
		
		myGrid.printGrid();
		myGrid.scanGrid();
		myGrid.printGrid();
		System.out.println("done");
			
	}

}
