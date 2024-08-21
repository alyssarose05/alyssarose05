/**
 * Warm-Up Project A: Polynomial Class - Double Array
 * This class uses a double array of coefficients and an integer degree to do the following operations with polynomials:
 * Member Methods: A constructor, evaluate(x), add, subtract, scale, and multiply. 
 * Polynomial Methods: sum, diff, and product.
 * Additional Methods: toString, simplify, and exponent.
 * @author Alyssa Ayala, CSCI 313-13
 * Due Date: September 27th, 2021 @ 9:00AM
 */
public class Polynomial {
	
	protected double[] coeff; // Polynomial attributes: A coefficient for each degree
	protected int deg; // deg >= 0
	
	// MEMBER METHODS: A constructor, evaluate(x), add, subtract, scale, and multiply. //
	
	/**
	 * A constructor that takes one coefficient and the degree of the polynomial.
	 * @param coeff The coefficient of the polynomial.
	 * @param deg The degree of the polynomial.
	 */
	 public Polynomial(double coeff, int deg) {
		 if (deg < 0) // If deg, the degree the user wants, is negative, or if deg >= 0 is false.
			 throw new IllegalArgumentException("Negative degree.");
		 this.coeff = new double[deg + 1];
	     this.coeff[deg] = coeff;
	     this.simplify(); 
	 }
	 
	 /**
	  * A constructor that takes an array of coefficients and the degree of the polynomial.
	  * @param coeff The array of coefficients of the polynomial.
	  * @param deg The degree of the polynomial.
	  */
	 public Polynomial(double[] coeff, int deg) {
		if (deg < 0) // If deg, the degree the user wants, is negative, or if deg >= 0 is false.
			 throw new IllegalArgumentException("Negative degree.");
		this.coeff = new double[deg + 1];
		for (int i = 0; i < coeff.length; i++)
			 this.coeff[i] = coeff[i];
		this.simplify();
	 }
	 
	 /**
	  * A constructor that replaces the current polynomial with another one.
	  * @param q The polynomial to replace the current polynomial.
	  */
	 public Polynomial(Polynomial q) {
		 this.coeff = new double[q.coeff.length]; // Note: Polynomial q would throw an IllegalArgumentException in its constructor if its degree was negative.
		 for (int i = 0; i < q.coeff.length; i++)
			 this.coeff[i] = q.coeff[i];
		 this.simplify();
	 }
	 
	 /**
	  * Evaluates the value of the polynomial when x is replaced with a double value.
	  * @param x The value that is plugged into the polynomial.
	  * @return The result of plugging x into the polynomial.
	  */
	public double evaluate(double x) {
		 double result = 0;
	        for (int i = 0; i < this.coeff.length; i++)
	            result += this.coeff[i] * this.exponent(x,i); // Goal: For each degree: multiply the coefficient by the result of raising x to the power of the degree.
	     return result; 
	}
	
	/**
	 * Permanent addition by directly modifying the current polynomial.
	 * @param q The polynomial to be added to the current one.
	 * @return p = p + q
	 */
	public Polynomial add(Polynomial q) { 
		if (q.deg > this.deg) { 
			Polynomial temp = new Polynomial(this); 
			this.coeff = new double[q.coeff.length];// We'd also need to replace the polynomial with to have its original values, but q's degree.
			for (int i = 0; i < temp.coeff.length; i++)
				this.coeff[i] = temp.coeff[i]; // Now the current polynomial has the maximum degree.
		}
		for (int i = 0; i < q.coeff.length; i++) 
			this.coeff[i] += q.coeff[i]; 
		this.simplify();
		return this;
	}
	
	/**
	 * Permanent subtraction by directly modifying the current polynomial.
	 * @param q The polynomial to be subtracted from the current one.
	 * @return p = p - q
	 */
	public Polynomial subtract(Polynomial q) {
		if (q.deg > this.deg) { 
			Polynomial temp = new Polynomial(this);
			this.coeff = new double[q.coeff.length]; // We'd also need to replace the polynomial with to have its original values, but q's degree.
			for (int i = 0; i < temp.coeff.length; i++)
				this.coeff[i] = temp.coeff[i]; // Now the current polynomial has the maximum degree.
		}
		for (int i = 0; i < q.coeff.length; i++) 
			this.coeff[i] -= q.coeff[i]; 
		this.simplify();
		return this;
	}
	
	/**
	 * Multiplies a constant scalar by the current polynomial.
	 * @param a The constant scalar to be multiplied by the current polynomial.
	 * @return p = a * p
	 */
	public Polynomial scale(double a) { 
		for (int i = 0; i < this.coeff.length; i++)
			this.coeff[i] *= a; // Multiply each coefficient by the constant scalar.
		return this;
	}

	/**
	 * Permanent multiplication by directly modifying the current polynomial.
	 * @param q The polynomial to be multiplied by the current one.
	 * @return p = p * q
	 */
	public Polynomial multiply(Polynomial q) { 
		if ((this.deg == 0) && (q.deg == 0)) { 
			this.coeff[0] = this.coeff[0] * q.coeff[0];
			this.simplify();
			return this;
		}
				
		Polynomial temp = new Polynomial(0, this.deg); 
		for (int i = 0; i <= this.deg; i++)
			temp.coeff[i] = this.coeff[i];
		this.coeff = new double[(this.deg + q.deg) + 1];  
		 for (int i = 0; i <= temp.deg; i++) 
		        for (int j = 0; j <= q.deg; j++) 
		               this.coeff[i + j] += (temp.coeff[i] * q.coeff[j]);
		 this.simplify();
		 return this;

	}
	
	//  POLYNOMIAL METHODS: sum, diff, and product. //
	
	/**
	 * Temporary addition by placing the sum of two polynomials to a new one.
	 * @param p The first polynomial to be added.
	 * @param q The second polynomial to be added to the first polynomial.
	 * @return r = p + q
	 */
	public static Polynomial sum(Polynomial p, Polynomial q) {
		Polynomial r;
		if (p.deg > q.deg) // The degree must be the maximum degree out of the two polynomials to have room for all values.
			r = new Polynomial(p.coeff, p.deg); 
		else
			r = new Polynomial(p.coeff, q.deg);
		for (int i = 0; i <= q.deg; i++)
			r.coeff[i] += q.coeff[i];
		r.simplify();
		return r;
	}
	
	/**
	 * Temporary subtraction by placing the difference between two polynomials to a new one.
	 * @param p The first polynomial to be subtracted
	 * @param q The polynomial to be subtracted from the first polynomial.
	 * @return r = p - q
	 */
	public static Polynomial diff(Polynomial p, Polynomial q) {
		Polynomial r;
		if (p.deg > q.deg) // The degree must be the maximumd egree out of the two polynomials to have room for all values.
			r = new Polynomial(p.coeff, p.deg);
		else
			r = new Polynomial(p.coeff, q.deg);
		for (int i = 0; i <= q.deg; i++) 
			r.coeff[i] -= q.coeff[i]; // The current polynomial being subtracted from another. 
		r.simplify();
		return r;
	}
	
	/**
	 * Temporary multiplication by placing the product of two polynomials to a new one.
	 * @param p The polynomial to be multiplied.
	 * @param q The polynomial to be multiplied by the first polynomial.
	 * @return r = p * q
	 */
	public static Polynomial product(Polynomial p, Polynomial q) {
		Polynomial r = new Polynomial(p.coeff, p.deg + q.deg); // When we multiply the coefficients, we must also add the degrees. 
        for (int i = 0; i <= p.deg; i++) 
        	for (int j = 0; j <= q.deg; j++) 
        		r.coeff[i + j] = (p.coeff[i] * q.coeff[j]); // Sum of the degrees with the product of the coefficients.
        r.simplify();
        return r;
	}
	
	// ADDITIONAL METHODS: toString, simplify, and exponent. //
	
	/**
	 * Handles how exactly a polynomial is displayed to the user, including when x's and their coefficients
	 * are placed depending on the value of both the coefficient and degree. 
	 */
	 public String toString() {
		if (this.deg == 0) // If the degree is 0 or 1, we should return something right away to save time.
			return this.coeff[0] + "";
		else if (this.deg == 1) {
			if (this.coeff[0] > 0)
				return this.coeff[1] + "x + " + this.coeff[0];
			else if (this.coeff[0] < 0)
				return this.coeff[1] + "x - " + -(this.coeff[0]);
			else
				return this.coeff[1] + "x";
		}
			
		String str = this.coeff[deg] + "x^" + this.deg; // this.deg > 1 Note: A polynomial would throw an IllegalArgumentException in its constructor if its degree were negative.
		for (int i = this.deg - 1; i >= 0; i--) {
			if (this.coeff[i] != 0) { // Nothing should be added to the polynomial if the coefficient is 0.
				if (this.coeff[i] > 0)
					str += " + " + coeff[i];
				else // this.coeff[i] < 0
					str += " - " + -(coeff[i]);
			    if (i > 1)
				    str += "x^" + i;
			    else if (i == 1)
				    str += "x";
			}
		}
		return str; 
	 }
	 
	 /**
	  * Simplifies the current polynomial and determines its new degree, getting rid of any extra 0's at the top of the polynomial.
	  */
	 private void simplify() {
		 for (int i = this.coeff.length - 1; i >= 0; i--) 
			 if (this.coeff[i] != 0) { // The first coefficient that isn't 0 contains the maximum degree.
				 this.deg= i;
	             break;
			 }   
	  }
	 
	 /**
	  * Determines the exponent of a number.
	  * @param x The number to be raised to the power of a number.
	  * @param exp The exponent to raise the number.
	  * @return The result of raising x to the exp power.
	  */
	 private double exponent(double x, double exp) {
		 if (exp == 0) // Anything raised to the power of 0 equals 1.
			 return 1;
		 double result = x; // exp > 0. Note: Polynomials would throw an IllegalArgumentException in their constructors if they had a negative degree.
		 for (int i = 0; i < exp - 1; i++)
			 result *= x;
		 return result;
	 }
}