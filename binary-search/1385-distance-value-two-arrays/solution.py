from typing import List
from dataclasses import dataclass

class Solution:
    def solve(self, arr1, arr2, d):
        def bst(target, arr, d):
            n = len(arr)
            lo, hi = 0, n-1
            while lo <= hi:
                m = lo+(hi-lo)//2
                el = arr[m]
                if abs(target-el) <= d:
                    return False

                # cant be equal so we cool
                if el > target: # el larger, make high lower
                    hi = m - 1
                else:
                    lo = m + 1
            return True

        arr2 = sorted(arr2)
        distanced = 0
        for num in arr1:
            if bst(num, arr2, d):
                distanced += 1
        return distanced


@dataclass
class TestCase:
    arr1: List[int]
    arr2: List[int]
    d: int
    expected: int


if __name__ == "__main__":
    s = Solution()

    tests = [
        TestCase([4,5,8], [10,9,1,8], 2, 2),
        TestCase([1,4,2,3], [-4,-3,6,10,20,30], 3, 2),
        TestCase([2,1,100,3], [-5,-2,10,-3,7], 6, 1),
    ]

    correct = 0
    for i, t in enumerate(tests):
        actual = s.solve(t.arr1, t.arr2, t.d)
        passed = actual == t.expected
        correct += passed

        print(f"Test {i}: {'PASS' if passed else 'FAIL'}")
        print(f"\tExpected: {t.expected}")
        print(f"\tActual:   {actual}")

    print(f"\n{correct} / {len(tests)} correct.")
