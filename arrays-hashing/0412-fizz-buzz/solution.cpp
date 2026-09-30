#include <iostream>
#include <string>
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
    std::vector<std::string> solve(int nums) {
      std::vector<std::string> ret(nums);

      for (int i=1; i<nums+1; i++) {
        bool div3 = (i%3==0);
        bool div5 = (i%5==0);
        int idx = i-1;
        if (div3 && div5) {
          ret[idx] = "FizzBuzz";
        } else if (div3) {
          ret[idx] = "Fizz";
        } else if (div5) {
          ret[idx] = "Buzz";
        } else {
          ret[idx] = std::to_string(i);
        }
      }
      return ret;
    }
};

struct TestCase {
    int nums;
    std::vector<std::string> expected;
};

int main() {
    std::vector<TestCase> tests = {
        {3, {"1","2","Fizz"}},
        {5, {"1","2","Fizz","4","Buzz"}},
        {15, {"1","2","Fizz","4","Buzz","Fizz","7","8","Fizz","Buzz","11","Fizz","13","14","FizzBuzz"}}
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
