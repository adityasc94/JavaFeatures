package com.java9;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * Java 9 introduced factory methods for creating small unmodifiable Collection instances
 * using a concise one line code.
 * The collections created using the factory methods are not commonly used implementations.
 * For example, the List returned by the factory method  is not an ArrayList and 
 * the Map is not a HashMap. 
 * Those are different implementations which are introduced in Java 9. 
 * These implementations are internal and their constructors have restricted access.
 *
 */
public class CollectionFactoryMethodsDemo {
	public static void main(String[] args) {
		
		/************ Traditional ways of creating immutable List, Set & Map ************/
		Set<String> set1 = new HashSet<>();
		set1.add("foo");
		set1.add("bar");
		set1.add("baz");
		set1 = Collections.unmodifiableSet(set1);
		System.out.println("Immutable Set using Collections.unmodifiableSet : " + set1);
		
		// creating List using Arrays.asList method
		List<String> list = Arrays.asList("foo", "bar", "baz");
		System.out.println("Immutable List using Arrays.asList method : " + list);
		
		// double-brace technique
		Set<String> set2 = Collections.unmodifiableSet(new HashSet<String>() {{
		    add("foo"); add("bar"); add("baz");
		}});
		System.out.println("Immutable Set using Collections.unmodifiableSet (double-brace technique) : " + set2);
		
		
		
		/***************** Java 9 Convenience Factory Methods for Collections************/
		// Static methods have been provided for List, Set, and Map interfaces which take the 
		// elements as arguments and return an instance of List, Set and Map respectively.
		// This method is named of(…) for all the three interfaces
		
		List<String> newList = List.of("Abc", "Efg", "Hij");
		Set<String> newSet1 = Set.of("Abc", "Efg", "Hij");
		System.out.println("Immutable List using Collection Factory Method : " + newList);
		System.out.println("Immutable Set using Collection Factory Method : " + newSet1);
		
		/*
		 * In the example, we've used the method with takes exactly three elements as
		 * parameters and returns a List / Set of size 3.
		 * But, there are 12 overloaded versions of this method – eleven with 0 to 10
		 * parameters and one with var-args:
		 * 
		 * static <E> List<E> of() 
		 * static <E> List<E> of(E e1) 
		 * static <E> List<E> of(E e1, E e2) // ....and so on
		 * 
		 * static <E> List<E> of(E... elems) For most practical purposes, 10 elements
		 * would be sufficient but if more are required, the var-args version can be
		 * used.
		 */
		
		// During the creation of a Set using a factory method, if duplicate elements 
		// are passed as parameters, then IllegalArgumentException is thrown at runtime
		try {
			Set<String> newSet2 = Set.of("Abc", "Efg", "Efg");
		} 
		catch(IllegalArgumentException e) {
			System.out.println(e);
		}
		
		// The signature of Map factory method is:
		// static <K,V> Map<K,V> of(K k1, V v1, K k2, V v2, K k3, V v3)
		Map<String, String> newMap1 = Map.of("Abc", "a", "Efg", "b", "Hij", "c");
		System.out.println("Immutable Map using Collection Factory Method : " + newMap1);
		
		
		// Similarly to List and Set, the of(…) method is overloaded to have 0 to 10 
		// key-value pairs.
		// In the case of Map, there is a different method for more than 10 key-value pairs
		// static <K,V> Map<K,V> ofEntries(Map.Entry<? extends K,? extends V>... entries)

		Map<String, String> newMap2 = Map.ofEntries(
				  new AbstractMap.SimpleEntry<>("Abc", "a"),
				  new AbstractMap.SimpleEntry<>("Efg", "b"),
				  new AbstractMap.SimpleEntry<>("Hij", "c"));
		System.out.println("Immutable Map using var arg Collection Factory Method : " + newMap2);
		// Passing in duplicate values for Key would throw an IllegalArgumentException
		
		
		// The collections created using factory methods are immutable and changing an element, 
		// adding new elements or removing an element throws UnsupportedOperationException
		try {
			Set<String> set = Set.of("Abc", "Efg");
		    set.add("Hij");
		}
		catch(UnsupportedOperationException e) {
			System.out.println(e);
		}
		
		
		// In the case of List and Set, no elements can be null. In the case of a Map, 
		// neither keys nor values can be null. Passing null argument throws a 
		// NullPointerException
		try {
			List.of("foo", "bar", null);
		} catch (NullPointerException e) {
			System.out.println(e);
		}
	}
}
