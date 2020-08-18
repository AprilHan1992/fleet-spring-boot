package com.fleet.alg;

public class ReverseListNode {

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
        System.out.println(reverseList(head));
    }

    public static ListNode reverseList(ListNode cur) {
        // 新的链表
        ListNode nln = null;
        while (cur != null) {
            ListNode ln = new ListNode(cur.val);
            ln.next = nln;
            nln = ln;
            cur = cur.next;
        }
        return nln;
    }
}
