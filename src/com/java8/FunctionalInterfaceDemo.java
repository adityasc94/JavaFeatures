package com.java8;


/**
 * An interface with exactly one abstract method is called Functional Interface. 
 * @FunctionalInterface annotation is added so that we can mark an interface 
 * as functional interface.
 * It is not mandatory to use it, but it’s best practice to use it with functional 
 * interfaces to avoid addition of extra methods accidentally. 
 * If the interface is annotated with @FunctionalInterface annotation and we try 
 * to have more than one abstract method, it throws compiler error.
 */

/**
 * Valid Functional Interface
 */
@FunctionalInterface
interface TestFunctInterface1 {
	public int add(int a, int b);
}



/**
 * Not functional because multiple abstract methods
 */
//@FunctionalInterface
interface TestFunctInterface2 {
	public int add(int a, int b);
	public int subtract(int a, int b);
}



/**
 * Not functional because equals is already an implicit member (Object class)
 */
//@FunctionalInterface
interface TestFunctInterface3 {
	@Override
	public boolean equals(Object obj);
}



/**
 * Valid Functional Interface having overridden methods from Object class and 
 * also having default & static methods
 */
@FunctionalInterface
interface TestFunctInterface4 {
	public int add(int a, int b);
	
	@Override
	public boolean equals(Object obj);
	
	@Override
	public String toString();
	
	default int subtract(int a, int b) {
		return a-b;
	}
	
	static int multiply(int a, int b) {
		return a*b;
	}
}



interface X { int add(int a, int b); }
/**
 * Valid Functional Interface
 */
@FunctionalInterface
interface TestFunctInterface5 extends X {}



public class FunctionalInterfaceDemo {

	public static void main(String[] args) {
		
		// Using lambda expressions with functional interfaces
		TestFunctInterface1 tfi1 = (a, b) -> a+b;
		System.out.println(tfi1.add(10,  15));
	}

}
