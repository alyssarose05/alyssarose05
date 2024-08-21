
public class HomeworkMain {
	public static void main(String[] args) {
		queue a = new queue();
		a.enQ(5);
		a.enQ(6);
		a.enQ(7);
		a.deQ();
		System.out.println(a.peekFront());
	}
}

