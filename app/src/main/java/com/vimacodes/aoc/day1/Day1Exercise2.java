package com.vimacodes.aoc.day1;

class Day1Exercise2 {

  public long solve(final String text) {
    Similarities sim = Similarities.parse(text);
    return sim.score();
  }
}
