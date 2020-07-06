import java.util.*;

public class Solution0701 {
    //不同路径2
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        //行数
        int m = obstacleGrid.length;
        //列数
        int n = obstacleGrid[0].length;

        if(m == 1){
            for(int i = 0; i < obstacleGrid[0].length;i++){
                if(obstacleGrid[0][i] == 1) return 0;
            }
            return 1;
        }

        if(n == 1){
            for(int i = 0; i < obstacleGrid.length;i++){
                if(obstacleGrid[i][0] == 1) return 0;
            }
            return 1;
        }

        for(int i = 1; i < n;i++){
            if(obstacleGrid[0][i] == 1) {
                for(int j = i; j < n;j++){
                    obstacleGrid[0][j] = 0;
                }
                break;
            }

            obstacleGrid[0][i] = 1;
        }

        for(int i = 1; i < m; i++){
            if(obstacleGrid[i][0] == 1){
                for(int j = i; j<m;j++){
                    obstacleGrid[j][0] = 0;
                }
                break;
            }

            obstacleGrid[i][0] = 1;
        }

        for(int i = 1; i<m; i++){
            for(int j = 1; j<n;j++){
                if(obstacleGrid[i][j]== 1) obstacleGrid[i][j] = 0;
                else obstacleGrid[i][j] = obstacleGrid[i-1][j] + obstacleGrid[i][j-1];
            }
        }

        return obstacleGrid[m-1][n-1];

    }
    // 不同路径
    public int uniquePaths(int m, int n) {
        if(m == 1 || n == 1) return  1;
        int rst = 0;
        int [][] grid = new int[m][n];

        for(int i = 1; i < grid[0].length;i++){
            grid[0][i] = 1;
        }

        for(int i = 1; i < grid.length;i++){
            grid[i][0] = 1;
        }

        for(int i = 1; i < grid.length;i++){
            for(int j = 1; j < grid[i].length;j++){
                grid[i][j] = grid[i][j-1] + grid[i-1][j];
            }
        }

        return grid[m-1][n-1];
    }

    // 合并K个排序链表
    public ListNode mergeKLists(ListNode[] lists) {
        int n = lists.length;
        if(n == 0) return null;
        if(n == 1) return lists[0];
        ListNode rst = mergeTwoLists(lists[0],lists[1]);
        for(int i = 2; i < n;i++){
            rst = mergeTwoLists(rst,lists[i]);
        }
        return rst;
    }

    public ListNode mergeTwoLists(ListNode one, ListNode two){

        // 1 -> 3 -> 5
        // 1 -> 2
        if(one == null) return two;
        if(two == null) return one;

        ListNode rst= new ListNode(0);
        ListNode nodeOne = one;
        ListNode nodeTwo = two;
        ListNode temp = rst;

        while(nodeOne != null && nodeTwo != null){
            if(nodeOne.val < nodeTwo.val ) {
                temp.next = nodeOne;
                nodeOne = nodeOne.next;
            }
            else {
                temp.next = nodeTwo;
                nodeTwo = nodeTwo.next;
            }
            temp = temp.next;
        }

        if(nodeOne == null) temp.next = nodeTwo;
        if(nodeTwo == null) temp.next = nodeOne;

        return rst.next;
    }


    // 寻找两个正序数组的中位数
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        double rst = 0;

        return rst;
    }


    // 旋转链表
    public ListNode rotateRight(ListNode head, int k) {
        if(head == null) return null;
        int size = 0;

        ListNode node = head;
        ListNode tail = null;
        while(node != null){
            if(node.next == null ){
                tail = node;
            }
            size++;
            node = node.next;

        }

        int p = k % size;
        if(p == 0) return head;
        int q = size - p;
        int n = 0;
        ListNode newHead = null;
        ListNode newTail = null;
        node = head;

        while( true){
            if(n == q){
                newHead = node;
                break;
            }
            if(n == q -1){
                newTail = node;
            }
            n++;
            node = node.next;
        }


        tail.next = head;
        newTail.next = null;

        return newHead;

    }

    // 第k个排列
    public String getPermutation(int n, int k) {
        if(n == 1 ) return "1";
        StringBuffer rst = new StringBuffer();
        List<Integer> p = new ArrayList<>();
        for(int i = 1; i <=n; i++){
            p.add(i);
        }
        int nf = factorial(n-1);
        getk(rst,p,n-1,nf,k);

        return rst.toString();
    }

    public int factorial(int n){
        int nf = 1;
        for(int i=2; i<=n; i++){
            nf = nf*i;
        }
        return nf;
    }

    public void getk(StringBuffer rst, List<Integer> p, int n, int nf, int k){
        if(k == 0){
            for(int i = p.size() -1; i >= 0;i--){
                rst.append(p.get(i));
            }
            return;
        }
        int group = k / nf;
        int newk = k % nf;
        rst.append( newk != 0 ? p.remove(group): p.remove(group-1));
        getk(rst, p , n-1, nf /n,k % nf);
    }


    // 螺旋矩阵2
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int nSqr = n * n;

        Queue<Integer> p = new LinkedList<>();
        for(int i = 1; i <= nSqr;i++){
            p.offer(i);
        }

        int ROW = n;
        int i = 0;
        while(n > 0){
            for(int m = i; m < ROW - i;m++){
                matrix[i][m] = p.poll();
            }

            for(int m = i+1; m < ROW - i; m++){
                matrix[m][ROW - i - 1] = p.poll();
            }

            for(int m = ROW - i - 2; m >=i; m--){
                matrix[ROW - i - 1][m] = p.poll();
            }

            for(int m = ROW - i -2; m >= i+1;m--){
                matrix[m][i] = p.poll();
            }
            n -= 2;
            i++;
        }
        return matrix;

    }
    // 跳跃游戏
    // 维护最大位置
    public boolean canJump2(int[] nums){
        if(nums.length == 0|| nums.length == 1) return true;
        int farReach = 0;
        for(int i = 0; i < nums.length;i++){
            if(i > farReach) return false;
            int rightMost = i + nums[i];
            farReach = rightMost > farReach ? rightMost: farReach;
            if(farReach >= nums.length -1) return true;
        }

        return false;
    }
    //自己想出的从后遍历
    public boolean canJump(int[] nums) {
        if(nums.length == 0|| nums.length == 1) return true;
        int n = nums.length - 2;
        int cur = nums.length -1;

        while(n >=0){
            int i = cur - n;
            if(nums[n] >= i ){
                cur = n;
            }
            n--;
        }

        return cur == 0;
    }

    // 螺旋矩阵2


    // 螺旋矩阵
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> rst = new ArrayList<>();
        int WIDTH = matrix.length;
        int LENGTH = matrix[0].length;
        if(WIDTH ==0 || LENGTH ==0) return rst;
        int width = matrix.length;
        int length = matrix[0].length;
        int i = 0;
        while(width >0 && length > 0){
            for(int m = i; m < LENGTH-i ;m++){
                rst.add(matrix[i][m]);
            }

            for(int m = i+1;m < WIDTH-i;m++){
                rst.add(matrix[m][LENGTH-i-1]);
            }

            for(int m = LENGTH-i-2;m >= i && WIDTH-i-1 !=i ;m--){
                rst.add(matrix[WIDTH-i-1][m]);
            }

            for(int m = WIDTH-i-2; m >= i+1 && LENGTH-i-1 !=i ; m--){
                rst.add(matrix[m][i]);
            }
            width -= 2;
            length -=2;
            i++;
        }

        return rst;
    }

    public static void main(String[] args) {

        Solution0701 s = new Solution0701();
        System.out.println(s.uniquePaths(1,9));


    }
}
