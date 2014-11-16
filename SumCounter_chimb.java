/*
SumCounter_chimb.java
Author: Bryan Chim
Submission Date: 10/21/2013
- Main Method prompts user for an integer for which the count of its sum-partitions
will be calculated
- Input is read in and passed to a sumcount method via a constructed IntSums object
    - parameter k <directly from input> -> used as desiredsum in sumcount method
    - parameter j <k - 1> -> used as largestnum_in_partition in sumcount method
- Output is the resultant partition count as calculated by the sumcount method
*/

import java.util.Scanner;

public class SumCounter_chimb {

 public static void main (String [] args) {
 
  Scanner in = new Scanner(System.in);
  System.out.print("Enter integer to count partitions: ");
  int k = in.nextInt(); 
  int j  = k - 1;
 
  IntSums s = new IntSums();
  System.out.println(s.sumcount(k, j));
 }
}


/* 
IntSums class has a default constructor purely used for calling sumcount method
Starting at top of recursion tree with:
k = initial desiredsum, 
j = initial largestnum_in_partition
Base Case 1 ~ return 0 when recursed to the point where largest partition num = 0 
              OR when summand set exceeds desired sum (difference is < 0)
Base Case 2 ~ return 1 when the summand set sums to the desired sum              
             (the difference between a recursed summand set and the 
             desired sum = 0 -- satisfactory solution, bottom of tree)
             
RECURSIVE CALLS:
- Stems from summation of two initial calls using the initial input params, forming a branching recursive tree
- Basic Format ~  
      Left Branch: R(remaining sum, try next smaller number as summand [decrement largestnum_in_partiion]) + 
      Right Branch: R(resulting sum after substracting previous summand, current summand)
- * The left recursive branches keep the desiredsum from the last call, and try using the next smaller integer as a summand
- * The right recursive branches subtract the summand from the last desiredsum, and tries that same summand again
- * Once a branch reaches one of the base cases, it stops there and ...
- ** returns 0 if that summand set is greater than the initial desiredsum (desiredsum < 0)
     OR if next largestnum_in_partition is 0 (0 is an invalid summand)
- ** returns 1 if the sum of the summand set at that branch equals the initial desiredsum 
     (desiredsum = 0 after taking the difference)
- Final Result is essentially the sum of all the branches,
  with each of the 1s representing satisfactory summand sets, 0s being unsatisfactory
*/
class IntSums {

 IntSums () {}
 
 public int sumcount(int desiredsum, int largestnum_in_partition){
  
   if (largestnum_in_partition == 0 || desiredsum < 0) {
    return 0;
  }
    if (desiredsum == 0) {
    return 1;
  }

  return this.sumcount(desiredsum, largestnum_in_partition - 1) + this.sumcount(desiredsum - largestnum_in_partition, largestnum_in_partition);
 }
}




