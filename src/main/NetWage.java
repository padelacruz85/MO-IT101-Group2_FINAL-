package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class NetWage extends CalculationFormat
{  
	private static String EmployeeData = "employee.csv";
	private static String TimeData = "time.csv";
	private static BufferedReader buffer_Line;
	private static String Firstname = "";
	private static String Lastname= "";
	private static String Line = ""; 
	private static double TotalTime = 0;
	private static double TotalDeductions = 0;
	private static double GrossSalary = 0;
	private static double NetSalary = 0;
	private static int max_row = 0;
	private static int Count = 0;
	private static int PassFail=0;
	private static Printouts Print;	
	
	public static BufferedReader buffer_reader(String file)   
	{  
		try {
			buffer_Line = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
        return buffer_Line;
	}
	
	public double WageCalculation() {
		 try   
			{  
			@SuppressWarnings("resource")
			Scanner scan = new Scanner (System.in); 
			Print = new Printouts();Print.PrintEmployeeName();
	        System.out.print("\nEnter First Name: ");Firstname= scan.next();
	        System.out.print("\nEnter Last Name: ");Lastname = scan.next();
				//GET EMPLOYEE DETAILS
				buffer_reader(TimeData);
				while ((Line = buffer_Line.readLine()) != null) 
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
				while ((Line = buffer_Line.readLine()) != null) {
					max_row++;
				}
				//GET EMPLOYEE DETAILS
				buffer_reader(EmployeeData);
				while ((Line = buffer_Line.readLine()) != null) 
				{  
					Count++;
					if(Count != 0) {
						String[] row_data = Line.split(","); 
						if (row_data[1].equalsIgnoreCase(Lastname) && row_data[2].equalsIgnoreCase(Firstname)) {					     
						     GrossSalary = TotalTime * Double.parseDouble(row_data[11]);		
						     SalaryDeductions net = new SalaryDeductions(GrossSalary);
							 TotalDeductions = net.getTotalDeductions();
							 NetSalary = GrossSalary - TotalDeductions;
						     System.out.println("""
						                ------------------------------------------           
						                Employee ID: %s
						                First Name: %s
						                Last Name: %s
						                Hourly Rate: PHP%s
						                Total Hours: %s
						                Gross Wage: %s
						                Deductions: %s
						                Net Wage: PHP%s
						                ------------------------------------------
						                """.formatted(row_data[0].strip(),row_data[1],row_data[2], CalcFormat.format(Double.parseDouble(row_data[11])), TotalTime,  CalcFormat.format(NetSalary),  CalcFormat.format(TotalDeductions), CalcFormat.format(NetSalary)));PassFail=1;}
						else if (Count==max_row && PassFail==0) {System.out.print(Firstname.toUpperCase()+" "+Lastname.toUpperCase()+ " has no employee record!\n");break;}
						else {continue;}}
				}
			}   
		catch (Throwable e) {e.printStackTrace();}
	return NetSalary;
	}

} 

