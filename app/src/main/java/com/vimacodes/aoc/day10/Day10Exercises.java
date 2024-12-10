package com.vimacodes.aoc.day10;

import com.vimacodes.aoc.day10.TopographicMap.TrailStats;

public class Day10Exercises {
  public static long firstStar(final String text) {
    TopographicMap map = TopographicMap.parse(text);
    return map.trailHeads().map(map::stats).mapToLong(TrailStats::score).sum();
  }

  public static long secondStar(final String text) {
    TopographicMap map = TopographicMap.parse(text);
    return map.trailHeads().map(map::stats).mapToLong(TrailStats::rank).sum();
  }
}
