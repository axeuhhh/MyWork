import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * QuickerThanQuickSort: 
 *  • this is an implementation of a sort algorithms that is supposed to be quicker than quick sort!
 *  • it also compares the speed of the original quick sort and the hybrid version created
 *  • the implementation uses insertion as the quadratic sort to make quick sort faster
 */
public class QuickerThanQuicksort {
	
	/*
	 * swap: swaps elements in an array from one index to the other.
	 */
	private static void swap(double[] arr, int index, int end) {
		double temp = arr[index];
		arr[index]= arr[end];
		arr[end] = temp;
	}

	private static int partition(double[] arr, int beg, int end) {
		int i = beg, j=beg;
		double temp = 0;
		Random rand = new Random();
		swap(arr, rand.nextInt(beg, end), end-1);
		//pivot is end -1 
		while (j<end-1) {
			if (arr[j]<=arr[end-1]) {
				temp = arr[j];
				arr[j]=arr[i];
				arr[i]=temp;
				i++;
			}
			j++;
		}
		temp=arr[i];
		arr[i]=arr[end-1];
		arr[end-1]=temp;
		
		return i;
	}
	
	/*
	 * Quicker than quick sort: implementation of quicker than quick sort
	 */
	public static void quicksort(double[] arr, int beg, int end){
		if (end-beg<=30) {
			InsertionSort(arr,beg,end);
			return;
		}

		int pivotIndex = partition(arr, beg, end);
		quicksort(arr,beg,pivotIndex);
		quicksort(arr,pivotIndex+1,end);
	}
	
	/*
	 * original quicksort: used to compare with quicker than quicksort.
	 */
	 private static void originalQuickSort(double[] arr, int beg, int end){
	        if (end-beg<=1) {
	            return;
	        }

	        int pivotIndex = partition(arr, beg, end);
	        originalQuickSort(arr,beg,pivotIndex);
	        originalQuickSort(arr,pivotIndex+1,end);
	    }   
	       
	////////////////////////////////////

	/*
     * Insertion sort: the quadratic sort that I decided to implement in combination with quick sort
     */
    private static void  InsertionSort(double[] arr, int beg, int end) {
		
		for (int i = beg;i<end;i++) {
			double temp = arr[i];
			int j = i-1;
			while(j >= 0 && arr[j] > temp) {
				arr[j+1]=arr[j];
				j--;
			}
			arr[j+1] = temp;
		}
	}
    
    /*
     * Random: a method that creates arrays with random doubles with values from -500 - 500
     */
    private static double[] Random(int size) {
		double[] arr = new double[size];
		
		for (int i =0; i<arr.length;i++) {
			Random rand = new Random();
			arr[i]=rand.nextFloat(-500, 500);
		}
		return arr;
	}
    
    /*
	 * copiedArr: produces a copy on the array so that for each size all the sorting algorithms have the same array to sort.
	 */
	private static double[] copiedArr(double [] arr) {
		double[] copiedArr = Arrays.copyOf(arr, arr.length);
		return copiedArr;
		
	}

	/*
     * Sort 
     * Purpose: the function in charge of calling the different coding Algorithms and returns the time taken
     */   
    public static long sort(String algorithm, double [] arr) {
    	
    	long start = System.nanoTime();
    	
    	if (algorithm.equals("quick")) {
 
    		quicksort(arr, 0, arr.length);
    		
    	}
    	else if(algorithm.equals("original")) {
    		
    		originalQuickSort(arr,0,arr.length);	
    		
    	}
    	else if(algorithm.equals("insertion")) {
    		
    		InsertionSort(arr,0,arr.length);
    		
    	}
    	
    	long end = System.nanoTime();
    	return end-start;
		
	}

	public static void main (String [] args) {
		
		Scanner scan = new Scanner(System.in);
		int input=0;
		System.out.println("Give me an number that is greater than 1 and I'll sort a list with that size for you! \n else just give me a negative number or 0 and I'll show you how I tested my program");
		if (scan.hasNextInt()) {
			input = scan.nextInt();
			if (input>=1) {
				double[] arr = Random(input);
				sort("quick", arr);
				for (double d : arr) {
					System.out.println(d);
				}
				
			}	
			else {
				//comparing quick sort and quicker than quick sort
				for (int i = 50000; i<=500000;i+=50000) {
					double[] originalArr = Random(i);
					double[] copiedArr = copiedArr(originalArr);
					long quick = sort("quick", copiedArr);
					copiedArr = copiedArr(originalArr);
					long insertion = sort("original", copiedArr);
					if (insertion>quick) {
						System.out.println(i + " mixed is quicker");
					}
					else {
						System.out.println(i + " original  is quicker");
					}
					
				}
				System.out.println();
				// printing the values for the time to compare insertionsort and quicksort
				for (int i = 0; i<50;i++) {
					double[] originalArr = Random(i);
					double[] copiedArr = copiedArr(originalArr);
					System.out.print(sort("quick", copiedArr));
					copiedArr = copiedArr(originalArr);
					System.out.println("       " +sort("insertion", copiedArr)+"       "+i);
				}
			}
		} 
	}
}