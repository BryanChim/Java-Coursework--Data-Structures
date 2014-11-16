/* DirectedGraph_chimb.java
 * Author: Bryan Chim  
 * Submission Date: 11/29/2013
 * 
 * Java Version Used: 1.7.0_25
 * Java(TM) SE Runtime Environment (build 1.7.0_25-b16)
 * Java Hotspot(TM) Client VM (build 23.25-b01, mixed mode, sharing)
 * 
 * Description:
 * - Constructs a directed graph using integer input from a file
 * - Tests if constructing an edge results in duplicate pathing
 * 
 * Input: 
 * - 1st Command Line Prompt, user enters name of input file (ie. "test1.txt")
 * - 2nd Command Line Prompt, user enters int IDs of the two nodes to be tested (ie. "3 4")
 * - Graph Input Data Format~
 * -- First line contains a single integer, which indicates the number of nodes to be created
 * -- Each successive line contains two integers that represent the edges to be created
 * --- the first integer is read as the source, second integer is read as the target
 * 
 * Outputs: 
 * - Prints out each node, followed by its parent, followed by its edges and their targets
 * - Prints out "TRUE" if the two test nodes have a common ancestor, otherwise prints "FALSE"
 * 
 * Static Methods:
 * - getNode (line 120)
 * - getTailEdge (line 130)
 * - checkPath (line 143)
 */

import java.io.*;
import java.lang.*;
import java.util.Scanner;

public class DirectedGraph_chimb {
  
  public static void main (String[] args) throws IOException {

    String line;                             // temporary String variable to hold each line of input
    int nodecount;                           // int to hold the # of nodes to be constructed
    int i = 3;                               // iterator for construction of 3rd node, after header and 2nd nodes
    String[] linearray = new String[2];      // array to temporarily hold integer values from each line
    
// accept command-line input for file name and the two test nodes
   Scanner in = new Scanner(System.in);
   System.out.print("Enter file name containing graph data: ");
   String file_name = in.nextLine();
   System.out.print("Enter the integer IDs of two nodes to test, with a single space in between: ");
   int test1 = in.nextInt();
   int test2 = in.nextInt();

    BufferedReader input = new BufferedReader(new FileReader(file_name)); // construct Buffered Reader object to parse file
 
    line = input.readLine (); 
    nodecount = Integer.parseInt (line);  // read first line, get the node count
    
    GraphNode header = new GraphNode (1); // construct header node
    GraphNode n2 = new GraphNode (2);     // construct the next node
    header.setNext(n2);                   // link n2 to header as its nextnode
    GraphNode tail = n2;                  // set n2 as the current tail node
    
// construct the rest of the nodes, linking them by using the setNext method and updating the tail each time    
    while (  i <= nodecount) {
      GraphNode node = new GraphNode (i); 
      if (i == 3) {
        n2.setNext(node);
      } else { 
        tail.setNext(node);
      }
      tail = node;
      i++;
      }

    
// Read in successive lines and construct edges
// Construct edge, specify its target, make sure it is properly linked 
//   (see setEdge or setNext methods in the GraphNode and GraphEdge classes)
// Set the source node as the parent of the target node, using setParent method
// getNode static method iterates through the Node linked list to access the correct node (see line 120)
// getTailEdge static method iterates through the Edge linked list to access the tail edge (see line 130)   
   while (input.ready()) {
     line = input.readLine(); 
     linearray = line.split (" ");
     int source = Integer.parseInt(linearray[0]);
     int target = Integer.parseInt(linearray[1]);
   GraphEdge edge = new GraphEdge(getNode(header, target));
                              if (getNode(header, source).getEdge() == null) {
                                getNode(header, source).setEdge(edge);
                              } else {
                                  getTailEdge(getNode(header, source).getEdge()).setNext(edge);
                                }
                              
                              getNode(header, target).setParent(getNode(header, source));                               
     }
 
input.close();
   
// print out node information, for debugging and reference 
     for (i = 1; i <= nodecount; i++ ) {
      System.out.println (getNode(header, i) + "\tPARENT: " + getNode(header, i).getParent() + "\tEDGE: " + getNode(header, i).getEdge());
     }
    
     System.out.println ("************************************");

     
// checkPath static method - tests for path duplication by tracing each node's parents (see line 143)      
     checkPath (getNode(header, test1), getNode(header, test2), nodecount);

    
 
   

  
} // end of main

  
// getNode static method accepts a node (typically the header node) and an integer representing the desired node's ID
// repeatedly calls the getNext method to iterate through the Node linked list until it reaches the desired node
// returns the desired node
public static GraphNode getNode (GraphNode h, int i) {
  for (int j = i; j > 1; j--) {
    h = h.getNext();
  }
  return h;
  }

// getTailEdge static method accepts an Edge object 
// iterates through the Edge linked list via getNext(), repeatedly until it hits null
// this Edge, which points to null, is the tail Edge and is returned
public static GraphEdge getTailEdge (GraphEdge e) {
  while (e.getNext() != null) {
    e = e.getNext();
  }
  return e;
}

// checkPath static method accepts two Nodes (the test nodes) and an integer (the node count)
// constructs an array of the maximum size to hold a list of parents for each test node
// iterates through parents of each test node until reaching null, stores them in the respective array
// iterates through each array, comparing every parent pair by their IDs
// if any pair is identical, set int test to 1, print out TRUE, otherwise print out FALSE
// *if there is a cycle in the graph, abort adding Parent nodes once array is filled (when at m-1 index) 
public static void checkPath (GraphNode a, GraphNode b, int m) {
  System.out.println ("Testing " + a + " and " + b + "...");
  GraphNode[] aParents = new GraphNode[m];
  GraphNode[] bParents = new GraphNode[m];
  int p = 0;
  int q = 0;
  int test = 0;
  
  while (a.getParent() != null && p < m - 1) {
    aParents[p] = a.getParent();
    p++;
    a = a.getParent();
  }
  p = 0;
    while (b.getParent() != null && p < m - 1) {
    bParents[p] = b.getParent();
    p++;
    b = b.getParent();
  }
  p = 0;

  while (aParents[p] != null && p < m - 1 ) {
    while (bParents[q] != null && q < m - 1) { 
//      System.out.println("COMPARING " + aParents[p] + " and " + bParents[q]);
      if (aParents[p].getID() == bParents[q].getID()) {
        test = 1;
      }
      q++;
    }
    q = 0;
    p++;
  }
  
  if (test == 1) {
    System.out.println ("TRUE");
  }
  else {System.out.println ("FALSE");
  }      
}

} // end of class DirectedGraph_chimb



    
    
    
    
    
    
    
    
    
    
    
    