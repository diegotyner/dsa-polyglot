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
    def eraseOverlapIntervals(self, intervals: List[List[int]]) -> int:
      pass


@dataclass
class TestCase:
    intervals: List[List[int]]
    expected: int


if __name__ == "__main__":
    s = Solution()

    tests = [
        TestCase([[1, 2], [2, 3], [3, 4], [1, 3]], 1),
        TestCase([[1, 2], [1, 2], [1, 2]], 2),
        TestCase([[1, 2], [2, 3]], 0),
    ]

    correct = 0
    for i, t in enumerate(tests):
        actual = s.eraseOverlapIntervals(t.intervals)
        passed = actual == t.expected
        correct += passed

        print(f"Test {i}: {'PASS' if passed else 'FAIL'}")
        print(f"\tExpected: {t.expected}")
        print(f"\tActual:   {actual}")

    print(f"\n{correct} / {len(tests)} correct.")
