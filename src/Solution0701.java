import java.util.*;

public class Solution0701 {
    // 搜索旋转排序数组 复习
    public int search(int[] nums, int target){
        int n = nums.length;
        if(n == 0) return -1;

        int index = -1 , left = 0, right = n - 1, firstValue = nums[0];
        if(target == firstValue) return 0;

        while(left <= right){
            int mid = left + (right - left) / 2, value = nums[mid];
            if(value == target) return mid;

            if(target > firstValue){
                if(value > target){
                    right = mid - 1;
                }else{
                    if(value >= firstValue){
                        left = mid + 1;
                    }else{
                        right = mid -1;
                    }
                }
            }else{
                if(value > target){
                   if(value >=firstValue){
                       left = mid + 1;
                   }else{
                       right = mid - 1;
                   }
                }else{
                   left = mid + 1;
                }
            }
        }

        return index;
    }
    //搜索旋转排序数组2
    public boolean search2(int[] nums, int target) {
        boolean t = false;


        return t;
    }

    //删除排序数组中的重复项2
    public int removeDuplicates(int[] nums) {
        int n = nums.length;
        if(n == 0) return 0;

        int count = 1;
        int slotIndex = 1;
        for(int i = 1; i < n;i++){
           count = nums[i] == nums[i-1] ? count + 1 : 1;
           if(count <= 2) nums[slotIndex++] = nums[i];
        }
        return slotIndex;
    }

    // 为单词搜索提供的变量
    private boolean[][] marked;
    private char[][] board;
    private int m;
    private int n;
    private String word;
    private int[][] directions = {{-1,0},{0,1},{1,0},{0,-1}};


    // *单词搜索(优化)，第一次写得太乱了
    public boolean exist2(char[][] board, String word){
        this.board = board;
        this.word = word;
        m = board.length;
        if( m == 0) return false;
        n = board[0].length;
        marked = new boolean[m][n];

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(dfs(i,j,0))
                    return true;
            }
        }
        return false;
    }

    private boolean dfs(int i, int j, int start){
        if(start == word.length() -1)
            return board[i][j] == word.charAt(start);

        if(board[i][j] == word.charAt(start)){
            marked[i][j] = true;
            for(int k = 0; k < 4; k++){
                int newX = i + directions[k][0];
                int newY = j + directions[k][1];
                if(inArea(newX,newY) && !marked[newX][newY]){
                    if(dfs(newX,newY,start+1))
                        return true;
                }
            }
            marked[i][j] = false;
        }

        return false;
    }

    private boolean inArea(int x, int y){
        return x >= 0 && y >=0 && x < m && y < n;
    }


    // *单词搜索  完成，但是时间超时，原因是剪枝不够彻底
    public boolean exist(char[][] board, String word) {

        boolean[] rst = new boolean[]{false};
        int length = word.length();
        char start = word.charAt(0);
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length;j++){
                if(board[i][j] == start && !rst[0]){
                    boolean[][] ts = new boolean[board.length][board[0].length];
                    findString(i,j,rst,board,ts,null,word,length);
                }
            }
        }

        return rst[0];
    }




    public void findString(int m, int n, boolean[] rst, char[][] board, boolean[][] ts,String pathWord, String word, int length){
        if(rst[0]) return;
        if(length == 0){
            return;
        }

        String newPathWord = "";
        if(pathWord == null)  newPathWord = "" + board[m][n];
        else newPathWord  = pathWord + board[m][n];


        // 这个剪枝 必须添加，否则 会耗费大量时间
        if(!newPathWord.equals(word.substring(0,word.length()-length+1))) return;

        if(newPathWord.equals(word)) rst[0] = true;

        //  防止回退

        ts[m][n] = true;

        // left
        if(n -1 >= 0 && !ts[m][n-1]){
            findString(m,n-1,rst,board, ts, newPathWord,word,length - 1);
            ts[m][n-1] = false;
        }


        // right
        if(n + 1 <= board[0].length - 1 && !ts[m][n+1])
        {
            findString(m,n+1,rst,board, ts, newPathWord,word,length-1);
            ts[m][n+1] = false;
        }


        // up
        if(m - 1  >= 0 && !ts[m-1][n])
        {
            findString(m-1, n ,rst,board, ts, newPathWord,word,length-1);
            ts[m-1][n] =false;
        }

        // down
        if(m + 1 <= board.length - 1 && !ts[m+1][n])
        {
            findString(m+1, n ,rst,board, ts, newPathWord,word,length -1);
            ts[m+1][n] = false;
        }

    }


    // 颜色分类（复习）
    public void sortColors(int[] nums) {
        int n = nums.length - 1;
        int lt = -1, gt = n, i = 0;
        while(i <=gt){
            if(nums[i] > 1){
                    int p = nums[gt];
                    nums[gt--] = nums[i];
                    nums[i] = p;
            }else if(nums[i] < 1){
                if(lt >=0){
                    int p = nums[i];
                    nums[i++] = nums[lt];
                    nums[lt++] = p;
                }else{
                    i++;
                }

            }else{
                if(lt < 0) {
                    lt = i;
                }
                i++;
            }
        }
    }


    // 子集
    public List<List<Integer>> subsets(int[] nums) {
        if(nums == null) return null;
        int n = nums.length;
        if(n == 0) return new ArrayList<>();
        if(n == 1)  {
            return new ArrayList<>(){
                {
                    add(new ArrayList<>());
                    add(new ArrayList<>(){
                        {
                            add(nums[0]);
                        }
                    });
                }
            };
        }

        Arrays.sort(nums);

        List<List<Integer>> rst = new ArrayList<>();
        rst.add(new ArrayList<>());
        backTrace(rst,null,n,nums,n,0);


        return rst;
    }

    public void backTrace(List<List<Integer>> rst, List<Integer> oneSubSet, int tk, int[] nums, int n ,int k  ){
        if(tk == 0){
            return;
        }

        for(int i = k; i < n  ;i++ ){
            List<Integer> newSubSet = new ArrayList<>();
            if(oneSubSet != null) newSubSet.addAll(oneSubSet);
            newSubSet.add(nums[i]);
            rst.add(newSubSet);

            backTrace(rst, newSubSet, tk - 1,nums, n, i + 1);

        }

    }

    // 组合
    public List<List<Integer>> combine(int n, int k) {
        if(k > n) return null;
        if(k == n){
            return new ArrayList<>(){
                {
                    add(new ArrayList<>(){
                        {
                            for (int i = 1; i <= n; i++) {
                                add(i);
                            }
                        }
                    });
                }
            };
        }

        List<List<Integer>> rst = new ArrayList<>();
        backtrack(rst,null,0,1,n,k);

        return rst;
     }


     public void backtrack(List<List<Integer>> rst, List<Integer> oneCombine,int count, int currentN, int n, int k){
        if(count == k){
            rst.add(oneCombine);
            return;
        }

        for(int i = currentN; i <= n ;i++){
            List<Integer> newCombine;
            if(oneCombine == null) {
               newCombine = new ArrayList<>();
            }else{
                newCombine = new ArrayList<>(oneCombine);
            }

            newCombine.add(i);
            int newCount = count+1, newCurrentN = i + 1;
            backtrack(rst,newCombine,newCount,newCurrentN,n,k);
        }
     }

    // 搜索二维矩阵
    public boolean searchMatrix2(int[][] matrix, int target){
        boolean t = false;

        int rows = matrix.length;
        if(rows == 0) return false;
        int columns = matrix[0].length;

        int left = 0, right = rows * columns - 1;

        while(left <= right){
            int mid = left + (right - left) /2;
            int m = mid / columns, n = mid % columns;
            int midValue = matrix[m][n];
            if(midValue == target) return true;
            else if(midValue > target) right = mid - 1;
            else left = mid + 1;
        }

        return t;
    }


    //两次 二分查找，做的复杂了，可以通过转换数组下标的方式来搞
    public boolean searchMatrix(int[][] matrix, int target) {
        boolean t = false;
        int rows = matrix.length;
        if(rows == 0) return false;

        int column = matrix[0].length;
        if(column == 0 ) return false;
        int k = 0;

        int left = 0;
        int right = rows -1;
        while(left <= right){
            int mid = left + (right - left) / 2;
            int midValue = matrix[mid][0];
            if(midValue == target) return true;
            else if(midValue > target ){
                right = mid - 1;
            }else{
                if(matrix[mid][column-1] == target) return true;
                else if(matrix[mid][column-1] > target){
                    k = mid;
                    break;
                }else{
                    left = mid + 1;
                }
            }
        }

        return binarySearch(matrix[k],target);
    }

    public boolean binarySearch(int a[], int target){
        boolean t = false;

        int left = 0;
        int right = a.length -1;

        while(left <= right){
            int mid = left + (right - left) /2;
            if(a[mid] == target) {
                t = true;
                break;
            }
            else if( a[mid] > target) right  = mid-1;
            else left = mid + 1;
        }


        return t;
    }
    // 矩阵置零
    public void setZeroes(int[][] matrix) {
        HashSet<Integer> zeroColumns = new HashSet<>();

        int rows = matrix.length;
        if(rows == 0) return;
        int columns = matrix[0].length;

        for(int i = 0; i < rows;i++){
            boolean isZero = false;
            for(int j = 0; j < columns;j++){
                if(matrix[i][j] == 0){
                    isZero = true;
                    zeroColumns.add(j);
                }
            }

            if(isZero) matrix[i] = new int[columns];
        }

        for(int i : zeroColumns){
            for(int j = 0; j < rows;j++){
                matrix[j][i] = 0;
            }
        }

    }

    //简化路径
    public String simplifyPath(String path) {
        Stack<String> dirs = new Stack<>();
        int index = 0;
        while(index < path.length()){
            if(path.charAt(index) == '/'){
                while(index < path.length() && path.charAt(index) == '/'){
                    index++;
                }
                if(dirs.empty()){
                    dirs.push("/");
                }
            }else if(path.charAt(index) == '.'){
                String dots = "";
                while(index < path.length() && path.charAt(index) == '.'){
                    dots = dots + path.charAt(index++);
                }
                if(index < path.length() && path.charAt(index) != '/'){
                    StringBuilder dir = new StringBuilder();
                    while(index < path.length() && path.charAt(index) != '/' && path.charAt(index) != '.'){
                        dir.append(path.charAt(index++));
                    }
                    dirs.push(dots+dir.toString());
                } else if(dots.length() == 1) continue;
                else if(dots.length() == 2) dirs.pop();
                else {
                    dirs.push(dots);
                }
            }else {
                StringBuilder dir = new StringBuilder();
                while(index < path.length() && path.charAt(index) != '/' && path.charAt(index) != '.'){
                    dir.append(path.charAt(index++));
                }
                dirs.push(dir.toString());
            }

        }

        if(dirs.size() == 0) return "/";

        StringBuilder standardPath = new StringBuilder();
        for(String dir: dirs){
            standardPath.append("/"+dir);
        }


        if(dirs.size() == 1) standardPath.delete(1,2);
        else standardPath.delete(1,3);
        return standardPath.toString();
    }

    // 最小路径和
    // 动态规划速度更快
    public int minPathSum2(int[][] grid){
        int rst = 0,m = grid.length,n = grid[0].length;
        if( m == 1 ) {
            for(int i : grid[0]) rst += i;
            return rst;
        }
        if(n == 1){
            for(int i = 0; i < m;i++) rst += grid[i][0];
            return rst;
        }
        for(int i = 1; i < m; i++ ) grid[i][0] += grid[i-1][0];
        for(int i = 1;i < n; i++) grid[0][i] += grid[0][i-1];
        for(int i = 1; i < m;i++) for(int j = 1; j < n;j++) grid[i][j] = grid[i-1][j] < grid[i][j-1] ? grid[i][j] + grid[i-1][j] : grid[i][j] + grid[i][j-1];
        return grid[m-1][n-1];
    }

    //回溯算法 超出时间限制
    public int minPathSum(int[][] grid) {
        int rst = 0;
        int m = grid.length;
        int n = grid[0].length;

        if( m == 1 ) {
            for(int i : grid[0])
                rst += i;
            return rst;
        }

        if(n == 1){
            for(int i = 0; i < m;i++)
                rst += grid[i][0];
            return rst;
        }

        int[] min = {-1};
        findMax(min,0,0,grid,0);

        return min[0];
    }


    public void findMax(int[] min, int m, int n, int[][] grid, int cur){
        if(m >= grid.length || n >= grid[0].length) return;
        int newCur = cur + grid[m][n];
        if(m == grid.length-1 && n == grid[0].length-1){
            if(min[0] == -1) min[0] = newCur;
            else min[0] = newCur < min[0] ? newCur : min[0];
        }

        findMax(min,m+1,n,grid,newCur);
        findMax(min,m,n+1,grid,newCur);
    }




    // 不同路径2
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
        int[] t = {4,5,6,7,0,1,2};
        System.out.println(s.search(t,3));





    }
}
