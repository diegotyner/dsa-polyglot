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
    std::vector<std::vector<int>> insert(std::vector<std::vector<int>>& intervals, std::vector<int>& newInterval) {
        std::vector<std::vector<int>> ret;

        size_t i=0;
        // strictly before
        while (i < intervals.size() && intervals[i][1] < newInterval[0]) {
            ret.push_back(intervals[i]);
            i++;
        }
        
        // in the middle
        int start = newInterval[0];
        int end = newInterval[1];
        while (i < intervals.size() && intervals[i][0] <= newInterval[1]) {
            start = std::min(intervals[i][0], start);
            end = std::max(intervals[i][1], end);
            i++;
        }
        ret.push_back(std::vector<int>{start, end});

        while (i < intervals.size()) {
            ret.push_back(intervals[i]);
            i++;
        }
        return ret;
    }
};

struct TestCase {
    std::vector<std::vector<int>> intervals;
    std::vector<int> newInterval;
    std::vector<std::vector<int>> expected;
};

int main() {
    std::vector<TestCase> tests = {
        {{{1, 3}, {6, 9}}, {2, 5}, {{1, 5}, {6, 9}}},
        {{{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}}, {4, 8}, {{1, 2}, {3, 10}, {12, 16}}}
    };

    Solution sol;
    int correct = 0;

    for (size_t i = 0; i < tests.size(); i++) {
        TestCase& t = tests[i];
        auto actual = sol.insert(t.intervals, t.newInterval);
        bool passed = (actual == t.expected);
        correct += passed;

        std::cout << "Test " << i << ": " << (passed ? "PASS" : "FAIL") << "\n";
        std::cout << "\tExpected: " << t.expected << "\n";
        std::cout << "\tActual:   " << actual << "\n";
    }

    std::cout << "\n" << correct << " / " << tests.size() << " correct.\n";
    return 0;
}
