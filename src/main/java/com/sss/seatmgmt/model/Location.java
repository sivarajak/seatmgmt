package com.sss.seatmgmt.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
public class Location implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7932886999001816166L;
	@Id
	@GeneratedValue
	@Column(name="LOC_ID")
	private int locId;
	private String locName;
	private int locPosX;
	private int locPosY;
	@OneToOne(cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn(name = "loc_id")
	private Employee employee;
		
	public Location() {
	}

	public Location(int locId, String locName, int locPosX, int locPosY,
			Employee employee) {
		super();
		this.locId = locId;
		this.locName = locName;
		this.locPosX = locPosX;
		this.locPosY = locPosY;
		this.employee = employee;
	}

	public int getLocId() {
		return locId;
	}

	public void setLocId(int locId) {
		this.locId = locId;
	}

	public String getLocName() {
		return locName;
	}

	public void setLocName(String locName) {
		this.locName = locName;
	}

	public int getLocPosX() {
		return locPosX;
	}

	public void setLocPosX(int locPosX) {
		this.locPosX = locPosX;
	}

	public int getLocPosY() {
		return locPosY;
	}

	public void setLocPosY(int locPosY) {
		this.locPosY = locPosY;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	

}
