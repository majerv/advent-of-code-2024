package com.vimacodes.aoc;

import com.vimacodes.aoc.day1.Day1Exercises;
import com.vimacodes.aoc.day2.Day2Exercises;
import com.vimacodes.aoc.day3.Day3Exercises;
import com.vimacodes.aoc.day4.Day4Exercises;
import com.vimacodes.aoc.day5.Day5Exercises;
import com.vimacodes.aoc.day6.Day6Exercises;
import com.vimacodes.aoc.utils.Inputs;
import java.util.function.Function;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class DailyExercisesTest {

  private static Function<String, Long> solve(Function<String, Long> solver) {
    return solver;
  }

  private static Stream<Arguments> testInputs() {
    return Stream.of(
        // day1
        Arguments.of("day1_sample", solve(Day1Exercises::firstStar), 11),
        Arguments.of("day1_inputs", solve(Day1Exercises::firstStar), 2264607),
        Arguments.of("day1_sample", solve(Day1Exercises::secondStar), 31),
        Arguments.of("day1_inputs", solve(Day1Exercises::secondStar), 19457120),
        // day2
        Arguments.of("day2_sample", solve(Day2Exercises::firstStar), 2),
        Arguments.of("day2_inputs", solve(Day2Exercises::firstStar), 236),
        Arguments.of("day2_sample", solve(Day2Exercises::secondStar), 4),
        Arguments.of("day2_inputs", solve(Day2Exercises::secondStar), 308),
        // day3
        Arguments.of("day3_sample", solve(Day3Exercises::firstStar), 161),
        Arguments.of("day3_inputs", solve(Day3Exercises::firstStar), 166905464),
        Arguments.of("day3_sample", solve(Day3Exercises::secondStar), 48),
        Arguments.of("day3_inputs", solve(Day3Exercises::secondStar), 72948684),
        // day4
        Arguments.of("day4_sample", solve(Day4Exercises::firstStar), 18),
        Arguments.of("day4_inputs", solve(Day4Exercises::firstStar), 2336),
        Arguments.of("day4_sample", solve(Day4Exercises::secondStar), 9),
        Arguments.of("day4_inputs", solve(Day4Exercises::secondStar), 1831),
        // day5
        Arguments.of("day5_sample", solve(Day5Exercises::firstStar), 143),
        Arguments.of("day5_inputs", solve(Day5Exercises::firstStar), 5248),
        Arguments.of("day5_sample", solve(Day5Exercises::secondStar), 123),
        Arguments.of("day5_inputs", solve(Day5Exercises::secondStar), 4507));
  }

  private static Stream<Arguments> currentDay() {
    return Stream.of(
        Arguments.of("day6_sample", solve(Day6Exercises::firstStar), 41),
        Arguments.of("day6_inputs", solve(Day6Exercises::firstStar), 4515),
        Arguments.of("day6_sample", solve(Day6Exercises::secondStar), 6),
        Arguments.of("day6_inputs", solve(Day6Exercises::secondStar), 0));
  }

  @ParameterizedTest(name = "[{index}] {0} {2}")
  @MethodSource({"com.vimacodes.aoc.DailyExercisesTest#currentDay"})
  //  @MethodSource
  void testInputs(
      final String inputName, final Function<String, Long> solver, final long expectedResult) {
    long result = solver.apply(Inputs.resourceToString(inputName));
    Assertions.assertEquals(expectedResult, result);
  }
}
