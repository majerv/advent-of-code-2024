package com.vimacodes.aoc.day5;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Printer {

  Map<Integer, Set<Integer>> rules;
  List<Update> updates;

  public static Printer parse(String text) {
    String[] split = text.split("\\r\\n\\r\\n");

    var rules = parseRules(split[0]);
    Comparator<Integer> comparator = new PageOrderingComparator(rules);
    var updates = parseUpdates(split[1], comparator);

    //    System.out.println("Rules:\n" + rules);
    //    System.out.println();
    //    System.out.println("Updates:\n" + updates);

    return new Printer(rules, updates);
  }

  private static List<Update> parseUpdates(String updatesString, Comparator<Integer> comparator) {
    return updatesString.lines().map(line -> Update.of(line, comparator)).toList();
  }

  private static Map<Integer, Set<Integer>> parseRules(String ruleString) {
    Map<Integer, Set<Integer>> rules = new HashMap<>();

    ruleString
        .lines()
        .forEach(
            line -> {
              String[] rule = line.split("\\|");
              int before = Integer.parseInt(rule[0]);
              int after = Integer.parseInt(rule[1]);
              rules.computeIfAbsent(before, k -> new HashSet<>()).add(after);
            });

    return rules;
  }

  public List<Update> updates() {
    return updates;
  }
}
