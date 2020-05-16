import java.util.ArrayList;

public class Solution05 {
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
        System.out.println(s.con("AB",1));
        int[] a = {1,8,6,2,5,4,8,3,7};
        System.out.println(s.maxArea(a));

    }
}
