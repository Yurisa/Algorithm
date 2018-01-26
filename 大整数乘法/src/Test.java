import java.util.Scanner;

public class Test {
    static int Max = 200;
    static int num1[]=new int[Max];
    static int num2[]=new int[Max];
    static int result[]=new int[2*Max];
    static String s1=new String();
    static String s2=new String();
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        System.out.println("输入两个大整数");
        s1=scanner.nextLine();
        s2=scanner.nextLine();
        TransToInt(s1, num1);
        TransToInt(s2, num2);
        //逐个相乘
        for(int i=0;i<Max;i++) {
            for(int j=0;j<Max;j++) {
                result[i+j]+=num1[i]*num2[j];
            }
        }
        //移位、进位
        for(int i=0;i<2*Max-1;i++) {
            result[i+1]+=result[i]/10;
            result[i]=result[i]%10;
        }

        System.out.println("result=");
        int flag=2*Max-1;
        while(result[flag]==0) {
            if(flag==0) {
                System.out.println("0");
                return ;
            }
            flag--;
        }
        for(int i=flag;i>=0;i--) {
            System.out.print(result[i]);
        }
        System.out.println("");
    }
    private static void TransToInt(String s,int a[]) {
        for (int i = 0; i < s.length(); i++) {
            a[s.length() - 1 - i] = s.charAt(i) - '0';
        }
    }
}
