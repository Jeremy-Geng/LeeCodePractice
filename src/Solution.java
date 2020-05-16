import javax.print.DocFlavor;
import java.util.HashMap;
class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x){
        val = x;
    }
}

class ListNode{
    int val;
    ListNode next;
    ListNode(int x) { val = x; }

    public String toString(){
        ListNode p = this;
        String result = "";
        while(p != null){
            result = p.val + result;
            p = p.next;
        }
        return result;
    }
}


public class Solution {
    //15/04/2020

    public int romanToInt(String s){
        int result = 0;
        for(int i = 0; i < s.length();i++){
            char cur = s.charAt(i);
            char curNext = ' ';

            switch (cur){
                case 'I':
                    if(i+ 1 < s.length())curNext = s.charAt(i+1);
                    if(curNext == 'V') {
                        result = result + 4;
                        i++;
                    }
                    else if(curNext == 'X'){
                        result = result + 9;
                        i++;
                    }else{
                        result++;
                    }
                    break;
                case 'V':
                    result = result + 5;
                    break;
                case 'X':
                    if(i+ 1 < s.length()) curNext = s.charAt(i+1);
                    if(curNext == 'L') {
                        result = result + 40;
                        i++;
                    }
                    else if(curNext == 'C'){
                        result = result + 90;
                        i++;
                    }else{
                        result = result + 10;
                    }
                    break;
                case 'L':
                    result = result + 50;
                    break;
                case 'C':
                    if(i+ 1 < s.length()) curNext = s.charAt(i+1);
                    if(curNext == 'D') {
                        result = result + 400;
                        i++;
                    }
                    else if(curNext == 'M'){
                        result = result + 900;
                        i++;
                    }else{
                        result = result + 100;
                    }
                    break;
                case 'D':
                    result = result + 500;
                    break;
                case 'M':
                    result = result + 1000;
                    break;
            }
        }
        return result;
    }
    public boolean isPalindrome(int x) {
        if(x < 0) return false;
        int orign = x;
        int pop = 0;
        int temp = 0;
        int reverse = 0;
        while(x > 0){
            pop = x % 10;
            x = x / 10;

            reverse = temp * 10 + pop;
            temp = reverse;
        }

        return orign == reverse ? true : false;
    }
    //14/04//2020
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result = null, p = null;
        boolean carry = false;
        int extra = 0 ;
        while(l1 != null || l2 != null || carry){
            if((l1 == null ^ l2 == null) && !carry){
                p.next = (l2 != null) ? l2 : l1;
                break;
            }
            int valueOne = 0;
            int valueTwo = 0;
            if(l1 !=null){
                valueOne = l1.val;
                l1 = l1.next;
            }
            if(l2 !=null){
                valueTwo = l2.val;
                l2 = l2.next;
            }

            int curNum = carry ? (valueOne + valueTwo + 1) : (valueOne + valueTwo);
            if(curNum < 10){
                if(result == null){
                    result = new ListNode(curNum);
                    p = result;
                }else{
                    //ListNode cur = findLast(result);
                    //cur.next = new ListNode(curNum);
                    p.next = new ListNode(curNum);
                    p = p.next;
                }
                carry = false;
            }else{
                extra = curNum % 10;
                if(result == null){
                    result = new ListNode(extra);
                    p = result;
                }else{
                    //ListNode cur = findLast(result);
                    //cur.next = new ListNode(extra);
                    p.next = new ListNode(extra);
                    p = p.next;
                }
                carry = true;
            }

        }

        return result;
    }

    public static ListNode findLast(ListNode l){
        ListNode result = null;
        while(l != null){
            if(l.next == null) result = l;
            l = l.next;
        }
        return result;
    }


    //12/04/2020
    private TreeNode result = null;
    //自己做法，时间复杂度未达标

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        this.traverse(root, p, q);
        return this.result;
    }


    public boolean traverse(TreeNode root, TreeNode p, TreeNode q){
        boolean t = false;

        if (root == null) return false;

        boolean left = traverse(root.left, p, q);

        boolean right = traverse(root.right, p ,q);

        boolean mid = (root.val == p.val || root.val == q.val) ? true:false;

        if(((left && mid) || (right && mid) || ( left && right)) && (this.result == null) ) this.result = root;

        if( mid || left || right) t = true;

        return t;

    }



    //10/04/2020
    //整数反转
    public static int myAtoi(String str) {

        long res = 0;
        str = str.trim();
        if(str.isEmpty()) return 0;
        if(Character.isDigit(str.charAt(0)) || str.charAt(0) == '-' || str.charAt(0) == '+'){
            String t = "" + str.charAt(0);
           for(int i = 1;i < str.length();i++){
               if(Character.isDigit(str.charAt(i))){
                   t += str.charAt(i);
               }else break;
           }
           try{
               res = Long.parseLong(t);
           }catch (Exception e){
               if(t.length() > 10 && t.charAt(0) == '-') return Integer.MIN_VALUE;
               else if(t.length() > 10) return Integer.MAX_VALUE;
               else return 0;
           }
           if( res > Integer.MAX_VALUE) return Integer.MAX_VALUE;
           else if(res < Integer.MIN_VALUE) return Integer.MIN_VALUE;
        }else return 0;

        return (int)res;
    }

    public static int reverse(int x) {
        int rev = 0;
        int temp = 0;
        int pop = 0;

        while(x != 0){
            pop = x % 10;
            x = x / 10;

            if(temp > Integer.MAX_VALUE/10 || (temp == Integer.MAX_VALUE/10 && pop == 7) )  return 0;
            if(temp < Integer.MIN_VALUE/10 || (temp == Integer.MIN_VALUE/10 && pop == -8) ) return 0;
            rev = temp * 10 + pop;
            temp = rev;
        }
        return rev;
    }

    // 09/04/2020
    public static boolean isIsomorphic(String s, String t){
        HashMap<Character,Character> match = new HashMap<>();
        for(int i = 0; i < s.length();i++){
            if(match.containsKey(s.charAt(i))){
                if(!(match.get(s.charAt(i)) == t.charAt(i))) return false;
            }else{
                if(match.containsValue(t.charAt(i))) return false;
                else match.put(s.charAt(i),t.charAt(i));
            }
        }

        return true;
    }
    //哈希表可以以近乎恒定的时间进行查找，这是hash表存在的目的，时间复杂度为O（1）
    public static int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        HashMap<Integer, Integer> match = new HashMap<>();


        for(int i = 0; i < nums.length;i++){
            int complement = target - nums[i];
            if(match.containsKey(complement)){
                result[0] = match.get(complement);
                result[1] = i;
                break;
            }
            else{
                match.put(nums[i],i);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.romanToInt("LVIII"));
    }
}
