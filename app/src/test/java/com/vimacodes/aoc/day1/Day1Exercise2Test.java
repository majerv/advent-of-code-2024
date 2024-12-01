package com.vimacodes.aoc.day1;

import com.vimacodes.aoc.utils.Inputs;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class Day1Exercise2Test {
  private final Day1Exercise2 exercise = new Day1Exercise2();

  private static Stream<Arguments> testInputs() {
    return Stream.of(
        Arguments.of("day1_sample", 31), //
        Arguments.of("day1_input", 19457120) //
        );
  }

  @ParameterizedTest
  @MethodSource
  void testInputs(final String inputName, long expectedResult) {
    long result = exercise.solve(Inputs.resourceToString(inputName));
    Assertions.assertEquals(expectedResult, result);
  }
}
