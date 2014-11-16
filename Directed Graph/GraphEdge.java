
public class GraphEdge {
  
  private GraphNode target;
  private GraphEdge nextedge;
  
  GraphEdge () {
  target = null;
  nextedge = null;
  }
  
  GraphEdge (GraphNode t) {
    target = t;
  }
  
  GraphEdge (GraphNode t, GraphEdge n) {
    target = t;
    nextedge = n;
  }
  
  public void setTarget (GraphNode t) {
    target = t;
  }
  
  public void setNext (GraphEdge n) {
    nextedge = n;
  }
  
  public GraphNode getTarget () {
    return target;
  }
  
  public GraphEdge getNext () {
    return nextedge;
  }
  
  public String toString  () {
    return (target + "\t nextEDGE: " + nextedge);
  }
}
    
  

