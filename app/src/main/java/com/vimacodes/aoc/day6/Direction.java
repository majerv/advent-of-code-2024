package com.vimacodes.aoc.day6;


public record Direction(int i, int j) {

  public static Direction UP = new Direction(-1, 0);
  public static Direction RIGHT = new Direction(0, 1);
  public static Direction DOWN = new Direction(1, 0);
  public static Direction LEFT = new Direction(0, -1);

  Direction turnRight() {
    if (this.equals(UP)) return RIGHT;
    if (this.equals(RIGHT)) return DOWN;
    if (this.equals(DOWN)) return LEFT;
    if (this.equals(LEFT)) return UP;

    throw new IllegalArgumentException("No such turn");
  }
}
