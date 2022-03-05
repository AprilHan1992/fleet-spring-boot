package com.fleet.alg;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * <p>
 * candidates 中的数字可以无限制重复被选取。
 * <p>
 * 说明：
 * <p>
 * 所有数字（包括 target）都是正整数。
 * 解集不能包含重复的组合。 
 * 示例 1：
 * <p>
 * 输入：candidates = [2,3,6,7], target = 7,
 * 所求解集为：
 * [
 * [7],
 * [2,2,3]
 * ]
 * 示例 2：
 * <p>
 * 输入：candidates = [2,3,5], target = 8,
 * 所求解集为：
 * [
 *   [2,2,2,2],
 *   [2,3,3],
 *   [3,5]
 * ]
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= candidates.length <= 30
 * 1 <= candidates[i] <= 200
 * candidate 中的每个元素都是独一无二的。
 * 1 <= target <= 500
 */
public class CombinationSum {

    public static void main(String[] args) {
        int[] candidates = {2, 3, 5, 8};
        System.out.println(JSON.toJSONString(combinationSum(candidates, 8)));
    }

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> r = new ArrayList<>();
        List<Integer> combine = new ArrayList<>();
        combinationSum(r, candidates, combine, target, 0);
        return r;
    }

    public static void combinationSum(List<List<Integer>> r, int[] candidates, List<Integer> combine, int target, int i) {
        if (i == candidates.length) {
            return;
        }
        if (target == 0) {
            r.add(new ArrayList<>(combine));
            return;
        }
        combinationSum(r, candidates, combine, target, i + 1);
        if (target - candidates[i] >= 0) {
            combine.add(candidates[i]);
            combinationSum(r, candidates, combine, target - candidates[i], i);
            combine.remove(combine.size() - 1);
        }
    }
}
