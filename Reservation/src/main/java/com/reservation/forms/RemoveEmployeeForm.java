package com.reservation.forms;

import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;

public class RemoveEmployeeForm {
	public List<Integer> returnIDs(HttpServletRequest request) {
		String[] checkedEmployeesIds = request.getParameterValues("checkEmployee");
		List<Integer> employeesIds = new ArrayList<Integer>();
		if(checkedEmployeesIds != null) {
			for (String employeeId:checkedEmployeesIds) {	
				employeesIds.add(Integer.parseInt(employeeId));
			}
		}
		return employeesIds;
	}
}
