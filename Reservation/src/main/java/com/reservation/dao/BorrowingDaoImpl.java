package com.reservation.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.reservation.beans.BorrowingActions;

public class BorrowingDaoImpl implements BorrowingDao{
	 private DaoFactory daoFactory;

	 BorrowingDaoImpl(DaoFactory daoFactory) {
	        this.daoFactory = daoFactory;
	 }
	 
	@Override
    public void add(BorrowingActions borrow) throws DaoException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;

        try {
        	connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO borrowed_items(ID_product, ID_employee, start_date, return_date) VALUES(?, ?, ?, ?);");
            preparedStatement.setInt(1, borrow.getProductID());
            preparedStatement.setInt(2, borrow.getEmployeeID());
            preparedStatement.setDate(3, borrow.getStartDate());
            preparedStatement.setDate(4, borrow.getReturnDate());

            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                if (connection != null) {
                	connection.rollback();
                }
            } catch (SQLException e2) {
            }
            throw new DaoException("Communication with the database failed");
        }
        finally {
            try {
                if (connection != null) {
                	connection.close();  
                }
            } catch (SQLException e) {
                throw new DaoException("Communication with the database failed");
            }
        }

    }
	
	@Override
    public void returnItems(List<Integer> actionsIds) throws DaoException{
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
        	connection = daoFactory.getConnection();
        	for (int actionId:actionsIds) {
        		preparedStatement = connection.prepareStatement("DELETE FROM borrowed_items WHERE borrowed_ID = ?;");
                preparedStatement.setInt(1, actionId);
                preparedStatement.executeUpdate();
        	}  
            connection.commit();
        } catch (SQLException e) {
            try {
                if (connection != null) {
                	connection.rollback();
                }
            } catch (SQLException e2) {
            }
            throw new DaoException("Communication with the database failed");
        }
        finally {
            try {
                if (connection != null) {
                	connection.close();  
                }
            } catch (SQLException e) {
                throw new DaoException("Communication with the database failed");
            }
        }
    }
	
	@Override
    public List<BorrowingActions> list() throws DaoException {
        List<BorrowingActions> actions = new ArrayList<BorrowingActions>();
        Connection connection = null;
        Statement statement = null;
        ResultSet result = null;

        try {
        	connection = daoFactory.getConnection();
            statement = connection.createStatement();
            result = statement.executeQuery("SELECT * FROM borrowed_items WHERE 1;");

            while (result.next()) {
            	int id = result.getInt("borrowed_ID");
                int productId = result.getInt("ID_product");
                int employeeId = result.getInt("ID_employee");
                Date startDate = result.getDate("start_date");
                Date returnDate = result.getDate("return_date");

                BorrowingActions action = new BorrowingActions();
                action.setActionID(id);
                action.setProductID(productId);
                action.setEmployeeID(employeeId);
                action.setStartDate(startDate);
                action.setReturnDate(returnDate);
                
                actions.add(action);
            }
        } catch (SQLException e) {
            throw new DaoException("Communication with the database failed");
        }
        /*catch (BeanException e) {
            throw new DaoException("Invalid Data");
        }*/
        finally {
            try {
                if (connection != null) {
                    connection.close();  
                }
            } catch (SQLException e) {
                throw new DaoException("Communication with the database failed");
            }
        }
        return actions;
    }
	 
	public List<Integer> listProductsIds()throws DaoException{
			List<BorrowingActions> actions = list();
	        List<Integer> ids = new ArrayList<Integer>();
	        for (BorrowingActions action:actions) {
	        	ids.add(action.getProductID());
	        }
	        ids  = new ArrayList<>(new HashSet<>(ids));
	        return ids;
	}
	 
	 

}
