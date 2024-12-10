package com.vimacodes.aoc.day10;

public class Day10Exercises {
  public static long firstStar(final String text) {
    TopographicMap map = TopographicMap.parse(text);
    return map.trailHeads().map(map::stats).mapToLong(TrailStats::getScore).sum();
  }

  public static long secondStar(final String text) {
    TopographicMap map = TopographicMap.parse(text);
    return map.trailHeads().map(map::stats).mapToLong(TrailStats::getRank).sum();
  }
}
