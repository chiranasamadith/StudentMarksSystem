import java.util.*;

public class marksSystem{
	public static void main(String[] args){
		int[] pfMarksArray=new int[0];
		int[] dbmsMarksArray=new int[0];
		String[] stIdArray=new String[0];
		String[] stNameArray=new String[0];
		welcome(stIdArray,stNameArray,pfMarksArray,dbmsMarksArray);
	}
	public static void welcome(String[] stIdArray,String[] stNameArray,int[] pfMarksArray,int[] dbmsMarksArray){
		clearConsole();
		Scanner input=new Scanner(System.in);
		System.out.println("-------------------------------------------------------------------------------------------");
		System.out.println("|                       WELCOME TO GDSE MARKS MANAGEMENT SYSTEM                            |");
		System.out.println("-------------------------------------------------------------------------------------------");
		System.out.println();
		System.out.print("[1] Add New Student\t\t\t");
		System.out.println("[2] Add New Student With Marks");
		System.out.print("[3] Add Marks\t\t\t\t");
		System.out.println("[4] Update Student Details");
		System.out.print("[5] Update Marks\t\t\t");
		System.out.println("[6] Delete Student");
		System.out.print("[7] Print Student Details\t\t");
		System.out.println("[8] Print Student Ranks");
		System.out.print("[9] Best in Programming Fundementals\t");
		System.out.println("[10] Best in Database Management System");
		System.out.println();
		System.out.print("Enter an option to continue > ");
		int option =input.nextInt();
		input.nextLine();
		clearConsole();
		switch (option){
			case 1:
				addNewStudent(stIdArray,stNameArray,pfMarksArray,dbmsMarksArray);
				break;
			case 2:
				addNewStudentWithMarks(stIdArray,stNameArray,pfMarksArray,dbmsMarksArray);
				break;
			case 3:
				addMarks(stIdArray,stNameArray,pfMarksArray,dbmsMarksArray);
				break;
			case 4:
				updateDetails(stIdArray,stNameArray,pfMarksArray,dbmsMarksArray);
				break;
			case 5:
				updateMarks(stIdArray,stNameArray,pfMarksArray,dbmsMarksArray);
				break;
			case 6:
				deleteStudent(stIdArray,stNameArray,pfMarksArray,dbmsMarksArray);
				break;
			case 7:
				printStudentDetails(stIdArray,stNameArray,pfMarksArray,dbmsMarksArray);
				break;
			case 8:
				printStudentRanks(stIdArray,stNameArray,pfMarksArray,dbmsMarksArray);
				break;
			case 9:
				bestInPF(stIdArray,stNameArray,pfMarksArray,dbmsMarksArray);
				break;
			case 10:
				bestInDBMS(stIdArray,stNameArray,pfMarksArray,dbmsMarksArray);
				break;	
				}
	}
	public static int returnRankDBMS(String stId,String[] stIdArray,int[] pfMarksArray,int[] dbmsMarksArray){
		int rank=0;
		
		int[] sortedMarkArray=sort(temp(dbmsMarksArray));
		for (int i = 0; i < sortedMarkArray.length; i++)
		{
			if( (sortedMarkArray[i])==(dbmsMarksArray[indexOfId(stIdArray,stId)]) ){
				rank=i+1;
				break;
			}
		}return rank;
			
	}
	public static int returnRankPF(String stId,String[] stIdArray,int[] pfMarksArray,int[] dbmsMarksArray){
		int rank=0;
		int[] totalArray=new int[pfMarksArray.length];
		for (int i = 0; i < totalArray.length; i++)
		{
			totalArray[i]=pfMarksArray[i];
		}
		int[] sortedTotalArray=sort(temp(totalArray));
		for (int i = 0; i < sortedTotalArray.length; i++)
		{
			if ( (totalArray[indexOfId(stIdArray,stId)])==(sortedTotalArray[i]) ){
				rank=i+1;
				break;
			}
		}
		return rank;	
	}
	public static int returnRank(String stId,String[] stIdArray,int[] pfMarksArray,int[] dbmsMarksArray){
		int rank=0;
		int[] totalArray=new int[pfMarksArray.length];
		for (int i = 0; i < totalArray.length; i++)
		{
			totalArray[i]=pfMarksArray[i]+dbmsMarksArray[i];
		}
		int[] sortedTotalArray=sort(temp(totalArray));
		for (int i = 0; i < sortedTotalArray.length; i++)
		{
			if ( (totalArray[indexOfId(stIdArray,stId)])==(sortedTotalArray[i]) ){
				rank=i+1;
				break;
			}
		}
		return rank;	
	}
	public static int[] sort(int[] arr){
		for (int i = 0; i <arr.length-1; i++)
		{	
			int max_pos=i;
			for (int j = i+1; j < arr.length; j++)
			{
				if(arr[max_pos]<arr[j]){
					max_pos=j;
				}
			}
			int temp=arr[max_pos];
			arr[max_pos]=arr[i];
			arr[i]=temp;
		}
		return arr;
		
	}
	public static int[] temp(int[] arr){
		int[] arrayTemp=new int[arr.length];
		for (int i = 0; i < arr.length; i++)
		{
			arrayTemp[i]=arr[i];
		}
		return arrayTemp;
		
	}
	public static String printNValidate(String[] stIdArray,String[] stNameArray,int[] pfMarksArray,int[] dbmsMarksArray){
		Scanner input=new Scanner(System.in);
		System.out.println();
		
		System.out.print("Enter Student ID   : ");
		
		String stId=input.next();

		L1:while(true){
			if (studentExists(stIdArray,stId)){
				break L1;
			}else{
				System.out.print("Invalid student ID, Do you want to search again? (Y/n) " );
				String yesNo=input.next();
				if (yesNo.equals("n")){
					welcome(stIdArray,stNameArray,pfMarksArray,dbmsMarksArray);
				}else if(yesNo.equals("Y")){
					System.out.println();
					System.out.print("Enter Student ID   : ");
					stId=input.next();
					continue;
				}else{
					continue;
				}
			}
		}
		return stId;
		
	}
	
	public static String[] removeIdArray(String[] stIdArray,String stId){
		boolean x=false;
		String[] tempArray=new String[stIdArray.length-1];
		for (int i = 0; i < stIdArray.length; i++)
		{
			if(i==indexOfId(stIdArray,stId)){
				x=true;
				continue;
			}
			if (x){
				tempArray[i-1]=stIdArray[i];
			}else{
				tempArray[i]=stIdArray[i];
			}
		}
		return tempArray;
		
	}
	public static String[] removeArray(String[] arr,String[] stIdArray,String stId){
		boolean x=false;
		String[] tempArray=new String[arr.length-1];
		for (int i = 0; i < arr.length; i++)
		{
			if(i==indexOfId(stIdArray,stId)){
				x=true;
				continue;
			}
			if (x){
				tempArray[i-1]=arr[i];
			}else{
				tempArray[i]=arr[i];
			}
		}
		return tempArray;
	}
	public static int[] removeArray(int[] arr,String[] stIdArray,String stId){
		boolean x=false;
		int[] tempArray=new int[arr.length-1];
		for (int i = 0; i < arr.length; i++)
		{
			if(i==indexOfId(stIdArray,stId)){
				x=true;
				continue;
			}
			if (x){
				tempArray[i-1]=arr[i];
			}else{
				tempArray[i]=arr[i];
			}
		}
		return tempArray;
	}
	public static boolean studentExists(String[] stIdArray,String stId){
		for (int i = 0; i < stIdArray.length; i++)
		{
			if (stIdArray[i].equals(stId)){
				return true;
			}
		}return false;
		
	}
	public static String[] extendArray(String[] arr,String str){
		String[] tempArray=new String[arr.length+1];
		for (int i = 0; i < arr.length; i++)
		{
			tempArray[i]=arr[i];
		}
		tempArray[tempArray.length-1]=str;
		return tempArray;
	}
	public static int[] extendIntArray(int[] arr,int marks){
		int[] tempArray=new int[arr.length+1];
		for (int i = 0; i < arr.length; i++)
		{
			tempArray[i]=arr[i];
		}
		tempArray[tempArray.length-1]=marks;
		return tempArray;
	}
	public static boolean isMarksUpdated(int[] pfMarksArray,int i){
		if (((pfMarksArray.length)-1)==i){
			return true;
		}else{
			return false;
		}
	}
	public static int indexOfId(String[] stIdArray,String stId){
			for (int i = 0; i < stIdArray.length; i++)
			{
				if(stIdArray[i].equals(stId)){
					return i;
				}
				//couldnt think of a return number ,left -1 anyway
			}return -1;
				
	}
	public static void bestInDBMS(String[] stIdArray,String[] stNameArray,int[] pfMarksArray,int[] dbmsMarksArray){
		Scanner input=new Scanner(System.in);
		clearConsole();
		System.out.println("--------------------------------------------------------");
		System.out.println("|  	       BEST IN DATABASE MANAGEMENT SYSTEM      |");
		System.out.println("--------------------------------------------------------");
		System.out.println();
		//creating a rank array which consists of ranked ID's of best marks in PF
		String[] rankArray=new String[pfMarksArray.length];
		int ranked;
		for (int i = 0; i < pfMarksArray.length; i++)
		{
			ranked=returnRankDBMS((stIdArray[i]),stIdArray,pfMarksArray,dbmsMarksArray);
			while(rankArray[ranked-1]!=null){
				ranked++;
			}
			rankArray[(ranked-1)]=stIdArray[i];
		}

		System.out.println("+-----+------------------+--------------+--------------+");
		System.out.printf("|%-5s|%-18s|%-14s|%-14s|\n","ID","Name","DBMS Marks","PF Marks");
		System.out.println("+-----+------------------+--------------+--------------+");
		for (int i = 0; i < rankArray.length; i++)
		{
			int PF=pfMarksArray[indexOfId(stIdArray,rankArray[i])];
			int DBMS=dbmsMarksArray[indexOfId(stIdArray,rankArray[i])];
			System.out.printf("%s%-5s%s%-18s%s%14d%s%14s%s\n","|",rankArray[i],"|",stNameArray[indexOfId(stIdArray,rankArray[i])],"|",DBMS,"|",PF,"|");

		}
		System.out.println("+-----+------------------+--------------+--------------+");
		System.out.println();
		
		//exit or not
		System.out.print("Do you want to go back to main menu? (Y/n) ");
		String yesNo=input.next();
			
		while(true){
			if (yesNo.equals("Y")){
				welcome(stIdArray,stNameArray,pfMarksArray,dbmsMarksArray);
			}else{
				System.out.print("Do you want to go back to main menu? (Y/n) ");
				yesNo=input.next();
				continue;
				}
		}
	}
	public static void bestInPF(String[] stIdArray,String[] stNameArray,int[] pfMarksArray,int[] dbmsMarksArray){
		Scanner input=new Scanner(System.in);
		clearConsole();
		System.out.println("--------------------------------------------------------");
		System.out.println("|           BEST IN PROGRAMMING FUNDEMENTALS           |");
		System.out.println("--------------------------------------------------------");
		System.out.println();
		//creating a rank array which consists of ranked ID's of best marks in PF
		String[] rankArray=new String[pfMarksArray.length];
		int ranked;
		for (int i = 0; i < pfMarksArray.length; i++)
		{
			ranked=returnRankPF((stIdArray[i]),stIdArray,pfMarksArray,dbmsMarksArray);
			while(rankArray[ranked-1]!=null){
				ranked++;
			}
		rankArray[(ranked-1)]=stIdArray[i];
		
		}
		//printing the table
		System.out.println("+-----+------------------+--------------+--------------+");
		System.out.printf("|%-5s|%-18s|%-14s|%-14s|\n","ID","Name","PF Marks","DBMS Marks");
		System.out.println("+-----+------------------+--------------+--------------+");
		for (int i = 0; i < rankArray.length; i++)
		{
			int PF=pfMarksArray[indexOfId(stIdArray,rankArray[i])];
			int DBMS=dbmsMarksArray[indexOfId(stIdArray,rankArray[i])];
			System.out.printf("%s%-5s%s%-18s%s%14d%s%14s%s\n","|",rankArray[i],"|",stNameArray[indexOfId(stIdArray,rankArray[i])],"|",PF,"|",DBMS,"|");

		}
		System.out.println("+-----+------------------+--------------+--------------+");
		System.out.println();
		
		//exit or not
		System.out.print("Do you want to go back to main menu? (Y/n) ");
		String yesNo=input.next();
			
		while(true){
			if (yesNo.equals("Y")){
				welcome(stIdArray,stNameArray,pfMarksArray,dbmsMarksArray);
			}else{
				System.out.print("Do you want to go back to main menu? (Y/n) ");
				yesNo=input.next();
				continue;
				}
		}
		
		
	}
	public static void printStudentRanks(String[] stIdArray,String[] stNameArray,int[] pfMarksArray,int[] dbmsMarksArray){
		Scanner input=new Scanner(System.in);
		clearConsole();
		System.out.println("---------------------------------------------------------");
		System.out.println("|                 PRINT STUDENT'S RANKS	                |");
		System.out.println("---------------------------------------------------------");
		System.out.println();
		

		//making a rank list array with students ID being in the array
		String[] rankArray=new String[pfMarksArray.length];
		int ranked;

		for (int i = 0; i < pfMarksArray.length; i++)
		{
			ranked=returnRank((stIdArray[i]),stIdArray,pfMarksArray,dbmsMarksArray);
			while(rankArray[ranked-1]!=null){
				ranked++;
			}
			rankArray[(ranked-1)]=stIdArray[i];
		}
		//print the table
		int total;
		double avg;
		System.out.println("+-----+-----+------------------+--------------+------------+");
		System.out.printf("|%-5s|%-5s|%-18s|%-14s|%-12s|\n","Rank","ID","Name","Total Marks","Avg.Marks");
		System.out.println("+-----+-----+------------------+--------------+------------+");
		for (int i = 0; i < rankArray.length; i++)
		{
			total=pfMarksArray[indexOfId(stIdArray,rankArray[i])]+dbmsMarksArray[indexOfId(stIdArray,rankArray[i])];
			avg=total/2;
			System.out.printf("%s%-5d%s%-5s%s%-18s%s%14d%s%12s%s\n","|",(i+1),"|",rankArray[i],"|",stNameArray[indexOfId(stIdArray,rankArray[i])],"|",total,"|",avg,"|");

		}
		System.out.println("+-----+-----+------------------+--------------+------------+");
		System.out.println();
		
		
		
		System.out.print("Do you want to go back to main menu? (Y/n) ");
		String yesNo=input.next();
			
		while(true){
			if (yesNo.equals("Y")){
				welcome(stIdArray,stNameArray,pfMarksArray,dbmsMarksArray);
			}else{
				System.out.print("Do you want to go back to main menu? (Y/n) ");
				yesNo=input.next();
				continue;
				}
		}
	}
	public static void printStudentDetails(String[] stIdArray,String[] stNameArray,int[] pfMarksArray,int[] dbmsMarksArray){
		Scanner input=new Scanner(System.in);
		clearConsole();
		System.out.println("---------------------------------------------------------");
		System.out.println("|                PRINT STUDENT DETAILS	                |");
		System.out.println("---------------------------------------------------------");
		
		String stId=printNValidate(stIdArray,stNameArray,pfMarksArray,dbmsMarksArray);
		//print name
		String stName=stNameArray[indexOfId(stIdArray,stId)];
		System.out.println("Student name\t   : "+stName);

		//check whether the marks have been added before or not
		if (!(pfMarksArray.length>=indexOfId(stIdArray,stId)+1)){
			//if marks havent been added before
			System.out.println();
			System.out.print("Marks yet to be added .");
			System.out.println();
			System.out.println("Do you want to search another student details? (Y/n) ");
			String yesNo=input.next();
			
			while(true){
				if (yesNo.equals("Y")){
					printStudentDetails(stIdArray,stNameArray,pfMarksArray,dbmsMarksArray);
				}else if(yesNo.equals("n")){
					welcome(stIdArray,stNameArray,pfMarksArray,dbmsMarksArray);
				}else{
					System.out.print("Do you want to search another student details? (Y/n) ");
					yesNo=input.next();
					continue;
					}
				}
		}else{;
			int pfMarks=pfMarksArray[indexOfId(stIdArray,stId)];
			int dbmsMarks=dbmsMarksArray[indexOfId(stIdArray,stId)];
			int noOfStudents=pfMarksArray.length;
			int total=dbmsMarks+pfMarks;
			double avg=total/2;
			//find rank by creating a totalMarks array and sorting from maximum to minimum
			int rank=0;
			int[] totalArray=new int[pfMarksArray.length];
			for (int i = 0; i < totalArray.length; i++)
			{
				totalArray[i]=pfMarksArray[i]+dbmsMarksArray[i];
			}
			int[] sortedTotalArray=sort(temp(totalArray));
			for (int i = 0; i < sortedTotalArray.length; i++)
			{
				if (totalArray[indexOfId(stIdArray,stId)]==sortedTotalArray[i]){
						rank=i+1;
						break;
				}
			}
			System.out.println("+-----------------------------------+--------------+");
			System.out.print("|Programming Fundedmentals marks    |");
			System.out.printf("%15s %n", pfMarks+"|");
			System.out.print("|Database Management System Marks   |");
			System.out.printf("%15s %n",dbmsMarks+"|");
			System.out.print("|Total Marks                        |");
			System.out.printf("%15s %n",total+"|");
			System.out.print("|Avg. Marks                         |");
			System.out.printf("%15s %n", avg+"|");
			System.out.print("|Rank                               |");
			//should do something to add the rank in english word format
			//should do something to add the rank in english word format
			//should do something to add the rank in english word format
			System.out.printf("%15s %n",rank+"|");
			System.out.println("+-----------------------------------+--------------+");
			System.out.println();
			System.out.println("Do you want to search another student details? (Y/n) ");
			String yesNo=input.next();
			while(true){
				if (yesNo.equals("Y")){
					printStudentDetails(stIdArray,stNameArray,pfMarksArray,dbmsMarksArray);
				}else if(yesNo.equals("n")){
					welcome(stIdArray,stNameArray,pfMarksArray,dbmsMarksArray);
				}else{
					System.out.print("Do you want to search another student details? (Y/n) ");
					yesNo=input.next();
					continue;
					}
				}
			}
	}
	public static void deleteStudent(String[] stIdArray,String[] stNameArray,int[] pfMarksArray,int[] dbmsMarksArray){
		Scanner input=new Scanner(System.in);
		clearConsole();
		System.out.println("-----------------------------------------------------");
		System.out.println("|                  DELETE STUDENT	                |");
		System.out.println("-----------------------------------------------------");
		
		String stId=printNValidate(stIdArray,stNameArray,pfMarksArray,dbmsMarksArray);
		
		//deleting data
		stNameArray=removeArray(stNameArray,stIdArray,stId);
		if (pfMarksArray.length>=(indexOfId(stIdArray,stId)+1)){
			//delete data from mark arrays
			pfMarksArray=removeArray(pfMarksArray,stIdArray,stId);
			dbmsMarksArray=removeArray(dbmsMarksArray,stIdArray,stId);
			
		}
		//deleting student ID from ID array
		stIdArray=removeIdArray(stIdArray,stId);
		System.out.print("Student has been deleted successfully.\nDo you want to delete another student? (Y/n) ");
		String yesNo=input.next();
		
		while(true){
			if (yesNo.equals("Y")){
				deleteStudent(stIdArray,stNameArray,pfMarksArray,dbmsMarksArray);
			}else if(yesNo.equals("n")){
				welcome(stIdArray,stNameArray,pfMarksArray,dbmsMarksArray);
			}else{
				System.out.print("Student has been deleted successfully.\nDo you want to delete another student? (Y/n) ");
				yesNo=input.next();
				continue;
			}
		}	
		
	}
	public static void updateDetails(String[] stIdArray,String[] stNameArray,int[] pfMarksArray,int[] dbmsMarksArray){
		Scanner input=new Scanner(System.in);
		clearConsole();
		System.out.println("----------------------------------------------------------------------");
		System.out.println("|                       UPDATE STUDENT DETAILS	                     |");
		System.out.println("----------------------------------------------------------------------");
		
		String stId=printNValidate(stIdArray,stNameArray,pfMarksArray,dbmsMarksArray);
		
		//get the name from the Student name array and display
		String stName=stNameArray[indexOfId(stIdArray,stId)];
		System.out.println("Student name\t   : "+stName);
		System.out.println();
		System.out.print("Enter the new student name : ");
		stName=input.next();
		//updating name in the name array
		stNameArray[indexOfId(stIdArray,stId)]=stName;
		System.out.println();
		System.out.print("Student details has been updated successfully.\nDo you want to update another student details? (Y/n) ");
		String yesNo=input.next();
			if(yesNo.equals("Y")){
				updateDetails(stIdArray,stNameArray,pfMarksArray,dbmsMarksArray);
			}else{
				welcome(stIdArray,stNameArray,pfMarksArray,dbmsMarksArray);
			}
		
	}
	public static void updateMarks(String[] stIdArray,String[] stNameArray,int[] pfMarksArray,int[] dbmsMarksArray){
		Scanner input=new Scanner(System.in);
		clearConsole();
		System.out.println("----------------------------------------------------------");
		System.out.println("|                      UPDATE MARKS                      |");
		System.out.println("----------------------------------------------------------");
		
		String stId=printNValidate(stIdArray,stNameArray,pfMarksArray,dbmsMarksArray);
		
		String stName=stNameArray[indexOfId(stIdArray,stId)];
		System.out.println("Student name\t : "+stName);
		//check whether the marks have been added before or not
		
		if (!(pfMarksArray.length>=indexOfId(stIdArray,stId)+1)){
			//if marks havent been added before
				System.out.println();
				System.out.print("This student's marks yet to be added.");
				System.out.println("Do you want to add marks for another student? (Y/n) ");
				String yesNo=input.next();
	
				while(true){
					if (yesNo.equals("Y")){
					updateMarks(stIdArray,stNameArray,pfMarksArray,dbmsMarksArray);
				}else if(yesNo.equals("n")){
					welcome(stIdArray,stNameArray,pfMarksArray,dbmsMarksArray);
				}else{
					System.out.print("Do you want to add marks for another student? (Y/n) ");
					yesNo=input.next();
					continue;
					}
				}	
		  //if marks have been added before, then modify
		}else{
			int pfMarks=pfMarksArray[indexOfId(stIdArray,stId)];
			int dbmsMarks=dbmsMarksArray[indexOfId(stIdArray,stId)];
			System.out.println();
			System.out.println("Programming Fundementals Marks   : "+pfMarks);
			System.out.println("Database Management Marks\t : "+dbmsMarks);
			System.out.println();
			System.out.println();
			System.out.print("Enter new Programming Fundementals Marks : ");
			pfMarks=input.nextInt();
			//verifying marks
			while (!((0<=pfMarks) && (pfMarks<=100))){
				System.out.println("Invalid marks, please enter correct marks.");
				System.out.println();
				System.out.print("Enter new Programming Fundementals Marks   : ");
				pfMarks=input.nextInt();
			}
			//updating marks
			pfMarksArray[indexOfId(stIdArray,stId)]=pfMarks;
			
			System.out.println(Arrays.toString(pfMarksArray));
			//taking database marks
			System.out.print("Enter new Database Management Systems Marks : ");
			dbmsMarks=input.nextInt();
			while (!((0<=dbmsMarks) && (dbmsMarks<=100))){
				System.out.println("Invalid marks, please enter correct marks.");
				System.out.println();
				System.out.print("Enter new Database Management Marks\t : ");
				dbmsMarks=input.nextInt();
			}
			//updating marks 
			dbmsMarksArray[indexOfId(stIdArray,stId)]=dbmsMarks;

			System.out.println();
			System.out.print("Marks have been added successfully. Do you want to update marks for another student? (Y/n): ");
			String yesNo=input.next();
		
			while(true){
				if (yesNo.equals("Y")){
					updateMarks(stIdArray,stNameArray,pfMarksArray,dbmsMarksArray);
				}else if(yesNo.equals("n")){
					welcome(stIdArray,stNameArray,pfMarksArray,dbmsMarksArray);
				}else{
					System.out.print("Marks have been added successfully. Do you want to update marks for another student? (Y/n): ");
					yesNo=input.next();
					continue;
				}
			}	
			
			
		}
		
	}
	public static void addMarks(String[] stIdArray,String[] stNameArray,int[] pfMarksArray,int[] dbmsMarksArray){
		Scanner input=new Scanner(System.in);
		clearConsole();
		System.out.println("------------------------------------------");
		System.out.println("|                 ADD MARKS	         |");
		System.out.println("------------------------------------------");
		
		String stId=printNValidate(stIdArray,stNameArray,pfMarksArray,dbmsMarksArray);
		
		String stName=stNameArray[indexOfId(stIdArray,stId)];
		System.out.println("Student name\t : "+stName);
		//checks if marks already added to a relevant ID
		if (isMarksUpdated(pfMarksArray,indexOfId(stIdArray,stId))){
			System.out.print("This student's marks have been already added.\nIf you want to upadte the marks ,please use [4] Update Marks option.");
			System.out.println();
			System.out.println();
			System.out.println("Do you want to add marks for another student? (Y/n) ");
			String yesNo=input.next();
			if(yesNo.equals("Y")){
				addMarks(stIdArray,stNameArray,pfMarksArray,dbmsMarksArray);
			}else{
				welcome(stIdArray,stNameArray,pfMarksArray,dbmsMarksArray);
			}
		}
		
		System.out.println();
		System.out.print("Programming Fundementals Marks   : ");
		int pfMarks=input.nextInt();
		while (!((0<=pfMarks) && (pfMarks<=100))){
			System.out.println("Invalid marks, please enter correct marks.");
			System.out.println();
			System.out.print("Programming Fundementals Marks   : ");
			pfMarks=input.nextInt();
		}
		//adding marks to the array
		pfMarksArray=extendIntArray(pfMarksArray,pfMarks);
		//taking database marks
		System.out.print("Database Management Marks\t : ");
		int dbmsMarks=input.nextInt();
		while (!((0<=dbmsMarks) && (dbmsMarks<=100))){
			System.out.println("Invalid marks, please enter correct marks.");
			System.out.println();
			System.out.print("Database Management Marks\t : ");
			dbmsMarks=input.nextInt();
		}
		//adding marks to the array
		dbmsMarksArray=extendIntArray(dbmsMarksArray,dbmsMarks);
		System.out.print("Marks have been added. Do you want to add marks for another student? (Y/n) ");
		///
		String yesNo=input.next();
		
		while(true){
			if (yesNo.equals("Y")){
				addMarks(stIdArray,stNameArray,pfMarksArray,dbmsMarksArray);
			}else if(yesNo.equals("n")){
				welcome(stIdArray,stNameArray,pfMarksArray,dbmsMarksArray);
			}else{
				System.out.print("Marks have been added. Do you want to add marks for another student? (Y/n) ");
				yesNo=input.next();
				continue;
			}
		}	
		
	}
	public static void addNewStudentWithMarks(String[] stIdArray,String[] stNameArray,int[] pfMarksArray,int[] dbmsMarksArray){
		Scanner input=new Scanner(System.in);
		clearConsole();
		System.out.println("--------------------------------------------------------------------------");
		System.out.println("|                       ADD NEW STUDENT WITH MARKS                       |");
		System.out.println("--------------------------------------------------------------------------");
		System.out.println();
		System.out.print("Enter Student ID   : ");
		String stId=input.next();
		
		while (studentExists(stIdArray,stId)){
			System.out.println("The Student ID already exists");
			System.out.println();
			System.out.print("Enter Student ID   : ");
			stId=input.next();
		}
		stIdArray=extendArray(stIdArray,stId);
		
		System.out.print("Enter Student Name : ");
		String stName=input.next();
		stNameArray=extendArray(stNameArray,stName);
		System.out.println();
		//taking programming marks
		System.out.print("Programming Fundementals Marks   : ");
		int pfMarks=input.nextInt();
		while (!((0<=pfMarks) && (pfMarks<=100))){
			System.out.println("Invalid marks, please enter correct marks.");
			System.out.println();
			System.out.print("Programming Fundementals Marks   : ");
			pfMarks=input.nextInt();
		}
		//adding marks to the array
		pfMarksArray=extendIntArray(pfMarksArray,pfMarks);
		//taking database marks
		System.out.print("Database Management Marks\t : ");
		int dbmsMarks=input.nextInt();
		while (!((0<=dbmsMarks) && (dbmsMarks<=100))){
			System.out.println("Invalid marks, please enter correct marks.");
			System.out.println();
			System.out.print("Database Management Marks\t : ");
			dbmsMarks=input.nextInt();
		}
		//adding marks to the array
		dbmsMarksArray=extendIntArray(dbmsMarksArray,dbmsMarks);
		
		System.out.println();
		System.out.print("Student has been added successfully. Do you want to add a new student (Y/n): ");
		String yesNo=input.next();
		
		while(true){
			if (yesNo.equals("Y")){
				addNewStudentWithMarks(stIdArray,stNameArray,pfMarksArray,dbmsMarksArray);
			}else if(yesNo.equals("n")){
				welcome(stIdArray,stNameArray,pfMarksArray,dbmsMarksArray);
			}else{
				System.out.print("Student has been added successfully. Do you want to add a new student (Y/n): ");
				yesNo=input.next();
				continue;
			}
		}	
		
	}
	public static void addNewStudent(String[] stIdArray,String[] stNameArray,int[] pfMarksArray,int[] dbmsMarksArray){
		Scanner input=new Scanner(System.in);
		clearConsole();
		System.out.println("--------------------------------------------------------------------");
		System.out.println("|                       ADD NEW STUDENT                            |");
		System.out.println("--------------------------------------------------------------------");
		System.out.println();
		
		System.out.print("Enter Student ID   : ");
		String stId=input.next();
		
		while (studentExists(stIdArray,stId)){
			System.out.println("The Student ID already exists");
			System.out.println();
			System.out.print("Enter Student ID   : ");
			stId=input.next();
		}
		stIdArray=extendArray(stIdArray,stId);
		
		System.out.print("Enter Student Name : ");
		String stName=input.next();
		stNameArray=extendArray(stNameArray,stName);
		
		System.out.println();
		System.out.print("Student has been added successfully. Do you want to add a new student (Y/n): ");
		String yesNo=input.next();
		//yes or no
		while(true){
			if (yesNo.equals("Y")){
				addNewStudent(stIdArray,stNameArray,pfMarksArray,dbmsMarksArray);
			}else if(yesNo.equals("n")){
				welcome(stIdArray,stNameArray,pfMarksArray,dbmsMarksArray);
			}else{
				System.out.print("Student has been added successfully. Do you want to add a new student (Y/n): ");
				yesNo=input.next();
				continue;
			}
		}	
	}
	public final static void clearConsole(){
		
		try {
			final String os = System.getProperty("os.name"); 
			if (os.contains("Windows")) {
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			} else {
				System.out.print("\033[H\033[2J"); 
				System.out.flush();
			}
		} catch (final Exception e) {
			e.printStackTrace();
			// Handle any exceptions.
			}
	}



}
