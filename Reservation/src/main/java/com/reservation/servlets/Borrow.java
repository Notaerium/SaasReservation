package com.reservation.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import com.reservation.beans.BorrowingActions;
import com.reservation.beans.Employees;
import com.reservation.beans.Items;
import com.reservation.dao.BorrowingDao;
import com.reservation.dao.DaoException;
import com.reservation.dao.DaoFactory;
import com.reservation.dao.EmployeeDao;
import com.reservation.dao.ItemDao;
import com.reservation.forms.BorrowForm;

public class Borrow extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private BorrowingDao borrowDao;
    private EmployeeDao employeeDao;
    private ItemDao itemDao;
    
    
    public void init() throws ServletException{
		DaoFactory daoFactory = DaoFactory.getInstance();
		this.borrowDao = daoFactory.getBorrowingDao();
		this.employeeDao = daoFactory.getEmployeeDao();
		this.itemDao = daoFactory.getItemDao();
	}
    

    public Borrow() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BorrowForm borrowForm = new BorrowForm();
		
		String departmentFocus = request.getParameter("departmentFocus");
        String lastnameSearch = request.getParameter("lastnameSearch");
        
		request.setAttribute("focusOn", departmentFocus);
        if(lastnameSearch != null)request.setAttribute("focusName", lastnameSearch.trim());
        if(request.getParameter("categoryFocus") != null) {
			String categoryFocus = request.getParameter("categoryFocus");
            request.setAttribute("focusOn", categoryFocus);
        }
        if((request.getParameter("start")!=null && request.getParameter("end")!=null) && (request.getParameter("start")!="" && request.getParameter("end")!="")) {
        	Date startPeriod = Date.valueOf(request.getParameter("start"));
        	Date endPeriod = Date.valueOf(request.getParameter("end"));
        	request.setAttribute("startPeriod", startPeriod);
        	request.setAttribute("endPeriod", endPeriod);
        }
		if(request.getParameter("selectEmployee") != null) {
			int employeeId = borrowForm.returnEmployeeID(request);
			request.setAttribute("employeeId", employeeId);
		}

		
		try {
			List<Employees> employees = employeeDao.list();
			Collections.sort(employees);
			request.setAttribute("employees", employees);
			List<Integer> borrowedItemsIds = borrowDao.listProductsIds();

            request.setAttribute("departments", employeeDao.listDepartment());
            request.setAttribute("categories", itemDao.listCategory());
            request.setAttribute("borrowedItemsIds", borrowedItemsIds);
            request.setAttribute("borrowedItems", borrowDao.list());
			List<Items> items = itemDao.list();
			Collections.sort(items);
			request.setAttribute("items", items);

			
		}catch (DaoException e) {
			request.setAttribute("error", e.getMessage());
		}

        LocalDate now = LocalDate.now();
        request.setAttribute("now", now);
        LocalDate maxDate = now.plusYears(1);
        request.setAttribute("maxDate", maxDate);
        

        

		this.getServletContext().getRequestDispatcher("/WEB-INF/borrow.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BorrowForm borrowForm = new BorrowForm();
		BorrowingActions borrowData = new BorrowingActions();
		if(request.getParameter("employee_fullName") != null) {
			String employeeName = request.getParameter("employee_fullName");
			request.setAttribute("employeeName", employeeName);
		}
		
        try {
        	if(request.getParameter("start") != null && request.getParameter("end") != null && request.getParameter("selectItem") != null && borrowForm.checkDates(request) && request.getParameter("selectItem") != null) {
                int itemId = borrowForm.returnItemID(request);
        		borrowData.setProductID(itemId);
                borrowData.setEmployeeID(borrowForm.returnEmployeeID(request));
                borrowData.setStartDate(Date.valueOf(request.getParameter("start")));
                borrowData.setReturnDate(Date.valueOf(request.getParameter("end")));
                request.setAttribute("borrowData", borrowData);
                String item_name = itemDao.getItemName(itemId);
                request.setAttribute("item_name", item_name);
                borrowDao.add(borrowData);
        	}
        	else if( request.getParameter("start") != null || request.getParameter("end") != null || !borrowForm.checkDates(request)) {
        		request.setAttribute("error", "Dates conflict");
        	} 
        	else if(request.getParameter("selectItem") == null){
        		request.setAttribute("error", "Select an item");
        	}
			List<Employees> employees = employeeDao.list();
			Collections.sort(employees);
			request.setAttribute("employees", employees);
            request.setAttribute("departments", employeeDao.listDepartment());
            request.setAttribute("categories", itemDao.listCategory());
        }catch (DaoException e) {
			request.setAttribute("error", e.getMessage());
		}
		
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/borrow.jsp").forward(request, response);
	}

}
