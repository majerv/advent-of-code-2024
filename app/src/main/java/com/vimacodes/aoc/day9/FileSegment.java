package com.vimacodes.aoc.day9;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class FileSegment {

  private final int capacity;
  private final int[] blocks;

  int size; // i.e. pointer

  public FileSegment(int capacity) {
    this.capacity = capacity;
    blocks = new int[capacity];
  }

  boolean isFull() {
    return freeBlocks() == 0;
  }

  int freeBlocks() {
    return capacity - size;
  }

  boolean fits(int[] blocks) {
    return freeBlocks() >= blocks.length;
  }

  boolean fits(int size) {
    return freeBlocks() >= size;
  }

  void add(int[] newBlocks) {
    for (int newBlock : newBlocks) {
      add(newBlock);
    }
  }

  void add(int newBlock) {
    this.blocks[size] = newBlock;
    size++;
  }

  public String toLineFormat() {
    var sb = new StringBuilder();

    for (int i = 0; i < capacity; i++) {
      if (i < size) sb.append(blocks[i]);
      else sb.append(".");
    }

    return sb.toString();
  }

  public void erase() {
    size = 0;
  }
}
