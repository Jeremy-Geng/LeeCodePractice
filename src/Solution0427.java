import java.util.ArrayList;
import java.util.HashMap;

public class Solution0427 {
    public int lengthOfLongestSubstring(String s) {
        int r = 0, temp = 0;

        HashMap<Character,Integer> p = new HashMap<>();
        for(int i = 0; i < s.length();){
            char cur = s.charAt(i);
            if(!p.containsKey(cur)){
                p.put(cur,i);
                temp++;
                i++;
            }else{
                temp = Math.max(temp,r);
                i = p.get(cur) + 1;
                p.clear();
                temp = 0;
            }
        }

        temp = Math.max(temp,r);
        return r;
    }

    //优化
    public int length(String s){
        int r = 0, n = s.length();
        HashMap<Character, Integer> p = new HashMap<>();

        for(int i = 0,j = 0;j<n;j++){
            Character cur = s.charAt(j);
            if(p.containsKey(cur)){
                i = Math.max(p.get(cur),i);
            }

            r = Math.max(r, j - i + 1);
            p.put(cur,j+1);
        }

        return r;
    }

    public static void main(String[] args) {
        Solution0427 s = new Solution0427();
        System.out.println(s.length("dvdf"));

    }



}
