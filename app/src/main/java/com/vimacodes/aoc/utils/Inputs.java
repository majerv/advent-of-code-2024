package com.vimacodes.aoc.utils;

import com.google.common.io.Resources;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import org.javatuples.Pair;

public class Inputs {
  private Inputs() {}

  /** Loads the content of a given resource on the classpath. */
  public static String resourceToString(String resourceName) {
    URL url = Resources.getResource(resourceName);

    try {
      return Resources.toString(url, StandardCharsets.UTF_8);
    } catch (IOException e) {
      throw new RuntimeException("Could not open file: " + resourceName, e);
    }
  }

  /** Parses two longs in a line with any whitespace around them to a long pair. */
  public static Pair<Long, Long> parseToLongTuple(String line) {
    String[] split = line.split("\\s+");
    return Pair.with(Long.parseLong(split[0].trim()), Long.parseLong(split[1].trim()));
  }
}
