package com.fleet.alg;

/**
 * 输入一个链表，输出该链表中倒数第k个节点。为了符合大多数人的习惯，本题从1开始计数，即链表的尾节点是倒数第1个节点。例如，一个链表有6个节点，从头节点开始，它们的值依次是1、2、3、4、5、6。这个链表的倒数第3个节点是值为4的节点。
 * 示例：
 * 给定一个链表: 1->2->3->4->5, 和 k = 2.
 * 返回链表 4->5.
 */
public class KthFromEnd {

    public static void main(String[] args) {
        ListNode head = new ListNode(0);
        ListNode ln1 = new ListNode(1);
        ListNode ln2 = new ListNode(2);
        ListNode ln3 = new ListNode(3);
        ListNode ln4 = new ListNode(4);
        ListNode ln5 = new ListNode(5);
        ListNode ln6 = new ListNode(6);
        head.next = ln1;
        ln1.next = ln2;
        ln2.next = ln3;
        ln3.next = ln4;
        ln4.next = ln5;
        ln5.next = ln6;
        System.out.println(getKthFromEnd(head, 2));
    }

    public static ListNode getKthFromEnd(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        if (k < 1) {
            return null;
        }
        int len = 1;
        ListNode ln = head;
        while (ln.next != null) {
            ln = ln.next;
            len++;
        }
        if (k > len) {
            return null;
        }
        for (int i = 0; i < len - k; i++) {
            head = head.next;
        }
        return head;
    }
}
