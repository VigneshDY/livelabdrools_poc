package com.livelabdrools.model;

public class Person {

	private String id;
	private String firstName;
	private String lastName;
	private String location;
	private String timeZone;

	public String getTimeZone() {
		return timeZone;
	}
	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Person(String id, String firstName, String lastName, String location, String timeZone) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.location = location;
		this.timeZone = timeZone;
	}
	public Person() {
		
	}
	@Override
	public String toString() {
		return " [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", location=" + location
				+ ", timeZone=" + timeZone + "]";
	}
}
