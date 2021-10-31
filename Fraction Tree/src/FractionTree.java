/**
 * Daniel Ramirez 018706595
 * CSULB
 * Meat of the program, the algorithm that will find the desired fraction(output) given the input (a fraction)
 * Uses an algorithm that only traverses the needed portion of the FractionTree to find the desired output
 */
import java.math.BigInteger;

public class FractionTree {

	BigInteger m; //input m (numerator)
	BigInteger n; //input n (denominator)
	BigInteger difference; //the answer to the equation na^2 - mb^2; where n and m are the defined above, and a and b are the current numerator and denominator of the fraction being checked in the tree
	
	Fraction leftParent = new Fraction(new BigInteger("0"),new BigInteger("1")); //the left "parent" of the current fraction in the tree
	Fraction rightParent = new Fraction(new BigInteger("1"),new BigInteger("0")); //the right "parent" of the current fraction in the tree
	Fraction sum; //the sum of the two parents
	Fraction answer; //the desired output
	
	/**
	 * 
	 * @param num the input that is to be the numerator
	 * @param den the input that is to be the denominator
	 * @return the desired output: the first appearance of the fraction a/b such that a/b = m^(1/2) / n^(1/2)
	 */
	public Fraction runFractionTree(BigInteger num, BigInteger den)
	{
		treeRun(new Fraction(new BigInteger("0"),new BigInteger("1")), new Fraction(new BigInteger("1"),new BigInteger("0")), num, den); //run the recursive method to find desired output
		
		/**
		System.out.println(answer.getNumerator()); //testing print lines
		System.out.println(answer.getDenominator());
		**/
		return answer; //returns the desired output as a Fraction
	}
	
	/** attempt 1 code commented out
	 * Stack overflow error on large numbers, base case issue?
	private Fraction treeRun(Fraction left, Fraction right, BigInteger num, BigInteger den)
	{
		leftParent = left; //set the inputs to the variables
		rightParent = right;
		m = num;
		n = den;
		
		sum = Fraction.add(leftParent,rightParent); //calculate the current fraction being checked by adding its parents
		
		difference = ((n.multiply(sum.getNumerator().multiply(sum.getNumerator())) ).subtract(m.multiply(sum.getDenominator().multiply(sum.getDenominator())))); // = na^2 - mb^2
		System.out.println(difference);
		if (difference.abs().compareTo(sum.getDenominator()) < 0) // |na^2 - mb^2| < b
		{
			System.out.println("First fraction: " + sum.toString() + "\nDifference = " + difference);
			answer = sum;
			return answer;
		} //if the current Fraction passes then it is the desired output, set answer to desired output
		else if (difference.signum() > 0) 
		{
			rightParent = sum;
			treeRun(leftParent, sum, m, n);
		} //if the difference is positive then then the current Fraction is too large, set the current fraction to the rightmost parent and call recursively
		else 
			treeRun(sum, rightParent, m, n); //if this current fraction was not the answer and is not positive, then must be negative. Must be too small, set current fraction to the leftmost parent and call recursively
			
		return new Fraction(new BigInteger("0"), new BigInteger("0")); //base case needed, does nothing
	} **/
	
	/**
	 *attempt 2 code
	 *was receiving stack overflow error with recursive method, swap over to while loop
	 * @param left the leftmost parent of the current fraction (will start at 0/1)
	 * @param right the rightmost parent of the current fraction (will start at 1/0)
	 * @param num the input that is to be the numerator
	 * @param den the input that is to be the denominator
	 * @return
	 */
	private void treeRun(Fraction left, Fraction right, BigInteger num, BigInteger den)
	{
		leftParent = left; //set the inputs to the variables
		rightParent = right;
		m = num;
		n = den;
		boolean run = true;
		
		while (run) //while loop to run through the tree
		{
			sum = Fraction.add(leftParent,rightParent); //calculate the current fraction being checked by adding its parents
			
			difference = ((n.multiply(sum.getNumerator().multiply(sum.getNumerator())) ).subtract(m.multiply(sum.getDenominator().multiply(sum.getDenominator())))); // = na^2 - mb^2
			if (difference.abs().compareTo(sum.getDenominator()) < 0) // |na^2 - mb^2| < b
			{
				System.out.println("First fraction: " + sum.toString() + "\nDifference = " + difference);
				answer = sum;
				run = false;
			} //if the current Fraction passes then it is the desired output, set answer to desired output
			else if (difference.signum() > 0) 
			{
				rightParent = sum;
			} //if the difference is positive then then the current Fraction is too large, set the current fraction to the rightmost parent and loop again
			else 
				leftParent = sum; //if this current fraction was not the answer and is not positive, then must be negative. Must be too small, set current fraction to the leftmost parent and loop again
		}
	}
	
}
