package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;


public class GrossWage extends CalculationFormat
{  
	private static String EmployeeData = "employee.csv";
	private static String TimeData = "time.csv";
	private static BufferedReader BufferLine;
	private static String Firstname = "";
	private static String Lastname= "";
	private static String Line = ""; 
	private static double TotalTime = 0;
	private static double GrossSalary = 0;
	private static int MaxRow = 0;
	private static int Count = 0;
	private static int PassFail=0;
	private static Printouts Print;
	
	public static BufferedReader buffer_reader(String file)   
	{  
		try {
			BufferLine = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
        return BufferLine;
	}
	
	public double WageCalculation()  
	{  
	  try   
			{  
			@SuppressWarnings("resource")
			Scanner scan = new Scanner (System.in); 
			Print = new Printouts();Print.PrintEmployeeName();
	        System.out.print("\nEnter First Name: ");Firstname= scan.next();
	        System.out.print("\nEnter Last Name: ");Lastname = scan.next();
				//GET EMPLOYEE DETAILS
				buffer_reader(TimeData);
				while ((Line = BufferLine.readLine()) != null) 
				{  
					Count++;
					if(Count != 0) {
						String[] row_data = Line.split(","); 						
						if (row_data[1].equalsIgnoreCase(Lastname) && row_data[2].equalsIgnoreCase(Firstname)) {
							TimeDifference time = new TimeDifference();
							TotalTime = time.getTimeDifference(row_data[3],row_data[4]);							
						}
						else {continue;}}} 
				///Get Employee Hour Rate
				Line = ""; 
				Count = 0;
				//GET MAX ROW
				buffer_reader(EmployeeData);
				while ((Line = BufferLine.readLine()) != null) {
					MaxRow++;
				}
				//GET EMPLOYEE DETAILS
				buffer_reader(EmployeeData);
				while ((Line = BufferLine.readLine()) != null) 
				{  
					Count++;
					if(Count != 0) {
						String[] row_data = Line.split(","); 
						if (row_data[1].equalsIgnoreCase(Lastname) && row_data[2].equalsIgnoreCase(Firstname)) {					     
						    //COMPUTE GROSS SALARY
						     GrossSalary = TotalTime * Double.parseDouble(row_data[11]);						 							 
						        System.out.println("""
						                ------------------------------------------           
						                Employee ID: %s
						                First Name: %s
						                Last Name: %s
						                Hourly Rate: PHP%s
						                Total Hours: %s
						                Gross Wage: PHP%s
						                ------------------------------------------
						                """.formatted(row_data[0].strip(),row_data[1],row_data[2], CalcFormat.format(Double.parseDouble(row_data[11])), TotalTime, CalcFormat.format(GrossSalary)));PassFail=1;}
						else if (Count==MaxRow && PassFail==0) {System.out.print(Firstname.toUpperCase()+" "+Lastname.toUpperCase()+ " has no employee record!\n");break;}
						else {continue;}}
				}
			}   
		catch (Throwable e) {e.printStackTrace();}
	return GrossSalary;
	}	
    
} 

