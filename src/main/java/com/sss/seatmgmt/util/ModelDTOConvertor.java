package com.sss.seatmgmt.util;

import com.sss.seatmgmt.dto.EmployeeDTO;
import com.sss.seatmgmt.dto.LocationDTO;
import com.sss.seatmgmt.model.Employee;
import com.sss.seatmgmt.model.Location;

public class ModelDTOConvertor {

	public static EmployeeDTO convertEmployeeToEmployeeDTO(Employee employee) {
		
		EmployeeDTO employeeDTO = null;
		if(employee != null) {
			employeeDTO = new EmployeeDTO(employee.getEmpId(),
					employee.getEmpName(),
					employee.getEmpGender(),
					employee.getEmpDesignation(),
					employee.getEmpTeam(),
					convertLocationToLocationDTO(employee.getEmpLocation()));
		}
		 
		 return employeeDTO;
	}
	
	public static LocationDTO convertLocationToLocationDTO(Location location) {
		
		LocationDTO locationDTO = null;
		if(location != null) {
			locationDTO = new LocationDTO(location.getLocId(),
					 location.getLocName(),
					 location.getLocPosX(),
					 location.getLocPosY(),
					 null);
		}
		 return locationDTO;
	}
	
	public static Location convertLocationDTOToLocation(LocationDTO locationDTO) {
		
		Location location = null;
		if(locationDTO != null) {
			location = new Location(locationDTO.getLocId(),
					locationDTO.getLocName(),
					locationDTO.getLocPosX(),
					locationDTO.getLocPosY(),
					 null);
		}
		 return location;
	}
}
