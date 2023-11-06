package com.reservation.beans;

import java.sql.Date;

public class BorrowingActions implements Comparable<BorrowingActions> {
	private int actionID;
	private int productID;
	private int employeeID;
	private Date startDate;
	private Date returnDate;
	
	
	public int getActionID() {
		return actionID;
	}
	public void setActionID(int actionID) {
		this.actionID = actionID;
	}
	public int getProductID() {
		return productID;
	}
	public void setProductID(int productID) {
		this.productID = productID;
	}
	public int getEmployeeID() {
		return employeeID;
	}
	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
	
	@Override
	public int compareTo(BorrowingActions actions) {
		return this.getReturnDate().compareTo(actions.getReturnDate());
	}
	
	
}
