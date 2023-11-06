package com.reservation.dao;

import java.util.List;


import com.reservation.beans.Employees;

public interface EmployeeDao {
    void add( Employees employee ) throws DaoException;
	List<Employees> list() throws DaoException;
	List<String> listDepartment() throws DaoException;
	void remove(List<Integer> employee_id) throws DaoException;
}