// class Node is very simple:
// Variables are all integers, which are used as indices in the node Array of the main method
// Each of the 4 integers for each node corresponds to: itself, its parent, its left child, its right child
// toString method returns "NODE ID#"

public class Node {
  
  int id;
  int parent;
  int left;
  int right;
  
  Node (int i, int p, int l, int r) {
    id = i;
    parent = p;
    left = l;
    right = r;
    
  }
  
  public String toString () {
    return ("NODE " + this.id);
  }
  
}