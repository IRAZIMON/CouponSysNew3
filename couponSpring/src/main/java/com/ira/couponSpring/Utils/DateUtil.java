package com.ira.couponSpring.Utils;

import java.sql.Date;
import java.text.SimpleDateFormat;

import com.sun.el.parser.ParseException;

public class DateUtil {

	public static Date convertDate(String mydate) throws ParseException, java.text.ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date1 = sdf.parse(mydate);

		return new Date(date1.getTime());

	}

	public static String GetDateStr(java.util.Date date) {
		return date.toString();
	}

	public static Date getDate(String date) throws ParseException, java.text.ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return (Date) sdf.parse(date);
	}
	
	public static Date convertDate(java.util.Date date) {
		return new Date(date.getTime());

	}
	
	
}
