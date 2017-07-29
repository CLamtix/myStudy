package xy20170609;

public class QuoteTest {
	public static void main(String[] args) {
		
		Integer t1 = null;
		
		Integer o = t1;
		System.out.println(o);
		t1 = new Integer(1);
		System.out.println(o);
	}
}
