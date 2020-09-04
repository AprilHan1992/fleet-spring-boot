package com.fleet.alg;

import com.alibaba.fastjson.JSON;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * 上图为 8 皇后问题的一种解法。
 * <p>
 * 给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。
 * <p>
 * 每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 * <p>
 * 示例：
 * <p>
 * 输入：4
 * 输出：[
 * [".Q..",  // 解法 1
 * "...Q",
 * "Q...",
 * "..Q."],
 * <p>
 * ["..Q.",  // 解法 2
 * "Q...",
 * "...Q",
 * ".Q.."]
 * ]
 * 解释: 4 皇后问题存在两个不同的解法。
 *  
 * <p>
 * 提示：
 * <p>
 * 皇后彼此不能相互攻击，也就是说：任何两个皇后都不能处于同一条横行、纵行或斜线上。
 */
public class SolveNQueens {

    public static void main(String[] args) {
        System.out.println(JSON.toJSONString(solveNQueens(4)));
    }

    public static List<List<String>> solveNQueens(int n) {
        // 声明一个棋盘，记录是否能放置 Queen
        String[][] cb = new String[n][n];
        for (String[] r : cb) {
            Arrays.fill(r, ".");
        }
        List<List<String>> rList = new LinkedList<>();
        row(0, cb, rList);
        return rList;
    }

    public static void row(int r, String[][] cb, List<List<String>> rList) {
        int n = cb.length;
        if (r == n) {
            List<String> rl = new LinkedList<>();
            for (String[] row : cb) {
                StringBuilder string = new StringBuilder();
                for (String column : row) {
                    string.append(column);
                }
                rl.add(string.toString());
            }
            rList.add(rl);
            return;
        }
        for (int i = 0; i < n; i++) {
            if (valid(r, i, cb)) {
                cb[r][i] = "Q";
                row(r + 1, cb, rList);
                cb[r][i] = ".";
            }
        }
    }

    public static boolean valid(int r, int c, String[][] cb) {
        int n = cb.length;
        for (int i = 0; i < n; i++) {
            if ("Q".equals(cb[r][i])) {
                return false;
            }
        }
        for (int i = 0; i < n; i++) {
            if ("Q".equals(cb[i][c])) {
                return false;
            }
        }
        int i = r - 1;
        int j = c - 1;
        while (i >= 0 && i < n && j >= 0 && j < n) {
            if ("Q".equals(cb[i][j])) {
                return false;
            }
            i--;
            j--;
        }
        i = r - 1;
        j = c + 1;
        while (i >= 0 && i < n && j >= 0 && j < n) {
            if ("Q".equals(cb[i][j])) {
                return false;
            }
            i--;
            j++;
        }
        return true;
    }
}
