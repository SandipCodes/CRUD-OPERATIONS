package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.Employee;
import com.app.service.EmployeeService;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService service;
	
	
	//adding obj to db
	//@RequestMapping(value="/employees",method=RequestMethod.POST)
	@PostMapping(value="/employees")
	public ResponseEntity<?> saveEmployee(@RequestBody Employee e ){
		
		ResponseEntity<?> response=null;
		//save employee
		service.saveEmployee(e);
		List<Employee> list=service.getAllEmployees();
		
		if(list!=null && !list.isEmpty()) {
			response=new ResponseEntity<List<Employee>>(list, HttpStatus.OK);
		}
		else {
			response=new ResponseEntity<String>("Data not Available", HttpStatus.BAD_REQUEST);
		}
		
		return response;
	}//saveEmployee()
	
	//get all Employees
	@GetMapping(value="/employees")
	public ResponseEntity<?> getAllEmployees(){
	
		List<Employee> list=null;
		ResponseEntity<?> response=null;
		
		list=service.getAllEmployees();

		if(list!=null && !list.isEmpty()) {
			response=new ResponseEntity<List<Employee>>(list, HttpStatus.OK);
		}
		else {
			response=new ResponseEntity<String>("Data not Available", HttpStatus.BAD_REQUEST);
		}
		
		return response;
	}
	
	//get one employee
	@GetMapping(value="/employees/{id}")
	public ResponseEntity<?> getEmployeeById(@PathVariable Integer id){

		Employee emp=null;
		ResponseEntity<?> responseEntity=null;
		
		emp=service.getEmployeeById(id);
		
		if(emp!=null) {
			responseEntity=new ResponseEntity<Employee>(emp, HttpStatus.OK);
		}
		else {
			responseEntity=new ResponseEntity<String>("DATA NOT AVILABLE", HttpStatus.BAD_REQUEST);
		}
		
		return responseEntity;
	}//getEmployeeById()
	
	
	@DeleteMapping(value="/employees/{id}")
	public ResponseEntity<?> deleteEmployee(@PathVariable Integer id){

		ResponseEntity<?> responseEntity=null;
		String body=null;
		
		
		try {
			service.deleteEmployee(id);
			body="DATA DELETED SUCCESSFULLy";
			responseEntity=new ResponseEntity<String>(body, HttpStatus.OK);
		}
	    catch(Exception exp) {
	    	body="DATA NOT DELETED";
			responseEntity=new ResponseEntity<String>(body, HttpStatus.BAD_REQUEST);
		}
		
		return responseEntity;
	}//getEmployeeById()

	@PutMapping(value="/employees")
	public ResponseEntity<?> updateEmployee(@RequestBody Employee e){
		
		ResponseEntity<?> responseEntity=null;
		
		String body=null;
		
		try {
			service.updateEmployee(e);	
			body="DATA UPDATED SUCCESSFULLy";
			responseEntity=new ResponseEntity<String>(body, HttpStatus.OK);
		}
	    catch(Exception exp) {
	    	body="DATA NOT UPDATED";
			responseEntity=new ResponseEntity<String>(body, HttpStatus.BAD_REQUEST);
		}
		
		return responseEntity;
	}//method
}//class
