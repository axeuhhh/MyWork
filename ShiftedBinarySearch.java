public class ShiftedBinarySearchStarterCode {
    public static void main(String[] args) {
        int[] arr1 = {2,5,8,23,99,103};
          int ret = shiftedSearchIndex(arr1, 50);
        System.out.println(ret);
    }
    
    /*
     * this method is a modified version of binary search where there are extra conditionals on whether to go left or right.
     */
    
    public static int shiftedSearchIndex(int[] nums, int target) {
        
    	int beg = 0;// Represent the beginning 
    	int end = nums.length-1;// Represents the end  	
    	
    	while(beg<=end) {
    		int mid = beg + (end-beg)/2;// middle
    		
    		// you want to check if the middle is the target
    		if (nums[mid]==target) {
    			return mid;
    		}
    		
    		//check if the target is in the right hand side. by checking the both that it  
    		//is greater than the mid and that the value of the end is less then or equal to it
    		 
    		  if (nums[mid] <= nums[end]) { // checks if the right side is sorted <=> the target is in that bound
                  if (target > nums[mid] && target <= nums[end]) {
                      beg = mid + 1;
                  } else {
                      end = mid - 1;
                  }
              } else { // checks if the left side is sortedf
                  if (target < nums[mid] && target >= nums[beg]) {
                      end = mid - 1;
                  } else {
                      beg = mid + 1;
                  }
              }
    	}
    	return -1;

    }

}