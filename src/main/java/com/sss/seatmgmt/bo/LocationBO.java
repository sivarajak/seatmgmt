package com.sss.seatmgmt.bo;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sss.seatmgmt.dto.LocationDTO;
import com.sss.seatmgmt.model.Location;
import com.sss.seatmgmt.repositories.LocationRepository;
import com.sss.seatmgmt.util.ModelDTOConvertor;

@Service
public class LocationBO implements ILocationBO {
	
	@Autowired
	private LocationRepository locRepository;


	@Override
	public void saveLocations(List<LocationDTO> locationDtos) {
		List<Location> locations = null;
		locations = locationDtos.stream()
			.map(ModelDTOConvertor::convertLocationDTOToLocation)
			.collect(Collectors.toList());
		/*if(locationDtos != null && !locationDtos.isEmpty()) {
			locations = new LinkedList<Location>();
			for(LocationDTO locationDTO : locationDtos) {
				locations.add(ModelDTOConvertor.convertLocationDTOToLocation(locationDTO));
			}
		}*/
		//employeeDao.saveEmployeeLocations(locations);
		locRepository.saveAll(locations);
	}
}
