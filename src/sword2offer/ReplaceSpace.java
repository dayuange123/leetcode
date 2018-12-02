package sword2offer;

public class ReplaceSpace {
    //string为传入的数组，length为传入的串长度
    public static void ReplaceBlank(char[] string, int length) {

        //length是数组中英文的长度。
        int count = 0;
        for (int i = 0; i < length; ++i) {
            if (string[i] == ' ')
                ++count;
        }
        int newLength = length + count << 1;
        if (newLength > string.length)
            throw new IndexOutOfBoundsException("新串越界" + newLength);

        while (newLength >= 0) {
            if (string[length--] != ' ') {
                string[newLength--] = string[length];
            } else {
                string[newLength--] = '0';
                string[newLength--] = '2';
                string[newLength--] = '%';
            }
            length--;
        }
    }
}