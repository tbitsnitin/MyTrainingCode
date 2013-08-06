package com.tbits.myClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import javax.management.RuntimeErrorException;
import javax.swing.text.StyledEditorKit.BoldAction;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.WebResource.Builder;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.tbits.common.Employee;

public class MyClient {

    private static BufferedReader br;
    private static long employee_id ; 
    private static String employee_Name  ;
    private static String employee_Designation  ; 
    private static double employee_Salary  ; 
    
    
    public static void main(String args[]) throws IOException {

	int choice = 1;

	// Create InputBuffer for consol read
	br = new BufferedReader(new InputStreamReader (System.in)) ;

	// Menu bar
	while (choice != 0) {
	    System.out.println("\n\n\nSelect any choice: ");

	    System.out.println("************Menu*************");
	    System.out.println("(1). Add    			");
	    System.out.println("(2). View   			");
	    System.out.println("(3). Update 			");
	    System.out.println("(4). Delete            	");
	    System.out.println("(5). List of Employees 	");
	    System.out.println("(0). Exit              	");
	    System.out.println("*****************************");

	    System.out.println("\n Enter Your Choice: ");

	    try {

		choice = Integer.parseInt(br.readLine() );
		System.out.println("Your choice is: " + choice);

	    } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }

	    switch (choice) {
	    case 0:
		break;

	    case 1:
		// Call add function
		 AddEntry ();
		break;
	    case 2:
		// Call view entry
		 ViewEntry();
		break;
	    case 3:
		// call Update function
		 UpdateEntry ();
		break;
	    case 4:
		// Call Delete Entry Function
		 DeleteEntry();
		break;
	    case 5:
		// List of Employee function
		 ListOfAllEmployee();
		break;
	    default:
		System.out
			.println("Incorrect Choice. Re-enter your choice otherwise input (0) for Exit");
		continue;
	    }

	}
	System.out.println("Bye!!");
	System.exit(1);
	
    }
    
    //Adding entry 
    private static void AddEntry () throws IOException
    {
	// Create client configuration 
	ClientConfig clientConfig = new DefaultClientConfig();

	// make mapping
	clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING,
		Boolean.TRUE);

	// create client
	Client client = Client.create(clientConfig);

	// Create Webresources //need to change
	WebResource webResource = client
		.resource("http://localhost:8080/jersey-1/rest/json/metallica/add");

	System.out.println("Enter Employees Details next to each other...\n");

	System.out.print(" Enter Id: ");
	
	
	try {
	    employee_id = Long.parseLong(br.readLine());
	
	} 
	catch (NumberFormatException e) {
	    e.printStackTrace();
	} 
	
	System.out.print(" Enter Name: ");
	employee_Name = br.readLine() ; 
	
	System.out.print(" Enter Designation: ");
	String employee_Designation = br.readLine();
	
	System.out.print(" Enter Salary: ");
	try {
	
	    employee_Salary = Double.parseDouble(br.readLine() ); 
	} 
	catch (NumberFormatException e)
	{
	    e.printStackTrace(); 
	}
	
	//create an object with above details
	Employee newEmployee = new Employee(employee_id, employee_Name, employee_Designation, employee_Salary) ; 
	
	//create client response 
	ClientResponse response = webResource.type("application/json").post(ClientResponse.class , newEmployee);
	
	//check response from server
	if ( response.getStatus() != 201)
	{
	    throw new RuntimeErrorException(null, "Failed : HTTP ERROR CODE " + response.getStatus()) ; 
	}
	
	//if all well, get response from server
	System.out.println("Response from server.... "+ response.getEntity(String.class));
	
    }
    
    //update employee data
    private static void UpdateEntry () throws IOException 
    {
	long temp_id = 0;
	
	
	// read id for update
	
	// Create client config for Client
	ClientConfig clientConfig = new DefaultClientConfig();

	// make mapping
	clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING,Boolean.TRUE);

	// create client
	Client client = Client.create(clientConfig);

	
	
	System.out.println(" Enter the id of employee whose data is to update: ");
	
	try 
	{
	    temp_id = Long.parseLong(br.readLine());
	}
	catch (NumberFormatException e) {
	    e.printStackTrace();
	} 
	
	System.out.println("Enter update Employees Details next to each other...\n");

	System.out.print(" Enter Id: ");
	
	
	try {
	    employee_id = Long.parseLong(br.readLine());
	
	} 
	catch (NumberFormatException e) {
	    e.printStackTrace();
	} 
	
	System.out.print(" Enter Name: ");
	employee_Name = br.readLine() ; 
	
	System.out.print(" Enter Designation: ");
	String employee_Designation = br.readLine();
	
	System.out.print(" Enter Salary: ");
	try {
	
	    employee_Salary = Double.parseDouble(br.readLine() ); 
	} 
	catch (NumberFormatException e)
	{
	    e.printStackTrace(); 
	}
	
	// Create Web resources
	WebResource webResource = client
		.resource("http://localhost:8080/jersey-1/rest/json/metallica/update?id="+temp_id);
		
	//create an object with above details
	Employee newEmployee = new Employee(employee_id, employee_Name, employee_Designation, employee_Salary) ; 
	
	ClientResponse response = webResource.type("application/json").put(ClientResponse.class , newEmployee);
	
	//check response from server
	if ( response.getStatus() != 200)
	{
		    throw new RuntimeErrorException(null, "Failed : HTTP ERROR CODE " + response.getStatus()) ; 
	}
		
		
	//if all well, get response from server
	System.out.println("Response from server.... "+ response.getEntity(String.class));
	
    }
    
    //view entry of employee
    private static void ViewEntry () throws IOException
    {
	long temp_id = 0;
	
	
	// read id for update
	
	// Create client config for Client
	ClientConfig clientConfig = new DefaultClientConfig();

	// make mapping
	clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING,	Boolean.TRUE);

	// create client
	Client client = Client.create(clientConfig);

	
	
	System.out.println(" Enter the id of employee whose data is to update: ");
	
	try 
	{
	    temp_id = Long.parseLong(br.readLine());
	}
	catch (NumberFormatException e) {
	    e.printStackTrace();
	} 
	// Create Web resources
	WebResource webResource = client
			.resource("http://localhost:8080/jersey-1/rest/json/metallica/get?id="+temp_id);
			
	ClientResponse response = webResource.type("application/json").get(ClientResponse.class );
	
	//check response from server
	if ( response.getStatus() != 200)
	{
	    throw new RuntimeErrorException(null, "Failed : HTTP ERROR CODE " + response.getStatus()) ; 
	}
		
		
	//if all well, get response from server
	System.out.println("Response from server.... "+ response.getEntity(String.class));

    }

    //Delete entry of employee
    private static void DeleteEntry () throws IOException
    {
	long temp_id = 0;
	
	
	// read id for update
	
	// Create client config for Client
	ClientConfig clientConfig = new DefaultClientConfig();

	// make mapping
	clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING,
		Boolean.TRUE);

	// create client
	Client client = Client.create(clientConfig);

	
	
	System.out.println(" Enter the id of employee whose data is to update: ");
	
	try 
	{
	    temp_id = Long.parseLong(br.readLine());
	}
	catch (NumberFormatException e) {
	    e.printStackTrace();
	} 
	// Create Web resources
	WebResource webResource = client
			.resource("http://localhost:8080/jersey-1/rest/json/metallica/delete?id="+temp_id);
			
	ClientResponse response = webResource.type("application/json").delete(ClientResponse.class  );
	//check response from server
	if ( response.getStatus() != 200)
	{
		    throw new RuntimeErrorException(null, "Failed : HTTP ERROR CODE " + response.getStatus()) ; 
	}
		
		
	//if all well, get response from server
	System.out.println("Response from server.... "+ response.getEntity(String.class));

    }
    
    private static void  ListOfAllEmployee () 
    {
	// read id for update
	
	// Create client config for Client
	ClientConfig clientConfig = new DefaultClientConfig();

	// make mapping
	clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING,Boolean.TRUE);

	// create client
	Client client = Client.create(clientConfig);

	WebResource webResource = client
		.resource("http://localhost:8080/jersey-1/rest/json/metallica/list");
		
	ClientResponse response = webResource.type("application/json").get(ClientResponse.class  );
	
	//check response from server
	if ( response.getStatus() != 200)
	{
		    throw new RuntimeErrorException(null, "Failed : HTTP ERROR CODE " + response.getStatus()) ; 
	}

	System.out.println("Output from Server .... \n");
	//System.out.println("Response from server.... "+ response.getEntity(String.class));
	
	List<Employee> output = response.getEntity(new GenericType<List<Employee>>() {});
	
	System.out.println("Total " + output.size() + " items received");
	
	int i=1;
	for (Employee emp : output) {
		System.out.println("Serial number: "+(i++)+" "+emp);
	}

		

    }

    
    
    //=====================================================================================================
    //		Function for JSF, while using UI these function will perform all action (CRUD)
    //=====================================================================================================
    

    //Adding entry via UI
    public void AddEntryUI (Employee newEmployee) 
    {
	System.out.println("got it here");
	// Create client configuration 
	ClientConfig clientConfig = new DefaultClientConfig();

	// make mapping
	clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING,
								Boolean.TRUE);

	// create client
	Client client = Client.create(clientConfig);

	// Create Web-resources //need to change
	WebResource webResource = client
		.resource("http://localhost:8080/JavaServerFacesEmployeeDatabase/rest/json/metallica/add");
	
	//create client response 
	ClientResponse response = webResource.type("application/json").accept(new MediaType("application", "json")).post(ClientResponse.class , newEmployee);
	
	//check response from server
	if ( response.getStatus() != 201)
	{
	    throw new RuntimeErrorException(null, "Failed : HTTP ERROR CODE " + response.getStatus()) ; 
	}
	
	//if all well, get response from server
	System.out.println("Response from server.... "+ response.getEntity(String.class));
	
    }
    

    //view entry of employee
   public Employee ViewEntryUI (final long temp_id ) throws IOException
    {
	
	// Create client config for Client
	ClientConfig clientConfig = new DefaultClientConfig();

	// make mapping
	clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING,	Boolean.TRUE);

	// create client
	Client client = Client.create(clientConfig);

	// Create Web resources
	WebResource webResource = client
			.resource("http://localhost:8080/JavaServerFacesEmployeeDatabase/rest/json/metallica/get?id="+temp_id);
			
	ClientResponse response = webResource.type("application/json").accept(new MediaType("application", "json")).get(ClientResponse.class );
	
	//check response from server
	if ( response.getStatus() != 200)
	{
	    throw new RuntimeErrorException(null, "Failed : HTTP ERROR CODE " + response.getStatus()) ; 
	}
		
		
	//if all well, get response from server
	return response.getEntity(Employee.class);

    }


   //update employee data
   public void UpdateEntryUI (final long temp_id, final Employee updatedEmployee) throws IOException 
   {
	// Create client config for Client
	ClientConfig clientConfig = new DefaultClientConfig();

	// make mapping
	clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING,Boolean.TRUE);

	// create client
	Client client = Client.create(clientConfig);

		// Create Web resources
	WebResource webResource = client
		.resource("http://localhost:8080/JavaServerFacesEmployeeDatabase/rest/json/metallica/update?id="+temp_id);
	
	ClientResponse response = webResource.type("application/json").accept(new MediaType("application", "json")).put(ClientResponse.class , updatedEmployee);
	
	//check response from server
	if ( response.getStatus() != 200)
	{
		    throw new RuntimeErrorException(null, "Failed : HTTP ERROR CODE " + response.getStatus()) ; 
	}
		
		
	//if all well, get response from server
	System.out.println("Response from server.... "+ response.getEntity(String.class));
	
   }

   //Delete entry of employee
   public void DeleteEntryUI (final long temp_id) throws IOException
   {
	// Create client config for Client
	ClientConfig clientConfig = new DefaultClientConfig();

	// make mapping
	clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING,
						Boolean.TRUE);

	// create client
	Client client = Client.create(clientConfig);

	
	// Create Web resources
	WebResource webResource = client
			.resource("http://localhost:8080/JavaServerFacesEmployeeDatabase/rest/json/metallica/delete?id="+temp_id);
			
	ClientResponse response = webResource.type("application/json").accept(new MediaType("application", "json")).delete(ClientResponse.class  );
	//check response from server
	if ( response.getStatus() != 200)
	{
		    throw new RuntimeErrorException(null, "Failed : HTTP ERROR CODE " + response.getStatus()) ; 
	}
		
		
	//if all well, get response from server
	//return response.getEntity(String.class);

   }
    

}
