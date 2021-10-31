import java.util.ArrayList;

public class Shape {
	
	private ArrayList<ArrayList<Character>> shapeGrid; //ArrayList will be the height of the grid
	private ArrayList<Character> arrayRow; 
	
	
	public Shape()
	{
		this.shapeGrid = new ArrayList<ArrayList<Character>>();
		this.shapeGrid.add(new ArrayList<Character>());
	}
	
	public void insert(int i, char ch)
	{
		shapeGrid.get(i).add(ch);
	}
	
	public void addRowAbove()
	{
		shapeGrid.add(0, new ArrayList<Character>());
	}
	
	public void addRowBelow()
	{
		shapeGrid.add(new ArrayList<Character>());
	}
	
	public void testShape()
	{
		System.out.println("This Shape object is empty: " + this.shapeGrid.isEmpty());
		System.out.println("Number of rows in Shape object: " + this.shapeGrid.size());
		System.out.println("Number of columns in Shape object: " + this.shapeGrid.get(0).size());
		
		System.out.println("Contents of Shape:");
		this.printShape();
	}
	
	public void printShape()
	{
		for (int i=0;i<shapeGrid.size();i++)
		{
			  arrayRow = shapeGrid.get(i);

			   for(int j=0;j<arrayRow.size();j++)
			   {
			      System.out.print(arrayRow.get(j)); 
			   }
			   
			   System.out.print("\n");

		}
	}
	
	public ArrayList<ArrayList<Character>> get2DArrayList()
	{
		return shapeGrid;
	}
	
	public Shape mirror()
	{
		Shape mirrorShape = new Shape();
		
		for (int row = 0; row < this.shapeGrid.size(); row++)
		{
			for (int col = this.shapeGrid.get(0).size() - 1; col >= 0; col--)
			{
				mirrorShape.get2DArrayList().get(row).add(this.get2DArrayList().get(row).get(col));
			}
			if (row != this.shapeGrid.size() - 1)
				mirrorShape.addRowBelow();
		}
		
		return mirrorShape;
	}
	
	public Shape rot90()
	{
		Shape rotShape = new Shape();
		
		for (int col = shapeGrid.get(0).size() - 1; col >= 0; col--)
		{
			for (int row = 0; row < shapeGrid.size(); row++)
			{
				rotShape.get2DArrayList().get(shapeGrid.get(0).size() - col - 1).add(this.get2DArrayList().get(row).get(col));
			}
			if (col != 0)
				rotShape.addRowBelow();
		}
		
		
		return rotShape;
	}
	
	public Shape rot180()
	{
		return this.rot90().rot90();
	}
	
	public Shape rot270()
	{
		return this.rot90().rot180();
	}
	
	public Shape rot360()
	{
		return this.rot180().rot180();
	}
	
	public boolean equals(Shape s)
	{
		boolean areEqual = true;
		
		if (this.get2DArrayList().get(0).size() != s.get2DArrayList().get(0).size() || this.get2DArrayList().size() != s.get2DArrayList().size())
			return false;
		
		for (int row = 0; row < this.get2DArrayList().size(); row++)
		{
			for (int col = 0; col < this.get2DArrayList().get(0).size(); col++)
			{
				if (this.get2DArrayList().get(row).get(col) != s.get2DArrayList().get(row).get(col))
				{
					areEqual = false;
					break;
				}
			}
		}
		
		return areEqual;
	}

}
