package tag4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Part1 {
  private static List<String> lines = getInputs();
  private static char[][] grid = new char[lines.size()][lines.get(0).length()];

  public static void main(String[] args) {
    for (int i = 0; i < lines.size(); i++) {
      grid[i] = lines.get(i).toCharArray();
    }

    int pickableCount = 0;

    for (int y = 0; y < grid.length; y++) {
      for (int x = 0; x < grid[0].length; x++) {
        if (grid[y][x] == '@' && numberOfNeighbouringPapers(x, y) < 4) {
          pickableCount++;
        }
      }
    }

    System.out.println("Pickable papers: " + pickableCount);
  }

  private static int numberOfNeighbouringPapers(int x, int y) {
    int neighbours = 0;

    for (int j = y - 1; j <= y + 1; j++) {
      for (int i = x - 1; i <= x + 1; i++) {
        if (i == x && j == y) continue;
        if (i >= 0 && i < grid[0].length && j >= 0 && j < grid.length && grid[j][i] == '@') {
          neighbours++;
        }
      }
    }

    return neighbours;
  }

  private static List<String> getInputs() {
    try {
      String file = System.getProperty("user.dir") + "\\src\\main\\java\\tag4\\inputs.txt";
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