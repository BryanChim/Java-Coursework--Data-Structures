// ********************************************************************************************
// MarryBC.java		Author: Bryan Chim		Section: 901	Date: 12/5/2011
// MarryBC generates an array of 20 Suitor objects based on the Suitor class.
// Suitor objects have a name, date of birth, height, weight and list of 5 preferred activities.
// Suitor data is read in and processed from a file containing lines of strings and integers
// corresponding to the above characteristics (in that order, a suitor per line).
// MarryBC then sorts through the Suitor objects, writing to 3 .dat files as follows
// 	AgeBC.dat - Suitors' names, DOB, height and weight, sorted oldest to youngest
// 	FatBC.dat - Suitors' names, height, weight and fat ratio, sorted skinniest to fattest
//	ActiveBC.dat - each activity name, followed by the names of Suitors who prefer it
//*********************************************************************************************

import java.util.Scanner;
import java.io.*;

public class MarryBC
{	
	public static void main(String[]args) throws FileNotFoundException
	{

// creation of array of Suitors and integer variables for array counting/printing
	Suitor[] suitor = new Suitor[20];
	int i = 0;
	int p = 0;

// creation of File object and Scanner object to read it
// while loop reads through each line in a uniform pattern, feeding data to Suitor objects
	File myFile = new File("suitor.dat");
	Scanner scan = new Scanner(myFile);
	while (scan.hasNext())
	{
		String line = scan.nextLine();
		line = line.replace("/",",");
		Scanner temp = new Scanner(line);
		temp.useDelimiter(",");
		suitor[i] = new Suitor();
		suitor[i].setName(temp.next());
		int x, y, z;
		x = temp.nextInt();
		y = temp.nextInt();
		z = temp.nextInt();
		suitor[i].setDOB(x, y, z);
		suitor[i].setHW(temp.nextInt(), temp.nextInt());
		while (temp.hasNext())
		{
			suitor[i].setActivity(temp.next());
		}
		i++;
	}

// bubble sort for age	
	Suitor temp = new Suitor();
	
	for(int j = 1; j < i; j++)
	{	
		for (int k = 0; k < (i-1); k++)
			if(suitor[k].younger(suitor[k + 1]))
			{
				temp = suitor[k];
				suitor[k] = suitor[k + 1];
				suitor[k + 1] = temp;
			}
	}
	

// creation of AgeBC.dat file and PrintWriter to write Suitor data sorted by date of birth		
	File ageFile = new File("AgeBC.dat");
	PrintWriter ageWrite = new PrintWriter(ageFile);
	
	while (p < i)
	{
		ageWrite.println(suitor[p].getName() + ", " + suitor[p].getDOBmonth() + "/" + suitor[p].getDOBday() + "/" + suitor[p].getDOByear() + ", " + suitor[p].getHeight() + ", " + suitor[p].getWeight() + ".");
		p++;
	}
	ageWrite.flush();
	ageWrite.close();
	
	for(int j = 1; j < i; j++)
	{	
		for (int k = 0; k < (i-1); k++)
			if(suitor[k].fatter(suitor[k + 1]))
			{	
				temp = suitor[k];
				suitor[k] = suitor[k + 1];
				suitor[k + 1] = temp;
			}
	}

// creation of FatBC.dat file and PrintWriter to write Suitor data sorted by weight/height ratio	
	File fatFile = new File("FatBC.dat");
	PrintWriter fatWrite = new PrintWriter(fatFile);
	p = 0;
	while (p < i)
	{
		fatWrite.println(suitor[p].getName() + ", " + suitor[p].getHeight() + ", " + suitor[p].getWeight() + ", " + suitor[p].getRatio() + ".");
		p++;
	}
	fatWrite.flush();
	fatWrite.close();	
	
// creation of ActiveBC.dat file and PrintWriter to write out each possible activity and list Suitors that prefer each one
	File actFile = new File("ActiveBC.dat");
	PrintWriter actWrite = new PrintWriter(actFile);

// ActiveChecks method reads through each Suitor's list of activities and prints their name if it matches (middle string parameter)
	ActiveChecks (suitor, "reading", actWrite);
	ActiveChecks (suitor, "travel", actWrite);
	ActiveChecks (suitor, "archery", actWrite);
	ActiveChecks (suitor, "horses", actWrite);
	ActiveChecks (suitor, "dogs", actWrite);
	ActiveChecks (suitor, "movies", actWrite);
	ActiveChecks (suitor, "gardening", actWrite);
	ActiveChecks (suitor, "hunting", actWrite);
	ActiveChecks (suitor, "football", actWrite);
	ActiveChecks (suitor, "baseball", actWrite);
	ActiveChecks (suitor, "fine dining", actWrite);
	ActiveChecks (suitor, "skiing", actWrite);
	actWrite.flush();
	actWrite.close();		
			
	} // end of main method




public static void ActiveChecks (Suitor[] sa, String s, PrintWriter pw)
{	
	int p = 0;
	int i = 20;
	pw.println(s);
	while (p < i)
	{
		if (sa[p].activityCheck(s))
			pw.println(sa[p].getName());
		p++;
	}
	
	p = 0;
	pw.println();
	
}
	
} // end of MarryBC class


// creation of Suitor class
class Suitor
{
	String name;
	int DOBmonth;
	int DOBday;
	int DOByear;
	int weight;
	int height;
	String[] activity = new String[5];
	int actCount;

// constructor
	Suitor ()
	{actCount = 0;
	}
	
// setters

	public void setName(String s)
		{name = s;
		}
	
	public void setDOB(int i, int j, int k)
		{DOBmonth = i;
		DOBday = j;
		DOByear = k;
		}
	
	public void setHW(int i, int j)
		{height = i;
		weight = j;
		}
	
	public void setActivity(String s)
		{activity[actCount] = s;
		actCount++;
		}

//getters
	public String getName()
		{return name;
		}
	
	public int getDOBday()
		{return DOBday;
		}

	public int getDOBmonth()
		{return DOBmonth;
		}
		
	public int getDOByear()
		{return DOByear;
		}
		

	public int getHeight()
		{return height;
		}
		
	public int getWeight()
		{return weight;
		}
				
	public String getActivity (int i)
		{return activity[i];			
		}

// activityCheck method is used in ActiveChecks to search through Suitor activities and return true if there is a string match		
	public boolean activityCheck (String s)
	{
		boolean hasActivity = false;
		int i = 0;
		while (!hasActivity && i < 5)
		{
			if (activity[i].equals(s))
				 hasActivity = true;
				 
				i++;
		}	
		return hasActivity;
	}
		
	public float getRatio ()
		{return ((float)weight/(float)height);
		}

// fatter method compares weight/height ratios of 2 suitors
	public boolean fatter (Suitor s)
		{if (this.getRatio() > s.getRatio())
			return true;
		else return false;
		}

// younger method compares age of 2 suitors	
	public boolean younger (Suitor s)
		{boolean yn;
		yn = (this.getDOByear() > s.getDOByear());
		if (this.getDOByear() == s.getDOByear())
			{if (this.getDOBmonth() > s.getDOBmonth())
				yn = true;
			if (this.getDOBmonth() < s.getDOBmonth())
				yn = false;
			if (this.getDOBmonth() == s.getDOBmonth())
				{if (this.getDOBday() > s.getDOBday())
					yn = true;
				else yn = false;
				}
			}
		return yn;
		}
		
	public String toString()
		{String str = name + ", " + DOBmonth + "/" + DOBday + "/" + DOByear + ", " + height + ", " + weight + ", " + this.getRatio() + ", " + activity[0] + ", " + activity[1] + ", " + activity[2] + ", " + activity[3] + ", " + activity[4];
		return str;
		}

} // end of Suitor class
		
			
			
			
			
			
			
			
			
			
			
			
			