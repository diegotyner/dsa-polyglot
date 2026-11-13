#include <iostream>
#include <vector>

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
    int eraseOverlapIntervals(std::vector<std::vector<int>>& intervals) {
      return -1; // TODO
    }
};

struct TestCase {
    std::vector<std::vector<int>> intervals;
    int expected;
};

int main() {
    std::vector<TestCase> tests = {
        {{{1, 2}, {2, 3}, {3, 4}, {1, 3}}, 1},
        {{{1, 2}, {1, 2}, {1, 2}}, 2},
        {{{1, 2}, {2, 3}}, 0}
    };

    Solution sol;
    int correct = 0;

    for (size_t i = 0; i < tests.size(); i++) {
        TestCase& t = tests[i];
        auto actual = sol.eraseOverlapIntervals(t.intervals);
        bool passed = (actual == t.expected);
        correct += passed;

        std::cout << "Test " << i << ": " << (passed ? "PASS" : "FAIL") << "\n";
        std::cout << "\tExpected: " << t.expected << "\n";
        std::cout << "\tActual:   " << actual << "\n";
    }

    std::cout << "\n" << correct << " / " << tests.size() << " correct.\n";
    return 0;
}
