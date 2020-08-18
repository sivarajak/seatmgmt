package com.sss.seatmgmt.bo;

import java.util.List;

import com.sss.seatmgmt.dto.EmployeeDTO;

public interface IEmployeeBO {
	
	public List<EmployeeDTO> getAllEmployees();
	
	public EmployeeDTO getEmployeeById(int id);
	
	
}
