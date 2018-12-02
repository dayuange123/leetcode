package sword2offer.findandsort;

public class SortAges {
    public int[] sortAges(int[] ages) {
        int[] allAges = new int[100];

        for (int i = 0; i < ages.length; ++i) {
            if (ages[i] < 0 || ages[i] > 100)
                throw new RuntimeException("年龄存在问题" + ages[i]);
            allAges[ages[i]]++;
        }
        int k = 0;
        for (int i = 0; i < allAges.length; i++) {
            if (allAges[i] == 0) continue;
            for (int j = 0; j < allAges[i]; ++j)
                ages[k++] = i;
        }
        return ages;
    }

}