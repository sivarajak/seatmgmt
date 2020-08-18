package com.sss.seatmgmt.bo;

import java.util.List;

import com.sss.seatmgmt.dto.LocationDTO;

public interface ILocationBO {
	
	public void saveLocations(List<LocationDTO> locations);

}
