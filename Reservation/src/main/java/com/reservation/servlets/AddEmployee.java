package com.reservation.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.reservation.beans.Employees;
import com.reservation.dao.DaoException;
import com.reservation.dao.DaoFactory;
import com.reservation.dao.EmployeeDao;
import com.reservation.forms.AddEmployeeForm;

public class AddEmployee extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EmployeeDao employeeDao;
       
	public void init() throws ServletException{
		DaoFactory daoFactory = DaoFactory.getInstance();
		this.employeeDao = daoFactory.getEmployeeDao();
	}

    public AddEmployee() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
            request.setAttribute("departments", employeeDao.listDepartment());
        }
		catch (DaoException e) {
            request.setAttribute("erreur", e.getMessage());
        }
		this.getServletContext().getRequestDispatcher("/WEB-INF/addEmployee.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AddEmployeeForm empForm = new AddEmployeeForm();
		Employees employee = new Employees();
		if(empForm.verifyEmployeeData(request)) {
			employee.setFirstName(request.getParameter("emp_firstname").trim());
			employee.setLastName(request.getParameter("emp_lastname").trim());
			employee.setDepartment(empForm.returnDepartment(request));
			employee.setAdmin(empForm.getAdmin(request));
			try {
				employeeDao.add(employee);
				request.setAttribute("departments", employeeDao.listDepartment());
				request.setAttribute("employee", employee);
			}catch (Exception e){
				request.setAttribute("erreur", e.getMessage());
			}
		}
		else {
			request.setAttribute("erreur","The employee should have a firstname and a lastname");
		}
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/addEmployee.jsp").forward(request, response);
	}

}
