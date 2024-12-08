package com.vimacodes.aoc.day8;

import com.vimacodes.aoc.day6.Pos;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

public record City(int rows, int cols, char[][] map, Map<Character, List<Pos>> antennas) {

  public static City parse(String text) {

    List<String> lines = text.lines().toList();
    int rows = lines.size();
    int cols = lines.get(0).length();

    char[][] map = new char[rows][cols];
    Map<Character, List<Pos>> antennas = new HashMap<>();

    //    System.out.printf("rows: %s, cols: %s\n", rows, cols);

    String line;
    for (int i = 0; i < rows; i++) {
      line = lines.get(i);
      for (int j = 0; j < cols; j++) {
        char c = line.charAt(j);
        //        System.out.print(c);
        map[i][j] = c;
        if (c != '.') {
          antennas.computeIfAbsent(c, v -> new ArrayList<>()).add(new Pos(i, j));
        }
      }
      //      System.out.println();
    }

    return new City(rows, cols, map, antennas);
  }

  public Set<Pos> antinodes() {
    //    System.out.println(antennas);
    var antinodes = new HashSet<Pos>();
    antennas.forEach((antenna, locations) -> antinodes.addAll(locateAntinodes(locations, false)));
    return antinodes;
  }

  public Set<Pos> antinodesInLine() {
    var antinodes = new HashSet<Pos>();
    antennas.forEach((antenna, locations) -> antinodes.addAll(locateAntinodes(locations, true)));
    return antinodes;
  }

  private Collection<Pos> locateAntinodes(List<Pos> locations, boolean allInLine) {
    Set<Pos> antinodes = new HashSet<>();

    for (int i = 0; i < locations.size(); i++) {
      for (int j = i + 1; j < locations.size(); j++) {
        var a1 = locations.get(i);
        var a2 = locations.get(j);
        //        System.out.printf("Locating antinodes for: %s and %s with signal: %s\n", a1, a2,
        // antenna);
        antinodes.addAll(locateAntinodes(a1, a2, allInLine));
      }
    }

    return antinodes;
  }

  private Collection<Pos> locateAntinodes(Pos a1, Pos a2, boolean allInLine) {
    int iDiff = a2.i() - a1.i();
    int jDiff = a2.j() - a1.j();

    if (!allInLine) {
      Pos p1 = new Pos(a1.i() - iDiff, a1.j() - jDiff);
      Pos p2 = new Pos(a2.i() + iDiff, a2.j() + jDiff);

      return Stream.of(p1, p2).filter(this::validPos).toList();
    } else {

      var all = new HashSet<Pos>();

      var last1 = a1;
      while (validPos(last1)) {
        all.add(last1);
        last1 = new Pos(last1.i() - iDiff, last1.j() - jDiff);
      }

      var last2 = a2;
      while (validPos(last2)) {
        all.add(last2);
        last2 = new Pos(last2.i() + iDiff, last2.j() + jDiff);
      }
      return all;
    }
  }

  private boolean validPos(Pos pos) {
    return validPos(pos.i(), pos.j());
  }

  private boolean validPos(int i, int j) {
    return 0 <= i && i < rows && 0 <= j && j < cols;
  }
}
