public class GraphNode {
  
  private GraphEdge edge;
  private int id;
  private GraphNode nextnode;
  private GraphNode parent;
  
  GraphNode () {
  edge = null;
  id = -1;
  nextnode = null;  
  parent = null;
  }
  
  GraphNode (GraphEdge e, int i, GraphNode n) {
    edge = e;
    id = i;
    nextnode = n;
    parent = null;
  }
  
  GraphNode (GraphEdge e, int i) {
    edge = e;
    id = i;
    nextnode = null;
    parent = null;
  }
    
  GraphNode (int i, GraphNode n) {
    edge = null;
    id = i;
    nextnode = n;
    parent = null;
  }
  
  GraphNode (int i) {
    edge = null;
    id = i;
    nextnode = null;
    parent = null;
  }

  public void setEdge (GraphEdge e) {
    edge = e;
  }
  
  public void setID (int i) {
    id = i;
  }
  
  public void setNext (GraphNode n) {
    nextnode = n;
  }
  
  public void setParent (GraphNode p) {
    parent = p;
  } 
   
  
  public GraphEdge getEdge () {
    return edge;
  }
  
  public int getID () {
    return id;
  }
  
  public GraphNode getNext () {
    return nextnode;
  }
  
  public GraphNode getParent () {
    return parent;
  }
  
  
  
  public String toString () {
    return ("NODE" + id);
  }
  
}
