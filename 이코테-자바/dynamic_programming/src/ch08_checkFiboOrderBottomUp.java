public class ch08_checkFiboOrderBottomUp {

    public static long[] d = new long[100];

    public static void main(String[] args) {
        d[1] = 1;
        d[2] = 1;
        int n = 50;

        for (int i = 3; i < n + 1; i++) {
            d[i] = d[i - 1] + d[i - 2];
        }
        System.out.println(d[n]);
    }

}
