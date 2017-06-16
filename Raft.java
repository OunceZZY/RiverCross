
public class Raft {
  public String pos;
  public boolean explored;

  public Raft(String p) {
    pos = p;
    explored = false;
  }

  public String toString() {
    String re = "";
    char[] p = pos.toCharArray();
    for (int i = 0; i < pos.length(); i++){
      re += (p[i] == '1') ? '0' : '1';
      re += "  ";
    }
    return re;
  }
}
