
public class MazeSimilarity {

	private int numOfMazes;
	private Maze[] mazes;
	private int dimOfMazes;
	private int[] leastSimilar = new int[2];
	private int tempInt;
	private int leastVal;
	private int[][] matrixOfMazes;
	private int[][] lcsMatrix;
	
	public MazeSimilarity(int n, Maze[] m) {
		numOfMazes = n;
		mazes = m;
	}
	
	public int[] getLeastSimilar() {
		matrixOfMazes = new int[numOfMazes][numOfMazes];
		leastVal = -1;
		for (int i = 0; i < numOfMazes; i++) {
			for (int j = 0; j < numOfMazes;j++) {
				if (i != j && matrixOfMazes[j][i] == 0)
				{
					//System.out.println("Comparing [" + i + ", " + j + "]" );
					matrixOfMazes[i][j] = LCS(mazes[i].getPath(), mazes[j].getPath(), leastVal);
					if (i == 0 && j == 1) {
						leastVal = matrixOfMazes[i][j];
						leastSimilar[0] = i;
						leastSimilar[1] = j;
						//System.out.println("Mazes " + leastSimilar[0] + " and " + leastSimilar[1] + " are least similar with a value of " + leastVal);
					} else if (matrixOfMazes[i][j] != -1 && matrixOfMazes[i][j] < leastVal) {
						leastVal = matrixOfMazes[i][j];
						leastSimilar[0] = i;
						leastSimilar[1] = j;
						//System.out.println("Mazes " + leastSimilar[0] + " and " + leastSimilar[1] + " are least similar with a value of " + leastVal);
					}
				}
			}
		}
		
		for (int i = 0; i < matrixOfMazes.length; i++) {
			for (int j = 0; j < matrixOfMazes.length;j++) {
				System.out.print(matrixOfMazes[i][j] + " ");
			}
			System.out.println();
		}
		return leastSimilar;
	}
	
	private int LCS(String x, String y, int maxVal) {
		//System.out.println("Comparing Strings: " + x + " and " + y);
		lcsMatrix = new int[x.length()+1][y.length()+1];
		for (int i = 1; i < lcsMatrix.length; i++) {
			for (int j = 1; j < lcsMatrix[1].length; j++) {
				if (x.substring(i-1, i).equals(y.substring(j-1, j))) {
					tempInt = lcsMatrix[i-1][j-1] + 1;
					if (maxVal != -1 &&  tempInt > maxVal) {
						//System.out.println("Value is too large, skipping at value [" + i + "," + j + "] out of [" + lcsMatrix.length + "," + lcsMatrix[1].length + "]");
						return -1;
					}
					else 
						lcsMatrix[i][j] = tempInt;
				}
				else {
					lcsMatrix[i][j] = this.maxOf(lcsMatrix[i][j-1], lcsMatrix[i-1][j]);
				}
			}
		}
		/*
		for (int i = 0; i < lcsMatrix.length; i++) {
			for (int j = 0; j < lcsMatrix[1].length; j++) {
				System.out.print(lcsMatrix[i][j] + " ");
			}
			System.out.println();
		}
		*/
		return lcsMatrix[x.length()][y.length()];
	}
	
	private int maxOf(int a, int b) {
		if (a < b)
			return b;
		else 
			return a;
	}
	
}
