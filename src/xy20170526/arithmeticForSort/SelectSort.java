package xy20170526.arithmeticForSort;

import org.junit.Test;

import util.SortSupport;

public class SelectSort {

	public void sort(Comparable[] arr) {
		for(int i=0;i<arr.length;i++){
			int temp = i;
			for(int j=i;j<arr.length;j++){
				if(arr[j].compareTo(arr[temp])<0){
					temp = j;
				}
			}
			if(temp!=i){
				SortSupport.changeLocation(arr, temp, i);
			}
		}
	}
	
	@Test
	public void test(){
		Comparable[] arr = SortSupport.getRandomArr(Integer.class, 10);
		sort(arr);
		SortSupport.printArr("排序后:", arr);
		System.out.println("检查结果:"+SortSupport.checkSorted(arr, true));
	}

}
