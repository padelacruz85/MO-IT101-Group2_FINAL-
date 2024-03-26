package main;

public class Printouts {
	
    public void PrintMainMenu(){
            System.out.print("""
            -------- SYSTEM MENU --------
            [1] Employee Details
            [2] Calculate Gross Wage
            [3] Calculate Net Wage
            [4] Time-IN
            [5] Time-OUT
            [0] EXIT
            -----------------------------
            SELECT: """);
     }
    
    public void PrintSubMenu(){
    System.out.print("""
            -------- SUB-MENU --------
            [1] Complete Employee Details
            [2] Find Employee Details
            -------------------------
            Choose: """);
    }

    public void PrintEmployeeDetails(){
        System.out.print("""
                -----------------------------
                    FULL EMPLOYEE DETAILS
                -----------------------------
                 """);
        System.out.print("""
                -------- SUB-MENU --------
                [1] Complete Employee Details
                [2] Find Employee Details
                -------------------------
                 """);
    }
    
    public void PrintEmployeeName(){
    	System.out.print("""
                -----------------------------
                    ENTER EMPLOYEE NAME
                -----------------------------
                 """);
    }
    
    public void PrintTimeIn(){
        System.out.print("""
                -----------------------------
                      EMPLOYEE TIME-IN
                -----------------------------
                 """);
    }
    
    public void PrintTimeOut(){
        System.out.print("""
                -----------------------------
                      EMPLOYEE TIME-OUT
                -----------------------------
                 """);
    }
  
    public void PrintLogin(){
  	  System.out.print("""
              -----------------------------
                  ENTER CREDENTIAL
              -----------------------------
               """);
    }

}
