package com.sss.seatmgmt.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.sss.seatmgmt.model.Employee;
import com.sss.seatmgmt.model.Location;
import com.sss.seatmgmt.util.HibernateUtil;

public class EmployeeDAO {
	
	public List<Employee> getAllEmployees() {
		Session session = HibernateUtil.getCurrentSession();
		List<Employee> employees = session.createCriteria(Employee.class).list();
		HibernateUtil.closeSession();
		return employees;
	}
	
	public void saveEmployeeLocations(List<Location> locations) {
		/*List<Employee> empList;
		if(locations != null) {
			empList = new ArrayList<Employee>();
			for(Location location : locations) {
				Employee emp = getEmployeeById(location.getLocId());
				emp.setEmpLocation(location);
				empList.add(emp);
				//location.setEmployee(getEmployeeById(location.getLocId()));
			}*/
			HibernateUtil.persistListObject(locations);
		//}
		
	}
	
	public Employee getEmployeeById(int id) {
		Session session = HibernateUtil.getCurrentSession();
		Employee employee = (Employee)session.get(Employee.class, id);
		return employee;
	}

}
