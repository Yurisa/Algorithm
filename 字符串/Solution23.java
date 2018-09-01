import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Solution23 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            String str = in.nextLine();
            char[] chars = str.toCharArray();
            Set<String> set = CalcAllPermutation(chars,0, chars.length - 1);
            Iterator<String> iterator = set.iterator();
            while(iterator.hasNext()) {
                System.out.println(iterator.next());
            }
            System.out.println();
        }
    }
    public static Set<String> CalcAllPermutation(char[] chars, int from, int to) {
        Set<String> set = new TreeSet<>();
        if (from == to) {
            set.add(String.valueOf(chars));
        } else {
            for (int i = from; i <= to ; i++) {
                swap(chars, i, from);
                set.addAll(CalcAllPermutation(chars, from + 1, to));
                swap(chars, i, from);
            }
        }
        return set;
    }


    private static void swap(char[] cs,int i,int j) {
        char t = cs[i];
        cs[i] = cs[j];
        cs[j] = t;
    }
}
