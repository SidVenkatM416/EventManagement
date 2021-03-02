package com.cg.ems.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.cg.ems.exception.EventManagementException;
import com.cg.ems.model.Event;

public class EventManagementIOStreamImpl implements IEventManagementDao {
	private static final String DATA_STORE_FILE_NAME="event.dat";

	
	private Map<String, Event> events;
	
	public EventManagementIOStreamImpl() throws EventManagementException {
		File file=new File(DATA_STORE_FILE_NAME);
		
		if(!file.exists()) {
			events=new TreeMap<>();
		}
		else {
			try (ObjectInputStream fin = new ObjectInputStream(new FileInputStream(DATA_STORE_FILE_NAME))) {
				
				Object obj = fin.readObject();

				if (obj instanceof Map) {
					events = (Map<String, Event>)obj;
				} 
				else {
					throw new EventManagementException("Not a valid Data");
				}
			} 
			catch (IOException | ClassNotFoundException exp) {
				throw new EventManagementException("Loading Data Failed");
			}
		}
	}

	@Override
	public String add(Event event) throws EventManagementException {
		String id=null;
		if(event !=null) {
			id=event.getId();
			events.put(id, event);
		}
		return id;
	}

	@Override
	public boolean delete(String id) throws EventManagementException {
		boolean Flag = false;
		if (id != null) {
			events.remove(id);
			Flag = true;
		}
		return Flag;
	}

	@Override
	public List<Event> getAscDate() throws EventManagementException {
		return new ArrayList<Event>(events.values());
	}

	@Override
	public List<Event> getAscLocation() throws EventManagementException {
		return new ArrayList<Event>(events.values());
	}

	@Override
	public List<Event> getLocation(String loc) throws EventManagementException {
		List<Event> location1=new ArrayList<Event>(events.values());
		List<Event> location2=new ArrayList<>();
		for(Event event: location1) {
			if(event.getLocation().equalsIgnoreCase(loc)) {
				location2.add(event);
			}
		}
		return location2;
	}

	@Override
	public List<Event> getParticularDate(String date) throws EventManagementException {
		List<Event> date1=new ArrayList<Event>(events.values());
		List<Event> date2=new ArrayList<>();
		for(Event event: date1) {
			if(event.getDateScheduled().toString().equalsIgnoreCase(date)) {
				date2.add(event);
			}
		}
		return date2;
	}

	@Override
	public void persist() throws EventManagementException {
		try (ObjectOutputStream fout = new ObjectOutputStream(
				new FileOutputStream(DATA_STORE_FILE_NAME))) {
			fout.writeObject(events);
		} catch (IOException exp) {
			throw new EventManagementException("Saving Data Failed" + exp.getMessage());
		}
		
	}
}
