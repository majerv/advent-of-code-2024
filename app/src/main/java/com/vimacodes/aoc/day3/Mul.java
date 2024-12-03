package com.vimacodes.aoc.day3;

public record Mul(long op1, long op2) {

  public static Mul parse(String operation) {
    var split = operation.replace("mul(", "").replace(")", "").split(",");

    long op1 = Long.parseLong(split[0].trim());
    long op2 = Long.parseLong(split[1].trim());

    return new Mul(op1, op2);
  }

  public long result() {
    return op1 * op2;
  }
}
