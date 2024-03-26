package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import account.account;

public class Login
{
    private static EmployeeModel EmployeeDetail;
    private static String TimeData = "time.csv";
    private static Scanner Scan = new Scanner(System.in);
    private static String TextFormat = "%-20s%-20s%-20s%-20s";
    private static String MenuItem = "";
    private static int Valid = 0;
    private static int Continuation = 1; 
    private static String Username = "";
    private static String Password = "";
    private static String Firstname = "";
    private static String Lastname = "";    
    private static String Line = "";
	private static final String Delimiter = ",";
	private static final String EndDelimiter = "|";
	private static final String Seperator = "\n";
	private static BufferedReader br;
	private static SimpleDateFormat Formatter;
	private static Date date;
	private static  FileWriter writer;
	private static  List<String> Values;
	private static Printouts Print;
	
	public static void main(String[] args) throws Throwable 
    {
      Scanner input = new Scanner (System.in);  
      Print = new Printouts();
      do{
    	  Print.PrintLogin();
	      System.out.println("Username: ");Username = input.nextLine();
	      System.out.println("Password: ");Password = input.nextLine();
	      account login = new account(Username, Password);
	      if(login.checkPassword()) {
	    	  EmployeeDetail();
	    	  MainMenu();
	    	  Valid=1;
	      }
	      else {
	          System.out.println("The Username and Password you entered are incorrect.");
	          
	      }
      	}while (Valid == 0);
      input.close();   
    }
    
	
    private static void MainMenu() throws Throwable {
        do{ 
            Print.PrintMainMenu();        	
            String option = Scan.next();     
            switch (option){
            	case "0":
            		System.exit(0);
                	break;
                case "1":
                    Print.PrintSubMenu();  
                    MenuItem = Scan.next();
                    System.out.println("***********************************************");
                    MenuItem(MenuItem);
                    break;     
                case "2":
                    CalculationFormat grossWage = new GrossWage();              
                    grossWage.WageCalculation();         
                    break;
                case "3":
                    CalculationFormat netWage = new NetWage();              
                    netWage.WageCalculation();         
                    break;
                case "4":
                	timeIn();       
                    break;
                case "5":
                	timeOut();       
                    break;
                default:
                    System.out.println("InValid Input!");
                    break;
            }
           System.out.println("\nRETURN TO MAIN MENU? [0] NO, [1] YES");
            Continuation = Scan.nextInt();
            }while (Continuation != 0);
        }
    

	private static void MenuItem(String menu){
	    switch (menu){case "1" -> completeEmployeesDetail();case "2" -> individualEmployeeDetail();}
	}

	private static void EmployeeDetail() throws Exception {
    		EmployeeDetail = new EmployeeDatabaseFile();
	}


    private static void completeEmployeesDetail() {
        EmployeeDetails[] EmployeeList = EmployeeDetail.getEmployeeModelList();
        Print.PrintEmployeeDetails();
        for (int a = 0; a < EmployeeList.length; a++) {
        	EmployeeDetails Employee = EmployeeList[a];
        	try {
            System.out.printf(TextFormat, Employee.getEmpNo().strip(),Employee.getLastName(),Employee.getFirstName(),Employee.getBasicSalary()+"\n");
        	} catch (Exception e) {break;}}
    }

    private static void individualEmployeeDetail() {
        EmployeeDetails[] EmployeeList = EmployeeDetail.getEmployeeModelList();
        Print.PrintEmployeeName();
        System.out.print("\nEnter First Name: ");Firstname= Scan.next();
        System.out.print("\nEnter Last Name: ");Lastname = Scan.next();Valid=0;
 
        for (int a = 0; a < EmployeeList.length; a++) {
        	EmployeeDetails Employee = EmployeeList[a];
        	try {
        		if (Firstname.equalsIgnoreCase(Employee.getFirstName().strip()) && Lastname.equalsIgnoreCase(Employee.getLastName().strip())) {
        			System.out.printf(TextFormat, Employee.getEmpNo().trim(),Employee.getLastName(),Employee.getFirstName(),Employee.getBasicSalary()+"\n");Valid=1;}
        	} catch (Exception e){
        		if (Valid==0) {System.out.print(Firstname.toUpperCase()+" "+Lastname.toUpperCase()+ " has no Employee record!\n");
        		}break;}}
    }
    
    
    private static void timeIn() {
        EmployeeDetails[] EmployeeList = EmployeeDetail.getEmployeeModelList();
        Print.PrintTimeIn();
        System.out.print("\nEnter First Name: ");Firstname= Scan.next();
        System.out.print("\nEnter Last Name: ");Lastname = Scan.next();Valid=0;
 
        for (int a = 0; a < EmployeeList.length; a++) {
        	EmployeeDetails Employee = EmployeeList[a];
        	try {
        		if (Firstname.equalsIgnoreCase(Employee.getFirstName().strip()) && Lastname.equalsIgnoreCase(Employee.getLastName().strip())) {
        			writeCsvFile(Employee.getEmpNo(), Firstname.toUpperCase(), Lastname.toUpperCase());Valid=1;}
        	} catch (Exception e){
        		if (Valid==0) {System.out.print(Firstname.toUpperCase()+" "+Lastname.toUpperCase()+ " has no Employee record!\n");
        		}break;}}
    }    
    
	public static void writeCsvFile(String EmpNo, String Name, String Last)
    { 
	    
		try {
			FileWriter fileWriter = null;
			fileWriter = new FileWriter(TimeData,true);
			fileWriter.append(EmpNo);fileWriter.append(Delimiter);
	        fileWriter.append(Name);fileWriter.append(Delimiter);
	        fileWriter.append(Last);fileWriter.append(Delimiter);
	        fileWriter.append(Formatter.format(date));fileWriter.append(Delimiter);
	        fileWriter.append(EndDelimiter);fileWriter.append(Seperator);
            System.out.print(Firstname.toUpperCase()+" "+Lastname.toUpperCase()+ " time recorded!\n");
            fileWriter.flush();
            fileWriter.close();
		}catch (IOException e) {e.printStackTrace();}
		
    }

    public static void timeOut() throws Throwable {
    	try {
    	    Formatter = new SimpleDateFormat("HH:mm:ss");  
		    date = new Date(); 
	        EmployeeDetails[] EmployeeList = EmployeeDetail.getEmployeeModelList();
	        Print.PrintTimeOut();
	        System.out.print("\nEnter First Name: ");Firstname= Scan.next();
	        System.out.print("\nEnter Last Name: ");Lastname = Scan.next();Valid=0;
	        for (int a = 0; a < EmployeeList.length; a++) {
	        	EmployeeDetails Employee = EmployeeList[a];
		        if (Firstname.equalsIgnoreCase(Employee.getFirstName().strip()) && Lastname.equalsIgnoreCase(Employee.getLastName().strip())) {
		        	   Valid=1;
	    		       List<String> Lines = new ArrayList<>();List<String> LinesUpdate = new ArrayList<>();
	    		       br = new BufferedReader(new FileReader(TimeData));
	    		       while((Line = br.readLine()) != null){
	    		            Values = Arrays.asList(Line.split(Delimiter));
	    		            LinesUpdate.clear();
	    		            for(int i = 0; i < Values.size(); i++) {
	    		                if ((Values.get(i).trim()).equals("|")) {
	    		                	LinesUpdate.add(Values.get(i).replace(EndDelimiter,Formatter.format(date)));
	    		                }else {
	    		                	LinesUpdate.add(Values.get(i));
	    		                }
	    		            }
	    		            Lines.add(LinesUpdate.toString());
	    		       }
	    		       writer = new FileWriter(TimeData);	    		       
	    		       for(int h = 0; h < Lines.size(); h++) {
	    		    	   String s = Lines.get(h).replaceAll("]", "");
	    		    	   writer.append(s);writer.append("\n");}
	    		       writer.flush();writer.close();
		        }
		      }     
    	}catch (Exception e){
    		if (Valid==0) {System.out.print(Firstname.toUpperCase()+" "+Lastname.toUpperCase()+ " has no Employee record!\n");
    		}
    	}
    }   
}