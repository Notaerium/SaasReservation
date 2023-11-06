package com.reservation.forms;

import jakarta.servlet.http.HttpServletRequest;

public class AddEmployeeForm {
	
	public boolean verifyEmployeeData(HttpServletRequest request) {
		 String firstname = request.getParameter("emp_firstname").trim();
		 String lastname = request.getParameter("emp_lastname").trim();
		 if(firstname != "" && lastname != ""){
        	return true;
		 }
		 else{
        	return false;
		 }
	}
	
	public String returnDepartment(HttpServletRequest request) {
		String department = request.getParameter("departmentsSelect").trim();
        String newDepartment = request.getParameter("newDepartment").trim();
        if(department == "") {
        	return newDepartment;
        }
        else {
        	return department;
        }
	}
	
	public boolean getAdmin(HttpServletRequest request) {
		String[] checkedAdmin = request.getParameterValues("checkAdmin");
		if(checkedAdmin == null) {
			return false;
		}
		else {
			return true;
		}
	}
}
