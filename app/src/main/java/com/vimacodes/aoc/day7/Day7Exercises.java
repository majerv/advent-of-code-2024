package com.vimacodes.aoc.day7;

public class Day7Exercises {
  public static long firstStar(final String text) {
    return text.lines()
        .map(Equation::parse)
        .filter(Equation::solvable)
        .mapToLong(Equation::result)
        .sum();
  }

  public static long secondStar(final String text) {
    return text.lines()
        .map(Equation::parse)
        .filter(Equation::solvableWithConcat)
        .mapToLong(Equation::result)
        .sum();
  }
}
