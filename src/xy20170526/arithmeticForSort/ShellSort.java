package xy20170526.arithmeticForSort;

import org.junit.Test;

import util.SortSupport;

public class ShellSort {

	public void sort(Comparable[] arr) {
		Comparable temp;
		int j;
		for(int increment=arr.length/2;increment>0;increment /=2){
			for(int i=increment;i<arr.length;i++){
				temp = arr[i];
				for(j=i-increment;j>=0;j-=increment){
					if(temp.compareTo(arr[j])<0){
						arr[j+increment] = arr[j];
					}else{
						break;
					}
				}
				arr[j+increment] = temp;
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


/*package xy20170526.arithmeticForSort;

import org.junit.Test;

import util.SortSupport;

public class ShellSort {

	public void sort(Comparable[] arr) {
		Comparable temp;
		int j;
		for(int increment=arr.length/2;increment>0;increment /=2){
			for(int k=0;k<increment;k++){
				for(int i=k+increment;i<arr.length;i++){
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
	}
	
	@Test
	public void test(){
		Comparable[] arr = SortSupport.getRandomArr(Integer.class, 10);
		sort(arr);
		SortSupport.printArr("排序后:", arr);
		System.out.println("检查结果:"+SortSupport.checkSorted(arr, true));
	}

}
*/