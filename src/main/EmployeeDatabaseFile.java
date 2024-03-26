package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class EmployeeDatabaseFile extends EmployeeModel 
{  
	private static BufferedReader BufferReader;	
	private static String Line = ""; 
	private static int Count = 0;
	private static int Max = 999999999;
	private static String Delimiter = "[,]";
	
    public EmployeeDatabaseFile() throws Exception {
        String csvFile = "employee.csv";
        Employees = new EmployeeDetails[Max];
        buffer_reader(csvFile);
    }

    @Override
    public EmployeeDetails[] getEmployeeModelList() {
        return Employees;
    }
	
	
    private void buffer_reader(String file) throws Exception   
	{  
		try {
			BufferReader = new BufferedReader(new FileReader(file));
			while ((Line = BufferReader.readLine()) != null) {
				Scanner dataScanner = new Scanner(Line);
				dataScanner(dataScanner, Count);Count++;}
		} catch (FileNotFoundException e) {
		} 
	}
	
    private void dataScanner(Scanner scanner, int counter) {
        scanner.useDelimiter(Delimiter);
        while (scanner.hasNext()) {
        	EmployeeDetails Employee = new EmployeeDetails();
        	Employee.setEmpNo(scanner.next());
        	Employee.setLastName(scanner.next());
        	Employee.setFirstName(scanner.next());
        	Employee.setSssNo(scanner.next());
        	Employee.setPhilHealthNo(scanner.next());
        	Employee.setTinNo(scanner.next());
        	Employee.setPagibigNo(scanner.next());
        	Employee.setBasicSalary(Double.parseDouble(scanner.next()));
        	Employee.setRiceSubsidy(Double.parseDouble(scanner.next()));
        	Employee.setPhoneAllowance(Double.parseDouble(scanner.next()));
        	Employee.setClothingAllowance(Double.parseDouble(scanner.next()));
        	Employee.setHourlyRate(Double.parseDouble(scanner.next()));
        	Employees[Count] = Employee;
        }
        scanner.close();
    }

	
}