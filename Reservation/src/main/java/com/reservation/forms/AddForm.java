package com.reservation.forms;

import jakarta.servlet.http.HttpServletRequest;

public class AddForm {
	private String resultat;
	
	
	public boolean verifyAddData(HttpServletRequest request) {
		 String itemName = request.getParameter("item_name").trim();
		 if(itemName != ""){
         	return true;
         }
         else{
         	return false;
         }
	}
	
	public String returnCategory(HttpServletRequest request) {
		 String category = request.getParameter("categoriesSelect").trim();
         String newCategory = request.getParameter("newCategory").trim();
         if(category == "") {
         	return newCategory;
         }
         else {
         	return category;
         }
	}

	public String getResultat() {
		return resultat;
	}

	public void setResultat(String resultat) {
		this.resultat = resultat;
	}
	
}
