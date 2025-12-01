package tag1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Part2 {
  private static int position = 50;
  private static AtomicInteger zeroCounter = new AtomicInteger();

  public static void main(String[] args) {
    List<String> inputs = getInputs();

    inputs.forEach(input -> position = updatePosition(input));

    System.out.println("Number of zeros: " + zeroCounter.get());
  }

  private static List<String> getInputs() {
    try {
      String file = System.getProperty("user.dir") + "\\src\\main\\java\\tag1\\inputs.txt";
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
      return List.of();
    }
  }

  private static int updatePosition(String input) {
    String direction = input.substring(0, 1);
    int amount = Integer.parseInt(input.substring(1));

    if (direction.equalsIgnoreCase("L")) {
      for (int i = 0; i < amount; i++) {
        changePosition(false);
      }
    } else {
      for (int i = 0; i < amount; i++) {
        changePosition(true);
      }
    }

    return position;
  }

  private static void changePosition(boolean positive) {
    if (positive) {
      position = (position + 1) % 100;
    } else {
      position = (position - 1 + 100) % 100;
    }

    if (position == 0) {
      zeroCounter.addAndGet(1);
    }
  }
}