package from0to100;

import java.util.*;

public class CombinationSum {
    public List<List<Integer>> list = new ArrayList<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<Integer> list1 = new ArrayList<>();
        recuion(target, candidates.length - 1, candidates, list1);
        return list;
    }

    public void recuion(int target, int index, int[] candidates, List<Integer> list1) {
        //找到了 放入list，这里复制了一个lsit1副本。因为我们从头到尾之操作了一个list1，不能直接将list1引用放进去
        if (target == 0) {
            List<Integer> l = new ArrayList<>();
            for (int i = 0; i < list1.size(); i++) {
                l.add(list1.get(i));
            }
            list.add(l);
            return;
        }
        //通过这次递归没找到结果，回溯
        if (index == -1)
            return;
        while (candidates[index] > target) {
            index--;
            if (index == -1) {
                return;
            }
        }

        for (int j = target / candidates[index]; j >= 0; j--) {
            for (int h = 0; h < j; h++)
                list1.add(candidates[index]);
            recuion(target - candidates[index] * j, index - 1, candidates, list1);
            for (int h = 0; h < j; h++)
                list1.remove(list1.size() - 1);
        }
    }


}