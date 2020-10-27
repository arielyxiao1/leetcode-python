/*. 206 Reverse Linked List 
*/
class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode current = head;
        //reverse each of the one, between two nodes, pre is the dummy at first
        while(current!=null){
            ListNode next = current.next;
            current.next = pre;
            pre = current;
            current = next;
        }
        return pre;
    }
}


/*92. Reverse Linked List II
*/
class Solution {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if(head == null) return head;
        
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode mNode = head;
        ListNode nNode = head;
        ListNode pre = dummy;
        
         //find mode, and nnode 
        for(int i = 0; i<m-1; i++){
            pre = mNode;
            mNode = mNode.next;
        }
        
        for(int j=0 ; j < n-1; j++){
            nNode = nNode.next;
        }
        
        /*
        --> 当mnode和nnode没有重合之前，将mnode移到nnode后面，
        然后令mnode为之前mnode的下一个节点
        --> 当mnode和nnode重合时，对应部分的链表就翻转成功了
        */
        while(mNode != nNode){
            //把mnode放在nnode的后面;
            pre.next = mNode.next; //1-->3 --4--5
            mNode.next = nNode.next;// 1-->3-->4-->5;  2-->5;
            nNode.next = mNode;//1-->3-->4-->2-->5;
            //把当前mnode的节点移到nnode的下一位，2移到4后面 i.e： 1-2-3-4-5 --> 1-3-4-2--5    
            mNode = pre.next;//mNode = 3; nNode =4
            
        }
        
        
        return dummy.next;
    }
}
/*#61
Rotate List O(n), O(1)
Input: 1->2->3->4->5->NULL
Output: 5->4->3->2->1->NULL
https://yoxode.github.io/post/lian-biao-zong-jie-python/
*/
public ListNode rotateRight(ListNode head, int n) {
	if (head == null || head.next == null) {
		return head;
	}

	ListNode dummy = new ListNode(0);
	dummy.next = head;
	ListNode fast = dummy, slow = dummy;

	int  count = 0;
	//get the length
	for (; fast.next!= null; count++) {
		fast = fast.next;
	}
	//Input: 1->2->3->4->5->NULL, k = 2
	//fast  = 5, count = 4

	//get the i- n% node
	for (int j = count - n%count; j > 0 ;j--) {
		slow = slow.next;
	}
	//slow = 3
	//slow and fast -> right part needed to be rotate

	fast.next = dummy.next;
	dummy.next = slow.next;
	slow.next = null;
	return dummy.next;


}

/*21 O(N), O(1)
Merge Two sorted List*/
public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
		ListNode dummy = new ListNode(0);
		ListNode current = dummy;

		//get both of the length, and do while loop togther, and compare
		while(list1!=null && list2 !=null){
			if (list1.val < list2.val) {
				current.next = list1;
				current = current.next;
				list1 = list1.next;
			} else {
				//move current.next, and move current
				current.next = list2;
                current = current.next;
                list2 = list2.next;
			}

			if (list1 != null) {
	            current.next = list1;
	        } else {
	            current.next = list2;
	        }

		}

		return dummy.next;


}

/*23 merge K sorted List
O(nlogK)
The size of the priority queue is k. 
Each insertion takes logk time and we are iterating over n distinct words
 in the worst case and inserting them into the priority queue which makes the total runtime nlogk.
*/
public ListNode mergeKLists(ListNode[] lists) {
	if (lists == null || lists.length == 0) {
		 return null;
	}

	/*min heap, only put the firstNode*/
	PriorityQueue<ListNode> pq = new PriorityQueue<ListNode>((o1,o2) -> o1.val - o2.val);
	for (ListNode node : lists) {
		if (node != null) {
         	pq.offer(node);
        }
	}

	ListNode dummy = new ListNode(0);
	ListNode current = dummy;

	while(!pq.isEmpty()) {
		//get the smallest out
		ListNode temp = pq.poll();
		current.next = temp;
		if (temp.next != null) {
			//add the info to pq
            pq.offer(temp.next);
		}
		current = current.next;
	}
	return dummy.next;

}

/*817 · Linked List Components
判断链表里面有几个节点是“connected components”，
单个的节点在数组中出现算一个“connected components”，
相连的几个节点在数组中出现，只算一个“connected components”。
我们可以用二分法查找当前节点是否在数组中出现，如果当前节点的后继节点同样存在，
则将指针后移，如果不存在则res++。这里要加一个flag，判断之前的节点是否存在数组中。
 STL提供了一个unordered_set容器可以使用count()直接查找，省去了写二分法。
Time Complexity: O(N+G.length), where N is the length of the linked list with root node head.
Space Complexity: O(G.length), to store Gset

输入: head = 0->1->2->3, G = [0, 1, 3]
输出: 2
解释: 
链表中,0 和 1 是相连接的，且 G 中不包含 2，所以 [0, 1] 是 G 的一个组件，同理 [3] 也是一个组件，故返回 2。
*/

class Solution {
    public int numComponents(ListNode head, int[] G) {
        // Write your code here.
        Set<Integer> setG = new HashSet<>();
        for (int i: G) {
        	setG.add(i);
        }
        int res = 0;
        while (head != null) {
            if (setG.contains(head.val) && (head.next == null || !setG.contains(head.next.val))){ 
            	res++
            };
            head = head.next;
        }
        return res;
    }
}


/*LRU*/
/*
LRU Cache Misses
Given the maximum size of the cache and a list of page requests,
write an algorithm to calculate the number of cache misses. 
A cache miss occurs when a page is requested and isn't found in the cache

num = 6
pages = [1,2,1,3,1,2]
maxCacheSize = 2
Output:
4
Explanation:
Cache state as requests come in ordered longest-time-in-cache
to shortest-time-in-cache:
1*
1,2*
2,1
1,3*
3,1
1,2*
Asterisk (*) represents a cache miss. Hence, the total number of a cache miss is 4.
*/

// Time O(N) Space O(N)
public class Main {
    public static int lruCacheMisses(int num, List<Integer> pages, int maxCacheSize) {
        LRUCache cache = new LRUCache(maxCacheSize);
        int count = num;
        for (int page : pages) {
            //check if cached, if cached, then --
            if (cache.put(page, 0)) {
                count--;
            }
        }
        return count;
    }

public static class LRUCache {
    int newcapacity;
    // The LinkedHashMap is just like HashMap with 
    // an additional feature of maintaining an order of elements inserted into it.
    LinkedHashMap<Integer, Integer> mymap;
    public LRUCache(int capacity) {
        newcapacity = capacity;
        mymap = new LinkedHashMap<>();
    }

    // Time O(1) Space O(1)
    public int get(int key) {
        int value = -1;
        if (mymap.containsKey(key)) {
            value = mymap.get(key);
            mymap.remove(key);
            mymap.put(key, value);
        }
        return value;
    }

    // Time O(1) Space O(1)
    public boolean put(int key, int value) {
        boolean cached = false;
        if (mymap.containsKey(key)) {
            mymap.remove(key);
            cached = true;
        } else if (mymap.size() == newcapacity) {
            //remove the last one the map, iterate through the whole map
            Iterator iter = mymap.entrySet().iterator();
            iter.next();
            iter.remove();
        }
        mymap.put(key, value);
        return cached;
    }
}

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        System.out.println(lruCacheMisses(6, Arrays.asList(1,2,1,3,1,2), 2));
    }
}
