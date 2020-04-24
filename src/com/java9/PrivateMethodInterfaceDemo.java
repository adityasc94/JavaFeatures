package com.java9;

/**
 * Java 9 introduced private methods in interfaces to remove the redundancy 
 * by sharing the common code of multiple default methods through private methods.
 * the advantages of having private methods in interfaces are:
 *	1. Allows default methods to share common code to avoid duplicate code (redundancy)
 *	2. Improves readability of code.
 */
interface TestInterface3 {
	default void method1() {
		//calling private method
		printLines();
		System.out.println("Default Method1 of Interface3");
		System.out.println();
	}
	default void method2() {
		//calling private method
		printLines();
		System.out.println("Default Method2 of Interface3");
		System.out.println();
	}
	
	// Private Method
	private void printLines() {
		System.out.println("Private method : Starting method");
		System.out.println("Private method : Doing someting");
	}
}


interface TestInterface4 {
	public static void method1() {
		//calling private method
		printLines();
		System.out.println("Static Method1 of Interface4");
		System.out.println();
	}
	public static void method2() {
		//calling private method
		printLines();
		System.out.println("Static Method2 of Interface4");
		System.out.println();
	}
	
	// Private Static Method
	private static void printLines() {
		System.out.println("Private Static : Starting method");
		System.out.println("Private Static : Doing someting");
	}
}


public class PrivateMethodInterfaceDemo implements TestInterface3 {

	public static void main(String[] args) {
		// Private Method
		PrivateMethodInterfaceDemo demo = new PrivateMethodInterfaceDemo();
		demo.method1();
		demo.method1();
		
		// Private Static Method
		TestInterface4.method1();
		TestInterface4.method2();
		
	}

}
