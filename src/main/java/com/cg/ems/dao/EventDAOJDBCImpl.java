package com.cg.ems.dao;

import com.cg.ems.model.*;
import com.cg.ems.util.ConnectionProvider;
import com.cg.ems.exception.EventManagementException;


import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class EventDAOJDBCImpl implements IEventManagementDao {

	ConnectionProvider conProvider;
	

	public EventDAOJDBCImpl() throws EventManagementException {

		try {
			conProvider = ConnectionProvider.getInstance();
		} catch (ClassNotFoundException | IOException exp) {
			throw new EventManagementException("Database is not reachable");
		}
	}

	@Override
	public String add(Event event) throws EventManagementException {
		String id = null;
		if (event != null) {
			try (Connection con = conProvider.getConnection();
					PreparedStatement pInsert = con
							.prepareStatement(IQueryMapper.ADD_EVENT_QRY)) {

				pInsert.setString(1, event.getId());
				pInsert.setString(2, event.getTitle());
				pInsert.setDate(3, Date.valueOf(event.getDateScheduled()));
				pInsert.setString(4, event.getLocation());
				pInsert.setDouble(5, event.getCost());

				int rowCount = pInsert.executeUpdate();

				if (rowCount == 1) {
					id = event.getId();
				}
			} catch (SQLException exp) {
				throw new EventManagementException("Event is not inserted");
			}
		}
		return id;
	}

	@Override
	public boolean delete(String id) throws EventManagementException {
		boolean isDone = false;

		try (Connection con = conProvider.getConnection();
				PreparedStatement pDelete = con
						.prepareStatement(IQueryMapper.DEL_EVENT_QRY)) {

			pDelete.setString(1, id);

			int rowCount = pDelete.executeUpdate();

			if (rowCount == 1) {
				isDone = true;
			}
		} catch (SQLException exp) {
			throw new EventManagementException("Event is deleted");
		}

		return isDone;
	}

//	@Override
//	public List<Event> getAscDate() throws EventManagementException {
//		Event event=null;
//		try (Connection con = conProvider.getConnection();
//				PreparedStatement pSelect = con
//						.prepareStatement(IQueryMapper.DATE_ASC_EVENT_QRY)) {
//
//			pSelect.setString(1, event);
//
//			ResultSet rs = pSelect.executeQuery();
//			
//			if(rs.next()){
//				book = new Book();
//				book.setBcode(rs.getString("bcode"));
//				book.setTitle(rs.getString("title"));
//				book.setPrice(rs.getDouble("price"));
//				book.setPublishDate(rs.getDate("publishdate").toLocalDate());
//			}
//			
//		} catch (SQLException exp) {
//			throw new EventManagementException("Book is not available");
//		}		
//		return book;
//	}
//
//	@Override
//	public List<Event> getAscDate() throws EventManagementException {
//		List<Event> event=null;
//		try (Connection con = conProvider.getConnection();
//				PreparedStatement pSelect = con
//						.prepareStatement(IQueryMapper.DATE_ASC_EVENT_QRY)) {
//
//			ResultSet rs = pSelect.executeQuery();
//			
//			event = new ArrayList<Event>();
//			
//			while(rs.next()){
//				Event event = new Event();
//				event.setBcode(rs.getString("bcode"));
//				event.setTitle(rs.getString("title"));
//				event.setPrice(rs.getDouble("price"));
//				event.setPublishDate(rs.getDate("publishdate").toLocalDate());
//				event.add(event);
//			}
//			
//		} catch (SQLException exp) {
//			throw new EventManagementException("No Books are available.");
//		}		
//		return event;	
//	}
//
//	@Override
//	public boolean update(Book book) throws EventManagementException {
//		boolean isDone = false;
//		if (book != null) {
//			try (Connection con = conProvider.getConnection();
//					PreparedStatement pUpdate = con
//							.prepareStatement(IQueryMapper.MODIFY_BOOK_QRY)) {
//
//				
//				pUpdate.setString(1, book.getTitle());
//				pUpdate.setDouble(2, book.getPrice());
//				pUpdate.setDate(3, Date.valueOf(book.getPublishDate()));
//				pUpdate.setString(4, book.getBcode());
//				
//
//				int rowCount = pUpdate.executeUpdate();
//
//				if (rowCount == 1) {
//					isDone = true;
//				}
//			} catch (SQLException exp) {
//				throw new EventManagementException("Book is not updated.");
//			}
//		}
//		return isDone;
//	}
//
//	@Override
//	public void persist() throws EventManagementException {
//
//		
//	}
//
//
//	}

	@Override
	public List<Event> getAscLocation() throws EventManagementException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Event> getLocation(String loc) throws EventManagementException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Event> getParticularDate(String date) throws EventManagementException {
		// TODO Auto-generated method stub
		return null;
	}

////	@Override
////	public List<Event> getAscDate() throws EventManagementException {
////		List<Event> event=null;
////		try (Connection con = conProvider.getConnection();
////				PreparedStatement pSelect = con.prepareStatement(IQueryMapper.DATE_ASC_EVENT_QRY)) {
////
////			ResultSet rs = pSelect.executeQuery();
////		}
//		return null;
//	}
//
//	@Override
//	public void persist() throws EventManagementException {
//		// TODO Auto-generated method stub
//		
//	}
}
