package com.fleet.alg;


/**
 * @author April Han
 */
public class ListNodeTest {

    public static void main(String[] args) {
        ListNode head = new ListNode(0);
        ListNode first = new ListNode(1);
        ListNode second = new ListNode(2);
        ListNode third = new ListNode(3);
        head.next = first;
        first.next = second;
        second.next = third;
        print(head);
    }

    public static void print(ListNode ln) {
        System.out.println("head 节点值" + ln.val);
        System.out.println("first 节点值" + ln.next.val);
        System.out.println("second 节点值" + ln.next.next.val);
        System.out.println("third 节点值" + ln.next.next.next.val);
    }
}
