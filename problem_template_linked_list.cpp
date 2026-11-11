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
    ListNode* solve(ListNode* head) {
      return nullptr; // TODO
    }
};

struct TestCase {
    std::vector<int> input;
    std::vector<int> expected;
};

int main() {
    std::vector<TestCase> tests = {
        {{1, 2, 3, 4, 5}, {1, 2, 3, 4, 5}},
        {{1, 2}, {1, 2}},
        {{}, {}}
    };

    Solution sol;
    int correct = 0;

    for (size_t i = 0; i < tests.size(); i++) {
        TestCase& t = tests[i];
        ListNode* head = buildList(t.input);
        std::vector<int> actual = toVector(sol.solve(head));
        bool passed = (actual == t.expected);
        correct += passed;

        std::cout << "Test " << i << ": " << (passed ? "PASS" : "FAIL") << "\n";
        std::cout << "\tExpected: " << t.expected << "\n";
        std::cout << "\tActual:   " << actual << "\n";
    }

    std::cout << "\n" << correct << " / " << tests.size() << " correct.\n";
    return 0;
}
