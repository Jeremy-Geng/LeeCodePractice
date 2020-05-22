import java.io.PrintStream;
import java.util.*;

public class Solution05 {
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

    // DFS 深度优先搜寻
    private HashMap<Integer,String> p= new HashMap<>(){ {put(2,"abc");
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
        System.out.println(s.isValid("([)"));


    }
}
