package xy20170526.arithmeticForSort;

import org.junit.Test;

import util.ISort;
import util.SortSupport;

public class SelectSort<T extends Comparable<T>> implements ISort<T> {

	@Override
	public void sort(T[] arr) {
		for(int i=0;i<arr.length;i++){
			int temp = i;
			for(int j=i;j<arr.length-1;j++){
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
		T[] arr = (T[]) SortSupport.getRandomArr(Integer.class, 10);
		sort(arr);
		SortSupport.printArr("ÅÅÐòºó:", arr);
	}

}
