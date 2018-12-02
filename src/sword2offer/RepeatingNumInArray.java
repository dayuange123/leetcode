package sword2offer;

public class RepeatingNumInArray {


    public int getDuplication(int[] number, int length) {
        if (number == null || length <= 0)
            return -1;
        int start = 1, end = length - 1;
        while (end >= start) {
            //求出中间值 确定范围为 start-middle
            int middle = ((end - start) >> 1) + start;
            //计算数在该范围内出现的次数
            int count = countRange(number, length, start, middle);
            //只有一个数 一般情况下都是返回start 因为至少有一组。如果break代表没有按规则输入
            if (end == start) {
                if (count > 1) return start;
                break;
            }
            //范围内数出现的次数大于这个范围。说明这个范围内的某个数是重复的，继续在该范围查找。
            if (count > (middle - start + 1))
                end = middle;
            //没在该范围，在后面的那个范围中查找。
            else start = middle + 1;
        }
        return -1;
    }

    public int countRange(int[] number, int length, int start, int end) {
        int count = 0;
        for (int i = 0; i < number.length; ++i) {
            if (number[i] >= start && number[i] <= end)
                count++;
        }
        return count;
    }

    public int duplicate(int[] number, int length) {
        if (number == null || length <= 0)
            return -1;
        for (int i = 0; i < length; ++i) {
            if (number[i] < 0 || number[i] > length - 1)
                return -1;
        }

        for (int i = 0; i < length - 1; ++i) {
            while (number[i] != i) {
                if (number[i] == number[number[i]])
                    return number[i];

                int temp = number[i];
                number[i] = number[temp];
                number[temp] = temp;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(  new RepeatingNumInArray().duplicate(new int[]{1,2,2,2,5,6,6,7},8));

    }
}