import java.util.Arrays;
import java.util.Random;
/**
 * This Program are implementations of 5 Sorting algorithms: 
 * quadratic sorts:
	 * bubble sort
	 * insertion sort
	 * selection sort 
 * divide-and-conquer:
 	* merge sort
 	* quick sort
 *
 * It also prints the different times it takes for the algorithms to sort the same array from 
 * 50,000 elements to 500,000 elements, incrementing by 50,000 each time.
 */

public class SortingAlgorithms {
	/*
	 * Merge sort: divide and conquer algorithm that splits the array and combines the splitted array in order. 
	 */
	public static void MergeSort(float[] arr, int low, int high) {
        if (high - low < 2) {
            return;
        }

        int mid = (high + low) / 2;

        MergeSort(arr, low, mid);
        MergeSort(arr, mid, high);

        Merge(arr, low, mid, high);
    }
	
	private static void Merge(float[] arr, int low, int mid, int high) {
        // 0 left side and then n right side mid middle
        // high - mid-1 mid - high-1
        
        int sArr1 = low;
        int sArr2 =  mid;
        float[] sortedArr= new float[high-low];
        int index=0;
        //does the merging and keeping track of the indices
        for ( ; index<high; index++) {
            
            if (sArr1==mid || sArr2==high) {
                break;
            }
            
            if (arr[sArr1]>arr[sArr2]) {
                sortedArr[index]=arr[sArr2];
                sArr2++;
            }else {
                sortedArr[index]=arr[sArr1];
                sArr1++;
            }
        }
        // handles the3 case to adding at the end of the arrs
        if (sArr1 == (mid)) {
            for (int i = index;i<high-low;i++) {
                sortedArr[i] = arr[sArr2++]; 
            }
        }else if (sArr2 == (high)){
            for (int i = index;i<high-low;i++) {
                sortedArr[i] = arr[sArr1++]; 
            }
        }
        int beg = low;
        
        for (int i = 0; i<sortedArr.length; i++, beg++) {
            arr[beg]=sortedArr[i];
        }
		
	}
	

	/*
	 * Quick sort: divide and conquer algorithm that always places the pivot in the correct position
	 */
	 private static void QuickSort(float[] arr, int beg, int end){
        if (end-beg<=1) {
            return;
        }

        int pivotIndex = partition(arr, beg, end);
        QuickSort(arr,beg,pivotIndex);
        QuickSort(arr,pivotIndex+1,end);
    }
	private static int partition(float[] arr, int beg, int end) {
        int i = beg, j=beg;
        float temp = 0;
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
     * Insertion sort: looks at the entry in at index i and compares it to the previous entries to determine where to place the value.
     */
    private static void  InsertionSort(float[] arr) {
		
		for (int i = 1;i<arr.length;i++) {
			float temp = arr[i];
			int j = i-1;
			while(j >= 0 && arr[j] > temp) {
				arr[j+1]=arr[j];
				j--;
			}
			arr[j+1] = temp;
		}
	}
	
    /* 
     * Bubble Sort: bubbles the largest element to the end of array, comparing adjacent entries
     */
    private static void BubbleSort(float[] arr){
		float temp = 0;
        
        for (int i = arr.length-1; i>=0;i--) {
           
            for (int j = 1; j<=i;j++) {
                if (arr[j-1]>arr[j]) {
                    temp = arr[j-1];
                    arr[j-1] = arr[j];
                    arr[j] = temp;
                    
                }
            }
        }
	}
    
	/*
	 * Selection sort: finds the smallest entry from i - n-1 and places it at index of i
	 */
    private static void SelectionSort(float[] arr){
		
		float temp = 0;
        int indexMin = 0;
        float currentMin=0;
        for (int i = 0;i<arr.length;i++) {
            currentMin=arr[i];
            indexMin = i;
            for (int j = i; j<arr.length;j++) {
                 if (arr[j]<currentMin){
                    currentMin=arr[j];
                     indexMin=j;
                  }
            }
            if (arr[indexMin]<arr[i]) {
                temp=arr[i];
                arr[i]=arr[indexMin];
                arr[indexMin]=temp;
            }
        }
		
	}
    
    /*
     * Sort 
     * Purpose: the function in charge of calling the different coding Algorithms and returns the time taken
     */   
    public static long sort(String algorithm, float [] arr) {
    	
    	long start = System.currentTimeMillis();
    	if (algorithm.equals("quick")) {
 
    		QuickSort(arr, 0, arr.length);
    		
    	}else if(algorithm.equals("merge")) {
    		
    		MergeSort(arr, 0, arr.length);
    		
    	}else if(algorithm.equals("insertion")) {
    		
    		InsertionSort(arr);
    		
    	}else if(algorithm.equals("bubble")){
    		
    		BubbleSort(arr);
    		
    	}else if(algorithm.equals("selection")) {
    		
    		SelectionSort(arr);
    		
    	}
    	long end = System.currentTimeMillis();
    	return end-start;
		
	}
    
    /*
     * Random: a method that creates arrays with random floats with values from -500 - 500
     */
	private static float[] Random(int size) {
		float[] arr = new float[size];
		
		for (int i =0; i<arr.length;i++) {
			Random rand = new Random();
			arr[i]=rand.nextFloat(-500, 500);
		}
		return arr;
	}
	
	/*
	 * copiedArr: produces a copy on the array so that for each size all the sorting algorithms have the same array to sort.
	 */
	private static float[] copiedArr(float [] arr) {
		float[] copiedArr = Arrays.copyOf(arr, arr.length);
		return copiedArr;
		
	}


	public static void main (String [] args) {
		// Printing the values for each sorthing algorithms so that I can graph them.
		String[] sorts = {"bubble", "selection","merge","insertion", "quick"};
		for (int k=50000; k<=500000;k+=50000) {
			float[] arr = Random(k);
			System.out.println("Size of the array: "+ k);
			for (String sortingAlgorithms : sorts) {
				float[] arrToSort = copiedArr(arr);
				long time = sort(sortingAlgorithms, arrToSort);
				System.out.println("The sort is: " + sortingAlgorithms);
				System.out.println(time);
				arr=Random(k);
			}
			System.out.println();
			System.out.println();
		}
	}
}