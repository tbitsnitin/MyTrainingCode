package com.tbits.common;

import java.io.Serializable;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import org.codehaus.jackson.annotate.JsonTypeInfo;

@PersistenceCapable(detachable = "true", table = "employee")
@JsonTypeInfo(use=JsonTypeInfo.Id.CLASS, include=JsonTypeInfo.As.PROPERTY, property="@class")
public class Employee implements Serializable {
    
    	@NotPersistent
	private static final long serialVersionUID = 1L;

	@Persistent(primaryKey = "true")
	private long employee_Id ; 
	
	@Column(name = "username", jdbcType = "VARCHAR", length = 100)
	private String employee_Name ; 
	
	@Column(name = "designation", jdbcType = "VARCHAR", length = 100)
	private String employee_Designation ; 
	
	@Column(name = "Salary", jdbcType = "DOUBLE")
	private double employee_Salary ;

	public Employee() {
		// TODO Auto-generated constructor stub
	}

	public Employee(long employee_Id, String employee_Name,
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

	@Override
	public String toString() {
		return "Employee [employee_Id=" + employee_Id + ", employee_Name="
				+ employee_Name + ", employee_Designation="
				+ employee_Designation + ", employee_Salary=" + employee_Salary
				+ "]";
	}

	
	
	
}
