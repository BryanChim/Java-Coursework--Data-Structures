/* Binary_Tree_chimb.java
 * Author: Bryan Chim  
 * Submission Date: 11/16/2013
 * 
 * Java Version Used: 1.7.0_25
 * Java(TM) SE Runtime Environment (build 1.7.0_25-b16)
 * Java Hotspot(TM) Client VM (build 23.25-b01, mixed mode, sharing)
 * 
 * Description:
 * - Accepts a text file containing node data, processes 
 *   the data and constructs a binary tree from it 
 * - Nodes are constructed from the input file, line by line
 * - Nodes are collected into an array of Nodes, which are all
 *   linked to each other by the parent/child relationships 
 *   described in the input file
 * - The array of Nodes is processed and traversed via:
 *   In Order, Pre Order and Post Order Traversal
 * - Program also calculates height of the tree
 * 
 * Input: 
 * - Upon running program, the user is prompted to input a file name corresponding
 *   to the file containing the data
 * - Each line of the file must be composed of 4 single-space-delimited integers
 * - The 4 integers on each line are read-in and used to construct a Node (line 83)
 * - The order and meaning of the integers are:
 * "<node ID> <node's parent> <node's left child> <node's right child>"
 * ie. "5 3 6 7" 
 * ~> node ID # is 5, parent is node 3, left child is node 6, right child is node 7
 * - NOTE: an integer of "-1" will indicate that a node for that position does not exist
 * 
 * Outputs: 
 * - Using the resultant binary tree, the program uses the respective static method to perform:  
 *   In Order Traversal (static method at line 132), 
 *   Pre Order Traversal (static method at line 149), and 
 *   Post Order Traversal (static method at line 166).
 * - Program then calculates and outputs the overall height of the tree (static method at line 200) 
 * - For each traversal type, the program will print out visited nodes in appropriate order:
 * "VISITING NODE 7
 *  VISITING NODE 8
 *  VISITING NODE 3" ... etc.
 * 
 */


import java.io.*;
import java.lang.*;
import java.util.Scanner;

public class Binary_Tree_chimb {
  
  public static void main (String[] args) throws IOException {

    int nodecount = 0;                       // counter variable for constructing the node Array at appropriate size
    int rootIndex = 0;                       // keeps track of the index of the root Node in the Node array
    String [] nodeData = new String[4];      // String array to hold the 4 values that are parsed on each line
    String line;                             // temporary String variable to hold each line that is read in from input

// accept command-line input for file name
    Scanner in = new Scanner(System.in);
    System.out.print("Enter file name containing node data: ");
    String file_name = in.nextLine();

// create and wrap FileReader in Buffered Reader objects to parse file
    BufferedReader input1st = new BufferedReader(new FileReader(file_name)); 
    BufferedReader input2nd = new BufferedReader(new FileReader(file_name));
    
// read input file, count number of lines - interpret as node count
    while (input1st.ready()) {
      line = input1st.readLine();
      nodecount++;
    }
    
    input1st.close();
   
// construct an array of Nodes using the node count above
    Node [] nodeArray = new Node[nodecount];
      
    
// - Parse through the input file again, this time constructing Node objects
//   line-by-line, passing the appropriate integer values to the constructor
// - Look out for the Node with "-1" as its parent --> assign it to the rootIndex
// - Insert each newly constructed Node into nodeArray, using its ID as its index
    while (input2nd.ready()) {
      line = input2nd.readLine();
      nodeData = line.split(" ");
      Node n = new Node (Integer.parseInt(nodeData[0]),Integer.parseInt(nodeData[1]),
                         Integer.parseInt(nodeData[2]),Integer.parseInt(nodeData[3]));
      
      if (Integer.parseInt(nodeData[1]) == -1) {
        rootIndex = Integer.parseInt(nodeData[0]);
      }
        
      nodeArray[Integer.parseInt(nodeData[0])] = n;   
    }    
    
    input2nd.close();
    
    
// - Perform In Order, Pre Order and Post Order Traversals
// - Pass the root Node and the node Array to each of the static methods
// - (see below at lines 132, 149 and 166 for the methods themselves) 
// - The outputs for each traversal are separated by a new line and "____"
   System.out.println("__________________________\n In Order Traversal");
   inorderTraverse(nodeArray[rootIndex], nodeArray);
   
   System.out.println("__________________________\n Pre Order Traversal");                   
   preorderTraverse(nodeArray[rootIndex], nodeArray);
   
   System.out.println("__________________________\n Post Order Traversal");
   postorderTraverse(nodeArray[rootIndex], nodeArray);                   

// - Calculating the height -- need an initial Node array containing just the root node   
   Node[] initialRoot = new Node[1];  
   initialRoot[0] = nodeArray[rootIndex];
  
// call the calcHeight method, passing it the initial root array, the whole nodeArray, 
// the initial root size, and the initial height (see line 200 for the static method and details)
   calcHeight (initialRoot, nodeArray, 1, 0);
  
  } // end of main method
  
  
  
//////////////////////////////////////////////////////////////////////////  
///////////////////// STATIC METHODS FOR TRAVERSALS ////////////////////// 
////////////////////////////////////////////////////////////////////////// 
  
// In Order Traversal: |-Go Left-|-Visit Current-|-Go Right-|
// Checks left, recursive call if left node exists (aka not equal to -1)
// If left node does not exist, visit current Node
// Check right, recursive call if right node exists
public static void inorderTraverse ( Node currentNode, Node[] nArray ) {
      
      if (currentNode.left != -1) {  
        inorderTraverse( nArray[currentNode.left], nArray );       
      } 
      
      System.out.println("Visiting " + currentNode);
      
      if (currentNode.right != -1) {
        inorderTraverse( nArray[currentNode.right], nArray);      
      }      
}  

// Pre Order Traversal: |-Visit Current-|-Go Left-|-Go Right-|
// Visit current Node
// Checks left, recursive call if left node exists 
// Check right, recursive call if right node exists
public static void preorderTraverse ( Node currentNode, Node[] nArray ) {
  
      System.out.println("Visiting " + currentNode); 
      
      if (currentNode.left != -1) {  
        preorderTraverse( nArray[currentNode.left], nArray );       
      } 
      
      if (currentNode.right != -1) {
        preorderTraverse( nArray[currentNode.right], nArray);     
      } 
  } 

// Post Order Traversal: |-Go Left-|-Go Right-|-Visit Current-|
// Checks left, recursive call if left node exists 
// Check right, recursive call if right node exists
// Visit current Node if left and right do not exist
public static void postorderTraverse ( Node currentNode, Node[] nArray ) {
          
      if (currentNode.left != -1) {  
        postorderTraverse( nArray[currentNode.left], nArray );       
      } 
      
      if (currentNode.right != -1) {
        postorderTraverse( nArray[currentNode.right], nArray);      
      }
      
      System.out.println("Visiting " + currentNode); 
} 
    





/////////////////////////////////////////////////////////////////////////////////
///////////////////// STATIC METHOD FOR CALCULATING HEIGHT ////////////////////// 
///////////////////////////////////////////////////////////////////////////////// 
// Iterates through the parent Nodes at each level of the tree, starting at the root level
// For each parent at the current level, checks for existence of left or right children
// If ANY children exist for ANY of the parent Nodes in the current level, hasNextLevel -> true
// Indices of these children are stored to build a new list of parents for the next recursive call
//
// If hasNextLevel is true, then the tree goes deeper ~ 
// -- height is incremented
// -- new array of parent Nodes is built for next recursion
// -- method is recursively called, using: 
//    the new array of parent Nodes, the same whole node Array, the new parent array size and the updated height
//
// If hasNextLevel is false, we have reached the lowest level ~
// -- print out the final height. DONE. 
public static void calcHeight ( Node[] currentParentArray, Node[] wholeNodeArray, int parentArraySize, int height ){

  boolean hasNextLevel = false;
  int nextparentArraySize = 0;
  int[] parentIndices = new int[parentArraySize*2];
  int p = 0;
  
  for (int i = 0; i < parentArraySize; i++) {
    if (currentParentArray[i].left != -1) { 
      hasNextLevel = true;
      nextparentArraySize++;
      parentIndices[p] = currentParentArray[i].left;
      p++;
    }
    if( currentParentArray[i].right != -1) {
      hasNextLevel = true;
      nextparentArraySize++;
      parentIndices[p] = currentParentArray[i].right;
      p++;
    }
  }
  
  if (hasNextLevel) {
    height++;
    Node[] nextParentArray = new Node[nextparentArraySize];
    int npaIndex = 0;
    for (int n = 0; n < nextparentArraySize; n++) { 
      nextParentArray[npaIndex] = wholeNodeArray[parentIndices[n]];
      npaIndex++;
    }
    calcHeight (nextParentArray, wholeNodeArray, nextparentArraySize, height); 
  } 
  
  if (!hasNextLevel) {
    System.out.println ("__________________________\n Height = " + height);
  }
    
}
  
} // end of class Binary_Tree_chimb
      
      
      