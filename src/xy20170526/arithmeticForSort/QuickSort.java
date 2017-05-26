package xy20170526.arithmeticForSort;

import org.junit.Test;

import util.ISort;
import util.SortSupport;

public class QuickSort<T extends Comparable<T>> implements ISort<T>{
	
	public void sort(T[] arr) {
		this.sort(arr, 0, arr.length-1);
	}
	
	private void sort(T[]arr,int low,int high){
		int start = low;
		int end = high;
		T key = arr[start];
		while(start<end){
			while(end>start && arr[end].compareTo(key)>=0){
				end--;
			}
			if(arr[end].compareTo(key)<=0){
				SortSupport.changeLocation(arr, start, end);
			}
			while(end>start && arr[start].compareTo(key)<=0){
				start++;
			}
			if(arr[start].compareTo(key)>=0){
				SortSupport.changeLocation(arr, start, end);
			}
		}
		if(start>low) sort(arr,low,start-1);
		if(end<high) sort(arr,end+1,high);
	}
	
	@Test
	public void test(){
		T[] strArr = (T[]) SortSupport.getRandomArr(Integer.class, 10);
		sort(strArr);
		SortSupport.printArr("ÅÅÐòºó:",strArr);
	}

}
