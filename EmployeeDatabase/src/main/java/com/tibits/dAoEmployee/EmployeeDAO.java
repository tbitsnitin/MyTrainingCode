package com.tibits.dAoEmployee;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

import com.tbits.common.Employee;

public class EmployeeDAO
{
	
    static PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
 	
	//To create new entry in data base 
	public static void createEmployee(Employee emp)
	{
	    PersistenceManager pm = pmf.getPersistenceManager();
	    Transaction tx = pm.currentTransaction(); //getting current transaction 
		try
		{
			tx.begin(); // begin transaction 
			pm.makePersistent(emp); // persist emp object in database 
			tx.commit();//commit operation 	
			
		} finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
		
	}
	
	//delete an employee corresponding to id 
	public static void deleteEmployee(final long id)
	{
	    PersistenceManager pm = pmf.getPersistenceManager();

		Transaction tx = pm.currentTransaction();
		try
		{
			tx.begin();
			Query q = pm.newQuery(Employee.class, "employee_Id ==" + id ) ; 
			List<Employee> e1 = (List<Employee>) q.execute();
			pm.deletePersistentAll(e1);
			tx.commit();
		}finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}
	
	//update Employee data corresponding to id 
	public static void UpdateEmployeeDatabase (final Employee emp)
	{
	    	PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try
		{
			tx.begin();
			Query q = pm.newQuery(Employee.class, "employee_Id ==" + emp.getEmployee_Id()) ; 
			List<Employee> e1 = (List<Employee>) q.execute();
			
			for(Employee e2:e1)
			{
				e2.setEmployee_Name(emp.getEmployee_Name());
				e2.setEmployee_Designation(emp.getEmployee_Designation());
        		    	e2.setEmployee_Salary(emp.getEmployee_Salary());
        		    	System.out.println("done");
        		 }
			tx.commit();
		}finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}
	
	//Read the data form Employee data base; list of all employee
	public static void readEmployee (final long id)
	{
	    	PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try
		{
			tx.begin();
			Query q = pm.newQuery(Employee.class,"employee_Id ==" + id);
			List<Employee> e1 = (List<Employee>) q.execute();
			Iterator<Employee> iter = e1.iterator(); 
			
			while (iter.hasNext())
			{
			    System.out.println(iter.next().toString());
			}
			tx.commit(); 
			
		} finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}
	
	
	
}
