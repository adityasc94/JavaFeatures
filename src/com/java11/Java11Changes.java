package com.java11;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class Java11Changes {
	
	public static void main(String args[]) throws Exception{
		
		/************************* New String Methods *************************/
		// 1. repeat(int count)
		/*
		 * This method simply repeats a string n times. It returns a string whose value
		 * is the concatenation of given string repeated N times.
		 * If this string is empty or count is zero then the empty string is returned.
		 */
		String s1 = "ab";
		System.out.println("\"ab\".repeat(4) : " + s1.repeat(4));
		
		
		// 2. isBlank()
		/*
		 * This method indicates whether a string is empty or contains only
		 * white-spaces.
		 */
		String s2 = "";       // true
		String s3 = "   ";    // true
		String s4 = "1";      // false
		System.out.println("\"\".isBlank() : " + s2.isBlank());
		System.out.println("\"   \".isBlank() : " + s3.isBlank());
		System.out.println("\"1\".isBlank() : " + s4.isBlank());
		
		
		// 3. strip()
		/*
		 * This method takes care of removing leading and trailing white-spaces. We can
		 * be even more specific by removing just the leading characters by using
		 * String.stripLeading() or just the trailing characters by using
		 * String.stripTrailing()
		 */
		String s5 = "   hi    ";
		System.out.println("\"   hi    \".strip() : " + s5.strip());
		System.out.println("\"   hi    \".stripLeading() : " + s5.stripLeading());
		System.out.println("\"   hi    \".stripTrailing() : " + s5.stripTrailing());
		
		
		// 4. lines()
		/* This method helps in processing multi-line texts as a Stream */
		String s6 = "hello\nworld\nis\nexecuted";
        List<String> lines = new ArrayList<>();
        s6.lines().forEach(line -> lines.add(line));
        System.out.println(lines);
        
        
        
        
        /************************* New File Methods *************************/
		/*
		 * Using these overloaded methods, Java 11 aims to reduce a lot of boilerplate
		 * code which makes much easier to read and write files
		 */
        // 1. writeString
        Files.writeString(Path.of("testfile1.txt"), "Java 11 Enhancements");
        
        // 2. readString
        String readText = Files.readString(Path.of("testfile1.txt"));
        System.out.println(readText);
        
        // 3. isSameFile
        boolean isSameTrue = Files.isSameFile(Path.of("testfile1.txt"), Path.of("testfile1.txt"));
        boolean isSameFalse = Files.isSameFile(Path.of("testfile1.txt"), Path.of("testfile2.txt"));
        System.out.println("Files.isSameFile(Path.of(\"testfile1.txt\"), Path.of(\"testfile1.txt\")) : " + isSameTrue);
        System.out.println("Files.isSameFile(Path.of(\"testfile1.txt\"), Path.of(\"testfile2.txt\")) : " + isSameFalse);
        
        
        
        
        
        /************************* TimeUnit Conversion *************************/
        TimeUnit tu1 = TimeUnit.DAYS;
        // Converting hours to days
        long days1 = tu1.convert(Duration.ofHours(48));
        System.out.println(days1);
        
        //converting minutes to days
        long days2 = tu1.convert(Duration.ofMinutes(7200));
        System.out.println(days2);
        
        
        
        
        /*************** Local-Variable Syntax for Lambda Parameters *****************/
		/*
		 * JDK 11 allows ‘var’ to be used in lambda expressions. This was introduced to
		 * be consistent with local ‘var’ syntax of Java 10
		 */
        // using var in predicate passed to filter method 
        IntStream.of(1, 2, 3, 5, 6, 7).filter((var i) -> i % 2 == 0).forEach(System.out::println);
	}
}
