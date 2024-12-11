package com.vimacodes.aoc.day11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.javatuples.Pair;

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
    for (int i = 0; i < times; i++) {
      this.stones = blinkAt(stones);
    }

    return this;
  }

  public long blinkRec(int times) {
    var cache = new HashMap<Pair<Long, Integer>, Long>();
    long count = 0;

    for (Long stone : stones) {
      count += blink(stone, times, cache);
    }

    return count;
  }

  private long blink(long stone, int times, Map<Pair<Long, Integer>, Long> cache) {
    if (times == 0) return 1;

    var key = new Pair<>(stone, times);
    if (cache.containsKey(key)) return cache.get(key);

    long result;
    String numStr = String.valueOf(stone);

    if (stone == 0) {
      result = blink(1, times - 1, cache);
    } else if (numStr.length() % 2 == 0) {
      int half = numStr.length() / 2;
      result =
          blink(Long.parseLong(numStr.substring(0, half)), times - 1, cache)
              + blink(Long.parseLong(numStr.substring(half)), times - 1, cache);
    } else {
      result = blink(stone * 2024, times - 1, cache);
    }

    cache.put(key, result);
    return result;
  }

  public long num() {
    return stones.size();
  }
}
