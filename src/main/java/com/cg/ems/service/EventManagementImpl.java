package com.cg.ems.service;

import java.util.List;

import com.cg.ems.dao.*;
import com.cg.ems.exception.*;
import com.cg.ems.model.*;

public class EventManagementImpl implements IEventManagement{
	private IEventManagementDao eventDao;
	
	public IEventManagementDao getDAO() {
		return eventDao;
	}
	
	public EventManagementImpl() throws EventManagementException {
		//eventDao=new EventManagementIOStreamImpl();
		eventDao = new EventDAOJDBCImpl();
	}

	@Override
	public String add(Event event) throws EventManagementException {
		String id=null;
		if(event!=null)
			id=eventDao.add(event);
		return id;
	}

	@Override
	public boolean delete(String id) throws EventManagementException {
		boolean Flag=false;
		if(id!=null){
			eventDao.delete(id);
			Flag = true;
		}
		return Flag;
	}

	@Override
	public List<Event> getAscDate() throws EventManagementException {
		return eventDao.getAscDate();
	}

	@Override
	public List<Event> getAscLocation() throws EventManagementException {
		
		return eventDao.getAscLocation();
	}

	@Override
	public List<Event> getLocation(String loc) throws EventManagementException {
		
		return eventDao.getLocation(loc);
	}

	@Override
	public List<Event> getParticularDate(String date) throws EventManagementException {
		
		return eventDao.getParticularDate(date);
	}

	@Override
	public void persist() throws EventManagementException {
		eventDao.persist();
		
	}
	
}