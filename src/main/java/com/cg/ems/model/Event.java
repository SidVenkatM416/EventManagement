package com.cg.ems.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Comparator;

public class Event implements Serializable, Comparable<Event> {
	
	private static final long serialVersionUID=1L;
	private String id;
	private String title;
	private LocalDate dateScheduled;
	private String location;
	private double cost;
	
	
	public Event() {
				
	}

	public Event(String id, String title, LocalDate dateScheduled, String location, double cost) {
		super();
		this.id = id;
		this.title = title;
		this.dateScheduled = dateScheduled;
		this.location = location;
		this.cost = cost;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public LocalDate getDateScheduled() {
		return dateScheduled;
	}

	public void setDateScheduled(LocalDate dateScheduled) {
		this.dateScheduled = dateScheduled;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}
	
	public static Comparator<Event> EventComparator = new Comparator<Event>() {
		public int compare(Event a, Event b) {
			String location1=a.getLocation().toUpperCase();
			String location2=b.getLocation().toUpperCase();
			
			return location1.compareTo(location2);
		}
	};
	
	public static Comparator<Event> EventComparator1 = new Comparator<Event>() {
		public int compare(Event a, Event b) {
			LocalDate date1=a.getDateScheduled();
			LocalDate date2=b.getDateScheduled();
			
			return date1.compareTo(date2);
		}
	};
	
	@Override
	public String toString() {
		StringBuilder output = new StringBuilder("Event id : ");
		output.append(id);
		output.append("\tTitle : ");
		output.append(title);
		output.append("\tScheduled Date :");
		output.append(dateScheduled);
		output.append("\tLocation :");
		output.append(location);
		output.append("\tCost : ");
		output.append(cost);
		return output.toString();
	}	
	
	@Override
	public int compareTo(Event event) {
		String firstId = this.id;
		String secondId = event.id;
		return firstId.compareTo(secondId);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Event other = (Event) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	

}
