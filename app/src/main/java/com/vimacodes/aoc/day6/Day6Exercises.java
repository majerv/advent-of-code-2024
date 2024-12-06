package com.vimacodes.aoc.day6;

public class Day6Exercises {
  public static long firstStar(final String text) {
    return Laboratory.parse(text).walk();
  }

  public static long secondStar(final String text) {
    return Laboratory.parse(text).possibleLoops();
  }
}
