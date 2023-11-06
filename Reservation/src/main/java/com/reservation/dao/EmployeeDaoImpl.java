package com.reservation.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.reservation.beans.Employees;

public class EmployeeDaoImpl implements EmployeeDao {
    private DaoFactory daoFactory;

    EmployeeDaoImpl(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void add(Employees employee) throws DaoException{
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
        	connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO employees(emp_name, emp_lastname, emp_department, emp_role_admin) VALUES(?, ?, ?, ?);");
            preparedStatement.setString(1, employee.getFirstName());
            preparedStatement.setString(2, employee.getLastName());
            preparedStatement.setString(3, employee.getDepartment());
            preparedStatement.setBoolean(4, employee.isAdmin());

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
    public void remove(List<Integer> employee_ids) throws DaoException{
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
        	connection = daoFactory.getConnection();
        	for (int employee_id:employee_ids) {
        		preparedStatement = connection.prepareStatement("DELETE FROM employees WHERE ID = ?;");
                preparedStatement.setInt(1, employee_id);
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
    public List<Employees> list() throws DaoException {
        List<Employees> employees = new ArrayList<Employees>();
        Connection connection = null;
        Statement statement = null;
        ResultSet result = null;

        try {
        	connection = daoFactory.getConnection();
            statement = connection.createStatement();
            result = statement.executeQuery("SELECT * FROM employees WHERE 1;");

            while (result.next()) {
            	int id = result.getInt("ID");
                String firstname = result.getString("emp_name");
                String lastname = result.getString("emp_lastname");
                String department = result.getString("emp_department");
                boolean admin = result.getBoolean("emp_role_admin");

                Employees employee = new Employees();
                employee.setId(id);
                employee.setFirstName(firstname);
                employee.setLastName(lastname);
                employee.setDepartment(department);
                employee.setAdmin(admin);

                employees.add(employee);
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
        return employees;
    }
    
    @Override
    public List<String> listDepartment() throws DaoException {
        List<Employees> employees = list();
        List<String> departments = new ArrayList<String>();
        for (Employees employee:employees) {
        	departments.add(employee.getDepartment());
        }
        departments  = new ArrayList<>(new HashSet<>(departments));
        return departments;
    }

   
}