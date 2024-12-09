package com.vimacodes.aoc.day9;

public class Day9Exercises {
  public static long firstStar(final String text) {
    return DiskMap.parse(text).checksum();
  }

  public static long secondStar(final String text) {
    return DiskMap.parse(text).checksumWhole();
  }
}
