package top.chaoranlaoge.bytecode;

public class Hello {
    public static void main(String[] args) {
        Hello hello = new Hello();
        int a = 0;
        String b = "abc";
        String c = a + b;
        System.out.println(c);
        int d = 2;
        int e = a * d;
        if(e == 0) {
            while(d-- != 0) {
                System.out.println(d);
            }
        }
    }
}
