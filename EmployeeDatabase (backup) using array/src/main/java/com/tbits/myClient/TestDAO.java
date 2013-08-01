package com.tbits.myClient;

import com.tbits.common.Employee;
import com.tibits.dAoEmployee.EmployeeDAO;

public class TestDAO {

    public static void main(String[] args) {

	//Create operation 
	System.out.println("start creating");
	
	EmployeeDAO.createEmployee(new Employee(129, "Ajay3", "Junior Architect", 111.5) ); 
    	
    	System.out.println("Done.. Employee with id= 129 has created");
    	
    	//delete operation 
    	System.out.println("start Deleteing");
    	    	
    	EmployeeDAO.deleteEmployee(125);
    	    	
    	EmployeeDAO.deleteEmployee(126);
    	    	
    	System.out.println("Done.. Employee with id= 125,126 has deleted");
    	    	

    	//update operation 
    	System.out.println("start Updateing");
    	Employee emp = new Employee(127, "new name", "new designation", 12.5) ; 
    	EmployeeDAO.UpdateEmployeeDatabase(emp);
    	
    	System.out.println("Done.. Employee with id= 127 has upadate with new info");
    	
    	//Read operation 
    	System.out.println("start Reading");
    	    	
    	EmployeeDAO.readEmployee(127);
    	
    	System.out.println("Read operation done on empid = 127");
    	
    	
    }

}
