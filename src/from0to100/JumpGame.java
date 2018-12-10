package from0to100;

import com.sun.org.apache.xpath.internal.SourceTree;

public class JumpGame {
    public boolean canJump(int[] nums) {

        int maxLength = 0;
        if (nums.length == 0) return true;
        for (int i = 0; i <= maxLength; ++i) {
            if (maxLength >= nums.length - 1) return true;
            if (i > nums.length - 1) return false;
            if (nums[i] + i > maxLength) {
                maxLength = nums[i] + i;
            }
        }
        return false;

    }

    public void test(char[] a) {
        a[0] = 1;
    }

    public static void main(String[] args) {
        // System.out.println(new JumpGame().canJump(new int[]{2, 0}));
        //   String a="a"+"b";
//        char[] a = new char[3];
//        a[0]=2;
//        JumpGame jumpGame = new JumpGame();
//        jumpGame.test(a);
//        System.out.println(a[0]+a[1]);
//        String a = "123";
//        String aa = new String("123");
//        System.out.println(a==aa);
//        System.out.println(a==aa.intern());
        StringBuffer stringBuffer=new StringBuffer("10");
        String s = stringBuffer.toString();
        String s1 = stringBuffer.toString();
        System.out.println(s==s1);
    }
}