import java.util.*;

public class Solution0602 {


    //字母异位词分组 拉链法
    public List<List<String>> groupAnagrams2(String[] strs) {
        List<List<String>> p = new ArrayList<>();
        if(strs.length == 0) return p;

        Map<String,List> t = new HashMap<>();
        for(String str : strs){
            char[] ca = str.toCharArray();
            Arrays.sort(ca);
            String key = String.valueOf(ca);
            if(!t.containsKey(key)) t.put(key, new ArrayList());
            t.get(key).add(str);
        }

        return new ArrayList(t.values());
    }


        // 时间不够快
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> p = new ArrayList<>();
        if(strs.length == 0) return p;

        List<String> first = new ArrayList<>();
        first.add(strs[0]);
        p.add(first);

        for(int i = 1; i < strs.length;i++){
            boolean test = false;

            for(int j = 0; j < p.size();j++){
                if(isSame(p.get(j).get(0),strs[i])) {
                    p.get(j).add(strs[i]);
                    test = true;
                    break;
                }
            }

            if(!test){
                List<String> newOne = new ArrayList<>();
                newOne.add(strs[i]);
                p.add(newOne);
            }
        }

        return p;
    }


    public boolean isSame(String a, String b){
        if(a.length() != b.length()) return false;
        HashMap<Character, Integer> p = new HashMap<>();
        HashMap<Character, Integer> t = new HashMap<>();
        for(int i = 0; i < a.length();i++){
            if(p.keySet().contains(a.charAt(i))) p.put(a.charAt(i), p.get(a.charAt(i)) + 1);
            else p.put(a.charAt(i),1);

            if(t.keySet().contains(b.charAt(i))) t.put(b.charAt(i), t.get(b.charAt(i)) + 1);
            else t.put(b.charAt(i),1);
        }
        return p.equals(t);
    }
    // 旋转图像 简单方法是traverse矩阵，再reverse每一行

    public void rotate2(int[][] matrix){
        int len = matrix.length;
        int k = 0;
        for(int i = k; i < len;i++){
            for(int j = k; j < len;j++){
                int p = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] =  p;
            }

            k++;
        }

        for(int i = 0;i < len;i++){
            for(int j = 0;j < len/2;j++){
                int p = matrix[i][j];
                matrix[i][j] =  matrix[i][len-1-j];
                matrix[i][len-1-j] = p;
            }
        }
    }



    public void rotate(int[][] matrix) {
        int len = matrix.length;
        int n = len;
        int i = 0;
        while(n > 1){
            //处理四个角
            int leftUp = matrix[i][i];

            int rightUp = matrix[i][len - 1 - i];

            int leftDown = matrix[len - 1 - i][i];

            int rightDown = matrix[len - 1 -i][len - 1 - i];

            matrix[i][i] = leftDown;

            matrix[i][len - 1 - i] = leftUp;

            matrix[len - 1 - i][i] = rightDown;

            matrix[len - 1 -i][len - 1 - i] = rightUp;

            //进行四次旋转

            int up[] = new int[n-2];
            int down[] = new int[n-2];
            int left[] = new int[n-2];
            int right[] = new int[n-2];


            for(int j = 0; j < n - 2; j++){
                up[j] = matrix[i][i + 1 + j];
                down[j] = matrix[len - 1- i][i + 1 + j];
                left[j] = matrix[i + 1 + j][i];
                right[j] = matrix[i + 1 + j][len - 1 - i];
            }


            for(int k = 0; k < n - 2; k++){
                //up
                matrix[i][i+1+k] = left[n-3-k];

                //down
                matrix[len - 1- i][i + 1 + k] = right[n-3-k];

                //left
                matrix[i + 1 + k][i] = down[k];

                //right
                matrix[i + 1 + k][len - 1 - i] = up[k];
            }

            n = n - 2;
            i++;

        }

    }

    public void exch(int[][] matrix,int m, int n, int k, int j){
        int p = matrix[m][n];
        matrix[m][n] = matrix[k][j];
        matrix[k][j] = p;
    }
    // 爬楼梯 2020/06/24
    public int climbStairs(int n) {

        int p[] = new int[n+1];
        p[0] = 0;
        p[1] = 1;
        p[2] = 2;
        p[3] = 3;
        for(int i = 4; i <= n; i++){
            p[i] = p[i-1] + p[i-1];
        }
        return p[n];
    }

    // 全排列II 2020/06/20
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> rst = new ArrayList<>();

        if(nums.length == 0) return rst;
        List<Integer> cands = new ArrayList<>();
        Arrays.sort(nums);

        for(int i : nums){
            cands.add(i);
        }
        backTrack(rst,cands,null);
        return rst;
    }

    public void backTrack(List<List<Integer>> rst, List<Integer> cands, List<Integer> single){
        if(cands.size() == 0){
            rst.add(single);
            return;
        }

        for(int i = 0; i < cands.size();i++){
            if(i > 0 && cands.get(i) == cands.get(i-1) ) continue;
            List<Integer> p = single == null? new ArrayList<>() : new ArrayList<>(single);
            p.add(cands.get(i));

            List<Integer> newCands = new ArrayList<>(cands);
            newCands.remove(i);
            backTrack(rst, newCands, p);
        }
    }


    // 颜色分类 2020/06/20
    public void sortColors(int[] nums) {
       sort3waycut(nums,0,nums.length-1);
    }

    public void sort3waycut(int[] nums, int lo, int hi){
        if(lo >= hi) return;
        int i = lo ,lt =lo, gt = hi + 1;
        while(i < gt){
            if(nums[i] < 1){
                int p = nums[lt];
                nums[lt++] = nums[i];
                nums[i++] = p;
            }else if(nums[i] > 1){
                int p = nums[i];
                nums[i] = nums[--gt];
                nums[gt] = p;
            }else {
                i++;
            }
        }
    }

    // x的平方根 2020/06/18 使用二分查找提高效率
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

    // 组合总数 2020/06/15
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
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println(s.groupAnagrams2(strs));
    }
}
