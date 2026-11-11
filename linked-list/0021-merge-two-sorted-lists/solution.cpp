#include <iostream>
#include <vector>
#include "linked_list.hpp"

template <typename T>
std::ostream& operator<<(std::ostream& os, const std::vector<T>& v) {
    os << "[";
    for (size_t i = 0; i < v.size(); i++) {
        os << v[i];
        if (i + 1 < v.size()) os << ", ";
    }
    os << "]";
    return os;
}

class Solution {
public:
    ListNode* mergeTwoLists(ListNode* list1, ListNode* list2) {
        ListNode dummy(0);
        ListNode* cur = &dummy;
        
        while (list1 != nullptr && list2 != nullptr) {
            if (list1->val < list2-> val) {
                cur->next = list1;
                list1 = list1->next;
            } else {
                cur->next = list2;
                list2 = list2->next;
            }
            cur = cur->next;
        }
        if (list1 != nullptr) {
            cur->next = list1;
        }
        if (list2 != nullptr) {
            cur->next = list2;
        }
        return dummy.next;
    }
};

struct TestCase {
    std::vector<int> list1;
    std::vector<int> list2;
    std::vector<int> expected;
};

int main() {
    std::vector<TestCase> tests = {
        {{1, 2, 4}, {1, 3, 4}, {1, 1, 2, 3, 4, 4}},
        {{}, {}, {}},
        {{}, {0}, {0}}
    };

    Solution sol;
    int correct = 0;

    for (size_t i = 0; i < tests.size(); i++) {
        TestCase& t = tests[i];
        ListNode* l1 = buildList(t.list1);
        ListNode* l2 = buildList(t.list2);
        std::vector<int> actual = toVector(sol.mergeTwoLists(l1, l2));
        bool passed = (actual == t.expected);
        correct += passed;

        std::cout << "Test " << i << ": " << (passed ? "PASS" : "FAIL") << "\n";
        std::cout << "\tExpected: " << t.expected << "\n";
        std::cout << "\tActual:   " << actual << "\n";
    }

    std::cout << "\n" << correct << " / " << tests.size() << " correct.\n";
    return 0;
}
