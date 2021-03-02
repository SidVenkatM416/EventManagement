package com.cg.ems.ui;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import com.cg.ems.exception.*;
import com.cg.ems.model.*;
import com.cg.ems.service.*;

public class EventManagementUI {
	
	private static IEventManagement eventService;
	private static Scanner scan;
	private static DateTimeFormatter dtFormatter;

	public static void main(String[] args) {
		
		try {
			eventService = new EventManagementImpl();
		}
		catch (EventManagementException e) {
			e.printStackTrace();
		}
		
		scan = new Scanner(System.in);
		dtFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		EventManagementAppMenu menu=null;
		while(menu!=EventManagementAppMenu.QUIT) {
			System.out.println("Choice \t Menu");
			System.out.println("-----------------------------------");
			for(EventManagementAppMenu m:EventManagementAppMenu.values()) {
				System.out.println(m.ordinal() + "\t" + m.name());
			}
			
			System.out.println("Your Choice: ");
			
			int choice = -1;
			if(scan.hasNextInt())
				choice =scan.nextInt();
			else {
				scan.next();
				System.out.println("Please enter a valid choice");
				continue;
			}
			if(choice < 0 || choice >= EventManagementAppMenu.values().length)
				System.out.println("Please enter a valid choice");
			
			else {
				menu = EventManagementAppMenu.values()[choice];
				
				switch(menu) {
				case ADD:
					doAdd();
					break;
				case REMOVE:
					doRemove();
					break;
				case LIST_DATE_IN_ASC:
					doLIST_IN_ASC_DATE_SCHEDULED();
					break;
				case LIST_LOCATION_IN_ASC:
					doLIST_IN_ASC_LOCATION();
					break;
				case LIST_LOCATION:
					doLIST_LOCATION();
					break;
				case LIST_DATE:
					doLIST_DATE();
					break;
				case QUIT:
					System.out.println("Thank you");
					break;
				}
			}
		}
		scan.close();
		try {
			eventService.persist();
		}
		catch(EventManagementException e) {
			e.printStackTrace();
		}
	}
	
	private static void doAdd() {
		try {
			Event event = new Event();
			System.out.print("Id: ");
			event.setId(scan.next());
			System.out.print("Title: ");
			event.setTitle(scan.next());
			System.out.print("Date Scheduled(dd-MM-yyyy): ");
			String date = scan.next();
			System.out.print("Location: ");
			event.setLocation(scan.next());

			try {
				event.setDateScheduled(LocalDate.parse(date, dtFormatter));
			} catch (DateTimeException exp) {
				throw new EventManagementException(
						"Date must be in the format day(dd)-month(MM)-year(yyyy)");
			}
			System.out.print("Cost: ");
			if (scan.hasNextDouble())
				event.setCost(scan.nextDouble());
			else {
				scan.next();
				throw new EventManagementException("Cost should be a number");
			}

			String id = eventService.add(event);
			System.out.println("Event with id "+ id + "  is added");
		} catch (EventManagementException exp) {
			System.out.println(exp.getMessage());
		}
	}
	
	private static void doRemove() {
		System.out.print("Id: ");
		String id = scan.next();
		try {
			boolean flag = eventService.delete(id);
			if (flag) {
				System.out.println("Event is removed from the list");
			} else {
				System.out.println("No such event");
			}
		} catch (EventManagementException exp) {
			System.out.println(exp.getMessage());
		}
	}
	
	private static void doLIST_IN_ASC_LOCATION() {
		List<Event> events;
		try {
			events = eventService.getAscLocation();
			if (events.isEmpty()) {
				System.out.println("No event is added yet!");
			} else {
				Collections.sort(events, Event.EventComparator);
				for (Event ev : events)
					System.out.println(ev);
			}
		} catch (EventManagementException exp) {
			System.out.println(exp.getMessage());
		}
	}
	
	private static void doLIST_IN_ASC_DATE_SCHEDULED() {
		List<Event> events;
		try {
			events = eventService.getAscLocation();
			if (events.isEmpty()) {
				System.out.println("No event is added yet!");
			} else {
				Collections.sort(events, Event.EventComparator1);
				for (Event ev : events)
					System.out.println(ev);
			}
		} catch (EventManagementException exp) {
			System.out.println(exp.getMessage());
		}
	}
	
	private static void doLIST_LOCATION() {
		System.out.println("Location: ");
		String location=scan.next();
		List<Event> events;
		try {
			events = eventService.getLocation(location);
			if (events.isEmpty()) {
				System.out.println("No event with this location has been added yet!");
			} else {
				for (Event ev : events)
					System.out.println(ev);
			}
		} catch (EventManagementException exp) {
			System.out.println(exp.getMessage());
		}
	}

	private static void doLIST_DATE() {
		System.out.println("Scheduled Date(yyyy-mm-dd): ");
		String dates= scan.next();
		List<Event> events;
		try {
			events = eventService.getParticularDate(dates);
			if (events.isEmpty()) {
				System.out.println("No event with this date has been added yet!");
			} else {
				for (Event ev : events)
					System.out.println(ev);
			}
		} catch (EventManagementException exp) {
			System.out.println(exp.getMessage());
		}
	}

}