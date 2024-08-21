/**
 * A class that creates a term used in a polynomial.
 * @author Alyssa Ayala, CSCI 313-13
 * Due Date: October 13th, 2021 @ 9:00AM
 */
public class term {
	protected double coeff; // term attributes: a coefficient for each term
	protected int power; // power >= 0
	
	/**
	 * A constructor that assigns a coefficient and power to a term.
	 * @param coeff The coefficient of the term.
	 * @param power The power of the term.
	 */
	public term(double coeff, int power) {
		if (power < 0) throw new IllegalArgumentException("Negative power."); // If the desired power is negative, or if power >= 0 is false.
		this.coeff = coeff;
		this.power = power;
	}
	
	/**
	 * Compares one term to another. Used to sort terms in a polynomial.
	 * @param term The term to be compared to the other one.
	 * @return 1 if it's greater, -1 if it's less, or 0 if it's equal.
	 */
	public int compareTo(term term) {
		if (this.power > term.power) // Compare powers first.
			return 1;
		else if (this.power < term.power)
			return -1;
		else { // Compare coefficients if powers are equal.
			if (this.coeff > term.coeff)
				return 1;
			else if (this.coeff < term.coeff)
				return -1;
		}
		return 0;
	}
	
	/**
	 * Determines exactly how a term is displayed to the user, including when to place an x and/or the power.
	 */
	public String toString() {
		if (this.power == 0) return this.coeff + ""; // Return no x if the power is 0.
		else if (this.power == 1) return this.coeff + "x"; // Return x with the power if it equals 1.
		return this.coeff + "x^" + this.power; // Return x with the power if it is greater than 1. Note: An IllegalArgumentException would be thrown if the power was negative.
	}
}