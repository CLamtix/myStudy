package xy20170526.arithmeticForSort;

import org.junit.Test;

import util.SortSupport;

public class QuickSort {
	
	public void sort(Comparable[] arr) {
		this.sort(arr, 0, arr.length-1);
	}
	
	private void sort(Comparable[]arr,int low,int high){
		int start = low;
		int end = high;
		Comparable key = arr[low];
		while(start<end){
			while(start<end && arr[end].compareTo(key)>=0) end--;
			if(arr[end].compareTo(key)<=0) SortSupport.changeLocation(arr, start, end);
			while(start<end && arr[start].compareTo(key)<=0) start++;
			if(arr[start].compareTo(key)>=0) SortSupport.changeLocation(arr, start, end);
		}
		if(low<start) sort(arr,low,start-1);
		if(end<high) sort(arr,end+1,high);
	}
	
	@Test
	public void test(){
		Comparable[] arr = SortSupport.getRandomArr(Integer.class, 10);
		sort(arr);
		SortSupport.printArr("排序后:",arr);
		System.out.println("检查结果:"+SortSupport.checkSorted(arr, true));
	}

}
