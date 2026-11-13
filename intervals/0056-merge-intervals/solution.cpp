#include <cstddef>
#include <iostream>
#include <iterator>
#include <vector>
#include <algorithm>

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
    std::vector<std::vector<int>> merge(std::vector<std::vector<int>>& intervals) {
        std::sort(intervals.begin(), intervals.end());


        auto last = intervals[0];
        std::vector<std::vector<int>> ret;
        for (size_t i=1; i<intervals.size(); i++) {
            auto cur = intervals[i];
            if (last[1] < cur[0]) {
                ret.push_back(last);
                last = cur;
            } else {
                last[1] = std::max(last[1], cur[1]);
            }
        }

        ret.push_back(last);
        return ret; 
    }
};

struct TestCase {
    std::vector<std::vector<int>> intervals;
    std::vector<std::vector<int>> expected;
};

int main() {
    std::vector<TestCase> tests = {
        {{{1, 3}, {2, 6}, {8, 10}, {15, 18}}, {{1, 6}, {8, 10}, {15, 18}}},
        {{{1, 4}, {4, 5}}, {{1, 5}}},
        {{{4, 7}, {1, 4}}, {{1, 7}}},
        {{{1, 4}, {2, 3}}, {{1, 4}}},
        {{{1, 4}}, {{1, 4}}}
    };

    Solution sol;
    int correct = 0;

    for (size_t i = 0; i < tests.size(); i++) {
        TestCase& t = tests[i];
        auto actual = sol.merge(t.intervals);
        bool passed = (actual == t.expected);
        correct += passed;

        std::cout << "Test " << i << ": " << (passed ? "PASS" : "FAIL") << "\n";
        std::cout << "\tExpected: " << t.expected << "\n";
        std::cout << "\tActual:   " << actual << "\n";
    }

    std::cout << "\n" << correct << " / " << tests.size() << " correct.\n";
    return 0;
}
