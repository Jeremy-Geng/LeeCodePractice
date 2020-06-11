import java.util.*;

public class Solution0601 {

    //最大子序和
    public int[] searchRange(int[] nums, int target) {
        if(nums.length == 0) return new int[]{-1,-1};
        if(nums.length == 1) {
            if(nums[0] == target) return new int[]{0,0};
            else return new int[]{-1,-1};
        }

        int i = binarySearch(nums,target);
        if(i == -1) return new int[]{-1,-1};
        else{
            int m = i;
            int n = i;
            while( m-1>= 0 &&nums[m-1] == target ){
                m--;
            }
            while( n+1 < nums.length && nums[n+1] == target){
                n++;
            }
            return new int[]{m, n};
        }
    }

    public static int binarySearch(int[] nums, int target){
        int lo = 0;
        int hi = nums.length -1;
        int mid = lo + (hi - lo)/2;

        while(lo <= hi){
            if(nums[mid] > target) hi = mid -1;
            else if(nums[mid] < target) lo = mid +1;
            else{
                return mid;
            }
            mid = lo + (hi - lo)/2;
        }
        return -1;
    }
    //动态规划
    public int maxSubArray2(int[] nums){
        if(nums.length == 0) return 0;
        if(nums.length == 1) return nums[0];
        int max = nums[0];
        int curMax = nums[0];
        for(int i = 1; i < nums.length; i++){
            curMax = curMax + nums[i] > nums[i] ? curMax + nums[i] : nums[i];
            max = curMax > max? curMax : max;
        }
        return max;
    }

    //自己做法
    public int maxSubArray(int[] nums) {
        if(nums.length == 0) return 0;
        if(nums.length == 1) return nums[0];
        int sumMax = nums[0];
        int cnt = 0;
        for(int i = 0;i < nums.length;i++){
            cnt += nums[i];
            sumMax = cnt > sumMax ? cnt : sumMax;
            cnt = cnt > 0 ? cnt : 0;
        }

        return sumMax;
    }


    //合并区间
    // 注意comparable<T> 和 comparator 的区别，自然排序和非自然排序, 通过比较器作为Arrays.sort 或 Collentions.sort()的参数实现
    public int[][] merge2(int[][] intervals){
        int l = intervals.length;
        if(l == 0) return new int[0][0];
        if(l == 1) return intervals;
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] < o2[0] ? -1 :(o1[0]==o2[0] ? o1[1]-o2[1] : 1);
            }
        });

        ArrayList<int[]> p = new ArrayList<>();

        p.add(intervals[0]);
        for(int i = 1; i < l;i++){
             if(intervals[i][1] <= p.get(p.size()-1)[1]){
                continue;
            }else if(intervals[i][0] <= p.get(p.size()-1)[1]){
                 p.get(p.size()-1)[1] = intervals[i][1];
             }else{
                 p.add(intervals[i]);
             }
        }

        return p.toArray(new int[0][]);
    }

    public int[][] merge(int[][] intervals) {

        class Interval implements Comparable<Interval>{
            int left;
            int right;
            Interval(int left, int right){
              this.left = left;
              this.right = right;
            }

            @Override
            public int compareTo(Interval o) {
                Interval d = o;
                if(left < d.left) return -1;
                else if(left == d.left) return right - d.right;
                else return 1;
            }

            public int[] getArrasys(){
                int t[] = {left, right};
                return t;
            }
        }
        int l = intervals.length;
        if(l == 0) return new int[0][0];
        if(l == 1) return intervals;
        Interval is[] = new Interval[l];

        for(int i = 0 ; i < l;i++){
            is[i] = new Interval(intervals[i][0],intervals[i][1]);
        }

        Arrays.sort(is);
        List<Interval> p = new ArrayList<>();
        p.add(is[0]);

        for(int i = 1 ;i < l;i++){
            if(is[i].right <= p.get(p.size()-1).right){
            continue;
            }else if(is[i].left <= p.get(p.size()-1).right){
                p.get(p.size()-1).right = is[i].right;
         }else{
                p.add(is[i]);
            }
        }

        int rst[][] = new int[p.size()][2];
        for(int i = 0; i < p.size();i++){
           rst[i] = p.get(i).getArrasys();
        }


        return rst;
    }
    public void nextPermutation(int[] nums) {
        // cornor cases
        if(nums.length == 0) return;
        if(nums.length == 1) return;
        // n >= 2
        int l = nums.length -1;
        boolean t = false;
        for(int i = l; i>=1; i-- ){
            if(nums[i-1] < nums[i]){
                t = true;
                for(int j = l ; j >=i;j--){
                    if(nums[j] > nums[i-1]){
                        int p = nums[i-1];
                        nums[i-1] = nums[j];
                        nums[j] = p;
                        break;
                    }
                }

                for(int m = i,k = 0; m <= l - (l-i+1)/2; m++, k++){
                    int p = nums[m];
                    nums[m] = nums[l-k];
                    nums[l-k] = p;
                }
            }

            if(t) break;
        }
        if(!t) Arrays.sort(nums);
    }

    //外观数列
    private int one = 1;
    private int two = 11;
    private int three = 21;
    private int four = 1211;
    private int five = 111221;

    public String countAndSay(int n) {
        String rst= "";
        if(n == 5) return ""+five;
        else if(n == 4) return ""+four;
        else if(n == 3) return ""+ three;
        else if(n == 2) return "" + two;
        else if(n == 1) return "" + one;
        else{
            int temp = 6;
            String p = "" + five;
            while(temp  <= n ){
                p = p + "-";
                String next = "";
                int m = 1;
                for(int i = 0;i < p.length()-1;i++){
                    if(p.charAt(i) != p.charAt(i+1)){
                         next = next + m + p.charAt(i);
                         m = 1;
                    }else{
                        m++;
                    }
                }
                temp++;
                p = next;
            }
            return p;
        }
    }

    //搜索旋转排序数组
    public int search(int[] nums, int target) {
        if(nums.length == 0) return -1;
        if(nums.length == 1) {
            if(nums[0] == target) return 0;
            else return -1;
        }

        if(nums.length == 2){
            if(nums[0] == target) return 0;
            else if(nums[1] == target) return 1;
            else return -1;
        }


        int p = 0;
        int rst = 0;
        int left = 0;
        int right = nums.length - 1;
        int middle = (right + left) / 2;
        if(nums[1] < nums[0]) p = 1;
        else {
            while (!(nums[middle - 1] < nums[middle] && nums[middle + 1] < nums[middle]) && (left < middle)) {
                if (nums[middle] > nums[left]) left = middle;
                else if (nums[middle] < nums[left]) right = middle;
                middle = (right + left) / 2;
            }
            p = middle + 1;
        }
        if(left == middle ){
            rst = BinarySearch(nums,0,nums.length-1,target);
            return rst;
        }

        rst = BinarySearch(nums,0,p-1,target);
        if(rst == -1) rst = BinarySearch(nums,p,nums.length-1,target);

        return rst;
    }

    public static int BinarySearch(int[] a,int left, int right,int target){
        int rst = -1;
        while(left <= right){
            int middle = (left + right)/2;
            if(a[middle] < target) left = middle + 1;
            else if(a[middle] > target) right = middle - 1;
            else{
                rst = middle;
                break;
            }
        }
        return rst;
    }
    // 两数相除
    // 使用位运算
    public int divide(int dividend, int divisor) {
        if(Math.abs((long)dividend) < Math.abs((long)divisor)) return 0;
        if(dividend == Integer.MIN_VALUE && divisor == -1)  return  Integer.MAX_VALUE;
        boolean t = true;

        if((dividend > 0 && divisor < 0) || (dividend < 0 && divisor > 0)) t = false;
        int rst = 0;

        long Dividend = Math.abs((long)dividend);
        long Divisor = Math.abs((long)divisor);

        while(Divisor <= Dividend){
            long temp = Divisor;
            int i = 0;
            while(temp <= Dividend){
                temp = temp << 1;
                i++;
            }
            rst = rst + (1 << i-1);
            Dividend = Dividend - (temp >> 1);
        }

        return t? (rst) : -(rst);
    }

    //搜索插入位置
    //二分查找 O(log n)
    public int searchInsert2(int[] nums,int target){
        int rst = 0;
        int left = 0;
        int right = nums.length - 1;
        while( left <= right){
            int middle = (left + right) /2;
            if(nums[middle] > target) right = middle - 1;
            else if(nums[middle] < target) left = middle +1 ;
            else {
                return  middle;
            }

        }
        return left;
    }

    // 暴力循环 O(n)
    public int searchInsert(int[] nums, int target) {
        int rst = 0;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] >= target)  {
                rst = i;
                break;
            }
        }
        return target > nums[nums.length-1] ? nums.length:rst;
    }
    //实现strStr
    //方法2
    public int strStr2(String haystack, String needle){
        if(needle.equals("")) return 0;
        if(needle.length() > haystack.length()) return -1;
        int rst = -1;
        for(int i = 0; i < haystack.length();i++){
            if(haystack.charAt(i) == needle.charAt(0)){
                int temp = i;
                boolean t = true;
                if(haystack.substring(i).length() < needle.length()) break;
                for(int j = 1, k = i+1; j < needle.length();j++,k++){
                    if(haystack.charAt(k) != needle.charAt(j)){
                        t = false;
                        break;
                    }
                }
                if(t) {
                    rst = i;
                    break;
                }
            }
        }

        return rst;
    }
    //方法1直接调用indexOf函数
    public int strStr(String haystack, String needle) {
        if(needle.equals("")) return 0;
        return haystack.indexOf(needle);
    }
    //两两交换链表中的节点
    public ListNode swapPairs(ListNode head) {
        if(head == null) return null;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode temp = dummy;
        while(temp.next != null && temp.next.next != null){
            ListNode p = temp.next.next;
            temp.next.next = p.next;
            p.next = temp.next;
            temp.next = p;
            temp = temp.next.next;
        }
        return dummy.next;
    }


    //全排列
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> rst = new ArrayList<>();
        List<Integer> ns = new ArrayList<>();
        for(int i : nums){
            ns.add(i);
        }
        getPermute(rst,new ArrayList<Integer>(),ns);

        return rst;
    }

    public void getPermute(List<List<Integer>> rst, List<Integer> curPerm, List<Integer> nums){
        if(nums.size() == 0){
            rst.add(curPerm);
            return;
        }
        for(int i = 0; i < nums.size() ;i++){
            List<Integer> newCurPerm = new ArrayList<>();
            newCurPerm.addAll(curPerm);
            List<Integer> newNums = new ArrayList<>();
            newNums.addAll(nums);
            newCurPerm.add(newNums.get(i));
            newNums.remove(newNums.get(i));
            getPermute(rst,newCurPerm,newNums);
        }
    }

    public static void main(String[] args) {
        Solution0601 s = new Solution0601();
        int test[] = {1,1,2};
        int a[] = s.searchRange(test,1);
        for(int i : a){
            System.out.println(i);
        }
    }
}

