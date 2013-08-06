package com.tbits.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.jdo.Query;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.tbits.common.Employee;
import com.tibits.dAoEmployee.EmployeeDAO;

@Path("/json/metallica")
public class JSONService {
 
	static int count  = -1 ; 
	static Employee Emp[] = new Employee [1000] ; 
	static PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
	
	
	
	@GET
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEmployeeInJSON(@QueryParam("id") int id) {
	/*    	if (count == -1)
	    	    return Response.status(201).entity("No record Exist! Data base is empty").build() ; 
	    	     
	    	if (Emp[id%1000] != null)
	    	    return Response.status(200).entity(Emp[id%1000]).build() ; 
	    	else 
	    	    return Response.status(200).entity("No record found").build() ; 
	    	 
	    
	*/
	    	PersistenceManager pm = pmf.getPersistenceManager();
        	Transaction tx = pm.currentTransaction();
        
        	try {
        	   tx.begin();
        	    Query q = pm.newQuery(Employee.class, "employee_Id ==" + id);
        	    
        	    //since result would be unique; id is an primary key
        	    q.setUnique(true);
        	    
        	    Employee e = (Employee) pm.detachCopy(q.execute()) ; 
        	    
        	    tx.commit();
        	    if (e!=null)
        		return Response.status(200).entity(e).build() ;
        	    else 
        		return Response.status(200).entity("Not found").build() ;
        	    
        	    
        
        	} finally {
        	    if (tx.isActive()) {
        		tx.rollback();
        	    
        	    }
        	    pm.close();
        	    
        	}
            	
    }	
 
	
	//----------------------------------------------------------------------------------------------
	
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createEmployeeInJSON(Employee employee) 
	{
	   /*EmployeeDAO employeeDAO = new EmployeeDAO();
	   employeeDAO.createEmployee(employee);
	   */ 
	    PersistenceManager pm = pmf.getPersistenceManager();
	    Transaction tx = pm.currentTransaction(); //getting current transaction 
		try
		{
			tx.begin(); // begin transaction 
			pm.detachCopy(pm.makePersistent(employee)); // persist emp object in database
			tx.commit();//commit operation 	
			
		} finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
		
		String result = "Track saved : " + employee;
		count++ ; 
		return Response.status(201).entity(result).build();
	
	}
	
	

	//----------------------------------------------------------------------------------------------
	
	
	
	@PUT
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response UpdateEmployeeInJSON (@QueryParam("id") int eid, Employee employee)
	{


        	PersistenceManager pm = pmf.getPersistenceManager();
        	Transaction tx = pm.currentTransaction();

        	try {
        	    
        	    tx.begin();
        	    Query q = pm.newQuery(Employee.class,"employee_Id ==" + eid );
        	    
        	    // since result would be unique; id is an primary key
        	    q.setUnique(true);
        
        	    Employee e = (Employee) pm.detachCopy(q.execute());
        	    
        	   // tx.commit();
        	    
        	    if (e != null) {
        		e.setEmployee_Id(employee.getEmployee_Id());
        		e.setEmployee_Name(employee.getEmployee_Name());
        		e.setEmployee_Designation(employee.getEmployee_Designation());
        		e.setEmployee_Salary(employee.getEmployee_Salary());

        		String result = "Track saved : " + employee;
        		
        		pm.makePersistent(e) ; 
        		tx.commit();
            	    
        		return Response.status(200).entity(result).build(); 

        	    }
        	    else{
        		tx.commit();
            	    
        		return Response.status(200).entity("No Employee exist with id = " +eid).build(); 
        	    }
            	    
        	} finally {
        	    if (tx.isActive()) {
        		tx.rollback();
        	    }
        	    pm.close();
        	}
    
	}
	
	@DELETE
	@Path("/delete")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response DeleteEmployeeInJSON (@QueryParam("id") int eid)
	{
        	PersistenceManager pm = pmf.getPersistenceManager();
        
        	Transaction tx = pm.currentTransaction();
        	try {
        	
        	    tx.begin();
        	    Query q = pm.newQuery(Employee.class, "employee_Id ==" + eid);
        
        	    // since result would be unique; id is an primary key
        	    q.setUnique(true);
        
        	    Employee e = (Employee) pm.detachCopy(q.execute());
        	    
        	    if (e != null)
        		{
        			pm.deletePersistentAll(e);
        			count--; 
        			String result = "Track deleted with employee id: " + eid;
        			tx.commit();
        	        	return Response.status(200).entity(result).build(); 
        		    		
        		}
        	    else 
        	    {
        		 tx.commit();
              	   	return Response.status(200).entity("No Employee exist with id = " +eid).build(); 
        	    }
        	    

        	} finally {
        	    if (tx.isActive()) {
        		tx.rollback();
        	    }
        	    pm.close();
        	}
	}
	
	
	//----------------------------------------------------------------------------------------------
	
	
	//this will not work properly
	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getListOfAllEmployees()
	{
        	PersistenceManager pm = pmf.getPersistenceManager();
        
        	Transaction tx = pm.currentTransaction();
        	try {
        
        	    tx.begin();
        	    Query q = pm.newQuery(Employee.class);
        
        	    @SuppressWarnings("unchecked")
        	    Collection<Employee> collection = (Collection<Employee>)q.execute();
		    List<Employee> ListOfEmployee = (List<Employee>)pm.detachCopyAll(collection);
        	    tx.commit();
        	    GenericEntity<List<Employee>> genericEntity = new GenericEntity<List<Employee>>( ListOfEmployee) {};
        	    return Response.status(Response.Status.OK.getStatusCode()).entity(genericEntity).build();
        	    
        	} finally {
        	    if (tx.isActive()) {
        		tx.rollback();
        	    }
        	    pm.close();
        	}
	
	 }
 
}