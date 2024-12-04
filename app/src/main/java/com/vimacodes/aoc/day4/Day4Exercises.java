package com.vimacodes.aoc.day4;

public class Day4Exercises {
  public static long firstStar(final String text) {
    return Puzzle.parse(text).count("XMAS");
  }

  public static long secondStar(final String text) {
    return Puzzle.parse(text).countCrossXmas();
  }
}
