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

  private static boolean isSafe(List<Integer> levels, boolean allowSingleError) {
    int sig = 0;
    int diff;

    for (int i = 1; i < levels.size(); i++) {
      int prev = levels.get(i - 1);
      int next = levels.get(i);

      diff = Math.abs(next - prev);
      if (diff < 1 || diff > 3) {
        return allowSingleError && isSafeAny(levels, i);
      }

      int newSig = (int) Math.signum(next - prev);
      if (sig != 0 && newSig != sig) {
        return allowSingleError && isSafeAny(levels, i);
      } else {
        sig = newSig;
      }
    }

    return true;
  }

  private static boolean isSafeAny(List<Integer> levels, int problemAt) {
    int from = Math.max(0, problemAt - 2);
    int to = Math.min(problemAt, levels.size() - 1);

    for (int i = from; i <= to; i++) {
      if (isSafe(elementsExcept(levels, i), false)) return true;
    }

    return false;
  }

  private static List<Integer> elementsExcept(List<Integer> list, int exceptIndex) {
    var copy = new ArrayList<>(list);
    copy.remove(exceptIndex);
    return copy;
  }

  public boolean isSafe() {
    return isSafe(levels, false);
  }

  public boolean isSafeWithSingleError() {
    return isSafe(levels, true);
  }
}
