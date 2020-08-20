package com.fleet.alg;

import com.alibaba.fastjson.JSON;

import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个二叉树，返回所有从根节点到叶子节点的路径。
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * @author April Han
 */
public class BinaryTreePaths {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode tn1 = new TreeNode(2);
        TreeNode tn2 = new TreeNode(5);
        TreeNode tn3 = new TreeNode(3);
        root.left = tn1;
        root.right = tn3;
        tn1.right = tn2;
        System.out.println(JSON.toJSONString(binaryTreePaths(root)));
    }

    public static List<String> binaryTreePaths(TreeNode root) {
        List<String> paths = new LinkedList<>();
        String path = "";
        helper(root, paths, path);
        return paths;
    }

    public static void helper(TreeNode treeNode, List<String> paths, String path) {
        if (treeNode != null) {
            path += String.valueOf(treeNode.val);
            if (treeNode.left == null && treeNode.right == null) {
                paths.add(path);
            } else {
                path += "->";
                if (treeNode.left != null) {
                    helper(treeNode.left, paths, path);
                }
                if (treeNode.right != null) {
                    helper(treeNode.right, paths, path);
                }
            }
        }
    }
}
