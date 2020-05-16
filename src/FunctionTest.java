import java.util.ArrayList;

public class FunctionTest {
    public static void main(String[] args) {
        StringBuilder strb = new StringBuilder();
        System.out.println(strb.append(5).append('c').append("HHAHAHA"));
        strb.setCharAt(1,'t');
        System.out.println(strb);
        strb.insert(2,"fafa");
        System.out.println(strb);
        strb.delete(0,strb.length()-1);
        System.out.println(strb);

    }
}
