package xy20170526.arithmeticForSort;

import org.junit.Test;

import util.ISort;
import util.SortSupport;

public class ShellSort<T extends Comparable<T>> implements ISort<T> {

	@Override
	public void sort(T[] arr) {
		T temp;
		int j;
		for(int increment=arr.length/2;increment>0;increment /=2){
			for(int i=increment;i<arr.length;i++){
				temp = arr[i];
				for(j=i;j>=increment;j-=increment){
					if(temp.compareTo(arr[j-increment])<0){
						arr[j] = arr[j-increment];
					}else{
						break;
					}
				}
				arr[j] = temp;
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
