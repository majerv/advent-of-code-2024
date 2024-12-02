package com.vimacodes.aoc.day2;

class Day2Exercise2 {

  public long solve(final String text) {
    return text.lines().map(Report::parse).filter(Report::isSafeWithDampener).count();
  }
}
