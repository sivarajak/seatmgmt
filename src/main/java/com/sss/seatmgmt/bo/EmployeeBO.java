package com.sss.seatmgmt.bo;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sss.seatmgmt.dto.EmployeeDTO;
import com.sss.seatmgmt.model.Employee;
import com.sss.seatmgmt.repositories.EmployeeRepository;
import com.sss.seatmgmt.util.ModelDTOConvertor;

@Service
public class EmployeeBO implements IEmployeeBO {
	
	//@Autowired
	//private EmployeeDAO employeeDao;
	
	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public List<EmployeeDTO> getAllEmployees() {
		
		List<EmployeeDTO> employeeDtoList = null;
		//List<Employee> employees = employeeDao.getAllEmployees();
		List<Employee> employees = employeeRepository.findAll();
		employeeDtoList = employees.stream()
			.map(ModelDTOConvertor::convertEmployeeToEmployeeDTO)
			.collect(Collectors.toList());
		/*if(employees != null && !employees.isEmpty()) {
			employeeDtoList = new LinkedList<EmployeeDTO>();
			for(Employee employee : employees) {
				employeeDtoList.add(ModelDTOConvertor.convertEmployeeToEmployeeDTO(employee));
			}
		}*/
		
		return employeeDtoList;
	}

	@Override
	public EmployeeDTO getEmployeeById(int id) {
		EmployeeDTO employeeDTO = null;
		Optional<Employee> opEmployee = employeeRepository.findById(id);
		Employee employee = null;
		if(opEmployee.isPresent()) {
			employee = opEmployee.get();
		}
		//Employee employee = employeeDao.getEmployeeById(id);
		employeeDTO = ModelDTOConvertor.convertEmployeeToEmployeeDTO(employee);	
		return employeeDTO;
	}

}


