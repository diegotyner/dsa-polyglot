import java.util.*;

class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode();
        ListNode cur = dummy;

        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                cur.next = list1;
                list1 = list1.next;
            } else {
                cur.next = list2;
                list2 = list2.next;
            }
            cur = cur.next;
        }
        if (list1 != null) {
            cur.next = list1;
        }
        if (list2 != null) {
            cur.next = list2;
        }
        return dummy.next;
    }

    record TestCase(List<Integer> list1, List<Integer> list2, List<Integer> expected) {
    }

    public static void main(String[] args) {
        Solution s = new Solution();

        List<TestCase> tests = List.of(
                new TestCase(List.of(1, 2, 4), List.of(1, 3, 4), List.of(1, 1, 2, 3, 4, 4)),
                new TestCase(List.of(), List.of(), List.of()),
                new TestCase(List.of(), List.of(0), List.of(0)));

        int correct = 0;
        for (int i = 0; i < tests.size(); i++) {
            TestCase t = tests.get(i);
            ListNode l1 = ListNode.build(t.list1());
            ListNode l2 = ListNode.build(t.list2());
            List<Integer> actual = ListNode.toList(s.mergeTwoLists(l1, l2));
            boolean passed = actual.equals(t.expected());
            correct += passed ? 1 : 0;

            System.out.printf("Test %d: %s%n", i, passed ? "PASS" : "FAIL");
            System.out.printf("\tExpected: %s%n", t.expected());
            System.out.printf("\tActual:   %s%n", actual);
        }

        System.out.printf("%n%d / %d correct.%n", correct, tests.size());
    }
}
