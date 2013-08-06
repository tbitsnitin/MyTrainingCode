package com.tbits.common;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.jdo.annotations.Column;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import org.codehaus.jackson.annotate.JsonTypeInfo;

import com.tbits.myClient.MyClient;

@ManagedBean
@SessionScoped

public class EmployeeBean implements Serializable {

    	private static final long serialVersionUID = 1L;

	private long employee_Id ; 
	
	private String employee_Name ; 
	
	private String employee_Designation ; 
	
	private double employee_Salary ;
	
	public String status = "Please Perfrom action" ; 

	public EmployeeBean() {
	    super();
	    // TODO Auto-generated constructor stub
	}

	public String getStatus() {
	    return status;
	}

	public void setStatus(String status) {
	    this.status = status;
	}

	public EmployeeBean(long employee_Id, String employee_Name,
		String employee_Designation, double employee_Salary) {
	    super();
	    this.employee_Id = employee_Id;
	    this.employee_Name = employee_Name;
	    this.employee_Designation = employee_Designation;
	    this.employee_Salary = employee_Salary;
	}

	public long getEmployee_Id() {
	    return employee_Id;
	}

	public void setEmployee_Id(long employee_Id) {
	    this.employee_Id = employee_Id;
	}

	public String getEmployee_Name() {
	    return employee_Name;
	}

	public void setEmployee_Name(String employee_Name) {
	    this.employee_Name = employee_Name;
	}

	public String getEmployee_Designation() {
	    return employee_Designation;
	}

	public void setEmployee_Designation(String employee_Designation) {
	    this.employee_Designation = employee_Designation;
	}

	public double getEmployee_Salary() {
	    return employee_Salary;
	}

	public void setEmployee_Salary(double employee_Salary) {
	    this.employee_Salary = employee_Salary;
	}

	public static long getSerialversionuid() {
	    return serialVersionUID;
	}

	@Override
	public String toString() {
	    return "EmployeeBean [employee_Id=" + employee_Id
		    + ", employee_Name=" + employee_Name
		    + ", employee_Designation=" + employee_Designation
		    + ", employee_Salary=" + employee_Salary + "]";
	}

	//For adding employee details from ui to database; it will call client that perform required action 
	public void addEmployee(){
	    status = "Please Perfrom action" ; 
	    
	    Employee emp = new Employee(getEmployee_Id(), getEmployee_Name(), 
		    			getEmployee_Designation(), getEmployee_Salary()) ; 
	    
	    new MyClient().AddEntryUI(emp);
	    status = "Operation Successful" ; 
	    
	}
	
	public void ViewEmployee() throws IOException
	{
	    status = "Please Perfrom action" ;
	   Employee employee =  new MyClient().ViewEntryUI(getEmployee_Id()) ;
	   
	   setEmployee_Name(employee.getEmployee_Name());
	   setEmployee_Designation(employee.getEmployee_Designation());
	   setEmployee_Salary(employee.getEmployee_Salary());
	   status = "Operation Successful" ; 
	}

	public void UpdateEmployee() throws IOException
	{
	    status = "Please Perfrom action" ;
	    Employee emp = new Employee(getEmployee_Id(), getEmployee_Name(), 
			getEmployee_Designation(), getEmployee_Salary()) ; 

	    new MyClient().UpdateEntryUI(getEmployee_Id(), emp);
	    status = "Operation Successful" ; 
	}
	
	public void DeleteEmployee() throws IOException
	{
	    status = "Please Perfrom action" ;
	      
		    new MyClient().DeleteEntryUI(getEmployee_Id());
		    status = "Operation Successful" ; 
	}

}
