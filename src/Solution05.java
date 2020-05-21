import java.io.PrintStream;
import java.util.ArrayList;

public class Solution05 {

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
        System.out.println(s.intToRoman(1882));
        System.out.println(s.intToRoman2(1882));

    }
}
