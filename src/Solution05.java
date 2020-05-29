import java.io.PrintStream;
import java.util.*;


public class Solution05 {


    //括号生成 深度优先搜索（dps）+ 剪枝
    public List<String> generateParenthesis(int n) {
        if(n == 0) return null;
        List<String> rst = new ArrayList<>();
        getBraces(n,n,"",rst);

        return rst;
    }

    public void getBraces(int left, int right, String cur, List<String> rst){
        //结束条件
        if(left == 0 && right == 0){
            rst.add(cur);
        }

        //剪枝
        if(left >  right) return;

        if(left > 0){
            getBraces(left-1,right,cur+"(",rst);
        }

        if(right >0){
            getBraces(left,right-1,cur+")",rst);
        }

    }

    //移除元素
    public int removeElement(int[] nums, int val) {
        int p = 0;
        if (nums.length ==0) return 0;
        for(int i =0;i<nums.length;i++){
            if(nums[i] != val){
                nums[p++] = nums[i];
            }
        }
        return p;
    }

    //删除链表的倒数第N个节点 可以使用dummy node（哑节点）来简化算法
    //也可以使用双指针一次遍历成功
    public ListNode removeNthFromEnd2(ListNode head, int n){
        ListNode Dummy = new ListNode(0);
        Dummy.next = head;
        ListNode first = Dummy;
        ListNode second = Dummy;
        int i = 0;
        while(i < n+1){
            second = second.next;
            i++;
        }

        while(second != null){
            first = first.next;
            second = second.next;
        }
        first.next = first.next.next;
        return Dummy.next;
    }
    public ListNode removeNthFromEnd(ListNode head, int n) {
            int length = 0;
            ListNode t = head;
            while(t != null){
                t = t.next;
                length++;
            }


            ListNode Dummy = new ListNode(0);
            Dummy.next = head;
            ListNode pre = Dummy;
            for(int i = 0; i < length - n;i++){
                pre = pre.next;
            }
            pre.next = pre.next.next;
            return Dummy.next;
    }

    //删除排序数组中的重复项
    public int removeDuplicates(int[] nums) {
        int rst = 0 ;
        if(nums.length == 0) return 0;
        int sameNum = nums[0];
        int p = 0;
        for(int i = 1; i < nums.length;i++){
            if(nums[i] != sameNum){
                nums[++p] = nums[i];
                sameNum = nums[i];
            }
        }
        return p+1;
    }
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
            if(l1 == null) return l2;
            if(l2 == null) return  l1;
            if(l1 == null && l2 ==null) return null;
            int l1Root = l1.val;
            int l2Root = l2.val;
            ListNode root = null;
            ListNode a = null;
            ListNode b = null;
            if(l1Root > l2Root){
                root = new ListNode(l2Root);
                a = l1;
                b = l2.next;
            }else{
                root = new ListNode(l1Root);
                a = l1.next;
                b = l2;
            }
            ListNode t = root;
            while(a != null && b != null){ // 1-3-5-7-8  2-3   1 2 3
                int aval = a.val;
                int bval = b.val;
                if(aval < bval){
                    t.next = new ListNode(aval);
                    a = a.next;
                }else{
                    t.next = new ListNode(bval);
                    b = b.next;
                }
                t = t.next;
            }
            if(a == null) t.next = b;
            if(b == null) t.next = a;

            return root;
    }


    //四数之和
    //双指针，用contains去重,时间复杂度可以优化
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);  // -2, -1, 0, 0, 1, 2;
        int len = nums.length;
        List<List<Integer>> p = new ArrayList<>();
        if(len < 4) return p;
        for(int i = 0; i <= len - 4 ; i++){
            for(int j = i+1; j <= len - 3; j++){
                int l = j + 1;
                int r = len - 1;
                while(l < r){
//                    while(nums[l] == nums[l+1] && l + 1 < r ) l++;
//                    while(nums[r] == nums[r-1] && r - 1 > l) r--;
                    if(nums[i] + nums[j] + nums[l] + nums[r] == target){
                        List<Integer> o = Arrays.asList(nums[i], nums[j], nums[l], nums[r]);
                        if(!p.contains(o)) p.add(o);
                        l++;
                        r--;
                    }else if(nums[i] + nums[j] + nums[l] + nums[r] > target){
                        r--;
                    }else{
                        l++;
                    }
                }
            }
        }
        return p;
    }
    //有效的括号
    public boolean isValid(String s) {
        if(s.equals("")) return true;
        boolean f = false;
        Stack<Character> p = new Stack<>();
        Stack<Character> l = new Stack<>();
        Stack<Character> r = new Stack<>();
        for(int i = 0; i < s.length();i++){
            f = false;
            char t = s.charAt(i);
            if(t == '(' || t == '[' || t == '{'){
                p.push(t);
                l.push(t);
            }else{
                r.push(t);
                if(t == ')'){
                    while(!p.empty() && p.peek() != '(')
                        p.pop();
                }else if(t == ']'){
                    while(!p.empty() && p.peek() != '[')
                        p.pop();
                }else{
                    while(!p.empty() && p.peek() != '{')
                        p.pop();
                }
                if(!p.empty()){
                    f = true;
                    p.pop();
                }
            }

        }
        return p.empty() && f && l.size() == r.size();
    }

    //电话号码的字母组合（复习）
    public List<String> letterCombinations2(String digits) {
        if(digits == null) return null;
        List<String> rst = new ArrayList<>();
        lc(0,digits,"",rst);
        return rst;
    }

    public void lc(int level, String digits, String curStr, List<String> rst){
        int num = digits.charAt(level) - '0';
        String letters = p.get(num);
        if(level == digits.length()-1) {
            for(int i = 0; i < letters.length();i++){
                rst.add(curStr+letters.charAt(i));
            }
            return;
        }
        for(int i = 0; i < letters.length();i++ ){
            lc(level+1,digits,curStr + letters.charAt(i),rst);
        }

    }


    // DFS 深度优先搜寻
    private HashMap<Integer,String> p = new HashMap<>(){ {put(2,"abc");
        put(3,"def");
        put(4,"ghi");
        put(5,"jkl");
        put(6,"mno");
        put(7,"pqrs");
        put(8,"tuv");
        put(9,"wxyz");}};

    //电话号码的字母组合
    private List<String> rst = new ArrayList<>();

    public List<String> letterCombinations(String digits) {
        backTract("", digits);
        return rst;
    }

    public void backTract(String result, String digits){
        if(digits.length() == 0){
            rst.add(result);
        }else{
            String num = digits.substring(0,1);
            String letter = p.get(Integer.parseInt(num));
            for(int i = 0; i < letter.length();i++){
                char t = letter.charAt(i);
                String subDigits = digits.substring(1);
                backTract(result + t,subDigits );
            }
        }
    }

    //最长公共前缀
    public String longestCommonPrefix(String[] strs) {

        if(strs.length == 0) return "";
        StringBuilder rst = new StringBuilder();
        int lengh = strs[0].length();
        for(String str : strs){
            lengh = lengh > str.length() ? str.length() : lengh;
        }

        for(int i = 0; i < lengh; i++){
            char t = strs[0].charAt(i);
            boolean f = true;
            for(String str : strs){
                if(str.charAt(i) != t) f = false;
            }
            if(f) rst.append(t);
            if(i == 0 && rst.toString().equals("")) break;
        }
        return rst.toString();
    }


    //最近的三数之和
    //排序，双指针
    public int threeSumClosest2(int[] nums, int target) {
        Arrays.sort(nums);
        int result = nums[0] + nums[1] + nums[nums.length-1];
        for(int i = 0; i < nums.length; i++){
            int L = i+1;
            int R = nums.length - 1;
            int sum = result;
            while(L < R){
                sum = nums[i] + nums[L] + nums[R];
                if(sum == target){
                    return sum;
                }else if(sum < target){
                    if((Math.abs(sum - target)) < (Math.abs(result - target))) result = sum;
                    L++;
                }else{
                    if((Math.abs(sum - target)) < (Math.abs(result - target))) result = sum;
                    R--;
                }
            }
        }

        return result;
    }

    // 双指针
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int result = nums[0] + nums[1] + nums[nums.length-1];
        for(int i = 0; i < nums.length; i++){
            int L = i+1;
            int R = nums.length - 1;
            int sum = result;
            while(L < R){
                sum = nums[i] + nums[L] + nums[R];
                if(sum == target){
                    return sum;
                }else if(sum < target){
                    if((Math.abs(sum - target)) < (Math.abs(result - target))) result = sum;
                    L++;
                }else{
                    if((Math.abs(sum - target)) < (Math.abs(result - target))) result = sum;
                    R--;
                }
            }
        }

        return result;
    }

    //三数之和
    //题解做法，按顺序固定i,在其右侧使用双指针
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        if(nums.length < 3) return result;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] > 0) break;
            if(i > 0 && nums[i] == nums[i-1]) continue;
            int L = i + 1;
            int R = nums.length - 1;
            while(L < R){
                int sum = nums[i] + nums[L] + nums[R];
                if(sum == 0){
                    result.add(Arrays.asList(nums[i], nums[L], nums[R]));
                    while(L < R && nums[L] == nums[L+1]) L++;
                    while(L < R && nums[R] == nums[R-1]) R--;
                    L++;
                    R--;
                }else if(sum < 0){
                    L++;
                }else if(sum > 0){
                    R--;
                }
            }

        }
        return result;
    }


    //整数转罗马数字
    // 贪心算法，因为罗马数字的规则是从左到右尽量选择大的符号
    public String intToRoman2(int num){
        StringBuilder result = new StringBuilder();
        int[] nums = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = {"M", "CM", "D", "CD","C","XC","L","XL","X","IX","V","IV","I"};
        for(int i = 0; i < nums.length && num >0;i++){
            while(nums[i] <= num){
                num -= nums[i];
                result.append(symbols[i]);
            }
        }
        return result.toString();
    }

    //用取余解析数字
    public String intToRoman(int num) {
        StringBuilder rst = new StringBuilder();
        while(num > 0) {
            int M = num / 1000;
            if (M > 0) {
                for (int i = 0; i < M; i++) {
                    rst.append('M');
                }
                num -= M * 1000;
            } else {
                int C = num / 100;
                if(C > 0) {
                    int D = num / 500;
                    if(D > 0){
                        if( C == 9){
                            rst.append("CM");
                            num -= 900;
                        }else{
                            rst.append('D');
                            num -= 500;
                        }
                    }else{
                        if(C == 4){
                            rst.append("CD");
                            num -= 400;
                        }else {
                            for (int i = 0; i < C; i++) {
                                rst.append('C');
                            }
                            num -=C * 100;
                        }
                    }
                }else{
                    int X = num / 10;
                    if(X > 0){
                        int L = num / 50;
                        if(L > 0 ){
                            if(X == 9){
                                rst.append("XC");
                                num -= 90;
                            }else{
                                rst.append('L');
                                num -= 50;
                            }
                        }else{
                            if( X == 4){
                                rst.append("XL");
                                num -= 40;
                            }else{
                                for(int i = 0; i < X; i++){
                                    rst.append('X');
                                }
                                num -= X * 10;
                            }
                        }
                    }else{
                        int I = num;
                        int V = I / 5;
                        if(V > 0){
                            if(I == 9){
                                rst.append("IX");
                                num -= 9;
                            }else{
                                rst.append('V');
                                num -= 5;
                            }
                        }else{
                            if(I == 4){
                                rst.append("IV");
                                num -= 4;
                            }else{
                                for(int i = 0; i < I; i++){
                                    rst.append('I');
                                }
                                num -= I;
                            }
                        }

                    }
                }
            }
        }
        return rst.toString();
    }

    // 盛最多水的容器
    // 利用双指针法可以将算法的时间复杂度优化到O(n)
    public int maxArea(int[] height) {
        int ret = 0;
        int left = 0, right = height.length -1;
        while(left != right){
            int curArea = (right - left) * Math.min(height[left], height[right]);
            ret = Math.max(ret, curArea);
            if(height[left] < height[right]) left++;
            else right--;
        }
        return ret;
    }

    // Z形字符串
    public String convert(String s, int numRows) {
            String result = "";
            if(numRows == 1) return s;
            ArrayList<Character>[] p = new ArrayList[numRows];
            for(int i=0; i<p.length; i++){
                p[i] = new ArrayList<Character>();
            }


            int t = numRows * 2 - 2;

            for(int i=0; i<s.length(); i++){
                int index = (i + 1) % t;
                if(index <= numRows && index !=0){
                    p[index - 1].add(s.charAt(i));
                }else if(index != 0){
                    int d = index - numRows;
                    p[numRows-1-d].add(s.charAt(i));
                }else{
                    p[1].add(s.charAt(i));
                }
            }

            for(int i=0;i<p.length;i++){
                for(Character c: p[i]){
                    result += c;
                }
            }


            return result;
    }

    // 完善后
    // 学习使用StringBuilder来提升运行时间
    // 只有两种可能向上或者向下，
    public String con(String s, int numRows){
        if(numRows == 1) return s;
        ArrayList<StringBuilder> ls = new ArrayList<>();
        int n = Math.min(numRows,s.length());

        for(int i = 0;i < n;i++){
            ls.add(new StringBuilder());
        }

        int curRow = 0;
        boolean goingDown = false;

        for(char c : s.toCharArray()){
            ls.get(curRow).append(c);

            if(curRow == 0 || curRow == n-1) goingDown = ! goingDown;
            curRow += goingDown ? 1 : -1;
        }

        StringBuilder result = new StringBuilder();
        for(StringBuilder strb : ls) result.append(strb);
        return result.toString();
    }

    public static void main(String[] args) {
        Solution05 s = new Solution05();
        System.out.println(s.letterCombinations2("2344"));
    }
}
