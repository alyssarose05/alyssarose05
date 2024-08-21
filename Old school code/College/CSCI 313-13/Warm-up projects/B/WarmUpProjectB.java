/**
 * A class that tests the polynomial class that uses a linked structure.
 * @author Alyssa Ayala, CSCI 313-13
 * (not being handed in)
 */
public class WarmUpProjectB {
	public static void main(String[] args) {
		polynomial g = new polynomial();
		polynomial h = new polynomial();
		g.insertSorted(new term(5,7));
		h.insertSorted(new term(3,7));
		g.insertSorted(new term(3,2));
		System.out.println(g.evaluate(4));
	
		
	}
}
