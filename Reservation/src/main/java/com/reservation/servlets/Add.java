package com.reservation.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.reservation.dao.DaoFactory;
import com.reservation.dao.DaoException;
import com.reservation.beans.Items;
import com.reservation.dao.ItemDao;
import com.reservation.forms.AddForm;

public class Add extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ItemDao itemDao;
	
	public void init() throws ServletException{
		DaoFactory daoFactory = DaoFactory.getInstance();
		this.itemDao = daoFactory.getItemDao();
	}

    public Add() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
            request.setAttribute("categories", itemDao.listCategory());
        }
		catch (DaoException e) {
            request.setAttribute("erreur", e.getMessage());
        }
		this.getServletContext().getRequestDispatcher("/WEB-INF/add.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Items item = new Items();
		AddForm addForm = new AddForm();
		try {    
            String category = addForm.returnCategory(request);
            String itemName = request.getParameter("item_name").trim();
            if(addForm.verifyAddData(request)){
            	item.setName(itemName);
            	item.setCategory(category);
                itemDao.add(item);
            }
            else{
            	request.setAttribute("erreur", "This item should at least have a name");
            }
            
            request.setAttribute("item", item);
            request.setAttribute("categories", itemDao.listCategory());
            
		} catch (Exception e) {
            request.setAttribute("erreur", e.getMessage());
        }
		
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/add.jsp").forward(request, response);
	}

}
