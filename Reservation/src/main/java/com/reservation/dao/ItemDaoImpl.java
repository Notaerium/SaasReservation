package com.reservation.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.reservation.beans.Items;

public class ItemDaoImpl implements ItemDao {
    private DaoFactory daoFactory;

    ItemDaoImpl(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void add(Items item) throws DaoException{
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
        	connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO items(category, item_name) VALUES(?, ?);");
            preparedStatement.setString(1, item.getCategory());
            preparedStatement.setString(2, item.getName());
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
    public void delete(List<String> item_names) throws DaoException{
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
        	connection = daoFactory.getConnection();
        	for (String item_name:item_names) {
        		preparedStatement = connection.prepareStatement("DELETE FROM items WHERE item_name = ?;");
                preparedStatement.setString(1, item_name);
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
    public String getItemName(int item_id) throws DaoException {
    	String name = "";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;

        try {
        	connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("SELECT item_name FROM items WHERE ID_product = ?;");
            preparedStatement.setInt(1, item_id);
            result = preparedStatement.executeQuery();
            connection.commit();

            while (result.next()) {
                name = result.getString("item_name");
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
        return name;
    }
    
    @Override
    public List<Items> list() throws DaoException {
        List<Items> items = new ArrayList<Items>();
        Connection connection = null;
        Statement statement = null;
        ResultSet result = null;

        try {
        	connection = daoFactory.getConnection();
            statement = connection.createStatement();
            result = statement.executeQuery("SELECT * FROM items WHERE 1;");

            while (result.next()) {
            	int itemId = result.getInt("ID_product");
                String category = result.getString("category");
                String name = result.getString("item_name");

                Items item = new Items();
                item.setId(itemId);
                item.setCategory(category);
                item.setName(name);

                items.add(item);
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
        return items;
    }
    
    @Override
    public List<Items> getByCategory(String selectCategory) throws DaoException {
        List<Items> items = new ArrayList<Items>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;

        try {
        	connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM items WHERE category = ?;");
            preparedStatement.setString(1, selectCategory);
            result = preparedStatement.executeQuery();
            connection.commit();

            while (result.next()) {
            	int itemId = result.getInt("ID_product");
                String category = result.getString("category");
                String name = result.getString("item_name");

                Items item = new Items();
                item.setId(itemId);
                item.setCategory(category);
                item.setName(name);

                items.add(item);
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
        return items;
    }
    
    
    @Override
    public List<String> listCategory() throws DaoException {
        List<Items> items = list();
        List<String> categories = new ArrayList<String>();
        for (Items item:items) {
        	categories.add(item.getCategory());
        }
        categories  = new ArrayList<>(new HashSet<>(categories));
        return categories;
    }

}