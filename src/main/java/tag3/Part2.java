package tag3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class Part2 {
  public static void main(String[] args) {
    AtomicLong totalOutputJoltage = new AtomicLong();

    List<String> lines = getInputs();

    lines.forEach(line -> {
      long largestJoltage = getLargest12DigitNumber(line);

      totalOutputJoltage.addAndGet(largestJoltage);
    });

    System.out.println("Total output joltage: " + totalOutputJoltage.get());
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

  private static long getLargest12DigitNumber(String line) {
    int n = line.length();
    int digitsToSelect = 12;

    StringBuilder result = new StringBuilder();

    int startIndex = 0;

    for (int i = 0; i < digitsToSelect; i++) {
      int maxIndex = n - (digitsToSelect - i);

      char maxDigit = '0';
      int maxDigitIndex = startIndex;
      for (int j = startIndex; j <= maxIndex; j++) {
        if (line.charAt(j) > maxDigit) {
          maxDigit = line.charAt(j);
          maxDigitIndex = j;
        }
      }

      result.append(maxDigit);

      startIndex = maxDigitIndex + 1;
    }

    return Long.parseLong(result.toString());
  }
}