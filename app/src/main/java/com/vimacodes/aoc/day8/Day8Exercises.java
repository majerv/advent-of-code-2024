package com.vimacodes.aoc.day8;

public class Day8Exercises {
  public static long firstStar(final String text) {
    return City.parse(text).antinodes().size();
  }

  public static long secondStar(final String text) {
    return City.parse(text).antinodesInLine().size();
  }
}
