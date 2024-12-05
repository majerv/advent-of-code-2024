package com.vimacodes.aoc.day5;

public class Day5Exercises {
  public static long firstStar(final String text) {
    return Printer.parse(text).updates().stream()
        .filter(Update::isInRightOrder)
        .mapToLong(Update::middle)
        .sum();
  }

  public static long secondStar(final String text) {
    return Printer.parse(text).updates().stream()
        .filter(u -> !u.sort())
        .mapToLong(Update::middle)
        .sum();
  }
}
