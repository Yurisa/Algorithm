public class Solution52 {
    public static int findString(String[] str, int n, String x) {
        int l = 0;
        int r = n - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (!str[mid].equals("")) {
                if (str[mid].equals(x)) {
                    return mid;
                } else if (str[mid].compareTo(x) < 0) {
                    l = mid + 1;
                } else if (str[mid].compareTo(x) > 0) {
                    r = mid - 1;
                }
            } else {
                int index = mid;
                while (index >= l && str[index].equals("")) {
                    index--;
                }
                if (index < l) {
                    l = mid + 1;
                } else if (str[index].compareTo(x) == 0) {
                    return index;
                } else if (str[index].compareTo(x) > 0) {
                    r = index - 1;
                } else {
                    l = mid + 1;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        String[] str = {"a","b","","c","","d"};
        System.out.println(findString(str, str.length, "c"));
    }
}
