package com.vimacodes.aoc.day2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.NonNull;

public record Report(List<Integer> levels) {

  public static Report parse(@NonNull String line) {
    String[] split = line.split("\\s+");
    var levels = Arrays.stream(split).map(String::trim).map(Integer::parseInt).toList();
    return new Report(levels);
  }

  public boolean isSafe(List<Integer> levels) {
    int sig = 0;
    int diff;

    System.out.printf("  Checking %s\n", levels);

    for (int i = 1; i < levels.size(); i++) {
      int prev = levels.get(i - 1);
      int next = levels.get(i);

      diff = Math.abs(next - prev);
      if (diff < 1 || diff > 3) {
        return false;
      }

      int newSig = (int) Math.signum(next - prev);
      if (sig != 0 && newSig != sig) {
        return false;
      } else {
        sig = newSig;
      }
    }

    return true;
  }

  public boolean isSafe() {
    return isSafe(levels);
  }

  public boolean isSafeWithDampener() {
    int sig = 0;
    int diff;
    int errors = 0;

    for (int i = 1; i < levels.size(); i++) {
      int prev = levels.get(i - 1);
      int next = levels.get(i);

      diff = Math.abs(next - prev);
      if (diff < 1 || diff > 3) {
        if (errors == 0) {
          System.out.printf("Problem with %s at i=%s, prev=%s, next=%s\n", levels, i, prev, next);
          return isSafeAny(levels);
        } else {
          return false;
        }
      }

      int newSig = (int) Math.signum(next - prev);
      if (sig != 0 && newSig != sig) {
        if (errors == 0) {
          System.out.printf("Problem with %s at i=%s, prev=%s, next=%s\n", levels, i, prev, next);
          return isSafeAny(levels);
        } else {
          return false;
        }
      } else {
        sig = newSig;
      }
    }
    return true;
  }

  private boolean isSafeAny(List<Integer> list) {
    for (int i = 0; i < levels.size(); i++) {
      if (isSafe(newList(list, i))) return true;
    }
    return false;
  }

  public List<Integer> newList(List<Integer> list, int exceptIndex) {
    if (exceptIndex == -1) return list;

    var copy = new ArrayList<>(list);
    copy.remove(exceptIndex);
    return copy;
  }
}
