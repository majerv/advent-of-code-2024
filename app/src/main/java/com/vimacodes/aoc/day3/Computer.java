package com.vimacodes.aoc.day3;

import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.NonNull;

public record Computer(List<Mul> muls) {

  private static final Pattern MUL_PATTERN = Pattern.compile("mul\\([0-9]+,[0-9]+\\)");
  private static final String DON_T = "don't\\(\\)";
  private static final String DO = "do()";

  public static Computer parse(@NonNull String text) {
    Matcher matcher = MUL_PATTERN.matcher(text);
    List<Mul> muls = matcher.results().map(MatchResult::group).map(Mul::parse).toList();

    return new Computer(muls);
  }

  public static Computer parseEnabled(String text) {
    StringBuilder res = new StringBuilder();
    String[] donts = text.split(DON_T);

    for (int i = 0; i < donts.length; i++) {
      if (i == 0) {
        res.append(donts[i]); // first section of text is always enabled until the first "don't()"
      } else {
        int firstDo =
            donts[i].indexOf(DO); // look for the first "do()" within this "don't()) section
        if (firstDo != -1) {
          res.append(donts[i].substring(firstDo));
        }
      }
    }

    String remText = res.toString();
    return parse(remText);
  }

  public List<Mul> multiplications() {
    return muls;
  }
}
