import java.math.BigInteger;
import java.util.ArrayList;

public class Pads {

	private ArrayList<BigInteger> lilyPads;
	private ArrayList<BigInteger> shortestPath;
	private ArrayList<BigInteger> totalPaths = new ArrayList<BigInteger>();;
	private BigInteger bigOne = new BigInteger("1");
	private int[] maximal;
	private int[]minimal;
	private int[][] padGraph;
	private int[] shortestDist;
	private int[] removeNodes;
	private ShortestPath sPath;
	private boolean checkForPaths;
	
	/**
	 * constructor that will create adjacency matrix
	 * @param listInp
	 */
	public Pads(ArrayList<BigInteger> listInp) {
		lilyPads = listInp;
		
		lilyPads.sort(null);
		
		minimal = new int[lilyPads.size()];
		minimal[0] = 1;
		boolean isMinimal;
		for (int i = lilyPads.size() - 1; i > 0; i--) { //find minimal nodes
			isMinimal = true;
			for (int j = i - 1; j > -1; j--) {
				if (!(lilyPads.get(i).gcd(lilyPads.get(j)).equals(bigOne))) {
					isMinimal = false;
					break;
				}
			}
			if (isMinimal)
				minimal[i] = 1;
		}
		
		maximal = new int[lilyPads.size()];
		maximal[lilyPads.size() - 1] = 1;
		boolean isMaximal;
		for (int i = 0; i < lilyPads.size() - 1; i++) { //find maximal nodes
			isMaximal = true;
			for (int j = i + 1; j < lilyPads.size(); j++) {
				if (!(lilyPads.get(i).gcd(lilyPads.get(j)).equals(bigOne))) {
					isMaximal = false;
					break;
				}
			}
			
			if (isMaximal)
				maximal[i] = 1;
		}
		
		//TEST CODE
		/*System.out.println("Pad Value \t Is Minimal \t Is Maximal");
		for (int i = 0; i < lilyPads.size(); i++) {
			System.out.println(lilyPads.get(i) + " \t\t " + minimal[i] + " \t\t " + maximal[i]);
		} */
		
		padGraph = new int[lilyPads.size() + 1][lilyPads.size() + 1]; //create adjacency matrix
		for (int i = 0; i < minimal.length; i++) { //attach 1 to minimal pads
			if (minimal[i] == 1)
				padGraph[0][i + 1] = 1;
		}
		
		for (int i = 1; i < padGraph.length; i++) { //find edges between the rest of the nodes
			for (int j = 1; j < padGraph[1].length; j++) {
				if (i != j && maximal[i - 1] != 1 && minimal[j - 1] != 1 && !(lilyPads.get(i - 1).gcd(lilyPads.get(j - 1)).equals(bigOne))) {
					padGraph[i][j] = 1;
				}
			}
		}
		
		//TEST CODE
		/*System.out.println("Adjacency Matrix:");
		for (int i = 0; i < padGraph.length; i++) {
			for (int j = 0; j < padGraph[1].length; j++) {
				System.out.print(padGraph[i][j] + " \t");
			}
			System.out.println();
		}*/
	}
	
	public void findPaths() {
		//get the shortest path
		checkForPaths = true;
		while(possiblePaths() && checkForPaths) {
			sPath = new ShortestPath(padGraph);
			shortestDist = sPath.getShortestDist();
			shortestPath = new ArrayList<BigInteger>();
			removeNodes = new int[padGraph.length];
			
			//TEST CODE
			/*System.out.println("\nNode \t Value\t Distance From Source"); 
	        for (int i = 0; i < shortestDist.length; i++) {
	        	if (i == 0)
	        		System.out.println(i + " \t 1 \t " + shortestDist[i]); 
	        	else
	        		System.out.println(i + " \t " + lilyPads.get(i - 1) + " \t " + shortestDist[i]);
	        } */
	        
	        //find shortest distance from start to a maximal pad
	        int sNode = 0; //index of the final node of the shortest path
	        for (int i = 0; i < maximal.length; i++) {
	        	if (maximal[i] == 1 && (shortestDist[i + 1] < shortestDist[sNode] || sNode == 0)) {
	        		sNode = i + 1;
	        	}
	        }
	        
	        //TEST CODE
	        /*System.out.println("sNode: " + sNode + "\nshortestDist: " + shortestDist[sNode] + "\nmaximal: " + maximal[sNode - 1] + "\nlilyPads: " + lilyPads.get(sNode - 1));
	        System.out.println();
	        for (int i = 0; i < shortestPath.size(); i++) {
	        	System.out.print(shortestPath.get(i) + " ");
	        }
	        System.out.println("Adjacency Matrix:");
			for (int i = 0; i < padGraph.length; i++) {
				for (int j = 0; j < padGraph[1].length; j++) {
					System.out.print(padGraph[i][j] + " \t");
				}
				System.out.println();
			}*/
	        
	        searchPath(sNode);
	        //System.out.println("value following 1 = " + shortestPath.get(1));
	        if (!checkForPaths)
	        	break;
	        //remove pads
	        for (int i = 0; i < removeNodes.length; i++) {
	        	if (removeNodes[i] == 1) {
	        		for (int j = 0; j < padGraph.length; j++) {
	        			padGraph[j][i] = 0;
	        			padGraph[i][j] = 0;
	        		}
	        	}
	        }
	        //add path to total path
	        for (int i = 0; i < shortestPath.size(); i++) {
	        	totalPaths.add(shortestPath.get(i));
	        }
	        
	        //TEST CODE
	        /*System.out.println("\nShortest Path:");
	        for (int i = 0; i < shortestPath.size(); i++) {
	        	System.out.print(shortestPath.get(i) + " ");
	        }
	        System.out.println("\nNodes to remove:");
	        for (int i: removeNodes) {
	        	System.out.print(i + " ");
	        }
	        System.out.println(); */
		}
		
		/*System.out.println("\nTotal Paths:");
        for (int i = 0; i < totalPaths.size(); i++) {
        	if (i != 0 && totalPaths.get(i) == bigOne) {
        		System.out.println();
        	}
        	System.out.print(totalPaths.get(i) + " ");
        }*/
        
	}
	
	private void searchPath(int node) {
		int currentNode = node;
		int pathLength = 0;
		int[][] checkedNodes = new int[padGraph.length][padGraph.length];
		boolean reachedOne = false;
		//System.out.println("Shortest Path: " + shortestDist[node]);
		shortestPath.add(lilyPads.get(node - 1));
		for (int i = 0; i < padGraph.length; i++) {
			//System.out.println("Starting Row: " + i + " Col: " + currentNode);
			//System.out.println("Path Length: " + pathLength);
			if (reachedOne ) { //&& pathLength == shortestDist[node]
				//System.out.println("Shortest distance: " + shortestDist[node] + "\nPath Length: " + pathLength);
				return;
			}
			else if (pathLength > shortestDist[node]) { //reachedOne
				currentNode = node;
				pathLength = 0;
				removeNodes = new int[padGraph.length];
				shortestPath = new ArrayList<BigInteger>();
				shortestPath.add(lilyPads.get(node - 1));
				//System.out.println("Path Reset");
			} else if (padGraph[i][currentNode] == 1 && checkedNodes[i][currentNode] != 1) {
				checkedNodes[i][currentNode] = 1;
				removeNodes[currentNode] = 1;
				currentNode = i;
				pathLength++;
				//System.out.println("Ending current node value: " + currentNode);
				if (currentNode == 0) {
					reachedOne = true;
					//System.out.println("Reached one");
					shortestPath.add(0, bigOne);
				}
				else
					shortestPath.add(0, lilyPads.get(i - 1));
				i = -1;
			}
		}
		checkForPaths = false;
		//System.out.println("reached end of for loop");
	}
	
	private boolean possiblePaths() {
		boolean maxAvailable = false;
		boolean minAvailable = false;
		
		for (int i = 0; i < maximal.length; i++) {
			if (maximal[i] == 1) {
				for (int j = 0; j < padGraph.length; j++) {
					if (padGraph[i][j] == 1) {
						maxAvailable = true;
						break;
					}
				}
			if (maxAvailable == true)
				break;
			}
			
		}
		for (int i = 0; i < minimal.length; i++) {
			if (minimal[i] == 1) {
				for (int j = 0; j < padGraph.length; j++) {
					if (padGraph[i][j] == 1) {
						minAvailable = true;
						break;
					}
				}
			if (minAvailable == true)
				break;
			}
			
		}
		return minAvailable && maxAvailable;
	}
	
	public ArrayList<BigInteger> getTotalPaths() {
		return totalPaths;
	}
}
