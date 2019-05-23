package from0to100;

/**
 * 假设按升序排序的数组在事先未知的某个枢轴处旋转。
 * <p>
 * （也就是说，[0,0,1,2,2,5,6]可能会变成[2,5,6,0,0,1,2]）。
 * <p>
 * 您将获得要搜索的目标值。如果在数组中找到返回true，否则返回false
 * <p>
 * 首先判断是否进行了旋转。
 * 首>=尾部  有可能数组全一样
 * 这时候 二分。跟 中间元素判断 并且跟首元素判断，
 * 0.=中间 返回true
 * 如果大于 首元素
 * 右边段肯定没有
 * 所以 只要中间的小于首元素
 * 如果小于 首元素
 * <p>
 * <p>
 * 先二分查找 中间节点
 * 如果中间 <=右和 <=左     如果全等 说明全一样    否则 根据 和首元素比较 判断应该从哪边进行二分
 */
public class SearchinRotatedSortedArrayII {
    public boolean search(int[] nums, int target) {
        int start = 0, end = nums.length - 1, mid = -1;
        while(start <= end) {
            mid = (start + end) / 2;
            if (nums[mid] == target) {
                return true;
            }
            //如果我们确定知道右侧是排序还是左侧未排序
            if (nums[mid] < nums[end] || nums[mid] < nums[start]) {
                if (target > nums[mid] && target <= nums[end]) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
                //如果我们确定左侧是排序的或右侧是未排序的
            } else if (nums[mid] > nums[start] || nums[mid] > nums[end]) {
                if (target < nums[mid] && target >= nums[start]) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            } else {
                //如果我们到达这里，那意味着nums [start] == nums [mid] == nums [end]，然后转出
                //任何一方都不会改变结果，但可以帮助删除重复
                //考虑，这里我们只使用end--但左边的++也可以
                end--;
            }
        }
        return false;
    }
}
