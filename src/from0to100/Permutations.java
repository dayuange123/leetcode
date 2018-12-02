package from0to100;

import java.util.*;

public class Permutations {

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        rescure(lists, nums, 0, new ArrayList<>());
        return lists;
    }

    public void rescure(List<List<Integer>> lists, int[] nums, int index, List<Integer> list) {

        if (index == nums.length) {
            List<Integer> list1 = new ArrayList<>(list);
            lists.add(list1);
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!list.contains(nums[i]))
                list.add(nums[i]);
            else continue;
            rescure(lists, nums, index + 1, list);
            list.remove(list.size() - 1);
        }
    }

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(list, new ArrayList<>(), nums, 0);
        return list;
    }

    private void backtrack(List<List<Integer>> list, List<Integer> tempList, int[] nums, int start) {
        list.add(new ArrayList<>(tempList));
        for (int i = start; i < nums.length; i++) {
            tempList.add(nums[i]);
            backtrack(list, tempList, nums, i + 1);
            tempList.remove(tempList.size() - 1);
        }
    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (!map.containsKey(nums[i])) {
                map.put(nums[i], 1);
            } else map.replace(nums[i], map.get(nums[i])+1);
        }
     //   System.out.println(map);
        Set<Integer> set = map.keySet();
        List<Integer> nums1 = new ArrayList<>(set);
        List<List<Integer>> lists = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        rescurePermuteUnique(lists, map, nums1, list, nums.length);
        return lists;

    }

    private void rescurePermuteUnique(List<List<Integer>> lists,
                                      Map<Integer, Integer> map, List<Integer> nums1, List<Integer> list, int length) {
        if (list.size() == length) {
            lists.add(new ArrayList<>(list));
            return;
        }

        for (int i = 0; i < nums1.size(); i++) {
            if (map.get(nums1.get(i)) > 0) {
                list.add(nums1.get(i));
                map.replace(nums1.get(i), map.get(nums1.get(i)) - 1);
            } else continue;
            rescurePermuteUnique(lists, map, nums1, list, length);
            list.remove(list.size() - 1);
            map.replace(nums1.get(i), map.get(nums1.get(i)) + 1);

        }

    }


    public static void main(String[] args) {
        List<List<Integer>> permute = new Permutations().permuteUnique(new int[]{1, 1, 2});
        permute.forEach(list -> {
            System.out.println(list);
        });
    }
}