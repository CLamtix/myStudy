package xy20170526.arithmeticForSort;

import org.junit.Test;

import util.SortSupport;

public class InsertSort {

	public void sort(Comparable[] arr) {
		Comparable temp;
		int j;
		for(int i=1;i<arr.length;i++){
			temp = arr[i];
			for(j=i;j>0&&temp.compareTo(arr[j-1])<0;j--){
				arr[j] = arr[j-1];
			}
			arr[j] = temp;
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
