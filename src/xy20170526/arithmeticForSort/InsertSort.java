package xy20170526.arithmeticForSort;

import org.junit.Test;

import util.ISort;
import util.SortSupport;

public class InsertSort<T extends Comparable<T>> implements ISort<T> {

	@Override
	public void sort(T[] arr) {
		T temp;
		int j;
		for(int i=0;i<arr.length;i++){
			temp = arr[i];
			for(j=i;j>0&&temp.compareTo(arr[j-1])>0;j--){
				arr[j] = arr[j-1];
			}
			arr[j] = temp;
		}
	}
	
	@Test
	public void test(){
		T[] arr = (T[]) SortSupport.getRandomArr(Integer.class, 10);
		sort(arr);
		SortSupport.printArr("≈≈–Ú∫Û:", arr);
	}

}
