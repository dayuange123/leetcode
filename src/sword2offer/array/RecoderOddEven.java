package sword2offer.array;

public class RecoderOddEven {
    public int[] recoderOddEven1(int[] nums) {
        if (nums == null || nums.length == 0) return nums;
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            while (left < right && (nums[left] & 1) != 0) {
                left++;
            }
            while (right > left && (nums[right] & 1) == 0) {
                right--;
            }
            if (left >= right) return nums;
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }
        return nums;
    }

    public int[] recoderOddEven2(int[] nums) {
        if (nums == null || nums.length == 0) return nums;
        int var0 = 0, var1 = 0;
        while (var1 < nums.length) {
            if ((nums[var1] & 1) != 0) {
                int temp = nums[var0];
                nums[var0] = nums[var1];
                nums[var1] = temp;
                var0++;
            }
            var1++;
        }
        return nums;
    }

    public static void main(String[] args) {
        int[] ints = new RecoderOddEven().recoderOddEven2(new int[]{1, 3, 5, 7, 9, 8, 6, 7});
        for (int anInt : ints) {
            System.out.println(anInt);
        }
    }

}