package xy20170526.arithmeticForSort;

import org.junit.Test;

import util.SortSupport;

public class HeapSort {

	public void sort(Comparable[] arr) {
		for(int i =(arr.length-1)/2;i>=0;i--){
			maxHeapDown(arr,i,arr.length-1);
		}
		for(int j=arr.length-1;j>0;j--){
			SortSupport.changeLocation(arr, 0, j);
			maxHeapDown(arr,0,j-1);
		}
		
	}
	
	private void maxHeapDown(Comparable[] arr, int start, int end) {
		int l = start*2+1;
		int r = l+1;
		int p = start;
		Comparable temp = arr[p];
		for(;l<=end;p=l,l=l*2+1,r=l+1){
			if(l<end && arr[l].compareTo(arr[r])<0){
				l = r;
			}
			if(temp.compareTo(arr[l])>0){
				break;
			}else{
				SortSupport.changeLocation(arr, l, p);
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
