import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Solution0602 {
    //x的平方根 2020/06/18 使用二分查找提高效率

    public int mySqrt(int x) {
        if(x == 0 ) return 0;
        long lo = 1, hi = x, mid = lo + (hi - lo) /2;
        while(lo <= hi){
            long key = mid * mid;
            if(key == x  ) return (int)mid;
            else if(key < x  ) lo = mid + 1;
            else hi = mid-1;
            mid = lo + (hi - lo)/ 2;
        }

        return (int)hi;

    }
    //组合总数 2020/06/15
    //1.已选择路径 2. 循环选择路径 3.退出条件 这三者是回溯算法的常规考虑条件
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> rst = new ArrayList<>();
        Arrays.sort(candidates);
        combination(rst,null,0,candidates,target);

        return rst;
   }

   public void combination(List<List<Integer>> rst,
                           List<Integer> single,
                           int begin,
                           int[] cands,
                           int target){

        if(begin == cands.length) return;
        for(int i = begin; i < cands.length;i++){
            List<Integer> p = new ArrayList<>();
            if(single != null) p.addAll(single);
            if(i > begin && cands[i] == cands[i-1])continue;
            int cur = cands[i];
            if(cur == target){
                p.add(cur);
                rst.add(p);
                return;
            }else if(cur > target){
                return;
            }else{
                p.add(cur);
                combination(rst,p,i+1,cands,target-cur);
            }
        }
   }

    //二制求和 2020/06/15
    public String addBinary3(String a, String b){
        int l = Math.min(a.length(),b.length());
        int addOn = 0;
        int i = 0;
        StringBuilder rst = new StringBuilder();
        while(i < l){
            int curA = a.charAt(a.length()-1-i) - '0', curB = b.charAt(b.length()-1-i)-'0';
            int sum = curA + curB + addOn;
            if(sum == 0){
                rst.insert(0,0);
                addOn =0;
            }else if(sum == 1){
                rst.insert(0,1);
                addOn = 0;
            }else if(sum == 2){
                rst.insert(0,0);
                addOn = 1;
            }else if(sum == 3){
                rst.insert(0,1);
                addOn = 1;
            }
            i++;
        }

        String longer = a.length() > b.length() ? a : b;
        if(addOn == 0){
            rst.insert(0,longer.substring(0,longer.length() - l));
        }else{
            int m = longer.length() - l -1;
            while(m >= 0 && longer.charAt(m) != '0'){
                rst.insert(0,0);
                m--;
            }

            rst.insert(0,1);
            if(m>=0)rst.insert(0,longer.substring(0,m));

        }
        return rst.toString();
    }


    public String addBinary2(String a, String b) {
        int la = a.length();
        int lb = b.length();
        int l = Math.max(la,lb);
        int i = 0;
        int addOn = 0;
        StringBuilder rst = new StringBuilder();
        while(i < l){
            int cura = 0;
            int curb = 0;
            if(i < la) cura = a.charAt(la -1 -i) - '0';
            if(i < lb) curb = b.charAt(lb -1 -i) - '0';

            int temp = cura + curb + addOn;
            if(temp == 0) {
                rst.append(0);
                addOn = 0;
            }
            else if(temp == 1) {
                rst.append(1);
                addOn =0;
            }
            else if (temp == 2) {
                rst.append(0);
                addOn = 1;
            }else if(temp == 3){
                rst.append(1);
                addOn = 1;
            }

            i++;

        }

        String r = rst.reverse().toString();
        if(addOn == 1){
            r = 1+ r;
        }

        return r;
    }

    //强制转换导致不能处理过大的数字
    public String addBinary(String a, String b) {
        long A = 0, B = 0;
        for(int i = a.length()-1; i>=0;i--){
            int cur = a.charAt(i) - '0';
            A += (long)Math.pow(2,a.length()- 1 -i) *  cur;
        }

        for(int i = b.length()-1; i>=0;i--){
            int cur = b.charAt(i) - '0';
            B += (long)Math.pow(2,b.length()- 1 -i) *  cur;
        }

        long res = A + B;

        return Long.toBinaryString(res);

    }

    public static void main(String[] args) {
        Solution0602 s = new Solution0602();
        System.out.println(s.mySqrt(2147395599));
    }
}
