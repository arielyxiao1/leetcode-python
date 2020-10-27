/*763 partition labels
Input: S = "ababcbacadefegdehijhklij"
Output: [9,7,8]

The partition is "ababcbaca", "defegde", "hijhklij".
This is a partition so that each letter appears in at most one part.
给出一个由小写字母组成的字符串S。需要将这个字符串分割成尽可能多的部分，
使得每个字母最多只出现在一个部分中，并且返回每个部分的长度。
*/
/*O(N), O(1)*/

public List<Integer> partitionLabels(String S) {
	List<Integer> result = new List<Integer>();
	HashMap<Character, Integer> map = new HashMap<>();
	/*get the last index*/
	for (int i = 0; i <S.length(); i++){
		char c = s.chatAt(i);
		map.put(c,i);
	}


	int start = 0, end = 0; 
	/*every start and end
	再次遍历字符串S，用left和right表示当前子串的左边界和右边界，
	扩展当前的右边界right=max(right，当前字符的最右下标）。
	如果已经遍历到了right位置，这时我们就可切出一个子串，
	这个子串的下标是从left到right（包括right），
	之后再设置left为下一个字符的下标。重复上述操作，直到遍历完S*/
	for (int i = 0; i < S.length(); i++){
		/*find the current one that is max*/
		end = Math.max(end, map.get(S.charAt(i)));

		if (end == i) {
			//get size
			result.add(end - start + 1);
			start = i+1;
		}
	}
	return result;

}


/*253 meeting roomsII
 O(nlogn)
/*Input: [[0, 30],[5, 10],[15, 20]]
Output: 2*/
public int minMeetingRooms(int[][] intervals) {
    	// Check for the base case. If there are no intervals, return 0
        if (intervals.length == 0) {
          return 0;
        }
        //min heap, sort by its end time
        PriorityQueue<int[]> heap = new PriorityQueue<int[]>((o1,o2) -> o1[1]- o2[1]);
        //sort the array by its start time
        Arrays.sort(
            intervals,
            new Comparator<int[]>() {
                public int compare(int[]a, int[]b){
                   return a[0] - b[0];
                }
            }       
        );
        // start with the first meeting, put it to a meeting room
        heap.offer(intervals[0]);
    
        for (int i = 1; i < intervals.length; i++) {
            // get the meeting room that finishes earliest
            int[] interval = heap.poll();

            /*if the current meeting starts right after 
                 there's no need for a new room, merge the interval, use the same meeting room*/
            if (intervals[i][0] >= interval[1]) {
                interval[1]= intervals[i][1];
            } else {
                // otherwise, this meeting needs a new room
                heap.offer(intervals[i]);
            }

            // don't forget to put the meeting room back
            heap.offer(interval);
        }

        return heap.size();
    }
}

/*210 Course Schedule II
Input: numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
Output: [0,2,1,3]
Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0.
So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3].
*/
public class Solution {
    /*
     * @param numCourses: a total of n courses
     * @param prerequisites: a list of prerequisite pairs
     * @return: the course order
     */
    /*一个合法的选课序列就是一个拓扑序，拓扑序是指一个满足有向图上，
    不存在一条边出节点在入节点后的线性序列，如果有向图中有环，就不存在拓扑序。
    可以通过拓扑排序算法来得到拓扑序，以及判断是否存在环。
    */
    public int[] findOrder(int num, int[][] pre) {
        // write your code here
        int[] result = new int[num];
        
        //1. build graph, key: courses, value: all the preq
        Map<Integer, ArrayList<Integer>> graph = new HashMap<>();
        for (int i = 0; i < num; i++) {
        	//pre and all the rest
            graph.put(i, new ArrayList<Integer>());
        }
        for (int i = 0; i < pre.length; i++) {
            //这里和course 1 是反过来的
            graph.get(pre[i][1]).add(pre[i][0]);
        }
        
        //2. build indegree map,
        // count the number of the preq courses for a specific course
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < num; i++) {
            for (Integer neighbor : graph.get(i)) {
                if (map.containsKey(neighbor)) {
                    map.put(neighbor, map.get(neighbor) + 1);
                    continue;
                }
                map.put(neighbor, 1);
            }
        }
        
        //3. queue.offer 将入度为 0 的编号加入队列
        Queue<Integer> queue = new LinkedList<>();
        int count = 0;
        for (int i = 0; i < num; i++) {
            if (!map.containsKey(i)) {
                queue.offer(i);
            }
        }
        
        //4 do bfs 
        while (!queue.isEmpty()) {
        	//all the ones that do not have preq
            int current = queue.poll();
            result[count++] = current;

            // 将每条边删去，如果入度降为 0，再加入队列
            for (Integer neighbor : graph.get(current)) {
                map.put(neighbor, map.get(neighbor) - 1);
                if (map.get(neighbor) == 0) {
                    queue.offer(neighbor);
                }
            }
        }
        
        if (count == num) {//全部排完
            return result;
        }
        
        return new int[]{};
    }
}


