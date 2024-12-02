package com.vimacodes.aoc.day3;

import lombok.NonNull;

public record Exercise() {

  public static Exercise parse(@NonNull String line) {
    //    String[] split = line.split("\\s+");
    //    var levels = Arrays.stream(split).map(String::trim).map(Integer::parseInt).toList();
    return new Exercise();
  }
}
