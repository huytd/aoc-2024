import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Day03
 */
public class Day03 {
  final static String PARSE_REGEX = "mul\\((\\d+),(\\d+)\\)|do(n't)?\\(\\)";
  final static String DO = "do()";
  final static String DONT = "don't()";
  final static String MUL = "mul";

  static int toInt(String input) {
    try {
      return Integer.parseInt(input);
    }catch(Exception e) {
      return 0;
    }
  }

  static int parseProgram(String fileName, boolean handleSkip) throws IOException {
    boolean enabled = true;
    int result = 0;
    String content = Files.readString(Path.of(fileName));
    Pattern pattern = Pattern.compile(PARSE_REGEX);
    Matcher matcher = pattern.matcher(content);
    while (matcher.find()) {
      String ins = matcher.group();
      if (handleSkip && ins.equals(DO)) {
        enabled = true;
      } else if (handleSkip && ins.equals(DONT)) {
        enabled = false;
      } else if (ins.startsWith(MUL)) {
        if (enabled) {
          result += toInt(matcher.group(1)) * toInt(matcher.group(2));
        }
      }
    }
    return result;
  }

  public static void main(String[] args) throws IOException {
    String fileName = "data/day03_input";
    System.out.println("Part 1 = " + parseProgram(fileName, false));
    System.out.println("Part 2 = " + parseProgram(fileName, true));
  }
}
