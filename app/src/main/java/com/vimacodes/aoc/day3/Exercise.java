package com.vimacodes.aoc.day3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.NonNull;

public record Exercise() {

  public static Exercise parse(@NonNull String line) {
    //    String[] split = line.split("\\s+");
    //    var levels = Arrays.stream(split).map(String::trim).map(Integer::parseInt).toList();
    return new Exercise();
  }
}
