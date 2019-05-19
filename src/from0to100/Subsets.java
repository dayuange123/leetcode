package from0to100;

import javax.xml.bind.SchemaOutputResolver;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Subsets {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        lists.add(new ArrayList<>());
        for (int num : nums) {
            list.add(num);
        }
        Arrays.sort(nums);
        lists.add(list);
        list = new ArrayList<>();
        for (int i = 1; i < nums.length; i++) {
            solve(0, i, nums, list, lists);
        }
        return lists;
    }

    private void solve(int strat, int k, int[] nums, List<Integer> list, List<List<Integer>> lists) {
        if (strat > 0 && strat < nums.length && nums[strat] == nums[strat - 1]) return;

        if (k == 0) {
            lists.add(new ArrayList<>(list));
            return;
        }
        for (int i = strat; i < nums.length; i++) {
            list.add(nums[i]);
            solve(i + 1, k - 1, nums, list, lists);
            list.remove(list.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] a = new int[]{1, 2, 2,3};
        List<List<Integer>> subsets = new Subsets().subsets(a);
        subsets.forEach(list -> {
            System.out.println(list);
        });
    }
}