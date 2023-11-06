package com.reservation.forms;

import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;

public class DeleteForm {
	public List<String> returnValues(HttpServletRequest request) {
		String[] checkedItems = request.getParameterValues("checkItem");
		List<String> values = new ArrayList<String>();
		if(checkedItems != null) {
			for (String item:checkedItems) {	
				values.add(item);
			}
		}
		return values;
	}
}
