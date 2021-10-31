import java.util.ArrayList;
import java.math.BigInteger;

public class gcdTester {
	
	private int divNumIndex;
	private int dontCheckBelow = 0;
	
	private BigInteger[] numList;
	private int[] numListScore;
	private ArrayList<BigInteger> abInputs;
	private BigInteger valZero = BigInteger.ZERO;
	
	private BigInteger numAtIndex;
	private Boolean bDivis;
	private Boolean aDivis;
	
	
	public gcdTester(BigInteger[] n, ArrayList<BigInteger> inp)
	{
		numList = n;
		numListScore = new int[n.length];
		abInputs = inp;
	}
	
	private int checkDiv(BigInteger numA, BigInteger numB)
	{
		int i;
		//System.out.println("Don't check below: " + dontCheckBelow);
		for (i = dontCheckBelow; i < numList.length; i++)
		{
			numAtIndex = numList[i];
			if (numA.mod(numAtIndex) == valZero && numB.mod(numAtIndex) == valZero)
			{
				aDivis = true;
				bDivis = true;
				
				return i;	
			}
			else if (numA.mod(numAtIndex) == valZero)
			{
				aDivis = true;
				
				return i;
			}
			else if (numB.mod(numAtIndex) == valZero)
			{
				bDivis = true;
				
				return i;
			}
			dontCheckBelow++;
		}
		return -1;
	}
	
	public BigInteger gcd(BigInteger a, BigInteger b)
	{
		if (a == valZero)
		{
			return b;
		}
		else if (b == valZero)
		{
			return a;
		}
		else 
			return gcd(b, (a.mod(b)));
	}
	
	private void steinGcd(BigInteger a, BigInteger b)
	{
		aDivis = false;
		bDivis = false;
		divNumIndex = checkDiv(a, b);
		
		if (divNumIndex != -1)
		{
			if (aDivis && bDivis)
			{
				numListScore[divNumIndex] += 2;
				//System.out.println("A: " + a + " and B: " + b + " are both divisible by " + numList[divNumIndex] + ", Score for " + numList[divNumIndex] + " up 2");
				steinGcd(a.divide(numList[divNumIndex]), b.divide(numList[divNumIndex]));
			}
			else if (aDivis)
			{
				numListScore[divNumIndex]++;
				//System.out.println("A: " + a + " is divisible by " + numList[divNumIndex] + ", Score for " + numList[divNumIndex] + " up 1");
				steinGcd(a.divide(numList[divNumIndex]), b);
			}
			else
			{
				numListScore[divNumIndex]++;
				//System.out.println("B: " + b + " is divisible by " + numList[divNumIndex] + ", Score for " + numList[divNumIndex] + " up 1");
				steinGcd(a, b.divide(numList[divNumIndex]));
			}
		}
		else
		{
			//gcd((a.max(b)).subtract(a.min(b)), a.min(b));
			return;
		}	
	}
	
	public void testABList()
	{
		for (int i = 0; i < abInputs.size(); i++)
		{
			System.out.println(abInputs.get(i));
		}
		
		for (int i = 0; i < abInputs.size() - 1; i+=2)
		{
			System.out.println("A: " + abInputs.get(i) + " B: " + abInputs.get(i + 1));
		}
	}
	
	public void runTest()
	{
		int counter = 1;
		for (int i = 0; i < abInputs.size() - 1; i+=2)
		{
			dontCheckBelow = 0;
			//System.out.println("For Pair: " + counter);
			//counter++;
			steinGcd(abInputs.get(i), abInputs.get(i + 1));
		}
		
		System.out.println("SCORES:");
		for (int i = 0; i < numList.length; i++)
		{
			System.out.println("Easily-dividable number \"" + numList[i] + "\" has a score of " + numListScore[i]);
		}
	}

	public BigInteger[] getNumList()
	{
		return numList;
	}
	
	public int[] getNumListScore()
	{
		return numListScore;
	}
}
