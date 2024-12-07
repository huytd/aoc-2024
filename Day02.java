import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Day02
 */
public class Day02 {
  static enum Direction {
    Increasing,
    Decreasing;

    static Direction from(int a, int b) {
      return a < b ? Increasing : Decreasing;
    }

    public boolean same(Direction target) {
      return this == target;
    }
  }

  static List<List<Integer>> readInput(String fileName) {
    List<List<Integer>> result = new ArrayList<>();
    try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
      String line;
      while ((line = bufferedReader.readLine()) != null) {
        List<Integer> lineNumbers = Arrays.stream(line.split("\\s+"))
          .map(Integer::parseInt)
          .toList();
        result.add(lineNumbers);
      }
      return result;
    } catch (Exception e) {
      System.out.println("Error reading file: " + e.getMessage());
    }
    return List.of();
  }

  static boolean isSafeReport(List<Integer> report) {
    Direction reportDirection = Direction.from(report.get(1), report.get(0));
    for (int i = 1; i < report.size(); i++) {
      Direction currentDirection = Direction.from(report.get(i), report.get(i-1));
      if (!currentDirection.same(reportDirection)) return false;
      int diff = Math.abs(report.get(i) - report.get(i-1));
      if (diff < 1 || diff > 3) return false;
    }
    return true;
  }

  static boolean canReportBeSafe(List<Integer> report) {
    for (int i = 0; i < report.size(); i++) {
      List<Integer> modifiedReport = new ArrayList<>(report);
      modifiedReport.remove(i);
      if (isSafeReport(modifiedReport)) return true;
    }
    return false;
  }

  static void solvePart1(List<List<Integer>> input) {
    long count = input.stream().filter(Day02::isSafeReport).count();
    System.out.println("Part 1 = " + count);
  }

  static void solvePart2(List<List<Integer>> input) {
    long count = input.stream().filter(report -> isSafeReport(report) || canReportBeSafe(report)).count();
    System.out.println("Part 2 = " + count);
  }

  public static void main(String[] args) {
    List<List<Integer>> input = readInput("./data/day02_input");
    solvePart1(input);
    solvePart2(input);
  }
}
