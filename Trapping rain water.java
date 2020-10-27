/*LintCode 1849: Grumpy Bookstore Owner*/
/*滑动窗口求解，滑动 X天的窗口，获得最大值。
Input: customers = [1,0,1,2,1,1,7,5], grumpy = [0,1,0,1,0,1,0,1], X = 3
Output: 16
Explanation: The bookstore owner keeps themselves 
not grumpy for the last 3 minutes. 
The maximum number of customers that can
 be satisfied = 1 + 1 + 1 + 1 + 7 + 5 = 16.
*/

public class Solution {
    /**
     * @param customers: the number of customers
     * @param grumpy: the owner's temper every day
     * @param X: X days
     * @return: calc the max satisfied customers
     */
    public int maxSatisfied(int[] C, int[] A, int X) {
        int left = 0;
        int right = 0;
        int baseSatisified = 0;
        int curMax = 0;
        int max = 0;
        
        //Calculate the overall satisifaction without `grumpy`
        for (int i = 0; i < C.length; i++) {
            if (A[i] != 1) {// this is 0 then
                baseSatisified += C[i];
            }
        }

        //Sliding window templete 
        while (right < C.length) {
        	
            if (A[right] == 1) {
                //System.out.println(C[right]);
                curMax += C[right];
            }
            right++;
            //update the max within window range
            max = Math.max(max, curMax); 
            
            //update window range
            if (right - left == X) {
                if (A[left] == 1){
                  curMax -=C[left];    
                }
                left++;
            }
        }
        return baseSatisified + max;
    }

}