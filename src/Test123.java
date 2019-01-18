public class Test123 {
    public static void main(String[] args) {
        int[] a = new int[10];
        try {
            System.out.println(a[11]);
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println(2);
    }
}