package com.vimacodes.aoc.day1;


public class Day1Exercises {
  public static long firstStar(final String text) {
    Locations loc = Locations.parse(text);
    return loc.totalDifferences();
  }

  public static long secondStar(final String text) {
    Similarities sim = Similarities.parse(text);
    return sim.score();
  }
}
