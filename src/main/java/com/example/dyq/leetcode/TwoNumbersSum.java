package com.example.dyq.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: dongyuqiang
 * @Date: 2019/10/24 14:59
 * @Description: 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 * <p>
 * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
 */
public class TwoNumbersSum {

    public static void main(String[] args) {
        int[] nums = new int[]{2, 2, 9, 7, 15};
        int target = 9;
        int[] ints = twoSum(nums, target);
        System.out.println("暴力法:" + Arrays.toString(ints));
        int[] ints1 = twoSumTwoHash(nums, target);
        System.out.println("两遍hash表法:" + Arrays.toString(ints1));
        int[] ints2 = twoSumOneHash(nums, target);
        System.out.println("一遍hash表法:" + Arrays.toString(ints2));

    }

    //暴力法
    public static int[] twoSum(int[] nums, int target) {

        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int temp = nums[i] + nums[j];
                if (target == temp) {
                    return new int[]{i, j};

                }
            }
        }

        throw new IllegalArgumentException("No two sum solution");
    }

    //两遍哈希法
    public static int[] twoSumTwoHash(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }

        for (int i = 0; i < nums.length; i++) {
            int temp = target - nums[i];
            if (map.containsKey(temp) && map.get(temp) != i) {
                return new int[]{i, map.get(temp)};
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    //一遍哈希法:存在问题，无重复元素存在时，两数的数组下标是反的；存在重复元素时，两数的数组下标中，重复的那个元素下标会被覆盖
    public static int[] twoSumOneHash(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int temp = target - nums[i];
            if (map.containsKey(temp)) {
                return new int[]{map.get(temp), i};
            }
            map.put(nums[i], i);
        }

        throw new IllegalArgumentException("No two sum solution");
    }
}
