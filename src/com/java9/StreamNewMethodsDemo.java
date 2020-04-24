package com.java9;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * The new methods takeWhile and dropWhile allow you to get portions of a 
 * stream based on a predicate.
 *
 */
public class StreamNewMethodsDemo {
	public static void main(String args[]) {
		
		/**************************** takeWhile() ****************************/
		// On an ordered stream, takeWhile returns the “longest prefix” of 
		// elements taken from the stream that match the given predicate,
		// starting at the beginning of the stream.
		// On an un-ordered stream, takeWhile returns a subset of the stream’s
		// elements that match the given predicate (but not all), starting at
		// the beginning of the stream.
		
		List<String> alphabets1 = List.of("a", "b", "c", "d", "e", "f", "g", "h", "i");
		List<String> subset1 = alphabets1
		        .stream()
		        .takeWhile(s -> !s.equals("d"))
		        .collect(Collectors.toList());
		System.out.println(subset1);
		
		List<String> alphabets2 = List.of("f", "a", "h", "d", "e", "d", "b", "k", "i");
		List<String> subset2 = alphabets2
		        .stream()
		        .takeWhile(s -> !s.equals("d"))
		        .collect(Collectors.toList());
		System.out.println(subset2);
		
		
		/**************************** dropWhile() ****************************/
		// On an ordered stream, dropWhile returns remaining items after the 
		// “longest prefix” that match the given predicate.
		// On an un-ordered stream, dropWhile returns remaining stream elements 
		// after dropping subset of elements that match the given predicate.
		
		List<String> alphabets3 = List.of("a", "b", "c", "d", "e", "f", "g", "h", "i");
		List<String> subset3 = alphabets3
		        .stream()
		        .dropWhile(s -> !s.equals("d"))
		        .collect(Collectors.toList());
		System.out.println(subset3);
		
		List<String> alphabets4 = List.of("f", "a", "h", "d", "e", "d", "b", "k", "i");
		List<String> subset4 = alphabets4
		        .stream()
		        .dropWhile(s -> !s.equals("d"))
		        .collect(Collectors.toList());
		System.out.println(subset4);
		
		
		
		
		/*********************** Overloaded Stream iterate method ***********************/
		
		/*
		 * iterate() methods used for creating a stream which starts with a single
		 * element (the seed), and subsequent elements are produced by successively
		 * applying the unary operator. The result is an infinite stream. To terminate
		 * the stream, a limit or some other short-circuiting function, like findFirst
		 * or findAny is used.
		 * The iterate method in Java 8 has the signature:
		 * 
		 * static Stream iterate(final T seed, final UnaryOperator f) 
		 * 
		 * In Java 9, new overloaded version of iterate takes a Predicate as the second argument:
		 * 
		 * static Stream iterate(T seed, Predicate hasNext, UnaryOperator next)
		 */
		
		// Iterate method in Java 8
		List<Integer> numbers1 = Stream.iterate(1, i -> i+1)
				.limit(10)
				.collect(Collectors.toList());

		System.out.println("Java 8 Iterate : " + numbers1);
		
		// Overloaded Iterate method in Java 9
		List<Integer> numbers2 = Stream.iterate(1, i -> i <= 10 ,i -> i+1)
				.collect(Collectors.toList());

		System.out.println("Java 8 Iterate : " + numbers2);
		// In above examples, the first stream is the Java 8 way of using iterate with a limit.
		// The second one uses a Predicate as the second argument

		
		
		/*********************** New Stream ofNullable() method ***********************/
		/*
		 * Until Java 8, you cannot have null value in a stream. It would have caused
		 * NullPointerException.
		 * 
		 * In Java 9, the ofNullable method lets you create a single-element stream
		 * which wraps a value if not null, or is an empty stream otherwise.
		 */
		
		// Stream with null element without ofNullable() method
		try {
			Stream<String> stream1 = Stream.of(null);
		} catch (NullPointerException e) {
			System.out.println(e);
		}
		
		// Stream with null element with ofNullable() method
		Stream<String> stream2 = Stream.ofNullable(null);
		System.out.println(stream2.count());
		// Here, the count method returns the number of non-empty elements in a stream.
	}
}
