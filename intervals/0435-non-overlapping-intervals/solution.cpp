#include <iostream>
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
    int eraseOverlapIntervals(std::vector<std::vector<int>>& intervals) {
      if (intervals.size() <= 0) {
        return 0;
      }

      // passing by reference (using const auto&) MASSIVELY speeds up code over passing vectors around
      // passing by reference removes internal heap allocations / copies made when creating copies of each vec.
      std::sort(intervals.begin(), intervals.end(), [](const std::vector<int>& a, const std::vector<int>& b) {
        return a[1]<b[1];
      });
      int conflicts = 0;
      int prev_end = intervals[0][1];

      for (size_t i=1; i<intervals.size(); i++) {
        if (intervals[i][0] < prev_end) {
          conflicts++;
        } else {
          prev_end = intervals[i][1];
        }
      }

      return conflicts;
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
