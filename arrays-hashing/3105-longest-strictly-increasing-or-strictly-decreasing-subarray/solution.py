from typing import List, Optional, Tuple, Dict, Set
from collections import defaultdict, deque, Counter, OrderedDict
from dataclasses import dataclass
import heapq
import bisect
import functools
import itertools
import math
import sys


class Solution:
    def solve(self, nums: List[int]) -> int:
        longest = 1
        cur = 1
        increasing = True

        for i in range(1, len(nums)):
            if nums[i] == nums[i - 1]:
                cur = 1
                continue

            if (nums[i] > nums[i - 1]) == increasing:
                cur += 1
            else:
                cur = 2
                increasing = not increasing

            longest = max(longest, cur)

        return longest


@dataclass
class TestCase:
    nums: List[int]
    expected: int


if __name__ == "__main__":
    s = Solution()

    tests = [
        TestCase([1, 4, 3, 3, 2], 2),
        TestCase([3, 3, 3, 3], 1),
        TestCase([3, 2, 1], 3),
    ]

    correct = 0
    for i, t in enumerate(tests):
        actual = s.solve(t.nums)
        passed = actual == t.expected
        correct += passed

        print(f"Test {i}: {'PASS' if passed else 'FAIL'}")
        print(f"\tExpected: {t.expected}")
        print(f"\tActual:   {actual}")

    print(f"\n{correct} / {len(tests)} correct.")
