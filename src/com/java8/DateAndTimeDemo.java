package com.java8;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;

/**
 * Java 8 introduced new APIs for Date and Time to address the shortcomings 
 * of the older java.util.Date and java.util.Calendar
 *
 */
public class DateAndTimeDemo {

	public static void main(String args[]) {
		
		/*************** Using LocalDate, LocalTime and LocalDateTime ***************/
		// The most commonly used classes are LocalDate, LocalTime and LocalDateTime. 
		// As their names indicate, they represent the local Date/Time from the 
		// context of the observer.
		
		/****** LocalDate ******/
		LocalDate localDate = LocalDate.now();
		System.out.println("LocalDate.now() : " + localDate);
		
		//The LocalDate representing a specific day, month and year can be obtained using 
		// the “of” method or by using the “parse” method. For example the below code 
		// snippet represents the LocalDate for 20 February 2015
		LocalDate ofDate = LocalDate.of(2015, 02, 20);
		LocalDate parsedDate = LocalDate.parse("2015-02-20");
		System.out.println("LocalDate.of(2015, 02, 20) : " + ofDate);
		System.out.println("LocalDate.parse(\"2015-02-20\") : " + parsedDate);
		
		// the current local date and adds one day:
		LocalDate tomorrow = LocalDate.now().plusDays(1); 
		System.out.println("LocalDate.now().plusDays(1) : " + tomorrow);
		
		// obtain the current date and subtracts one month
		LocalDate previousMonthSameDay = LocalDate.now().minus(1, ChronoUnit.MONTHS);
		System.out.println("LocalDate.now().minus(1, ChronoUnit.MONTHS) : " + previousMonthSameDay);
		
		// get the day of the week
		DayOfWeek sunday = LocalDate.parse("2016-06-12").getDayOfWeek();
		System.out.println("LocalDate.parse(\"2016-06-12\").getDayOfWeek() : " + sunday);
		
		// get the day of the month
		int twelve = LocalDate.parse("2016-06-12").getDayOfMonth();
		System.out.println("LocalDate.parse(\"2016-06-12\").getDayOfMonth() : " + twelve);
		
		// test if a date occurs in a leap year
		boolean leapYear = LocalDate.parse("2016-06-12").isLeapYear();
		System.out.println("LocalDate.parse(\"2016-06-12\").isLeapYear() : " + leapYear);
		
		// The relationship of a date to another can be determined to occur before or 
		// after another date
		boolean notBefore = LocalDate.parse("2016-06-12")
				.isBefore(LocalDate.parse("2016-06-11"));
		System.out.println("LocalDate.parse(\"2016-06-12\").isBefore(LocalDate.parse(\"2016-06-11\")) : " + notBefore);
		boolean isAfter = LocalDate.parse("2016-06-12")
				.isAfter(LocalDate.parse("2016-06-11"));
		System.out.println("LocalDate.parse(\"2016-06-12\").isAfter(LocalDate.parse(\"2016-06-11\")) : " + isAfter);
		
		// Date boundaries can be obtained from a given date. In the following two examples 
		// we get the LocalDateTime that represents the beginning of the day (2016-06-12T00:00) 
		// of the given date and the LocalDate that represents the beginning of the month (2016-06-01) respectively
		LocalDateTime beginningOfDay = LocalDate.parse("2016-06-12").atStartOfDay();
		System.out.println("LocalDate.parse(\"2016-06-12\").atStartOfDay() : " + beginningOfDay);
		LocalDate firstDayOfMonth = LocalDate.parse("2016-06-12")
		  .with(TemporalAdjusters.firstDayOfMonth());
		System.out.println("LocalDate.parse(\"2016-06-12\").with(TemporalAdjusters.firstDayOfMonth()) : " + firstDayOfMonth);
		
		
		
		/****** LocalTime ******/
		// An instance of current LocalTime can be created from the system clock as below
		LocalTime now = LocalTime.now();
		System.out.println("LocalTime.now() : " + now);
		
	}
}
