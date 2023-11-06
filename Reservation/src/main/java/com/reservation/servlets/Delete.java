package com.reservation.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import com.reservation.beans.Items;
import com.reservation.dao.DaoException;
import com.reservation.dao.DaoFactory;
import com.reservation.dao.ItemDao;
import com.reservation.forms.DeleteForm;

public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ItemDao itemDao;
	
	public void init() throws ServletException{
		DaoFactory daoFactory = DaoFactory.getInstance();
		this.itemDao = daoFactory.getItemDao();
	}


    public Delete() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			List<Items> items = itemDao.list();
			Collections.sort(items);
            request.setAttribute("items", items);
            String categoryFocus = request.getParameter("categoryFocus");
            request.setAttribute("focusOn", categoryFocus);
            request.setAttribute("categories", itemDao.listCategory());
        }
		catch (DaoException e) {
            request.setAttribute("erreur", e.getMessage());
        }
		this.getServletContext().getRequestDispatcher("/WEB-INF/delete.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DeleteForm deleteForm = new DeleteForm();
		try {
        	// a array of the names of the checked items to be deleted
			List<String> values = deleteForm.returnValues(request);
			itemDao.delete(values);
			request.setAttribute("values", values);
			
			List<Items> items = itemDao.list();
			Collections.sort(items);
	        request.setAttribute("items", items);
	        request.setAttribute("categories", itemDao.listCategory());

		}catch (Exception e){
			request.setAttribute("erreur", e.getMessage());
		}
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/delete.jsp").forward(request, response);
	}

}
