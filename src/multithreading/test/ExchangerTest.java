package multithreading.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;

public class ExchangerTest {
	
	public static void main(String[] args) {
		List<String> listp = new ArrayList<String>();
		for(int t=0;t<5;t++){
			listp.add("produce:"+t);
		}
		
		List<String> listc = new ArrayList<String>();
		for(int t=0;t<5;t++){
			listc.add("consume:"+t);
		}
		Exchanger ex = new Exchanger();
		Thread consume = new Thread(new Consume(ex,listc));
		Thread produce = new Thread(new Produce(ex,listp));
		consume.start();
		produce.start();
		
	}
	
	static class Consume implements Runnable{
		private List<String> commodity;
		private Exchanger ex;
		public Consume(Exchanger ex,List<String> list) {
			this.commodity = list;
			this.ex = ex;
		}
		@Override
		public void run() {
			for(int i=0;i<4;i++){
				try {
					commodity = (List<String>) ex.exchange(commodity);
					for(int j=0;j<commodity.size();j++){
						commodity.set(j, commodity.get(j)+"C"+i+"-");
					}
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println("C:"+commodity);
		}
	
	}
	
	static class Produce implements Runnable{
		private List<String> commodity;
		private Exchanger ex;
		public Produce(Exchanger ex,List<String> list) {
			this.commodity = list;
			this.ex = ex;
		}
		@Override
		public void run() {
			for(int i=0;i<4;i++){
				try {
					commodity = (List<String>) ex.exchange(commodity);
					for(int j=0;j<commodity.size();j++){
						commodity.set(j, commodity.get(j)+"P"+i+"-");
					}
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println("P:"+commodity);
		}
	}
}
