import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FunctionTest {
    public static void main(String[] args) {

        List<Integer>   t= new ArrayList<>();
        t.add(10);
        t.add(2);
        t.add(3);
        System.out.println(t.subList(1,t.size()));


    }
}

