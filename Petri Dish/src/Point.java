import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Point {

	private int x;
	private int y;
	
	public Point(int xCoor, int yCoor)
	{
		x = xCoor;
		y = yCoor;
	}
	
	public void setX(int newX)
	{
		x = newX;
	}
	
	public int getX()
	{
		return x;
	}
	
	public void setY(int newY)
	{
		y = newY;
	}
	
	public int getY()
	{
		return y;
	}
	
	public boolean equals(Point p)
	{
		return (this.x == p.getX() && this.y == p.getY());
	}
	
	public String toString()
	{
		return "(" + x + "," + y + ")";
	}
}
