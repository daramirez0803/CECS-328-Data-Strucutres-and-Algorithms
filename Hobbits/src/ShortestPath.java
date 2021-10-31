
class ShortestPath { 
	
	private int[] shortestDist;		//array holding the distance value from node 0 to each other node
	private Boolean[] nodesChecked;	//array holding whether that node is checked
	private int numOfNodes;			//number of nodes in the graph

    /**
     * shortest path algo
     * @param graph adjacency matrix
     */
    public ShortestPath(int graph[][]) //single source shortest path
    { 
    	//assign value to variables
    	numOfNodes = graph.length;
        shortestDist = new int[numOfNodes];
        nodesChecked = new Boolean[numOfNodes]; 

        for (int i = 0; i < numOfNodes; i++) { 	//fill arrays with starting values
            shortestDist[i] = Integer.MAX_VALUE;//the distance from the starting node will be "infinity"
            nodesChecked[i] = false; 			//path will start unfounded 
        } 
        shortestDist[0] = 0;					//the starting node is index 0, distance from self is 0
         
  
        for (int i = 0; i < numOfNodes - 1; i++) { 

            int minVertex = minDistance(shortestDist, nodesChecked); //find the node of the shortest path
            nodesChecked[minVertex] = true; 
  
            for (int j = 0; j < numOfNodes; j++) {
  
                if (!nodesChecked[j] //not a part of the path yet
                		&& graph[minVertex][j] != 0 //there is an edge between the two nodes
                		&& shortestDist[minVertex] != Integer.MAX_VALUE //is an unvisited node
                		&& shortestDist[minVertex] + graph[minVertex][j] < shortestDist[j]) {//the path is smaller than the value of the current path
                	//TEST CODE
                	/*System.out.println("Found shortest path from " + i + " to " + j
                			+ "\n\tshortestDist[minVertex] + graph[minVertex][j] = " + (shortestDist[minVertex] + graph[minVertex][j])
                					+ "\n\tshortestDist[j] = " + shortestDist[j]); */
                    shortestDist[j] = shortestDist[minVertex] + graph[minVertex][j]; 
                }
            }
        } 
  
        //TEST CODE
        //printShortestPath(); 
    } 
  
	/**
	 * method to find the node with the min distance
	 * @param distInp 
	 * @param pathInp
	 * @return	the node with the shortest distance
	 */
    private int minDistance(int distInp[], Boolean pathInp[]) 
    { 
        int min = Integer.MAX_VALUE;
        int minIndex = -1; //innit as -1 so if -1 is returned it flags an issue
  
        for (int i = 0; i < numOfNodes; i++) 
            if (pathInp[i] == false && distInp[i] <= min) { //if the node is not currently a part of the path and its distance value is less than the min
                min = distInp[i]; //new min value
                minIndex = i; //note the new node
            } 
  
        return minIndex; 
    } 
  
    /**
     * 
     * @return shortestDist
     */
    public int[] getShortestDist() {
    	return shortestDist;
    }
    
    public Boolean[] getNodesChecked() {
    	return nodesChecked;
    }
    
    /**
     * Test function to print the distance from the starting node to the rest
     * @param distInp the array containing the distance values
     */
    private void printShortestPath() 
    { 
        System.out.println("Node \t Distance From Source"); 
        for (int i = 0; i < numOfNodes; i++) 
            System.out.println(i + " \t " + shortestDist[i]); 
        System.out.println("\nNode \t Checked"); 
        for (int i = 0; i < numOfNodes; i++) 
            System.out.println(i + " \t " + nodesChecked[i]);
    } 
} 
