//ControlPanel.java
/**
* This is a project that can help students select best fit colleges for them to apply to.
* The program first asks users to fill out the items that they would think importnat to them when they considering colleges, 
* and also give each item an important score between 1-5 
* (when the program calculates the total rating scorce for all the items they selected for a school,
* 5 will be weighted 5 times important as 1 does, i.e. for 5, the rating score for each item for a particular school will be multipied by 5 times; 
* for 1, it will be multipied by 1 time, so on).
* Users then give each school that are on their primitive school choice list a rating score for each of its comparing items.
* The program computes the total rating scores and sort the school from highest score to lowest score.
* Users then can further narrow down the school choice by different submission deadlines to eliminate school bnumber to a handful of schools  
* that they are able to handle well in each short period of time. (such as schools with early admission deadline and late deadline should be grouped sepeartely, etc.)
* By this way, the program helps students find the best fit colleges to apply.
* Once students pick up their final school choice, the app can also serve as a reminder of various deadline and useful school information.
*@author Linghong Chen
*@version Last modified on May 9th, 2014
**/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class ControlPanel 
{
	public static void main(String [] args)
	{
	 //add frame for adding schools
	 JFrame controlPanel = new JFrame("College Selection Helper");
	 ContentPanel panel = new ContentPanel();
	 controlPanel.setContentPane(panel); 
	 controlPanel.setSize(900,800);
	 controlPanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 controlPanel.setVisible(true);
	}
}

class ContentPanel extends JPanel implements ActionListener
{
	JPanel content;
	JPanel titleArea;
	JPanel schoolArea;
	JPanel buttonArea;
	JLabel appTitle1;
	JLabel appTitle2;
	JButton addSchool; 
	JButton addItems;
	JButton compareSchool;  

	//for the table of rating items
	ArrayList <JTextField> itemNameField = new ArrayList <JTextField>();
	ArrayList <JTextField> itemScoreField = new ArrayList <JTextField>();

	JButton addMore;
	JButton submitAddItem;
	int itemNumber=0;

	
	//for arrayList that collect the information from the rating items table
	ArrayList<String> itemName = new ArrayList<String>();
	ArrayList<Integer> itemScore = new ArrayList<Integer>();

	//for each school's rating
	JTextField [] itemRatingField;
	JTextField [] itemNoteField; 
	JTextField schoolNameField;
	JButton add;

	JLabel deadlineLabel;
	JTextField deadlineField;
	JLabel edDeadlineLabel;
	JTextField edDeadlineField;
	JLabel eaDeadlineLabel;
	JTextField eaDeadlineField;
	JLabel otherDeadlineLabel;
	JTextField otherDeadlineField;

	//for collecting information from each school's rating
	String schoolName;
	String deadline;
	String edDeadline;
	String eaDeadline; 
	String otherDeadline; 
	
	ArrayList<School> schools = new ArrayList<School>();
	//for compare schools 
	JButton[] delete;
	JPanel[] conArea;
	JPanel[] schArea; 
	JPanel[] tiArea;
	JButton saveList;
	JLabel[] score; 

	public ContentPanel()//construct
	{
		//set menu Area and content area
		JPanel menu =new JPanel();
		add(menu, BorderLayout.NORTH);
		content = new JPanel();
		add(content, BorderLayout.SOUTH);

		//for menu
		menu.setBackground(Color.YELLOW);
		menu.setLayout(new GridLayout(1,3));
		addSchool = new JButton("Add Schools");
		addItems = new JButton("Add Comparing Items");
		compareSchool = new JButton("Compare Schools");
		JButton schoolList = new JButton("Make the school List");		
		menu.add (addItems);
		menu.add (addSchool);
		menu.add (compareSchool);
		menu.add (schoolList);
		addItems.addActionListener(this);
		addSchool.addActionListener(this);
		compareSchool.addActionListener(this);

		//content Area has three areas
		content.setLayout(new BorderLayout());
		titleArea = new JPanel();
		content.add(titleArea, BorderLayout.NORTH);
		schoolArea = new JPanel();
		content.add(schoolArea, BorderLayout.CENTER);
		buttonArea = new JPanel();
		content.add(buttonArea, BorderLayout.SOUTH);	

		//set title 
		titleArea.setLayout(new GridLayout(2,1));
		appTitle1 = new JLabel(" This is an App that helps students select best fit colleges. ");
		appTitle2 = new JLabel(" To start, click the Add Comparing Items button.");
		appTitle1.setFont(new Font("Serif", Font.PLAIN, 30));
		appTitle2.setFont(new Font("Serif", Font.PLAIN, 20));
		titleArea.add(appTitle1);
		titleArea.add(appTitle2);		
	}		

	//method for alerting users
	public static void	alert(String alertContent)  
	{
		JFrame alert = new JFrame("Warning");
		alert.setSize(800,150);
		alert.setVisible(true);
		JLabel warn= new JLabel(alertContent);
		alert.add(warn);
	}

	//show functions for buttons
	public void actionPerformed(ActionEvent e)
	{			
		/**
		*------------------add a table for inputing comparing items used for comparing Colleges----------------------------------
		**/
		if(e.getSource()==addItems)
		{	
	 	//add frame for adding schools
	 	JFrame addItemsFrame = new JFrame("Table for Colledge Comparing Items");
	 	addItemsFrame.setSize(700,300);
	 	
		//content Area has three areas
		addItemsFrame.setLayout(new BorderLayout());
		titleArea = new JPanel();
		addItemsFrame.add(titleArea, BorderLayout.NORTH);
		schoolArea = new JPanel();
		addItemsFrame.add(schoolArea, BorderLayout.CENTER);
		buttonArea = new JPanel();
		addItemsFrame.add(buttonArea, BorderLayout.SOUTH);	

		//clear and reset the content area		
		//titleArea.removeAll();
		// schoolArea.removeAll();
		//buttonArea.removeAll();	
		//titleArea.repaint();
		//schoolArea.repaint();
		//buttonArea.repaint();	
		
		//set up a new title to instruct user what they need to do
		JLabel title = new JLabel("Write down items you want to compare among colleges on the table below.");
		title.setFont(new Font("Serif", Font.PLAIN, 20));
		titleArea.add(title);

		schoolArea.setLayout(new GridLayout(4,2));
		JLabel itemNameLabel = new JLabel("Items you want to compare among colleges");
		JLabel itemScoreLabel = new JLabel("Importnace score for each item (use 1 to 5 to rate)");
		schoolArea.add(itemNameLabel);
		schoolArea.add(itemScoreLabel);
		
		for (int i=itemNumber; i<itemNumber+3; i++)		//add three items one time, if not enough, can click 2nd time again
		{
			itemNameField.add(new JTextField());
			itemScoreField.add(new JTextField());
			schoolArea.add(itemNameField.get(i));	
			schoolArea.add(itemScoreField.get(i));
		}	
		 		
			//button for adding comparing items
			submitAddItem = new JButton("Add all the above items to the system");
			buttonArea.add(submitAddItem);
			submitAddItem.addActionListener(this);
	 		
	 		addItemsFrame.setVisible(true);

	 		//each time six rows
	 		itemNumber=itemNumber+3;		
		}		

		//collect information from Add Items table
		 else if(e.getSource()==submitAddItem)
	 	{
			try
			{				 				
				//get comparing items
				for( int i=itemNumber-3; i<itemNumber; i++)
				{					
					itemName.add(itemNameField.get(i).getText());
					int score = Integer.parseInt(itemScoreField.get(i).getText());
					if(score>5 || score<1)
					{
						throw new NumberOutOfBoundsException();
					}
					itemScore.add(score);
				}

				alert("Please close the Colledge Comparing Items table, then click Add Items to add more items or click Add School button to go to next step.");
				titleArea.removeAll();
		 		schoolArea.removeAll();
		 		buttonArea.removeAll();	
			}
			
			catch (NumberOutOfBoundsException nobe)
			{
				alert("Number must between 1-5.");
			}

			catch (NullPointerException npe)
			{
				alert("Please double check whether you have filled all of the fields you want to fill.");
			}
		
			catch (NumberFormatException cne)
			{
				alert("You should add correct format.");
			}
		}
		
		/** 
		*------------------------------------table for add schools and get information from the table-------------------------------------
		**/
 		else if(e.getSource()==addSchool)
 		{ 	
	 		//add frame for adding schools
		 	JFrame addSchoolsFrame = new JFrame("School Information Table");
		 	addSchoolsFrame.setSize(900,500);
			//content Area has three areas
			
			addSchoolsFrame.add(titleArea, BorderLayout.NORTH);
			addSchoolsFrame.add(schoolArea, BorderLayout.CENTER);
			addSchoolsFrame.add(buttonArea, BorderLayout.SOUTH);	

			//titleArea.removeAll();
		 	//schoolArea.removeAll();
		 	//buttonArea.removeAll();	
			//titleArea.repaint();
		 	//schoolArea.repaint();
		 	//buttonArea.repaint();	
					
			//for school name
	
			titleArea.setLayout(new GridLayout(5,2));	
			JLabel schoolNameLabel = new JLabel("Please write down the school name you want to rate.");
			schoolNameField= new JTextField();	
			titleArea.add(schoolNameLabel);
			titleArea.add(schoolNameField);

			//for deadlines			
			deadlineLabel = new JLabel("Regular application deadline");
			deadlineField= new JTextField();		
			edDeadlineLabel = new JLabel("Early application deadline(optional)");
			edDeadlineField= new JTextField();			
			eaDeadlineLabel = new JLabel("Early admission deadline(optional)");
			eaDeadlineField= new JTextField();	
			otherDeadlineLabel = new JLabel("Other important deadline");	
			otherDeadlineField = new JTextField();	
			titleArea.add(deadlineLabel);
			titleArea.add(deadlineField);
			titleArea.add(edDeadlineLabel);
			titleArea.add(edDeadlineField);
			titleArea.add(eaDeadlineLabel);
			titleArea.add(eaDeadlineField);
			titleArea.add(otherDeadlineLabel);
			titleArea.add(otherDeadlineField);

			try
			{
				int size= itemName.size(); //since the user may only fill several of them, so you shouldn't use 6 as size	
				schoolArea.setLayout(new GridLayout(size+1,3));

				//for the top line the comparing items rating table
				JLabel name =new JLabel("Item name");
				JLabel rating =new JLabel("Your rating score 1-10 (higher number is better)");
				JLabel notes =new JLabel("Explain why you give this rating number");
				schoolArea.add(name);
				schoolArea.add(rating);
				schoolArea.add(notes);
			
				//items and its rating scores
				JLabel[] itemLabel  = new JLabel[size];	
				itemRatingField  = new JTextField[size] ;
				itemNoteField = new JTextField[size];
		
				for(int i=0; i<size;i++)
				{						
					itemLabel[i]= new JLabel(itemName.get(i));
					itemRatingField[i] = new JTextField();
					itemNoteField[i] = new JTextField();
					schoolArea.add(itemLabel[i]);
					schoolArea.add(itemRatingField[i]);
					schoolArea.add(itemNoteField[i]);
				}				
			}
	
			catch (IndexOutOfBoundsException iobe)
			{
				alert("Index Out Of Bounds Exception.");
			}
			catch (NullPointerException npe)
			{
				alert("Please first click the AddItems button to fill out the items you want to compare among colleges.");
			}

			//for submission button										
			add = new JButton("Adding this School");
			buttonArea.add(add);
			add.addActionListener(this);

			addSchoolsFrame.setVisible(true);
		}

		//reaction for click "add" button, get information for add schools table
		else if(e.getSource()==add)
		{
			try
			{
				//clear contentArea
				titleArea.removeAll();
		 		schoolArea.removeAll();
		 		buttonArea.removeAll();	
				for(int i=0; i<itemNumber;i++)
				{						
					itemRatingField[i].removeAll(); 
					itemNoteField[i].removeAll();
				}

		  
			    //get school Name and deadline
			    schoolName = schoolNameField.getText();			    
			    deadline = deadlineField.getText();
			    if(edDeadlineField.getText()!="N/A") edDeadline = edDeadlineField.getText();
			    else edDeadline = "";
  				if(eaDeadlineField.getText()!="N/A") eaDeadline = eaDeadlineField.getText();		   	
				else eaDeadline = "";
				if(otherDeadlineField.getText()!="N/A") otherDeadline = otherDeadlineField.getText();
			    else otherDeadline = "";
			   	ArrayList<Integer> itemRating  = new ArrayList<Integer>();
				ArrayList<String> itemNotes = new ArrayList<String>();


			    //get rating for each item
			    for(int i=0; i<itemName.size();i++)
				{	
					
					itemRating.add(Integer.parseInt((itemRatingField[i]).getText()));
					itemNotes.add((itemNoteField[i]).getText());
					if(Integer.parseInt((itemRatingField[i]).getText())>10 || Integer.parseInt((itemRatingField[i]).getText())<1)
					throw new NumberOutOfBoundsException();		
				}
				
				//calculate this school's score
				int score = 0;
				for(int i=0; i<itemName.size(); i++)
				{
					score = score+itemRating.get(i)*itemScore.get(i);
				}

				schools.add(new School(schoolName, score, deadline, edDeadline, eaDeadline, otherDeadline, itemRating, itemNotes));
				alert("Please close the School Information table, then click Add School to add another school. When you are done, click Compare Schools button to go to next step."); 
			}

			catch ( NumberOutOfBoundsException nobe)
			{
				alert("You should fill in number between 1-10");
			}

			catch (NumberFormatException nfe)
			{
				alert("You should fill in right formate of the words");
			}
			catch (NullPointerException npe)
			{
				alert("Please add N/A on empty column.");
			}
			catch (ArrayIndexOutOfBoundsException aiobe)
			{
				alert("Array Index Out Of Bounds");
			}
		}

		/**
		*----------compare and sort schools as the score from high to low, then eliminate some school, get the final school list----------------
		**/
		else if(e.getSource()==compareSchool)
 		{ 		
	 		//add frame for adding schools
	 		JFrame compareSchoolsFrame = new JFrame("Compare Schools Frame");
	 		compareSchoolsFrame.setSize(700,900);
			
			JPanel 	saveListPanel = new JPanel();
			JPanel 	schoolPanel = new JPanel();
			compareSchoolsFrame.add(saveListPanel, BorderLayout.NORTH);
			compareSchoolsFrame.add(schoolPanel,BorderLayout.CENTER);
			
			JScrollPane scrollp = new JScrollPane(schoolPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			compareSchoolsFrame.add(scrollp);  
	 		//sort Schools
	 		Collections.sort(schools);

	 		int schoolNumber= schools.size();
	 		if (schoolNumber>0)
	 		{			 	
		 		if (schoolNumber>15) schoolNumber=15; //only show no more than 15 schools

		 		//for saveList Button
				saveList = new JButton("Save the final school list.");
				saveListPanel.add(saveList);
				saveList.addActionListener(this);
					
		 		//for schoolPanel
				schoolPanel.setLayout(new GridLayout(schoolNumber,1));
				
		 		//arraylist used for holding each school information
		 		conArea =new JPanel[schoolNumber];		//JPanel hold each school information, schArea, tiArea, delete button				
				schArea =new JPanel[schoolNumber];
				tiArea =new JPanel[schoolNumber];
				delete = new JButton[schoolNumber];
 				score = new JLabel[schoolNumber]; 
		 		for (int i=0; i<schoolNumber; i++)
				{
					conArea[i] = new JPanel();
					schoolPanel.add(conArea[i]);

					conArea[i].setLayout(new BorderLayout());
					tiArea[i] = new JPanel();
					schArea[i] = new JPanel();	
					conArea[i].add(tiArea[i], BorderLayout.NORTH);
					conArea[i].add(schArea[i], BorderLayout.SOUTH);

					//school information
					tiArea[i].setLayout(new GridLayout(6,2));	
					JLabel schoolname=new JLabel(schools.get(i).schoolName);					
					delete[i] = new JButton("Delete this School");
					JLabel scoreLabel = new JLabel ("School Score");
					score[i] = new JLabel(Integer.toString(schools.get(i).score));
					JLabel raLabel = new JLabel("RA Deadline");
					JLabel edLabel = new JLabel("ED Deadline");
					JLabel eaLabel= new JLabel("EA Deadline");
					JLabel odLabel= new JLabel("Other Important Deadline");
					JLabel ra = new JLabel(schools.get(i).deadline);					
					JLabel ed = new JLabel(schools.get(i).edDeadline);				
					JLabel ea= new JLabel(schools.get(i).eaDeadline);
					JLabel od= new JLabel(schools.get(i).otherDeadline);
					
		 			//school name
		 			tiArea[i].add(schoolname);
		 			tiArea[i].add(delete[i]);

		 			//for school's total score
		 			tiArea[i].add(scoreLabel);
		 			tiArea[i].add(score[i]);
		 			
		 			//for deadlines
		 			tiArea[i].add(raLabel);
		 			tiArea[i].add(ra);
		 			tiArea[i].add(edLabel);
		 			tiArea[i].add(ed);
		 			tiArea[i].add(eaLabel);
		 			tiArea[i].add(ea);
		 			tiArea[i].add(odLabel);
		 			tiArea[i].add(od);
		 			
		 			delete[i].addActionListener(this);
/*
		 			//school rating infomation 
					schArea[i].setLayout(new GridLayout(itemName.size(),3));
					for( int j = 0; j<itemName.size(); j++)
					{
						JLabel iName = new JLabel(itemName.get(j));
						JLabel iRating = new JLabel(Integer.toString(schools.get(i).itemRating.get(j)));
						JLabel iNotes = new JLabel (schools.get(i).itemNotes.get(j));
						schArea[i].add(iName);
						schArea[i].add(iRating);
		 				schArea[i].add(iNotes);
					}
	*/
				}
				compareSchoolsFrame.setVisible(true);
			}
			else
			{
				alert("No School Founded.");
			}
		}

		//save final schoolList
		else if(e.getSource()==saveList)
		{ 	
			//remove information from school
			alert("Your Schools list has been saved.\n To check the final school list, click Compare Schools buttons again. \n Want to add more schools: just click Add Schools button. \n However, the APP only show the top 15 highest score schools.");
	 	}	

	 	//when delete a school information
		else 
		{
			for( int i = 0; i<schools.size();i++)
			{
				if(e.getSource()==delete[i])
		 		{ 	

					//remove information from school
					schools.remove(i);
					alert("Your School information has been removed.");
					//remove school[i], and save it to removeSchool;
		 		}	
		 	}	
	 	}		 	
	}
}

//NumberOutOfBoundsException
class NumberOutOfBoundsException extends Exception
{
	 public NumberOutOfBoundsException()
	 {
	  	super();
	 }
}
