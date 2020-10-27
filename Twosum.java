/*Example 1:
Input: nums = [2,7,11,15], target = 9
Output: [0,1]
Output: Because nums[0] + nums[1] == 9, we return [0, 1].
*/
/*然后对数组排序，在排序后的数组中利用双指针从左右向中间寻找

如果numbers[i] + numbers [j] == target 说明找到答案
如果numbers[i] + numbers [j] < target 说明当前和比答案小。左指针右移
如果numbers[i] + numbers [j] > target 说明当前和比答案大。右指针左移
--- 时间复杂度O(nlogn) -- n为数组的大小
--- 空间复杂度O(n) -- n为数组的大小

Hashmap O(n), O(n)
*/
public class Solution{
	public int[] twoSum(int[] numbers, int target){
		int[] result = new int[2];
		Hashmap<Integer, Integer> map = new Hashmap<>();
		//int[] is the length
		for(int i = 0; i < numbers.length; i++) {
			if (map.containsKey(numbers[i])) {
				result[0] = map.get(nums[i]);
                result[1] = i;
                return result;
			}else {
				map.put(target - numbers[i],i)
			}
		}
		return result;
	}
}

/*
Two sum II 对撞型
Given an array of integers that is already sorted in ascending order, 
find two numbers such that they add up to a specific target number.
*/

public int[] twoSumII(int[] A, int T) {
	int[] result = new int[2];
	if (A == null || A.length <= 1) {
		return result;
	}

	int n = A.length;
	int i, j = n-1;
	for (int i = 0; i < n; i++) {
		if (i > 0 && A[i] == A[i-1]) {
			continue;
		}
		// 考虑满足某个条件
		while (j >= 0 && A[j] > T - A[i]) {
			--j;
		}

		if (j < 0 || i>= j){
			break;
		}

		// i < j
		if (A[i] + A[j] == T) {
			res[0] = i + 1;
			res[1] = j + 1;
			return res;
		}
	}
	return res;
}

