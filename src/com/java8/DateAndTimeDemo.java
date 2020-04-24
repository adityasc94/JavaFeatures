package com.java8;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

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
		
		// The Factory method “of” can be used to create a LocalTime
		LocalTime sixThirty = LocalTime.of(6, 30);
		System.out.println("LocalTime.of(6, 30) : " + sixThirty);
		
		// create a LocalTime by parsing a string and add an hour to it by using the “plus” API.
		LocalTime sevenThirty = LocalTime.parse("06:30").plus(1, ChronoUnit.HOURS);
		System.out.println("LocalTime sevenThirty = LocalTime.parse(\"06:30\").plus(1, ChronoUnit.HOURS); : " + sevenThirty);
		
		// Various getter methods are available which can be used to get specific units of time like 
		// hour, min and secs like below
		int six = LocalTime.parse("06:30:20").getHour();
		int thirty = LocalTime.parse("06:30:20").getMinute();
		int twenty = LocalTime.parse("06:30:20").getSecond();
		System.out.println("LocalTime.parse(\"06:30:20\").getHour() : " + six);
		System.out.println("LocalTime.parse(\"06:30:20\").getMinute() : " + thirty);
		System.out.println("LocalTime.parse(\"06:30:20\").getSecond() : " + twenty);
		
		// check if a specific time is before or after another specific time
		boolean isBefore = LocalTime.parse("06:30").isBefore(LocalTime.parse("07:30"));
		System.out.println("LocalTime.parse(\"06:30\").isBefore(LocalDate.parse(\"07:30\")) : " + isBefore);;
		
		// The max, min and noon time of a day can be obtained by constants in LocalTime class. 
		// This is very useful when performing database queries to find records within a given 
		// span of time.
		LocalTime maxTime = LocalTime.MAX;
		LocalTime minTime = LocalTime.MIN;
		LocalTime noonTime = LocalTime.NOON;
		LocalTime midNightTime = LocalTime.MIDNIGHT;
		System.out.println("LocalTime.MAX : " + maxTime);
		System.out.println("LocalTime.MIN : " + minTime);
		System.out.println("LocalTime.NOON : " + noonTime);
		System.out.println("LocalTime.MIDNIGHT : " + midNightTime);
		
		
		/****** LocalDateTime ******/
		// An instance of LocalDateTime can be obtained from the system clock similar 
		// to LocalDate and LocalTime
		LocalDateTime currentDateTime = LocalDateTime.now();
		System.out.println("LocalDateTime.now() : " + currentDateTime);
		
		// create an instance using the factory “of” and “parse” methods. 
		// The result would be a LocalDateTime instance representing 20 February 2015, 06:30 AM
		LocalDateTime ofDateTime = LocalDateTime.of(2015, Month.FEBRUARY, 20, 06, 30);
		System.out.println("LocalDateTime.of(2015, Month.FEBRUARY, 20, 06, 30) : " + ofDateTime);
		
		LocalDateTime parsedDateTime = LocalDateTime.parse("2015-02-20T06:30:00");
		System.out.println("LocalDateTime.parse(\"2015-02-20T06:30:00\") : " + parsedDateTime);
		
		// There are utility APIs to support addition and subtraction of specific units of time 
		// like days, months, year and minutes are available.
		LocalDateTime plusOneHour = LocalDateTime.now().plusHours(1);
		System.out.println("LocalDateTime.now().plusHours(1) : " + plusOneHour);
		
		LocalDateTime minusOneMonth = LocalDateTime.now().minusMonths(1);
		System.out.println("LocalDateTime.now().minusMonths(1) : " + minusOneMonth);
		
		// Getter methods are available to extract specific units similar to the date and time classes.
		int seven = LocalDateTime.parse("2015-02-20T07:30:00").getHour();
		int forty = LocalDateTime.parse("2015-02-20T06:40:00").getMinute();
		int ten = LocalDateTime.parse("2015-02-20T06:30:10").getSecond();
		System.out.println("LocalDateTime.parse(\"2015-02-20T07:30:00\").getHour() : " + seven);
		System.out.println("LocalDateTime.parse(\"2015-02-20T06:40:00\").getMinute() : " + forty);
		System.out.println("LocalDateTime.parse(\"2015-02-20T06:30:10\").getSecond() : " + ten);
		
		
		
		/******************************* Using ZonedDateTime API *******************************/
		
		// Java 8 provides ZonedDateTime when we need to deal with time zone specific date and time. 
		// The ZoneId is an identifier used to represent different zones. There are about 40 different
		// time zones and the ZoneId are used to represent them as follows
		ZoneId zoneId = ZoneId.of("Europe/Paris");
		System.out.println("ZoneId.of(\"Europe/Paris\") : " + zoneId);
		
		// Set of all zone ids can be obtained as follows
		Set<String> allZoneIds = ZoneId.getAvailableZoneIds();
		System.out.println("ZoneId.getAvailableZoneIds() : " + allZoneIds);
		
		// The LocalDateTime can be converted to a specific zone
		ZonedDateTime zonedDateTime = ZonedDateTime.of(LocalDateTime.now(), ZoneId.of("Europe/Paris"));
		System.out.println("ZonedDateTime.of(LocalDateTime.now(), ZoneId.of(\"Europe/Paris\")) : " + zonedDateTime);
		
		// The ZonedDateTime provides parse method to get time zone specific date time
		ZonedDateTime parsedZonedDateTime = ZonedDateTime.parse("2020-04-23T19:58:50.200+02:00[Europe/Paris]");
		System.out.println("ZonedDateTime.parse(\"2020-04-23T19:58:50.200+02:00[Europe/Paris]\") : " + parsedZonedDateTime);
		
		
		/******************************* Using Period and Duration *******************************/
		// The Period class represents a quantity of time in terms of years, months and days and 
		// the Duration class represents a quantity of time in terms of seconds and nano seconds.
		
		// The Date can be manipulated using Period as shown in the following code snippet
		LocalDate finalDate = LocalDate.now().plus(Period.ofDays(5));
		System.out.println("LocalDate.now().plus(Period.ofDays(5)) : " + finalDate);
		
		// The Period class has various getter methods such as getYears, getMonths and getDays 
		// to get values from a Period object.
		int five = Period.between(LocalDate.now(), LocalDate.now().plus(Period.ofDays(5))).getDays();
		System.out.println("Period.between(LocalDate.now(), LocalDate.now().plus(Period.ofDays(5))).getDays() : " + five);
		
		// The Period between two dates can be obtained in a specific unit such as days or month or years, 
		// using ChronoUnit.between
		long diff = ChronoUnit.DAYS.between(LocalDate.now(), LocalDate.now().plus(Period.ofDays(5)));
		System.out.println("ChronoUnit.DAYS.between(LocalDate.now(), LocalDate.now().plus(Period.ofDays(5))) : " + diff);
		
		
		// Similar to Period, the Duration class is use to deal with Time. In the following code we create a 
		// LocalTime of 6:30 am and then add a duration of 30 seconds to make a LocalTime of 06:30:30am
		LocalTime finalTime = LocalTime.now().plus(Duration.ofSeconds(30));
		System.out.println("LocalTime.now().plus(Duration.ofSeconds(30)) : " + finalTime);
		
		// The Duration between two instants can be obtained either as a Duration or as a specific unit. 
		// In the first code snippet we use the between() method of the Duration class to find the time 
		// difference between finalTime and initialTime and return the difference in seconds
		long secDiff1 = Duration.between(LocalTime.now(), LocalTime.now().plus(Duration.ofSeconds(30))).getSeconds();
		System.out.println("Duration.between(LocalTime.now(), LocalTime.now().plus(Duration.ofSeconds(30))).getSeconds() : " + secDiff1);
		
		// use the between() method of the ChronoUnit class to perform the same operation
		long secDiff2 = ChronoUnit.SECONDS.between(LocalTime.now(), LocalTime.now().plus(Duration.ofSeconds(30)));
		System.out.println("ChronoUnit.SECONDS.between(LocalTime.now(), LocalTime.now().plus(Duration.ofSeconds(30))) : " + secDiff2);
		
		
		
		
		/******************************* Compatibility with Date and Calendar *******************************/
		// Java 8 has added the toInstant() method which helps to convert existing Date and Calendar 
		// instance to new Date Time API
		LocalDateTime ofInstantDate = LocalDateTime.ofInstant(new Date().toInstant(), ZoneId.systemDefault());
		LocalDateTime ofInstantCalendar = LocalDateTime.ofInstant(Calendar.getInstance().toInstant(), ZoneId.systemDefault());
		System.out.println("LocalDateTime.ofInstant(new Date().toInstant(), ZoneId.systemDefault()) : " + ofInstantDate);
		System.out.println("LocalDateTime.ofInstant(Calendar.getInstance().toInstant(), ZoneId.systemDefault()) : " + ofInstantCalendar);
		
		
		
		
		/************************************* Date and Time Formatting *************************************/
		
		// The below code passes an ISO date format to format the local date
		String localDateISO = LocalDate.now().format(DateTimeFormatter.ISO_DATE);
		System.out.println("LocalDate.now().format(DateTimeFormatter.ISO_DATE) : " + localDateISO);
		
		// We can pass in formatting style either as SHORT, LONG or MEDIUM as part of the formatting option
		String mediumFormatStyle = LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM));
		System.out.println("LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)) : " + mediumFormatStyle);
	}
}
