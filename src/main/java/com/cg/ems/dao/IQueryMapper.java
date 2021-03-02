package com.cg.ems.dao;

public interface IQueryMapper {
	
	public static final String ADD_EVENT_QRY = 
			"INSERT INTO eventmanagement(id,title,datescheduled,location,cost) VALUES(?,?,?,?,?)";
	public static final String DATE_ASC_EVENT_QRY = 
			"SELECT id,title,datescheduled FROM eventmanagement ORDER BY datescheduled";
	public static final String DEL_EVENT_QRY = 
			"DELETE FROM eventmanagement WHERE id=?";
	public static final String LOC_ASC_EVENT_QRY = 
			"SELECT id,title,location FROM eventmanagement ORDER BY location";
	public static final String LIST_LOC_EVENT_QRY = 
			"SELECT id,title,datescheduled FROM eventmanagement WHERE location=?";
	public static final String LIST_DATE_EVENT_QRY = 
			"SELECT id,title,location FROM eventmanagement WHERE date=?";
}
