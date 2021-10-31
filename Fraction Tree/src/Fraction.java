/**
 * Daniel Ramirez 018706595
 * CSULB
 * This class allows for the creation of a fraction that holds a BigInteger for the numerator and denominator
 */
import java.math.BigInteger;

public class Fraction {
	
	private BigInteger n; //numerator
	private BigInteger d; //denominator
	
	/**
	 * Zero arg constr.
	 */
	public Fraction ()
	{
		n = new BigInteger("0");
		d = new BigInteger("0");
	}
	
	/**
	 * constr.
	 * @param num number to be numerator
	 * @param dem number to be denominator 
	 */
	public Fraction (BigInteger num, BigInteger den)
	{
		n = num;
		d = den;
	}
	
	/**
	 * 
	 * @return value of n (numerator)
	 */
	public BigInteger getNumerator()
	{
		return n;
	}
	
	/**
	 * 
	 * @return value of d (denominator)
	 */
	public BigInteger getDenominator()
	{
		return d;
	}
	
	/**
	 * 
	 * @param a fraction to be added to b
	 * @param b fraction to be added to a
	 * @return a Fraction that's numerator is the sum of a and b's numerator and a denominator that is the value of a and b's denominator 
	 */
	public static Fraction add(Fraction a, Fraction b)
	{
		return new Fraction(a.getNumerator().add(b.getNumerator()), a.getDenominator().add(b.getDenominator()));
	}
	
	/**
	 * toString method to output in fraction form
	 */
	@Override
	public String toString()
	{
		return n + "/" + d;
	}
}
