/*200. Number of Islands
Input: grid = [
  ["1","1","1","1","0"],
  ["1","1","0","1","0"],
  ["1","1","0","0","0"],
  ["0","0","0","0","0"]
]*/

Output: 1
class Solution {
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        
        int ans = 0;
        
        boolean[][] newIslands = new boolean[grid.length][grid[0].length];
        //不需要()
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if(!newIslands[i][j] && grid[i][j] == '1') {
                    ans++;
                    bfs(i, j, grid, newIslands);
                }
            }
        }
        return ans;
    }
    
    
    
    private void bfs(int i, int j, char[][]grid, boolean[][]newislands) {
        Queue<Integer> qx = new LinkedList<Integer>();
        Queue<Integer> qy = new LinkedList<Integer>();
        
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};
        
        qx.offer(i);
        qy.offer(j);
        newislands[i][j] = true;
        while (!qx.isEmpty()) {
            int cx = qx.poll();
            int cy = qy.poll();
        
            for (int in = 0; in < 4; in++) {
        
                int nx = cx + dx[in];
                int ny = cy + dy[in];

                if (nx >= 0 && ny >= 0 && nx < grid.length && ny < grid[0].length 
                   && !newislands[nx][ny] && grid[nx][ny] == '1') {
                    qx.offer(nx);
                    qy.offer(ny);
                    newislands[nx][ny] = true;
                }
            }
        }
        
    }
}