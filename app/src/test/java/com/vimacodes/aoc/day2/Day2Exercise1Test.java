package com.vimacodes.aoc.day2;

import com.vimacodes.aoc.utils.Inputs;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class Day2Exercise1Test {
  private final Day2Exercise1 exercise = new Day2Exercise1();

  private static Stream<Arguments> testInputs() {
    return Stream.of(
        Arguments.of("day2_sample", 2), //
        Arguments.of("day2_input", 236) //
        );
  }

  @ParameterizedTest
  @MethodSource
  void testInputs(final String inputName, long expectedResult) {
    long result = exercise.solve(Inputs.resourceToString(inputName));
    Assertions.assertEquals(expectedResult, result);
  }
}
