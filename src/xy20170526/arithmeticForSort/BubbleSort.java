package xy20170526.arithmeticForSort;

import org.junit.Test;
import util.SortSupport;

public class BubbleSort{

	public void sort(Comparable[] arr) {
		for(int i=0;i<arr.length-1;i++){
			for(int j=0;j<arr.length-i-1;j++){
				if(arr[j].compareTo(arr[j+1])>0){
					SortSupport.changeLocation(arr, j, j+1);
				}
			}
		}
	}
	
	@Test
	public void test(){
		Comparable[] arr = SortSupport.getRandomArr(Integer.class, 10);
		sort(arr);
		SortSupport.printArr("排序后:", arr );
		System.out.println("检查结果:"+SortSupport.checkSorted(arr, true));
	}
}
