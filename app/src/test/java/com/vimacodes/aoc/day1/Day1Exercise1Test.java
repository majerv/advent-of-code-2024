package com.vimacodes.aoc.day1;

import com.vimacodes.aoc.utils.Inputs;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class Day1Exercise1Test {
  private final Day1Exercise1 exercise = new Day1Exercise1();

  private static Stream<Arguments> testInputs() {
    return Stream.of(
        Arguments.of("day1_sample", 11), //
        Arguments.of("day1_input", 2264607) //
        );
  }

  @ParameterizedTest
  @MethodSource
  void testInputs(final String inputName, long expectedResult) {
    long result = exercise.solve(Inputs.resourceToString(inputName));
    Assertions.assertEquals(expectedResult, result);
  }
}
