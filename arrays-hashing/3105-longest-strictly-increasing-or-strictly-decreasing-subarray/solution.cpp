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
        int longest=1, cur=1;
        bool increasing = true;


        for (int i=1; i<nums.size(); i++) {
            if (nums[i] == nums[i-1]) {
                cur = 1;
                continue;
            }

            if (nums[i] > nums[i-1] == increasing) {
                cur++;
            } else {
                cur = 2;
                increasing = !increasing;
            }

            longest = std::max(longest, cur);
        }

        return longest;
    }
};

struct TestCase {
    std::vector<int> nums;
    int expected;
};

int main() {
    std::vector<TestCase> tests = {
        {{1, 4, 3, 3, 2}, 2},
        {{3, 3, 3, 3}, 1},
        {{3, 2, 1}, 3}
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
