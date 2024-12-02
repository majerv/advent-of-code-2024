package com.vimacodes.aoc.day3;

public class Day3Exercises {
  public static long firstStar(final String text) {
    return text.lines().map(Exercise::parse).count();
  }

  public static long secondStar(final String text) {
    return text.lines().map(Exercise::parse).count();
  }
}
