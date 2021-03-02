package com.cg.ems.service;

import java.util.List;

import com.cg.ems.exception.EventManagementException;
import com.cg.ems.model.Event;

public interface IEventManagement {
	String add(Event event) throws EventManagementException;
	
	boolean delete(String id) throws EventManagementException;
	
	List<Event> getAscDate() throws EventManagementException;
	
	List<Event> getAscLocation() throws EventManagementException;
	
	List<Event> getLocation(String loc) throws EventManagementException;
	
	List<Event> getParticularDate(String date) throws EventManagementException;
	
	void persist() throws EventManagementException;
}