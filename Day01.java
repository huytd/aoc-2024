import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.PriorityQueue;
/**
 * Day01
 */
public class Day01 {
  static PriorityQueue<Integer> left = new PriorityQueue<>();
  static PriorityQueue<Integer> right = new PriorityQueue<>();
  static Map<Integer, Integer> freq = new HashMap<>();

  static void readInput(String fileName) {
    try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
      String line;
      while ((line = bufferedReader.readLine()) != null) {
        List<Integer> lineInput = Arrays.stream(line.split("\\s+")).map(Integer::parseInt).toList();
        left.add(lineInput.get(0));
        right.add(lineInput.get(1));
        freq.put(lineInput.get(1), freq.getOrDefault(lineInput.get(1), 0) + 1);
      }
    } catch (Exception e) {
      System.out.println("Could not read file!");
    }
  }

  static void solvePart1() {
    PriorityQueue<Integer> pleft = new PriorityQueue<>();
    PriorityQueue<Integer> pright = new PriorityQueue<>();
    pleft.addAll(left); pright.addAll(right);
    int total = 0;
    while (!pleft.isEmpty()) {
      int a = pleft.poll(); int b = pright.poll();
      total += Math.abs(b - a);
    }
    System.out.println("Part 1 = " + total);
  }

  static void solvePart2() {
    PriorityQueue<Integer> pleft = new PriorityQueue<>();
    pleft.addAll(left);
    int total = 0;
    while (!left.isEmpty()) {
      int a = left.poll(); int b = freq.getOrDefault(a, 0);
      total += a * b;
    }
    System.out.println("Part 2 = " + total);
  }

  public static void main(String[] args) {
    readInput("data/day01_input");
    solvePart1();
    solvePart2();
  }
}
