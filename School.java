 /**
 *School.java
 *This file is for making a school object 
 *@author Linghong Chen
*@version Last modified on May 9th, 2014
**/

 import java.util.*;

 class School implements Comparable<School>
{
	String schoolName;
	int score;
	String deadline; 
	String edDeadline;
	String eaDeadline;
	String otherDeadline;
	ArrayList<Integer> itemRating;
	ArrayList<String> itemNotes;

	School(String n, int s, String ra, ArrayList<Integer> rate, ArrayList<String> notes)
	{
		this.schoolName = n;
		this.score = s;	
		this.deadline = ra; 
		this.itemRating = rate; 
		this.itemNotes = notes;
	}

	//construct
	School(String n, int s, String ra, String ed, String ea,String od, ArrayList<Integer> rate, ArrayList<String> notes)
	{
		this.schoolName = n;
		this.score = s;	
		this.deadline = ra; 
		this.edDeadline = ed;
		this.eaDeadline = ea;
		this.otherDeadline = od;
		this.itemRating = rate; 
		this.itemNotes = notes;
	}

	//method
	public String getSchoolName()
	{
		return schoolName;
	}

	public void setSchoolName(String schoolName) 
	{
		this.schoolName = schoolName;
	}

	public int getScore()
	{
		return score;
	}

	public void setScore(int score) 
	{
		this.score = score;
	}

	public String getDeadline()
	{
		return deadline;
	}

	public void setDeadline(String deadline) 
	{
		this.deadline= deadline;
	}

	public String getEdDeadline()
	{
		return edDeadline;
	}

	public void setEdDeadline(String deadline) 
	{
		this.edDeadline= edDeadline;
	}

	public String getEaDeadline()
	{
		return eaDeadline;
	}

	public void setEaDeadline(String deadline) 
	{
		this.eaDeadline= edDeadline;
	}
	public String getOtherDeadline()
	{
		return otherDeadline;
	}

	public void setotherDeadline(String otherDeadline) 
	{
		this.otherDeadline= otherDeadline;
	}

	public ArrayList<Integer> getItemRating()
	{
		return itemRating;
	}

	public void setItemRating(ArrayList<Integer> itemRating) 
	{
		this.itemRating= itemRating;
	}

	public ArrayList<String> getItemNotes()
	{
		return itemNotes;
	}

	public void setItemNotes(ArrayList<String> itemRating) 
	{
		this.itemNotes= itemNotes;
	}

	//compare method
	public int compareTo(School compareSchool) 
	{
 
		int compareScore = ((School) compareSchool).getScore(); 
 
		//descending order
		return compareScore-this.score;
 	}	

}