package edu.cscc.model;
//Calvin Gates, 11/7/2022, using spring boot to create API to (fake) database through Java
public class Order {
	public int id;
	public String firstName;
	public String lastName;
	public int quantity;
	public Double price;
	
	public Order(int id, String firstName, String lastName, int quantity, Double price) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.quantity = quantity;
		this.price = price;
	}
}
