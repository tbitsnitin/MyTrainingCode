package com.tbits.service;

import java.util.ArrayList;
import java.util.List;

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
 
@Path("/json/metallica")
public class JSONService {
 
	static int count  = -1 ; 
	static Employee Emp[] = new Employee [1000] ; 
	
	@GET
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEmployeeInJSON(@QueryParam("id") int id) {
	    	if (count == -1)
	    	    return Response.status(201).entity("No record Exist! Data base is empty").build() ; 
	    	     
	    	if (Emp[id%1000] != null)
	    	    return Response.status(201).entity(Emp[id%1000]).build() ; 
	    	else 
	    	    return Response.status(201).entity("No record found").build() ; 
 
	}
 
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createEmployeeInJSON(Employee employee) {
		if (count!=999)
		{
		    Emp[(int) employee.getEmployee_Id()%1000] = employee ; 
		    String result = "Track saved : " + employee;
		    count++ ; 
		    return Response.status(201).entity(result).build();
		}
		else 
		    return Response.status(201).entity("Sorry!!! Data area full of employee, only 1000 employee data can be store currently\n Tip: Delete employee which does not exist no more then add new employee").build();
	}
	
	@PUT
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response UpdateEmployeeInJSON (@QueryParam("id") int eid, Employee employee)
	{
	    if (Emp[eid%1000] != null)
		{
			Emp[(int) (employee.getEmployee_Id()%1000)] = employee ; 
			Emp[eid%1000] = null ; 
			String result = "Track saved : " + employee;
			return Response.status(201).entity(result).build(); 
		}
	    else 
		return Response.status(201).entity("Employee id" + eid + " Not found in Employee data base ").build(); 
		
	}
	
	@DELETE
	@Path("/delete")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response DeleteEmployeeInJSON (@QueryParam("id") int eid)
	{
	    if (Emp[eid%1000] != null)
	    {
		Emp[eid%1000] = null ; 
		System.gc();
		count--; 
		String result = "Track deleted with employee id: " + eid%1000;
		return Response.status(201).entity(result).build(); 
	    }
	    else
		return Response.status(201).entity("Employee id" + eid + " Not found in Employee data base ").build(); 
		
	}
	
	//this will not work properly
	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getListOfAllEmployees()
	{
		List<Employee> listOfEmployees = new ArrayList<Employee>();
		for(int i=0 ; i<1000 ; i++) {
			if (Emp[i]!=null)
			    	listOfEmployees.add(Emp[i]);
		}
		
		GenericEntity<List<Employee>> genericEntity = new GenericEntity<List<Employee>>(listOfEmployees){};
		
		return Response.status(Response.Status.OK.getStatusCode()).entity(genericEntity).build();
	}
 
}