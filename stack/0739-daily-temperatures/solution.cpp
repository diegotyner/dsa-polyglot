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
    std::vector<int> solve(std::vector<int>& nums) {
      std::vector<int> ret(nums.size());
      std::vector<int> stack;

      for (size_t i=0;i<nums.size();i++) {
        int tmp = nums[i];
        while (!stack.empty()) {
          int old_idx = stack.back();
          if (tmp <= nums[old_idx])
            break;
          ret[old_idx] = i-old_idx;
          stack.pop_back();
        }
        stack.push_back(i);
      }
      return ret;
    }
};

struct TestCase {
    std::vector<int> nums;
    std::vector<int> expected;
};

int main() {
    std::vector<TestCase> tests = {
        {{73,74,75,71,69,72,76,73}, {1,1,4,2,1,1,0,0}},
        {{30,40,50,60}, {1,1,1,0}},
        {{30,60,90}, {1,1,0}}
    };

    Solution sol;
    int correct = 0;

    for (size_t i = 0; i < tests.size(); i++) {
        TestCase& t = tests[i];
        auto actual = sol.solve(t.nums);
        bool passed = (actual == t.expected);
        correct += passed;

        std::cout << "Test " << i << ": " << (passed ? "PASS" : "FAIL") << "\n";
        std::cout << "\tExpected: " << t.expected << "\n";
        std::cout << "\tActual:   " << actual << "\n";
    }

    std::cout << "\n" << correct << " / " << tests.size() << " correct.\n";
    return 0;
}
