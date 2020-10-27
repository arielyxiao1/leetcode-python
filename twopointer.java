/* 相向双指针
Two Sum 
*/
class Solution {
    public int[] twoSum(int[] nums, int target) {
        /*check the current one is there,
            not -- put, 9-2, index, so next time would just check the remain, and get the index.
        */
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        int[] result = new int[2];
        
        for(int i = 0; i< nums.length; i++){
            if(map.containsKey(nums[i])){
                result[0] = map.get(nums[i]);
                result[1] = i;
                return result;
            }
            
            map.put(target - nums[i] , i);
            
        }
        return result;
    }
}
/*167
Two Sum II - Input array is sorted*/



/*
同向双指针 sliding window
https://fanshehu.github.io/2019/04/22/Algorithms/%E5%8F%8C%E6%8C%87%E9%92%88%E7%AE%97%E6%B3%95-Two%20Pointers/
https://leetcode.com/problems/find-all-anagrams-in-a-string/discuss/92007/Sliding-Window-algorithm-template-to-solve-all-the-Leetcode-substring-search-problem.
*/
/*76 Minimum Window Substring*/
public class Solution {
    public String minWindow(String s, String t) {
        if(t.length() > s.length()) 
            return "";
        /*create a map to count the number of the character showing in String T*/
        Map<Character, Integer> map = new HashMap<>();
        for(char c : t.toCharArray()){
            map.put(c, map.getOrDefault(c,0) + 1);
        }
        //counter is the number of the different characters in the map
        int counter = map.size();
        /*use two pointer*/
        int left = 0, right = 0;
        int head = 0;
        int len = Integer.MAX_VALUE;
        
        while(right < s.length()){
            // add right to the window, so if it show in the map, count of this char need to --, if ==0, means this character is gone so counter--
            char c = s.charAt(right);
            if (map.containsKey(c)){
                map.put(c, map.get(c)-1);
                if(map.get(c) == 0) 
                    counter--;
            }
            //check the next one
            right++;
        
            //if the current window has all the variable in the T
            while(counter == 0){
                char tempc = s.charAt(left);
                // move the left in order to get the min len
                if(map.containsKey(tempc)){
                    map.put(tempc, map.get(tempc) + 1);
                    if(map.get(tempc) > 0){
                        counter++;
                    }
                }
                if(right - left < len){
                    len = right - left;
                    head = left;
                }
                left++;
            }
            
        }
        if(len == Integer.MAX_VALUE) return "";
        return s.substring(head, head+len);
    }
}

/*
3.longest substring without repeating characters
*/
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int ans = 0;
        int[] counter = new int[256];
        
        for (int l = 0, r = 0; r < s.length(); r++) {
            counter[s.charAt(r)]++;
            while (counter[s.charAt(r)] > 1) {
                counter[s.charAt(l)]--;
                l++;
            }
            ans = Math.max(ans, r-l+1);
        }
        
        return ans;
    }
}

/*159 Longest Substring with At Most Two Distinct Characters*/
public class Solution {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        Map<Character,Integer> map = new HashMap<>();
        int start = 0, end = 0, counter = 0, len = 0;
        while(end < s.length()){
            char c = s.charAt(end);
            map.put(c, map.getOrDefault(c, 0) + 1);
            if(map.get(c) == 1) counter++;//new char
            end++;
            while(counter > 2){
                char cTemp = s.charAt(start);
                map.put(cTemp, map.get(cTemp) - 1);
                if(map.get(cTemp) == 0){
                    counter--;
                }
                start++;
            }
            len = Math.max(len, end-start);
        }
        return len;
    }
}

/*438 Find All Anagrams in a String
*/
public class Solution {
  public List<Integer> findAnagrams(String s, String p) {
    int ns = s.length(), np = p.length();
    if (ns < np) return new ArrayList();

    int [] pCount = new int[26];
    int [] sCount = new int[26];
    // build reference array using string p
    for (char ch : p.toCharArray()) {
      pCount[(int)(ch - 'a')]++;
    }

    List<Integer> output = new ArrayList();
    // sliding window on the string s
    for (int i = 0; i < ns; ++i) {
      // add one more letter 
      // on the right side of the window
      sCount[(int)(s.charAt(i) - 'a')]++;
      // remove one letter 
      // from the left side of the window
      if (i >= np) {
        sCount[(int)(s.charAt(i - np) - 'a')]--;
      }
      // compare array in the sliding window
      // with the reference array
      if (Arrays.equals(pCount, sCount)) {
        output.add(i - np + 1);
      }
    }
    return output;
  }

}
/*背向双指针 最长回文字符串*/