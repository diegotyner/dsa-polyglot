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
    int solve(std::vector<int>& nums) {
        // our heuristic is:
        // are we in a rotated section? 
        // YES is defined by right>left
            // If yes, search above
            // If no, search below

        int lo=0, hi=nums.size()-1;

        while (lo < hi) {
            int mid = lo + (hi-lo) / 2;
            bool rotated = nums[mid] > nums[hi];
            if (rotated) {
                lo = mid + 1;
            } else {
                hi = mid;
            }

        }

        return nums[lo];
    }
};

struct TestCase {
    std::vector<int> nums;
    int expected;
};

int main() {
    std::vector<TestCase> tests = {
        {{3,4,5,1,2}, 1},
        {{4,5,6,7,0,1,2}, 0},
        {{11,13,15,17}, 11}
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
