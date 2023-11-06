package com.reservation.beans;


public class Items implements Comparable<Items>{
	private int id;
	private String category;
	private String name;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getName() {
		return name;
	}
	public void setName(String name){
		this.name = name;
	}

	@Override
	public int compareTo(Items item) {
		return this.getCategory().compareTo(item.getCategory());
	}

}
