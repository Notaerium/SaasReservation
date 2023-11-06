package com.reservation.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.reservation.beans.BorrowingActions;
import com.reservation.beans.Items;
import com.reservation.dao.BorrowingDao;
import com.reservation.dao.DaoException;
import com.reservation.dao.DaoFactory;
import com.reservation.dao.EmployeeDao;
import com.reservation.dao.ItemDao;
import com.reservation.forms.BorrowForm;

public class Return extends HttpServlet {
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

    public Return() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LocalDate localNow = LocalDate.now();	   	    
	    Date dateNow = Date.valueOf(localNow);
	    request.setAttribute("now", dateNow);
	    String categoryFocus = request.getParameter("categoryFocus");
        request.setAttribute("categoryFocus", categoryFocus);
	    
		try {
			if(request.getParameter("categoryFocus") != null && request.getParameter("categoryFocus") != "") {
				List<Items> itemMatchCategory = itemDao.getByCategory(categoryFocus);
				request.setAttribute("items", itemMatchCategory);
			}
			else if (request.getParameter("categoryFocus") == null || request.getParameter("categoryFocus") == ""){
				request.setAttribute("items", itemDao.list());
			}
			request.setAttribute("employees", employeeDao.list());
            request.setAttribute("categories", itemDao.listCategory());
            
            List<BorrowingActions> actions = borrowDao.list();
			Collections.sort(actions);
            request.setAttribute("actions", actions);
			
		}catch (DaoException e) {
			request.setAttribute("error", e.getMessage());
		}

		this.getServletContext().getRequestDispatcher("/WEB-INF/return.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BorrowForm rtrnForm = new BorrowForm();
		List<Integer> ids = rtrnForm.returnActionsIDs(request);
		
		LocalDate localNow = LocalDate.now();	   	    
	    Date dateNow = Date.valueOf(localNow);
	    request.setAttribute("now", dateNow);
		
		try {
			
			List<BorrowingActions> actions = borrowDao.list();
			List<BorrowingActions> formerActionList = borrowDao.list();
			request.setAttribute("formerActionList", formerActionList);
			borrowDao.returnItems(ids);
			request.setAttribute("ids", ids);
			List<BorrowingActions> rtrnItems = new ArrayList<BorrowingActions>();
			for(BorrowingActions action:formerActionList) {
				if (ids.contains(action.getActionID())) { 
					rtrnItems.add(action);
				}
			}
			request.setAttribute("rtrnItems", rtrnItems);

			actions = borrowDao.list();
	        Collections.sort(actions);
	        request.setAttribute("actions", actions);
	        request.setAttribute("employees", employeeDao.list());
            request.setAttribute("categories", itemDao.listCategory());
			request.setAttribute("items", itemDao.list());

		} catch (DaoException e) {
			request.setAttribute("erreur", e.getMessage());
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/return.jsp").forward(request, response);
	}

}
