package com.java8;

import java.util.Comparator;
import java.util.function.Predicate;

/**
 * Lambda expression (or function) is just an anonymous function, 
 * i.e., a function with no name and without being bounded to an identifier.
 *
 */
public class LambdaExpDemo {

	public static void main(String[] args) {
		
		// Runnable using Anonymous inner class
		Runnable r1 = new Runnable(){
			@Override
			public void run() {
				System.out.println("Runnable using Anonymous inner class");
			}
		};
		
		
		
		// Runnable using Lambda expression	
		//Even if a lambda expression has no parameters, you still supply empty parentheses
		Runnable r2 = () -> System.out.println("Runnable using Lambda expression");
		
		
		
		// Comparator using Lambda expression
		Comparator<Employee> c1 = (Employee e1, Employee e2) -> {
			return e1.empId.compareTo(e2.empId);
		};
		
		
		
		// If the data types of the parameters are predictable, then you can omit the data types.
		Comparator<Employee> c2 = (e1, e2) -> {
			return e1.empId.compareTo(e2.empId);
		};
		
		
		
		 // If the lambda expression's body is just a single expression, then you can
		 // omit the curly brackets & the return keyword.	
		Comparator<Employee> c3 = (e1, e2) -> e1.empId.compareTo(e2.empId);
		
		
		
		// Predicate using Lambda Expression
		Predicate<Employee> p1 = (emp) -> emp.salary > 100000;
		
		
		
		// If a method has a single parameter with inferred type, you can even omit the parentheses.
		Predicate<Employee> p2 = emp -> emp.salary > 100000;
	}
}

