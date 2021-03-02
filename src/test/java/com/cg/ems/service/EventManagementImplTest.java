package com.cg.ems.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.cg.ems.exception.EventManagementException;
import com.cg.ems.model.Event;

public class EventManagementImplTest {

	EventManagementImpl event;
	
	@BeforeEach
	void runBeforeAnyTestCase() throws EventManagementException {
			event=new EventManagementImpl();
	}
	
	@AfterEach
	void cleanAfterEachTestCase() {
		event=null;
	}
	
	@Test
	@DisplayName("should return positive value")
	void testAddEvent() throws EventManagementException {
		Event e = new Event("E01","Marriage",LocalDate.parse("2000-06-12"),"Chennai",5000.0);
		String expected ="E01";
		String actual=event.add(e);
		assertEquals(expected,actual);
	}
	
	@Test
	@DisplayName("Should throw exception")
	void testThrowsError() throws EventManagementException{
		Event e= new Event("E02","Marriage",LocalDate.parse("2010-06-12"),"Chennai",5000.0);
		assertThrows(EventManagementException.class,()->{event.add(e);});
	}
	
	@Test
	@DisplayName("should delete event")
	void eventShouldDelete() throws EventManagementException{
		boolean actual=event.delete("E01");
		boolean expected=true;
		assertEquals(expected,actual);
	}
	
}