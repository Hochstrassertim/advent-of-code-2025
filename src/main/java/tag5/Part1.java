package tag5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

import lombok.Data;

public class Part1 {
  private static Set<Range> validIds = new HashSet<>();
  private static Set<Long> availableIds = new HashSet<>();

  public static void main(String[] args) {
    AtomicLong validIdCount = new AtomicLong();
    List<String> lines = getInputs();

    lines.forEach(line -> {
      if (line.contains("-")) {
        // Valid ID Range case

        String[] range = line.split("-");
        long start = Long.parseLong(range[0]);
        long end = Long.parseLong(range[1]);

        validIds.add(new Range(start, end));
      } else if (!line.equals("")) {
        // Available ID case

        availableIds.add(Long.parseLong(line));
      }
    });

    availableIds.forEach(id -> {
      if (idIsValid(id)) {
        validIdCount.addAndGet(1);
      }
    });

    System.out.println("Number of valid IDs: " + validIdCount.get());
  }

  private static boolean idIsValid(long id) {
    AtomicBoolean isValid = new AtomicBoolean(false);
    validIds.forEach(range -> {
      if (id >= range.getStart() && id <= range.getEnd()) {
        isValid.set(true);
      }
    });

    return isValid.get();
  }

  private static List<String> getInputs() {
    try {
      String file = System.getProperty("user.dir") + "\\src\\main\\java\\tag5\\inputs.txt";
      BufferedReader reader = new BufferedReader(new FileReader(file));
      List<String> inputs = new ArrayList<>();
      String line;

      while ((line = reader.readLine()) != null) {
        inputs.add(line);
      }

      reader.close();
      return inputs;
    } catch (Exception e) {
      e.printStackTrace();
      return List.of();
    }
  }
}

@Data
class Range {
  private final long start;
  private final long end;
}