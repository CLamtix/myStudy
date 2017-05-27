package util;

import java.util.Arrays;
import java.util.Random;

public class SortSupport {
	
	public static Random rand = new Random();
	public static Comparable[] getRandomArr(Class clazz,int size){
		Comparable[] arr = null;
		if(clazz.getName().equals("java.lang.String")){
			arr = new String[size];
			for(int i=0;i<size;i++){
				arr[i] = rand.nextInt(size*10)+"";
			}
		}else if(clazz.getName().equals("java.lang.Integer")){
			arr = new Integer[size];
			for(int i=0;i<size;i++){
				arr[i] = rand.nextInt(size*10);
			}
		}
		printArr("ÅÅÐòÇ°:",arr);
		return arr;
	}
	
	public static void printArr(String preStr,Object[] arr){
		System.out.println(preStr+Arrays.toString(arr));
	}
	
	public static void changeLocation(Object[] o ,int index1,int index2){
		Object temp = o[index1];
		o[index1] = o[index2];
		o[index2] = temp;
	}
	public static boolean checkSorted(Comparable[] o,boolean isIncrement){
		if(isIncrement){
			for(int i=0;i<o.length-1;i++){
				if(o[i+1].compareTo(o[i])<0){
					return false;
				}
			}
		}else{
			for(int i=0;i<o.length-1;i++){
				if(o[i+1].compareTo(o[i])>0){
					return false;
				}
			}
		}
		return true;
	}
}
