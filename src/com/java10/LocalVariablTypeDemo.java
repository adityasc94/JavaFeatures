package com.java10;

import java.util.Arrays;

import com.common.Employee;

public class LocalVariablTypeDemo {
	
	public static void main(String args[]) {
		
		/*
		 * Java 10 added the Local Variable Type Inference with an initializer to
		 * eliminate verbosity. Here, the LHS variable datatype will be determined by
		 * RHS statement.
		 */
		
		var str1 = "Hello world"; 
		//or
		String str2 = "Hello world";
		// In above example, in first statement, you are setting a String to variable str1
		// so it is implicitly assumed to be of String type. First statement is essentially 
		// equivalent to second statement in above example.
		
		
		
		// When using var, you must initialize the variable at same place. You cannot put
		// declaration and initialization at different places. If you do not initialize 
		// the variable in place, then you will get compilation error – 
		// Cannot use 'var' on variable without initializer.
		
		// var i;  //Invalid Declaration - - Cannot use 'var' on variable without initializer
		var j = 10; //Valid Declaration
		System.out.println("Local Variable-type var j : " + j);
		
		
		
		
		// var is not a reserved java keyword. So you can create variables with name ‘var’ – 
		// It is allowed.
		var var = "varstring";
		System.out.println("Var named var : " + var);
		
		
		
		
		// using var for user defined objects
		var emp = new Employee(1, "sd", 1000);
		System.out.println("Employee object with type var : " + emp);
		
		
		
		
		// var usage ALLOWED AS
		// 1. Local variables with initializers
		// 2. Indexes in the enhanced for-loop
		// 3. Locals declared in a traditional for-loop
		var list = Arrays.asList("Abc", "Efg", "Hij");
		for(var i=0; i<list.size(); i++) {
			System.out.println("var in traditional for loop : " + i + ") " + list.get(i));
		}
		for(var i : list) {
			System.out.println("vas as index in enhanced for loop : " + i);
		}
			
		
		
		// var usage NOT ALLOWED AS
		// 1. Method parameters
		// 2. Constructor parameters
		// 3. Method return types
		// 4. Class fields
		// 5. Catch formals (or any other kind of variable declaration)
		
		//	try {
		//			
		//	}
		//	catch(var e) {
		//			
		//	}
		
		// Backward Compatibility
		// var is not backward compatible. As this is new language feature, code written 
		// using var will not be compiled in lower JDK versions (less then 10). 
		
		// Performance Impact
		// var does not impact performance. Remember, in Java, the types are not inferred 
		// at runtime but at compile time. That means the resulting bytecode is the same as
		// with explicit type declaration – it does include the information about the type. 
		// That means no extra processing at runtime.
	}
	
	// var usage NOT ALLOWED AS
	// 1. Method parameters	
	// public void addNum(var a, var b) {  }
	
	// 2. Constructor parameters
	// public LocalVariablTypeDemo(var c){  }
	
	// 3. Method return types
	// public var subNums() { return null;	}
	
	// 4. Class fields
	// var classVar = "ClassVar";
}
