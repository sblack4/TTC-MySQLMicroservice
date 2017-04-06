package com.oracle.ttc.mysqlmicroservice.model;

public class CatalogItem {

	private String name;
	private String description;
	private String image;
	private Double price;
	
	public CatalogItem(String name, String description, String image, Double price) {
		super();
		this.name = name;
		this.description = description;
		this.image = image;
		this.price = price;
	}
	
	public CatalogItem() {
		super();
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	
}
