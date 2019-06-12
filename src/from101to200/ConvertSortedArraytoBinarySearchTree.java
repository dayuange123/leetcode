package from101to200;

import sun.nio.cs.ext.MacIceland;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
 * <p>
 * For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
 * <p>
 * Example:
 * <p>
 * Given the sorted array: [-10,-3,0,5,9],
 * <p>
 * One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:
 * <p>
 * 0
 * / \
 * -3   9
 * /   /
 * -10  5
 * 建立一个平衡树
 */
public class ConvertSortedArraytoBinarySearchTree {
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) return null;
        TreeNode root = buildChild(nums, 0, nums.length - 1);
        return root;
    }

    public TreeNode buildChild(int[] nums, int left, int right) {
        if (left > right) return null;
        int mid = (right - left) / 2 + left;
        TreeNode node = new TreeNode(nums[mid]);
        node.left = buildChild(nums, left, mid - 1);
        node.right = buildChild(nums, mid + 1, right);
        return node;
    }

    public TreeNode sortedListToBST(ListNode head) {
        int index = 0;
        ListNode p = head;
        while (p != null) {
            index++;
            p = p.next;
        }
        int[] nums = new int[index];
        index = 0;
        while (head != null) {
            nums[index++] = head.val;
            head = head.next;
        }
        return buildChild(nums, 0, nums.length - 1);
    }

    public TreeNode buildChild(List<Integer> nums, int left, int right) {
        if (left > right) return null;
        int mid = (right - left) / 2 + left;
        TreeNode node = new TreeNode(nums.get(mid));
        node.left = buildChild(nums, left, mid - 1);
        node.right = buildChild(nums, mid + 1, right);
        return node;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}
