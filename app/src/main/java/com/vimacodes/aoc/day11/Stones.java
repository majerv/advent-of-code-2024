package com.vimacodes.aoc.day11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Stones {

  private List<Long> stones;

  public Stones(List<Long> stones) {
    this.stones = stones;
  }

  public static Stones parse(String line) {
    String[] split = line.split("\\s+");
    List<Long> stones = Arrays.stream(split).map(String::trim).map(Long::parseLong).toList();
    return new Stones(stones);
  }

  private static List<Long> blinkAt(List<Long> stones) {
    var newStones = new ArrayList<Long>();

    for (long stone : stones) {
      if (stone == 0) {
        newStones.add(1L);
      } else {
        String numStr = String.valueOf(stone);
        if (numStr.length() % 2 == 0) {
          int half = numStr.length() / 2;
          newStones.add(Long.parseLong(numStr.substring(0, half)));
          newStones.add(Long.parseLong(numStr.substring(half)));
        } else {
          newStones.add(stone * 2024);
        }
      }
    }

    return newStones;
  }

  public Stones blink(int times) {
    //    System.out.println(stones);

    for (int i = 0; i < times; i++) {
      this.stones = blinkAt(stones);
      System.out.printf("blink %s: %s\n", i, stones.size());
    }

    return this;
  }

  public long num() {
    return stones.size();
  }
}
