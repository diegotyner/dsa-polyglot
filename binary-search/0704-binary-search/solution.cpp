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
    int solve(std::vector<int>& nums, int target) {
        int lo=0, hi=nums.size()-1;
      
        while (lo <= hi) {
            int mid = lo + (hi-lo)/2;
            int el = nums[mid];
            if (el == target) {
                return mid;
            } else if (el > target) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return -1;
    }
};

struct TestCase {
    std::vector<int> nums;
    int target;
    int expected;
};

int main() {
    std::vector<TestCase> tests = {
        {{-1,0,3,5,9,12}, 9, 4},
        {{-1,0,3,5,9,12}, 2, -1},
    };

    Solution sol;
    int correct = 0;

    for (size_t i = 0; i < tests.size(); i++) {
        TestCase& t = tests[i];
        auto actual = sol.solve(t.nums, t.target);
        bool passed = (actual == t.expected);
        correct += passed;

        std::cout << "Test " << i << ": " << (passed ? "PASS" : "FAIL") << "\n";
        std::cout << "\tExpected: " << t.expected << "\n";
        std::cout << "\tActual:   " << actual << "\n";
    }

    std::cout << "\n" << correct << " / " << tests.size() << " correct.\n";
    return 0;
}
