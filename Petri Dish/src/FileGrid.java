import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class FileGrid {

	private FileReader myInput = new FileReader();
	private Shape fileShape;
	private List<Shape> shapeContainer = new ArrayList<Shape>();
	private Queue<Point> queuedPoints = new LinkedList<Point>();
	private List<Point> checkedPoints = new ArrayList<Point>();
	private int minX = -1, minY = -1, maxX = -1, maxY = -1;
	private Point nextPoint;
	
	public FileGrid() throws IOException
	{
		fileShape = myInput.fileToShape();
	}
	
	public void printGrid()
	{
		System.out.println("X: " + fileShape.get2DArrayList().get(0).size() + " Y: " + fileShape.get2DArrayList().get(0).size());

		this.fileShape.printShape();
	}
	
	public void scanGrid() throws IOException
	{
		for (int i=0;i<fileShape.get2DArrayList().size();i++)
		{
			  ArrayList<Character> arrayRow = fileShape.get2DArrayList().get(i);

			   for(int j=0;j<arrayRow.size();j++)
			   {
			      if (arrayRow.get(j) == '*')
			      {
			    	  this.scanForShape(j, i);
			    	  this.createNewShape(j, i);
			    	  this.resetCoord();
			      }
			     
			   }
		}
		
		myInput.shapeToWrite(fileShape);
	}
	
	public void createNewShape(int x, int y)
	{
		int offset = 0;
		if (minX < x)
			offset = x - minX;
		Shape newShape = new Shape();
		
		for (int i = 0; i <= maxY - y; i++)
		{
			for (int j = x-offset; j <= maxX; j++)
			{
				newShape.get2DArrayList().get(i).add(fileShape.get2DArrayList().get(i+y).get(j));
			}
			if (i != maxY-y)
				newShape.addRowBelow();
		}
		this.nameShape(x, y, offset, checkShapeContainer(newShape));
	}
	
	public void scanForShape(int x, int y)
	{
		if (minX == -1 && maxX == -1 && minY == -1 && maxY == -1)
		{
			minX = x;
			maxX = x;
			minY= y;
			maxY = y;
			checkedPoints.add(new Point(x,y));
		}
		
		if (x < minX)
		{
			minX = x;
		}
		else if (x > maxX)
		{
			maxX = x;
		}
		if (y < minY)
		{
			minY = y;
		}
		else if (y > maxY)
		{
			maxY = y;
		}
			
		//check N
		if (this.checkGridPoint(x, 0, y, -1) == '*' && !this.contains(new Point(x,y-1)))
		{
			queuedPoints.add(new Point(x,y-1));
			checkedPoints.add(new Point(x,y-1));
		}
		//check NE
		if (this.checkGridPoint(x, 1, y, -1) == '*' && !this.contains(new Point(x+1,y-1)))
		{
			queuedPoints.add(new Point(x+1,y-1));
			checkedPoints.add(new Point(x+1,y-1));
		}
		//check E
		if (this.checkGridPoint(x, 1, y, 0) == '*' && !this.contains(new Point(x+1,y)))
		{
			queuedPoints.add(new Point(x+1,y));
			checkedPoints.add(new Point(x+1,y));
		}
		//check SE
		if (this.checkGridPoint(x, 1, y, 1) == '*' && !this.contains(new Point(x+1,y+1)))
		{
			queuedPoints.add(new Point(x+1,y+1));
			checkedPoints.add(new Point(x+1,y+1));
		}
		//check S
		if (this.checkGridPoint(x, 0, y, 1) == '*' && !this.contains(new Point(x,y+1)))
		{
			queuedPoints.add(new Point(x,y+1));
			checkedPoints.add(new Point(x,y+1));
		}
		//check SW
		if (this.checkGridPoint(x, -1, y, 1) == '*' && !this.contains(new Point(x-1,y+1)))
		{
			queuedPoints.add(new Point(x-1,y+1));
			checkedPoints.add(new Point(x-1,y+1));
		}
		//check W
		if (this.checkGridPoint(x, -1, y, 0) == '*' && !this.contains(new Point(x-1,y)))
		{
			queuedPoints.add(new Point(x-1,y));
			checkedPoints.add(new Point(x-1,y));
		}
		//check NW
		if (this.checkGridPoint(x, -1, y, -1) == '*' && !this.contains(new Point(x-1,y-1)))
		{
			queuedPoints.add(new Point(x-1,y-1));
			checkedPoints.add(new Point(x-1,y-1));
		}
		
		if (queuedPoints.isEmpty())
		{
			return;
		}
		else
		{
			this.scanForShape(queuedPoints.peek().getX(), queuedPoints.poll().getY());
		}
	}
	
	public char checkGridPoint(int xVal, int xOffset, int yVal, int yOffset)
	{
		char retChar = 0;
		try {
			retChar = fileShape.get2DArrayList().get(yVal+yOffset).get(xVal+xOffset);
			return retChar;
		} catch (Exception e) {
			return retChar;
		}
		
	}
	
	public boolean contains(Point p)
	{
		int lSize = checkedPoints.size();
		
		for (int i = 0; i < lSize; i++)
		{
			if (checkedPoints.get(i).equals(p))
				return true;
		}
		
		return false;
	}
	
	public void nameShape(int x, int y, int offset, int placeInContainer)
	{
		int aciiNum = 97 + placeInContainer;
		
		for (int i = y; i <= maxY; i++)
		{
			for (int j = x-offset; j <= maxX; j++)
			{
				if (fileShape.get2DArrayList().get(i).get(j) == '*')
				{
					fileShape.get2DArrayList().get(i).set(j, (char) aciiNum);
				}
			}
		}
	}
	
	public void resetCoord()
	{
		this.maxX = -1;
		this.maxY = -1;
		this.minX = -1;
		this.minY = -1;
		nextPoint = null;
	}
	
	public int checkShapeContainer(Shape s)
	{
		Shape temp, tempMirrorV, tempMirrorH;
		
		int i;
		for (i = 0; i < shapeContainer.size(); i++)
		{
			temp = shapeContainer.get(i);
			tempMirrorV = temp.mirror();
			tempMirrorH = temp.rot90().mirror();
			
			if (temp.equals(s) ||
					temp.equals(s.rot90()) ||
					temp.equals(s.rot180()) ||
					temp.equals(s.rot270()) ||
					tempMirrorV.equals(s) ||
					tempMirrorV.equals(s.rot90()) ||
					tempMirrorV.equals(s.rot180()) ||
					tempMirrorV.equals(s.rot270()) ||
					tempMirrorH.equals(s) ||
					tempMirrorH.equals(s.rot90()) ||
					tempMirrorH.equals(s.rot180()) ||
					tempMirrorH.equals(s.rot270())
					)
				return i;
		}
		
		shapeContainer.add(s);
		return i;
	}
}
