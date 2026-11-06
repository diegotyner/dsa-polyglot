#include <iostream>
#include <vector>
#include <algorithm>
#include <cmath>

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
    bool bst(int target, std::vector<int>& arr2, int d) {
      int lo = 0, hi = arr2.size();
      while (lo <= hi) {
        int mid = lo + (hi-lo) / 2;
        int el = arr2[mid];
        if (std::abs(target -el) <= d) {
          return false;
        }
        if (el > target) {
          hi = mid - 1;
        } else {
          lo = mid + 1;
        }
      }
      return true;
    }
    int solve(std::vector<int>& arr1, std::vector<int>& arr2, int d) {
      std::sort(arr2.begin(), arr2.end());

      int valid = 0;
      for (auto num : arr1) {
        if (bst(num, arr2, d)) {
          valid++;
        }
      }

      return valid; // TODO
    }
};

struct TestCase {
    std::vector<int> arr1;
    std::vector<int> arr2;
    int d;
    int expected;
};

int main() {
    std::vector<TestCase> tests = {
        {{4,5,8}, {10,9,1,8}, 2, 2},
        {{1,4,2,3}, {-4,-3,6,10,20,30}, 3, 2},
        {{2,1,100,3}, {-5,-2,10,-3,7}, 6, 1}
    };

    Solution sol;
    int correct = 0;

    for (size_t i = 0; i < tests.size(); i++) {
        TestCase& t = tests[i];
        auto actual = sol.solve(t.arr1, t.arr2, t.d);
        bool passed = (actual == t.expected);
        correct += passed;

        std::cout << "Test " << i << ": " << (passed ? "PASS" : "FAIL") << "\n";
        std::cout << "\tExpected: " << t.expected << "\n";
        std::cout << "\tActual:   " << actual << "\n";
    }

    std::cout << "\n" << correct << " / " << tests.size() << " correct.\n";
    return 0;
}
