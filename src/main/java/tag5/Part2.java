package tag5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Part2 {
  private static Set<Range> validIds = new HashSet<>();

  public static void main(String[] args) {
    List<String> lines = getInputs();

    lines.forEach(line -> {
      if (line.contains("-")) {
        String[] range = line.split("-");
        long start = Long.parseLong(range[0]);
        long end = Long.parseLong(range[1]);

        validIds.add(new Range(start, end));
      }
    });

    List<Range> mergedRanges = mergeRanges(new ArrayList<>(validIds));

    long totalFreshIds = 0;
    for (Range range : mergedRanges) {
      totalFreshIds += (range.getEnd() - range.getStart() + 1);
    }

    System.out.println("Number of valid IDs: " + totalFreshIds);
  }

  private static List<Range> mergeRanges(List<Range> ranges) {
    ranges.sort((r1, r2) -> Long.compare(r1.getStart(), r2.getStart()));

    List<Range> merged = new ArrayList<>();
    Range current = ranges.get(0);

    for (int i = 1; i < ranges.size(); i++) {
      Range next = ranges.get(i);

      if (current.getEnd() >= next.getStart() - 1) {
        current = new Range(current.getStart(), Math.max(current.getEnd(), next.getEnd()));
      } else {
        merged.add(current);
        current = next;
      }
    }

    merged.add(current);

    return merged;
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