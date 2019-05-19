package from0to100;

import java.util.ArrayList;
import java.util.List;

public class Combinations {

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> lists = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        recure(1, n, k, list, lists);
        return lists;

    }

    public void recure(int start, int n, int k, List<Integer> list, List<List<Integer>> lists) {
        if (k == 0) {
            lists.add(new ArrayList<>(list));
            return;
        }
        for (int i = start; i <= n; i++) {
            list.add(i);
            recure(i + 1, n, k-1, list, lists);
            list.remove(list.size() - 1);
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> combine = new Combinations().combine(4, 2);
        combine.forEach(list -> {
            System.out.println(list);
        });
    }

}