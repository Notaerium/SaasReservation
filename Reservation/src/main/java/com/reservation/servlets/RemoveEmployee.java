package com.reservation.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.reservation.beans.Employees;
import com.reservation.dao.DaoException;
import com.reservation.dao.DaoFactory;
import com.reservation.dao.EmployeeDao;
import com.reservation.forms.RemoveEmployeeForm;


public class RemoveEmployee extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EmployeeDao employeeDao;
    
	public void init() throws ServletException{
		DaoFactory daoFactory = DaoFactory.getInstance();
		this.employeeDao = daoFactory.getEmployeeDao();
	}

    public RemoveEmployee() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			List<Employees> employees = employeeDao.list();
			Collections.sort(employees);
            request.setAttribute("employees", employees);
            String departmentFocus = request.getParameter("departmentFocus");
            String lastnameSearch = request.getParameter("lastnameSearch");
            request.setAttribute("focusOn", departmentFocus);
            if(lastnameSearch != null)request.setAttribute("focusName", lastnameSearch);
            request.setAttribute("departments", employeeDao.listDepartment());
        }
		catch (DaoException e) {
            request.setAttribute("erreur", e.getMessage());
        }
		this.getServletContext().getRequestDispatcher("/WEB-INF/removeEmployee.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RemoveEmployeeForm rmvForm = new RemoveEmployeeForm();
		List<Integer> ids = rmvForm.returnIDs(request);
		
		try {
			List<Employees> rmvedEmps = new ArrayList<Employees>();
			List<Employees> employees = employeeDao.list(); //catch all employees before suppr and copy them in a list
			employeeDao.remove(ids); // remove the checked employee from the DB
			
			for(Employees employee:employees) { //check each employee before removal
				if (ids.contains(employee.getId())) { // if the id of the current employee is inside the tables of indexes of removed employees
					rmvedEmps.add(employee); //we add the employee to the list of removed employees (doesn't work somehow)
				}
			}
			request.setAttribute("rmvedEmps", rmvedEmps);

			employees = employeeDao.list(); // check again but this time without the removed ones
	        Collections.sort(employees); //sort by lastname
	        request.setAttribute("employees", employees);
	        request.setAttribute("departments", employeeDao.listDepartment()); // for the select

		} catch (DaoException e) {
			request.setAttribute("erreur", e.getMessage());
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/removeEmployee.jsp").forward(request, response);

	}

}
