/**
 * Warm-Up Project B: polynomial class - linked structure
 * This class is like that of Warm-Up Project A, except it uses the linked structure instead of a double array to manage polynomials.
 * Member methods: Constructor, evaluate(x), add, subtract, scale, and multiply. 
 * polynomial methods: sum, diff, and product.
 * Additional methods: insertSorted, isEmpty, toString, and exponent.
 * @author Alyssa Ayala, CSCI 313-13
 * Due Date: October 13th, 2021 @ 9:00AM
 */
public class polynomial {
	private node head; // polynomial attributes: a head and degree for each one
	private int degree; // degree >= 0
	
	// MEMBER METHODS: Constructor, evaluate(x), add, subtract, scale, and multiply. //
	
	/**
	 * A default constructor that creates an empty polynomial.
	 */
	public polynomial() {
		this.head = null;
		this.degree = -1;
	}
	
	/**
	 * Plugs the value of x into the polynomial.
	 * @param x The value to be plugged into the polynomial.
	 * @return The result of plugging the value of x into the polynomial.
	 */
	public double evaluate(double x) {
		if (this.isEmpty()) // Check for an empty polynomial.
			throw new IllegalArgumentException("Empty polynomial.");
		node pCurr = this.head;
		double result = 0;
		while (pCurr != null) {
			result += pCurr.term.coeff * this.exponent(x, pCurr.term.power);
			pCurr = pCurr.next;
		}
		return result;
	}
	
	/**
	 * Permanent addition by directly modifying polynomial p.
	 * @param q The polynomial to be added to the current polynomial.
	 * @return p = p + q
	 */
	public polynomial add(polynomial q) {
		if (this.isEmpty() || q.isEmpty()) // Check for an empty polynomial.
			throw new IllegalArgumentException("Empty polynomial.");
		node pCurr = this.head;
		node qCurr = q.head;
		while ((pCurr != null) || (qCurr != null)) {
			if (pCurr == null) { // Add q's terms if there's no more in p.
				this.insertSorted(qCurr.term);
				qCurr = qCurr.next;
			}
			else if (qCurr == null)  // Then we are finished.
				break;
			else if (pCurr.term.power == qCurr.term.power) {
				pCurr.term = new term(pCurr.term.coeff + qCurr.term.coeff, pCurr.term.power);
				pCurr = pCurr.next;
				qCurr = qCurr.next;
			}
			else if (qCurr.term.power > pCurr.term.power){ // pCurr.term.power < qCurr.term.power
				this.insertSorted(qCurr.term);
				qCurr = qCurr.next;
				this.degree = pCurr.term.power; // Then the degree of p needs to be updated.
			}
			else   // pCurr.term.power > qCurr.term.power
				pCurr = pCurr.next;	
		}
		return this;
	}
	
	/**
	 * Permanent subtraction by directly modifying polynomial p.
	 * @param q The polynomial to be subtracted from the current polynomial.
	 * @return p = p - q
	 */
	public polynomial subtract(polynomial p, polynomial q) {
		if (p.isEmpty() || q.isEmpty()) // Check for an empty polynomial.
			throw new IllegalArgumentException("Empty polynomial.");
		node pCurr = p.head;
		node qCurr = q.head;
		while ((pCurr != null) || (qCurr != null)) {
			if (pCurr == null) { // Subtract q's terms if there's no more in p.
				p.insertSorted(new term(-(qCurr.term.coeff), qCurr.term.power));
				qCurr = qCurr.next;
			}
			else if (qCurr == null)  // Then we are finished.
				break;
			else if (pCurr.term.power == qCurr.term.power) {
				pCurr.term = new term(pCurr.term.coeff - qCurr.term.coeff, pCurr.term.power);
				pCurr = pCurr.next;
				qCurr = qCurr.next;
			}
			else if (qCurr.term.power > pCurr.term.power){ // pCurr.term.power < qCurr.term.power
				p.insertSorted(new term(-(qCurr.term.coeff), qCurr.term.power));
				qCurr = qCurr.next;
				p.degree = pCurr.term.power; // Then the degree needs to be updated.
			}
			else  // Subtract (or add) the term with the largest power. 
				pCurr = pCurr.next;
		}
		return p;
	}
	
	/**
	 * Multiplies the polynomial by a constant scalar.
	 * @param a The constant scalar to multiply the current polynomial.
	 * @return p = a * p
	 */
	public polynomial scale(double a) {
		if (this.isEmpty()) // Check for an empty polynomial.
			throw new IllegalArgumentException("Empty polynomial.");
		node pCurr = this.head;
		while (pCurr != null) {
			pCurr.term = new term(a * pCurr.term.coeff, pCurr.term.power);
			pCurr = pCurr.next;
		}
		return this;
	}
	
	/**
	 * Permanent multiplication by directly modifying polynomial p.
	 * @param q The polynomial to be multiplied by the current polynomial.
	 * @return p = p * q
	 */
	public polynomial multiply(polynomial q) {
		if (this.isEmpty() || q.isEmpty()) // Check for an empty polynomial.
			throw new IllegalArgumentException("Empty polynomial.");
		node pCurr = this.head;
		node qCurr = q.head;
		while (pCurr != null) {
			while (qCurr != null) {
				pCurr.term = new term(pCurr.term.coeff * qCurr.term.coeff, pCurr.term.power + qCurr.term.power);
				qCurr = qCurr.next;
			}
			qCurr = q.head;
			pCurr = pCurr.next;
		}
		this.degree = this.head.term.power; // The degree has increased due to the powers of polynomials of p and q being added.
		return this;
	}
	
	// POLYNOMIAL METHODS: sum, diff, and multiply. //
	
	/**
	 * Temporary addition by placing the sum of two polynomials into a new one.
	 * @param p The first polynomial to be added.
	 * @param q The polynomial to be added to the first polynomial.
	 * @return r = p * q
	 */
	public static polynomial sum(polynomial p, polynomial q) {
		if (p.isEmpty() || q.isEmpty()) // Check for an empty polynomial.
			throw new IllegalArgumentException("Empty polynomial.");
		node pCurr = p.head;
		node qCurr = q.head;
		polynomial r = new polynomial();
		while ((pCurr != null) || (qCurr != null)) {
			if (pCurr == null) { // Continue adding, even if one of the nodes is finished. 
				r.insertSorted(qCurr.term);
				qCurr = qCurr.next;
			}
			else if (qCurr == null) { 
				r.insertSorted(pCurr.term);
				pCurr = pCurr.next;
			}
			else if (pCurr.term.power == qCurr.term.power) {
				r.insertSorted(new term(pCurr.term.coeff + qCurr.term.coeff, pCurr.term.power));
				pCurr = pCurr.next;
				qCurr = qCurr.next;
			}
			else if (pCurr.term.power > qCurr.term.power) { // Add the term with the largest power. 
				r.insertSorted(pCurr.term);
				pCurr = pCurr.next;
			}
			else { // pCurr.term.power < qCurr.term.power
				r.insertSorted(qCurr.term);
				qCurr = qCurr.next;
			}
		}
		return r;	
	}
	
	/**
	 * Temporary subtraction by placing the difference between two polynomials into a new one.
	 * @param p The first polynomial to be subtracted.
	 * @param q The polynomial to be subtracted from the first polynomial.
	 * @return r = p - q
	 */
	public static polynomial diff(polynomial p, polynomial q) {
		if (p.isEmpty() || q.isEmpty()) // Check for an empty polynomial.
			throw new IllegalArgumentException("Empty polynomial.");
		node pCurr = p.head;
		node qCurr = q.head;
		polynomial r = new polynomial();
		while ((pCurr != null) || (qCurr != null)) {
			if (pCurr == null) { // Continue subtracting (or adding), even if one of the nodes is finished. 
				r.insertSorted(new term(-(qCurr.term.coeff), qCurr.term.power));
				qCurr = qCurr.next;
			}
			else if (qCurr == null) { 
				r.insertSorted(pCurr.term);
				pCurr = pCurr.next;
			}
			else if (pCurr.term.power == qCurr.term.power) {
				r.insertSorted(new term(pCurr.term.coeff - qCurr.term.coeff, pCurr.term.power));
				pCurr = pCurr.next;
				qCurr = qCurr.next;
			}
			else if (pCurr.term.power > qCurr.term.power) { // Subtract (or add) the term with the largest power. 
				r.insertSorted(pCurr.term);
				pCurr = pCurr.next;
			}
			else { // pCurr.term.power < qCurr.term.power
				r.insertSorted(new term(-(qCurr.term.coeff), qCurr.term.power));
				qCurr = qCurr.next;
			}
		}
		return r;	
	}
	
	/**
	 * Temporary multiplication by placing the product of two polynomials into a new one.
	 * @param p The first polynomial to be multiplied.
	 * @param q The polynomial to be multiplied by the first polynomial.
	 * @return r = p * q
	 */
	public static polynomial product(polynomial p, polynomial q) {
		if (p.isEmpty() || q.isEmpty()) // Check for an empty polynomial.
			throw new IllegalArgumentException("Empty polynomial.");
		node pCurr = p.head;
		node qCurr = q.head;
		polynomial r = new polynomial();
		while ((pCurr != null)) {
			while (qCurr != null) {
				r.insertSorted(new term(pCurr.term.coeff * qCurr.term.coeff, pCurr.term.power + qCurr.term.power));
				qCurr = qCurr.next;
			}
			qCurr = q.head;
			pCurr = pCurr.next;
		}
		return r;
	}
		
	// ADDITIONAL METHODS: insertSorted, isEmpty, toString, and exponent.
	
	/**
	 * Inserts a term into the current polynomial in sorted order.
	 * @param term The term to be inserted into the current polynomial in sorted order.
	 */
	public void insertSorted(term term) {
		if (this.isEmpty()) { // Check for an empty polynomial.
			this.head = new node(term);
			this.degree = head.term.power; // Set the degree to that of the first term.
			return;
		}
		
		node headTemp = this.head;
		node nextTemp = this.head.next;
		node termTemp = new node(term);

		while (headTemp != null) { // Add coefficients with the same degree. 
			if (headTemp.term.power == term.power) {
				headTemp.term = new term(headTemp.term.coeff + term.coeff, term.power);
				return;
			}
			headTemp = headTemp.next;
		}
		headTemp = this.head;
	
		if (head.term.compareTo(term) < 0) { // Bring the highest degree to the beginning of the polynomial.
			termTemp.next = this.head;
			this.head = termTemp;
			this.degree = this.head.term.power; // Set the degree to that of the new highest degree.
			return;
		}
		
		while (nextTemp != null && (nextTemp.term.compareTo(termTemp.term) > 0)) { // Sort the term from greatest to smallest.
			headTemp = headTemp.next;
			nextTemp = nextTemp.next;
		}
		
		termTemp.next = nextTemp; // Occurs when the term is smaller than the previously smallest term.
		headTemp.next = termTemp;
	}
	
	/**
	 * Checks if the polynomial is empty.
	 * @return 1 if the polynomial is empty, 0 otherwise.
	 */
	private boolean isEmpty() {
		return this.head == null;
	}
	
	/**
	 * Handles how a polynomial is displayed to the user, including what sign should be placed
	 * depending on the sign of the coefficient.
	 */
	public String toString() {
		if (this.isEmpty()) // Check for an empty polynomial.
			throw new IllegalArgumentException("Empty polynomial.");
		node curr = this.head;
		String str = curr.term + "";
		while (curr.next != null) {	
			 if (curr.next.term.coeff != 0) { // Nothing should be added to the polynomial if the coefficient is 0.
				if (curr.next.term.coeff > 0)
					str += " + " + curr.next.term;
				else
					str += " - " + new term(-(curr.next.term.coeff), curr.next.term.power);
			}
			curr = curr.next;
		}
		return str;
	}
	
	/**
	 * Determines the result of raising a number to a certain exponent. 
	 * @param x The value to be raised to a certain exponent.
	 * @param exp The exponent to raise the value of x.
	 * @return x^exp
	 */
	private double exponent(double x, int exp) {
		if (exp == 0) // Anything raised to the power of 0 is 1. 
			return 1;
		double result = x;
		for (int i = 0; i < exp - 1; i++)
			result *= x; 
		return result;
	}
}