package com.vimacodes.aoc.day2;


public class Day2Exercises {
  public static long firstStar(final String text) {
    return text.lines().map(Report::parse).filter(Report::isSafe).count();
  }

  public static long secondStar(final String text) {
    return text.lines().map(Report::parse).filter(Report::isSafeWithSingleError).count();
  }
}
