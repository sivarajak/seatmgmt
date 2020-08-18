package com.sss.seatmgmt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sss.seatmgmt.model.Location;

public interface LocationRepository extends JpaRepository<Location, Integer>{

}
