package tag2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class Part2 {
  private static List<Long> invalidIds = new ArrayList<>();

  public static void main(String[] args) {
    List<String> inputs = getInputs();

    inputs.forEach(input -> {
      String[] bounds = input.split("-");
      checkRange(Long.parseLong(bounds[0]), Long.parseLong(bounds[1]));
    });

    System.out.println("Sum of invalid IDs: " + sum(invalidIds));
  }

  private static List<String> getInputs() {
    try {
      String file = System.getProperty("user.dir") + "\\src\\main\\java\\tag2\\inputs.txt";
      BufferedReader reader = new BufferedReader(new FileReader(file));
      String line;
      List<String> inputs = new ArrayList<>();

      try {
        while ((line = reader.readLine()) != null) {
          inputs = Arrays.stream(line.split(",")).toList();
        }
      } finally {
        reader.close();
      }

      return inputs;
    } catch (Exception e) {
      return List.of();
    }
  }

  private static void checkRange(long start, long end) {
    for (long i = start; i <= end; i++) {
      checkNumber(i);
    }
  }

  private static void checkNumber(long number) {
    String numberString = Long.toString(number);

    for (int len = 1; len <= numberString.length() / 2; len++) {
      if (numberString.length() % len == 0) {
        String substring = numberString.substring(0, len);
        int repeatCount = numberString.length() / len;
        String repeated = substring.repeat(repeatCount);

        if (repeated.equals(numberString)) {
          invalidIds.add(number);
          return;
        }
      }
    }
  }

  private static long sum(List<Long> numbers) {
    AtomicLong total = new AtomicLong();

    numbers.forEach(number -> total.addAndGet(number));

    return total.get();
  }
}
