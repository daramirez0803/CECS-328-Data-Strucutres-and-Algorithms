
public class Maze {
	private int[][] maze; //adjacency matrix
	private int dim;
	private String directions;
	
	//Variables for DFS
	int[] visited;
	int[] checked;
	int topOfVisited;
	int topOfChecked;
	int diff;
	String path = "";
	String lastDir;
	
	public Maze(int n, String dir) {
		int temp = n * n;
		dim = n;
		maze = new int[temp][temp];
		
		for (int i = 0; i < temp; i++) {
			directions = dir.substring(i*4, (i*4)+4);

			//north open
			if (directions.substring(0, 1).equalsIgnoreCase("0")) {
				maze[i][i-n] = 1;
			}
			//south open
			if (directions.substring(1, 2).equalsIgnoreCase("0")) {
				maze[i][i+n] = 1;
			}
			//west open
			if (directions.substring(2, 3).equalsIgnoreCase("0")) {
				maze[i-1][i] = 1;
			}
			//east open
			if (directions.substring(3, 4).equalsIgnoreCase("0")) {
				maze[i+1][i] = 1;
			}
		}
		this.DFS();
		//System.out.println("Done making a maze");
	}
	
	private void DFS() {
		visited = new int[dim*dim];
		checked = new int[dim*dim];
		topOfVisited = -1;
		topOfChecked = -1;
		//start at north west
		visited[++topOfVisited] = 0;
		
		while (topOfVisited != -1) {

			//move north if possible
			if (visited[topOfVisited]/dim != 0 &&
					maze[visited[topOfVisited]][visited[topOfVisited]-dim] == 1 &&
					this.visitedDoesNotContain(visited[topOfVisited]-dim) &&
					this.hasNotBeenChecked(visited[topOfVisited]-dim)) {
				visited[++topOfVisited] = visited[topOfVisited - 1]-dim;
				path += "N";
				//System.out.println("Moving North from node: " + visited[topOfVisited - 1] + " " + path);
			}
			//move south if possible
			else if (visited[topOfVisited]/dim != dim - 1 &&
					maze[visited[topOfVisited]][visited[topOfVisited]+dim] == 1 &&
					this.visitedDoesNotContain(visited[topOfVisited]+dim) &&
					this.hasNotBeenChecked(visited[topOfVisited]+dim)) {
				visited[++topOfVisited] = visited[topOfVisited - 1]+dim;
				path += "S";
				//System.out.println("Moving South from node: " + visited[topOfVisited - 1] + " " + path);
			}
			//move west if possible
			else if (visited[topOfVisited]%dim != 0 &&
					maze[visited[topOfVisited]][visited[topOfVisited]-1] == 1 &&
					this.visitedDoesNotContain(visited[topOfVisited]-1) &&
					this.hasNotBeenChecked(visited[topOfVisited]-1)) {
				visited[++topOfVisited] = visited[topOfVisited - 1]-1;
				path += "W";
				//System.out.println("Moving West from node: " + visited[topOfVisited - 1] + " " + path);
			}
			//move east if possible
			else if (visited[topOfVisited]%dim != dim - 1 &&
					maze[visited[topOfVisited]][visited[topOfVisited]+1] == 1 &&
					this.visitedDoesNotContain(visited[topOfVisited]+1) &&
					this.hasNotBeenChecked(visited[topOfVisited]+1)) {
				visited[++topOfVisited] = visited[topOfVisited - 1]+1;
				path += "E";
				//System.out.println("Moving East from node: " + visited[topOfVisited - 1] + " " + path);
			}
			else { //move back if no moves possible
				checked[++topOfChecked] = visited[topOfVisited--];
				if (topOfVisited != -1) {
					diff = checked[topOfChecked] - visited[topOfVisited];
					if (diff == dim) 
						path += "N";
					else if (diff == -1 * dim) 
						path += "S";
					else if (diff == 1) 
						path += "W";
					else
						path += "E";
					/*
					if (topOfVisited == -1)
						System.out.println("Backtracking from node " + checked[topOfChecked] + " to node " + visited[topOfVisited + 1] + " " + path); 
					else
						System.out.println("Backtracking from node " + checked[topOfChecked] + " to node " + visited[topOfVisited] + " " + path);
						*/
				}
			}
		}
		//System.out.println("Done with DFS");
	}
	
	public String getPath() {
		return path;
	}
	
	private boolean visitedDoesNotContain(int num) {
		for (int n: visited) {
			if (n == num)
				return false;
		}
		return true;
	}
	
	private boolean hasNotBeenChecked(int num) {
		for (int n: checked) {
			if (n == num)
				return false;
		}
		return true;
	}
	
	public void printEdges() {
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[0].length; j++) {
				if (maze[i][j] == 1)
					System.out.println("[" + i + "," + j + "]");
			}
		}
	}
}
