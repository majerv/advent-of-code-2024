package com.vimacodes.aoc.day6;

public record Pos(int i, int j) {

  Pos move(Direction dir) {
    return new Pos(i + dir.i(), j + dir.j());
  }
}
