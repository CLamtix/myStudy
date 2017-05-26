package xy20170526.arithmeticForSort;

import org.junit.Test;

import util.ISort;
import util.SortSupport;

public class MergeSort<T extends Comparable<T>> implements ISort<T>{
	
	public void sort(T[] arr) {
		sort(arr,0,arr.length-1);
	}
	
	private void sort(T[] arr, int left, int right) {
		if(left>=right)
			return;
		int center = (left+right)/2;
		sort(arr,left,center);
		sort(arr,center+1,right);
		merge(arr,left,center,right);
	}


	private void merge(T[] arr, int left, int center, int right) {
		T[] temArr = (T[]) new Object[right-left];
		int temp = 0;
		int mid = center+1;
		while(left<=center && mid<=right){
			if(arr[left].compareTo(arr[mid])>=0){
				temArr[temp++] = arr[mid++];
			}else{
				temArr[temp++] = arr[left++];
			}
		}
		while(left<center){
			temArr[temp++] = arr[left++];
		}
		while(mid<right){
			temArr[temp++] = arr[mid++];
		}
		System.arraycopy(temArr, 0, arr, left, temArr.length);
	}

	@Test
	public void test(){
		T[] strArr = (T[]) SortSupport.getRandomArr(Integer.class, 10);
		sort(strArr);
		SortSupport.printArr("ÅÅÐòºó:",strArr);
	}

}
