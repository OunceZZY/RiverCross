/*
 * Student Name:    Zhiyuan Zhang (Owen)
 * Student ID:      B00716809
 * CS ID:           zhiyuanz
 * Date:            Oct. 19th
 * Assignment 3 Bonus - River Crossing Puzzle
 * 
 * Run the program will print one possible solution 
 * of the river crossing puzzle
 *
 */
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RiverCross {
  public static void main(String[] args) {
    System.out.println("Fa: father\nS1: son 1\nS1: son 2\nM: mother\nD1"
        + ": daughter 1\nD2: daughter 2\nPo: Police\nTh: Thief\nBoat:"
        + " Boat position\n0 for left side of the river\n1 for right si"
        + "de of the river\nTarget: move everyone from left side to rig"
        + "ht side\n");
    System.out.println("Fa S1 S2 Mo D1 D2 Po Th Boat");
    Raft[] pos = getPossibleRafts();
    DFS(pos[0], pos);
  }

  /*
   * Recursive DFS find loop until reach '111111111' from '000000000'
   */
  public static boolean DFS(Raft curr, Raft[] list) {
    curr.explored = true;
    if (curr.pos.equals("111111111")) {
      System.out.println(curr);
      return true;
    }
    for (int i = 0; i < list.length; i++) {
      if (list[i].explored == false && legalMove(curr.pos, list[i].pos)) {
        if (DFS(list[i], list)) {
          System.out.println(curr);
          return true;
        }
      }
    }
    return false;
  }

  /*
   * Check if the move is a legal one
   */
  public static boolean legalMove(String x, String y) {
    if (x.charAt(x.length() - 1) == y.charAt(y.length() - 1)) 
      return false;
    char[] a = x.toCharArray(),b = y.toCharArray();
    int count = 0;
    for (int i = 0; i < a.length - 1; i++)
      count += (a[i] != b[i]) ? 1 : 0;
    if (count > 2) return false;

    if (!((a[8] == a[0] && b[8] == b[0]) || (a[8] == a[3] && b[8] == b[3])
        || (a[8] == a[6] && b[8] == b[6]))) return false;
    if (a[0] != b[0] && a[3] != b[3] && a[1] == b[1] && a[2] == b[2] && a[4] == b[4]
        && a[5] == b[5] && a[6] == b[6] && a[7] == b[7])
      return true;
    if (a[0] != b[0]) 
      return ((a[1] == b[1] || a[2] == b[2]) && a[4] == b[4] && a[5] == b[5]) ? true : false;
    if (a[3] != b[3]) 
      return ((a[4] == b[4] || a[5] == b[5]) && a[1] == b[1] && a[2] == b[2]) ? true : false;
    if (a[6] != b[6]) 
      return true;
    return false;
  }

  public static Raft[] getPossibleRafts() {
    String[] pattern = { "1[01][01]1[01][01]11[01]", "1[01][01]1[01][01]00[01]",
        "0[01][01]0[01][01]11[01]", "0[01][01]0[01][01]00[01]",
        "11100011[01]", "11100000[01]",
        "00011111[01]", "00011100[01]",
        "000000010", "111111101"
    };
    Pattern[] r = new Pattern[pattern.length]; //= Pattern.compile(pattern);
    for (int i = 0; i < pattern.length; i++) {
      r[i] = Pattern.compile(pattern[i]);
    }
    Matcher m; // = r.matcher(a);
    String g = null;
    int order = 0;
    Raft[] ret = new Raft[138];
    int count = 0;
    do {
      g = generate(order++);
      for (int i = 0; i < pattern.length; i++) {
        m = r[i].matcher(g);
        if (m.find()) {
          ret[count++] = new Raft(g);
          break;
        }
      }
    } while (!g.equals("111111111"));
    return ret;
  }

  public static String generate(int i) {
    String t = Integer.toBinaryString(i);
    while (t.length() < 9)
      t = "0".concat(t);
    return t;
  }
}
