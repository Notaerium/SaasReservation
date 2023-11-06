package com.reservation.dao;

import java.util.List;

import com.reservation.beans.BorrowingActions;

public interface BorrowingDao {
	void add(BorrowingActions borrow) throws DaoException;
	void returnItems(List<Integer> actionsIds) throws DaoException;
	List<BorrowingActions> list() throws DaoException;
	List<Integer> listProductsIds()throws DaoException;
}
