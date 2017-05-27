package xy20170526.arithmeticForSort;

import org.junit.Test;

import util.SortSupport;

public class MergeSort {
	
	public void sort(Comparable[] arr) {
		sort(arr,0,arr.length-1);
	}
	
	private void sort(Comparable[] arr, int left, int right) {
		if(left>=right)
			return;
		int center = (left+right)/2;
		sort(arr,left,center);
		sort(arr,center+1,right);
		merge(arr,left,center,right);
	}


	private void merge(Comparable[] arr, int left, int center, int right) {
		Comparable[] temArr = (Comparable[]) new Comparable[right-left+1];
		int temp = 0;
		int mid = center+1;
		int ll = left;
		while(left<=center && mid<=right){
			if(arr[left].compareTo(arr[mid])>=0){
				temArr[temp++] = arr[mid++];
			}else{
				temArr[temp++] = arr[left++];
			}
		}
		while(left<=center){
			temArr[temp++] = arr[left++];
		}
		while(mid<=right){
			temArr[temp++] = arr[mid++];
		}
		System.arraycopy(temArr, 0, arr, ll, temArr.length);
	}

	@Test
	public void test(){
		Comparable[] arr = SortSupport.getRandomArr(Integer.class, 10);
		sort(arr);
		SortSupport.printArr("排序后:",arr);
		System.out.println("检查结果:"+SortSupport.checkSorted(arr, true));
	}
	
}
