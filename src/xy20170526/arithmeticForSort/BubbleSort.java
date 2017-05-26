package xy20170526.arithmeticForSort;

import org.junit.Test;

import util.ISort;
import util.SortSupport;

public class BubbleSort<T extends Comparable<T>> implements ISort<T> {

	@Override
	public void sort(T[] arr) {
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
		T[] arr = (T[]) SortSupport.getRandomArr(Integer.class, 10);
		sort(arr);
		SortSupport.printArr("≈≈–Ú∫Û:", arr);
	}

}
