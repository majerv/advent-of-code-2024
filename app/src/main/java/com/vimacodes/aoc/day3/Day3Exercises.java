package com.vimacodes.aoc.day3;

public class Day3Exercises {
  public static long firstStar(final String text) {
    return Computer.parse(text).multiplications().stream().mapToLong(Mul::result).sum();
  }

  public static long secondStar(final String text) {
    return Computer.parseEnabled(text).multiplications().stream().mapToLong(Mul::result).sum();
  }
}
