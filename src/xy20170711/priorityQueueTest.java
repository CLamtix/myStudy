package xy20170711;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

public class priorityQueueTest {
	public static void main(String[] args) {
		Queue q = new PriorityQueue<TT>();
		Random rand = new Random();
		for(int i = 0;i<10;i++){
			q.add(rand.nextInt(100));
		}
		System.out.println(q.toString());
		while(q.size()!=0){
			System.out.println(q.poll());
		}
	}
}
class TT{
	
}
