package com.vimacodes.aoc.day9;

import java.util.ArrayList;
import java.util.List;
import lombok.Value;

@Value
public class DiskMap {

  char[] digits;

  public static DiskMap parse(String line) {
    char[] digits = new char[line.length()];

    for (int i = 0; i < line.length(); i++) {
      digits[i] = line.charAt(i);
    }

    //    System.out.println("Disk map: " + Arrays.toString(digits));
    return new DiskMap(digits);
  }

  public long checksum() {
    var expanded = expand();
    //    System.out.println(expanded);
    var condensed = condense(expanded);
    //    System.out.println(condensed);
    return checksum(expanded);
  }

  public long checksumWhole() {
    var expanded = expandWhole();
    //    System.out.println(expanded);
    var condensed = condenseWhole(expanded);
    //    System.out.println(condensed);
    return checksumWhole(condensed);
  }

  private List<FileSegment> expandWhole() {
    var expanded = new ArrayList<FileSegment>();

    int capacity;
    int cc = 0;
    for (int i = 0; i < digits.length; i++) {
      capacity = Character.getNumericValue(digits[i]);
      if (i % 2 == 0) {
        FileSegment segment = new FileSegment(capacity);
        for (int n = 0; n < capacity; n++) {
          segment.add(cc);
        }
        expanded.add(segment);
        cc++;
      } else {
        FileSegment segment = new FileSegment(capacity);
        expanded.add(segment);
      }
    }

    return expanded;
  }

  private List<FileSegment> condenseWhole(List<FileSegment> expanded) {
    for (int i = expanded.size() - 1; i >= 0; i--) {
      FileSegment s = expanded.get(i);
      if (s.size > 0) {
        //        System.out.printf("Condensing: %s. %s\n", i, s);
        FileSegment nextEmpty = findSpace(expanded, s.size, i);
        if (nextEmpty != null) {
          nextEmpty.add(s.getBlocks());
          s.erase();
        }
      }
    }

    for (FileSegment s : expanded) {
      System.out.print(s.toLineFormat());
    }
    System.out.println();

    return expanded;
  }

  private FileSegment findSpace(List<FileSegment> expanded, int size, int maxIndex) {
    for (int i = 0; i < maxIndex; i++) {
      FileSegment s = expanded.get(i);
      if (s.freeBlocks() >= size) return s;
    }
    return null;
  }

  private List<Integer> condense(List<Integer> values) {
    int tail = values.size() - 1;

    for (int i = 0; i < values.size() && i < tail; i++) {
      if (values.get(i) == -1) {
        while (values.get(tail) == -1) {
          tail--;
        }
        if (i < tail) {
          values.set(i, values.get(tail));
          values.set(tail, -1);
          tail--;
        }
      }
    }

    return values;
  }

  private List<Integer> expand() {
    var expanded = new ArrayList<Integer>();

    int d;
    int cc = 0;
    for (int i = 0; i < digits.length; i++) {
      d = Character.getNumericValue(digits[i]);
      if (i % 2 == 0) {
        for (int n = 0; n < d; n++) {
          expanded.add(cc);
        }
        cc++;
      } else {
        for (int n = 0; n < d; n++) {
          expanded.add(-1);
        }
      }
    }

    return expanded;
  }

  private long checksum(List<Integer> values) {
    long sum = 0;

    for (int i = 0; i < values.size(); i++) {
      if (values.get(i) == -1) break;
      sum += (long) i * values.get(i);
    }

    return sum;
  }

  private long checksumWhole(List<FileSegment> segments) {
    long sum = 0;
    int moves = 0;

    for (var segment : segments) {
      for (int i = 0; i < segment.getCapacity(); i++) {
        if (i < segment.getSize()) {
          int block = segment.getBlocks()[i];
          sum += (long) moves * block;
        }
        ++moves;
      }
    }

    return sum;
  }
}
