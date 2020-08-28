package com.fleet.alg;

/**
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 * <p>
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * <p>
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * <p>
 * 示例：
 * <p>
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 */
public class AddTwoNumbers {

    public static void main(String[] args) {
        ListNode l1 = new ListNode(2);
        ListNode ln1 = new ListNode(4);
        ListNode ln2 = new ListNode(3);

        ListNode l2 = new ListNode(5);
        ListNode ln4 = new ListNode(6);
        ListNode ln5 = new ListNode(4);
        l1.next = ln1;
        ln1.next = ln2;

        l2.next = ln4;
        ln4.next = ln5;
        ListNode ln = addTwoNumbers(l1, l2);
        System.out.println("");
    }

    private static boolean carry = false;

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int v = 0;
        if (l1 != null) {
            v = v + l1.val;
        }
        if (l2 != null) {
            v = v + l2.val;
        }
        if (carry) {
            v = v + 1;
        } else {
            if (l1 == null && l2 == null) {
                return null;
            }
        }
        if (v >= 10) {
            carry = true;
        } else {
            carry = false;
        }
        ListNode ln = new ListNode(v % 10);
        ln.next = addTwoNumbers(l1 != null ? l1.next : null, l2 != null ? l2.next : null);
        return ln;
    }
}
