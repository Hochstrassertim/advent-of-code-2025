package tag3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Part1 {
  public static void main(String[] args) {
    AtomicInteger sumOfNumbers = new AtomicInteger();

    List<String> lines = getInputs();

    lines.forEach(line -> {
      int[] numbers = new int[line.length()];
      for (int i = 0; i < line.length(); i++) {
        numbers[i] = Character.getNumericValue(line.charAt(i));
      }

      int largestJoltage = getLargestConcatenatedNumber(numbers);

      sumOfNumbers.addAndGet(largestJoltage);
    });

    System.out.println("Sum of biggest numbers: " + sumOfNumbers.get());
  }

  private static List<String> getInputs() {
    try {
      String file = System.getProperty("user.dir") + "\\src\\main\\java\\tag3\\inputs.txt";
      BufferedReader reader = new BufferedReader(new FileReader(file));
      String line;
      List<String> inputs = new ArrayList<>();

      try {
        while ((line = reader.readLine()) != null) {
          inputs.add(line);
        }
      } finally {
        reader.close();
      }

      return inputs;
    } catch (Exception e) {
      e.printStackTrace();
      return List.of();
    }
  }

  private static int getLargestConcatenatedNumber(int[] arr) {
    int largest = Integer.MIN_VALUE;

    for (int i = 0; i < arr.length; i++) {
      for (int j = i + 1; j < arr.length; j++) {
        int concatenated = Integer.parseInt("" + arr[i] + arr[j]);

        if (concatenated > largest) {
          largest = concatenated;
        }
      }
    }

    return largest;
  }
}