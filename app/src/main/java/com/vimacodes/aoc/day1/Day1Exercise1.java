package com.vimacodes.aoc.day1;

class Day1Exercise1 {
  public long solve(final String text) {
    Locations loc = Locations.parse(text);
    return loc.totalDifferences();
  }
}
