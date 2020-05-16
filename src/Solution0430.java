public class Solution0430 {
    //暴力算法超出时间限制
//    public String longestPalindrome(String s) {
////        String result = "";
////        for(int i = 0; i < s.length();i++){
////            for(int j = 0;j<=i;j++){
////                String sub = s.substring(j,s.length()-i + j);
////                if(isPalindrome(sub)) return sub;
////            }
////        }
////
////        return result;
////    }
////
////    public boolean isPalindrome(String s){
////        String firstHalf = s.substring(0,s.length()/2);
////        String secondHalf = "";
////        for(int i = s.length() -1 ; i > (s.length() - 1) /2; i--){
////            secondHalf = secondHalf + s.charAt(i);
////        }
////
////        return firstHalf.equals(secondHalf);
////    }

    //动态规划
    public String longestPalindrome(String s) {
        String result = "";
        char [] p = s.toCharArray();
        int l = s.length();
        boolean[][] t = new boolean[l][l];
        if( l == 1 || l == 0) return s;
        int left = 0, right = 0;

        //单字符初始状态 p(i,i) = true
        for(int i = 0;i<l;i++){
            t[i][i] = true;

        }

        //双字符初始状态 p(i, i + 1) = (S(i) == S(i + 1))

        for(int i = 0; i<l -1;i++){
            t[i][i+1] = (p[i] == p[i+1]);
            if(t[i][i+1]){
                left = i;
                right = i+1;
            }
        }

        //多字符字符串 p(i, j) = (p(i + 1, j -1 ) and S(i) == S(j))
        for(int i = 3; i <=l; i++){
            for(int j = 0; j <= l - i;j++){
                t[j][j + i -1] = (t[j+1][j+i-2] && (p[j] == p[j + i -1]));
                if(t[j][j + i -1]) {
                    left = j;
                    right = j + i -1;
                }
            }

        }
        return s.substring(left, right + 1);
    }

    public static void main(String[] args) {
        Solution0430 s = new Solution0430();
        System.out.println(s.longestPalindrome("babad"));

    }
}
