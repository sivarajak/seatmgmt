package com.sss.seatmgmt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sss.seatmgmt.bo.ILocationBO;
import com.sss.seatmgmt.dto.LocationDTO;

@RestController
public class LocationController {
	
	@Autowired
	ILocationBO locationBO;
	
	@RequestMapping(method = RequestMethod.POST, value = "/locations")
	public void saveLocations(@RequestBody List<LocationDTO> locationDTOs) {
		System.out.println("saveLocations" + locationDTOs.size());
		locationBO.saveLocations(locationDTOs);
		
	}

}
