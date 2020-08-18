package com.sss.seatmgmt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sss.seatmgmt.bo.IEmployeeBO;
import com.sss.seatmgmt.dto.EmployeeDTO;

@RestController
//@RequestMapping("/employees")
public class EmployeeController {
	
	@Autowired
	private IEmployeeBO employeeBo;
	
	@RequestMapping(method = RequestMethod.GET, value = "/")
	public String hello() {
		System.out.println("hello");
		return "Hello";
		
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/employees")
	public List<EmployeeDTO> getAllEmployees() {
		System.out.println("getAllEmployees");
		return employeeBo.getAllEmployees();
		
	}

	@RequestMapping(method = RequestMethod.GET, value = "/employees/{id}")
	public EmployeeDTO getEmployeeById(@PathVariable int id) {
		// TODO Auto-generated method stub
		System.out.println("getEmployeeById");
		return employeeBo.getEmployeeById(id);
	}

}
