package com.reservation.forms;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.reservation.beans.BorrowingActions;
import com.reservation.dao.BorrowingDao;
import com.reservation.dao.DaoException;
import com.reservation.dao.DaoFactory;

import jakarta.servlet.http.HttpServletRequest;

public class BorrowForm {
	DaoFactory daoFactory = DaoFactory.getInstance();
	
	
	public int returnEmployeeID(HttpServletRequest request) {
		String selectEmployeeId = request.getParameter("selectEmployee");
		int employeeId = Integer.parseInt(selectEmployeeId);
			
		return employeeId;
	}
	
	public int returnItemID(HttpServletRequest request) {
		String selectItemId = request.getParameter("selectItem");
		int itemId = Integer.parseInt(selectItemId);
		
		return itemId;
	}
	public boolean checkDates(HttpServletRequest request) {
		BorrowingDao borrowDao = daoFactory.getBorrowingDao();
		int itemId = returnItemID(request);
		Date requestStart = Date.valueOf(request.getParameter("start"));
		Date requestEnd = Date.valueOf(request.getParameter("end"));
		try {
			List<BorrowingActions> actions = borrowDao.list();
			for(BorrowingActions action:actions) {
				if(action.getProductID() == itemId &&
						((requestStart.getTime() >= action.getStartDate().getTime() &&
										requestStart.getTime() <= action.getReturnDate().getTime()) ||
						(requestEnd.getTime() >= action.getStartDate().getTime() &&
										requestEnd.getTime() <= action.getReturnDate().getTime())) ) {
					return false;
				}
			}
			
		} catch (DaoException e) {
			request.setAttribute("error", e.getMessage());
		}
		
		return true;
	}
	
	public List<Integer> returnActionsIDs(HttpServletRequest request) {
		String[] checkedActionsIds = request.getParameterValues("checkAction");
		List<Integer> actionsIds = new ArrayList<Integer>();
		if(checkedActionsIds != null) {
			for (String employeeId:checkedActionsIds) {	
				actionsIds.add(Integer.parseInt(employeeId));
			}
		}
		return actionsIds;
	}
}
