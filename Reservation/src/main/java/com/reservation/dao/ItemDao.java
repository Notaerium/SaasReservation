package com.reservation.dao;

import java.util.List;


import com.reservation.beans.Items;

public interface ItemDao {
    void add( Items item ) throws DaoException;
    List<String> listCategory() throws DaoException;
    List<Items> list() throws DaoException;
    public void delete(List<String> item_names) throws DaoException;
    List<Items> getByCategory(String selectCategory) throws DaoException;
    String getItemName(int item_id) throws DaoException;
}