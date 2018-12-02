package sword2offer.findandsort;

public class findMinInRotate {
    public int getMin(int[] numbers) {
        if (numbers == null || numbers.length == 0)
            throw new NullPointerException("传入空数组");
        int index1 = 0;
        int index2 = numbers.length - 1;
        int midIndex = 0;

        while (numbers[index1] >= numbers[index2]) {
            if (index2 - index1 == 1)
                return numbers[index2];
            midIndex = (index1 + index2) / 2;
            if (numbers[midIndex] == numbers[index1] && numbers[midIndex] == numbers[index2])
                return minInOrder(numbers, index1, index2);

            if (numbers[midIndex] >= numbers[index2])
                index1 = midIndex;
            else if (numbers[midIndex] <= numbers[index2])
                index2 = midIndex;
        }
        return numbers[midIndex];
    }

    private int minInOrder(int[] numbers, int index1, int index2) {
        int min = numbers[index1];
        for (int i = index1 + 1; i <= index2; i++) {
            if (min > numbers[i])
                min = numbers[i];
        }
        return min;
    }
}