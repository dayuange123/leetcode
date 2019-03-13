package from0to100;

public class PlusOne {
    public int[] plusOne(int[] digits) {
        int[] array = new int[digits.length + 1];
        int index = -1;
        for (int i = digits.length - 1; i >= 0; i--) {
            array[i + 1] = (digits[i] + 1) % 10;
            if (digits[i] + 1 <= 9) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            array[0] = 1;
            return array;
        }
        for (int i = index; i < array.length - 1; i++) {
            digits[i] = array[i + 1];
        }
        return digits;
    }
}