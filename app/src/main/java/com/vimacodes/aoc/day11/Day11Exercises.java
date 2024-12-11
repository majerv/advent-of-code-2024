package com.vimacodes.aoc.day11;

public class Day11Exercises {
  public static long firstStar(final String text) {
    return Stones.parse(text).blink(25).num();
  }

  public static long secondStar(final String text) {
    return Stones.parse(text).blinkRec(75);
  }
}
