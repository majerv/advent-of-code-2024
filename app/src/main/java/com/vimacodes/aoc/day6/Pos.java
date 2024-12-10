package com.vimacodes.aoc.day6;

public record Pos(int i, int j) {

  Pos move(Direction dir) {
    return new Pos(i + dir.i(), j + dir.j());
  }

  public Pos up() {
    return move(Direction.UP);
  }

  public Pos right() {
    return move(Direction.RIGHT);
  }

  public Pos down() {
    return move(Direction.DOWN);
  }

  public Pos left() {
    return move(Direction.LEFT);
  }
}
