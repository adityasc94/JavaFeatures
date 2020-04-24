package com.java8;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import com.common.Employee;

/**
 * You use lambda expressions to create anonymous methods. 
 * Sometimes, however, a lambda expression does nothing but call an existing method. 
 * In those cases, it's often clearer to refer to the existing method by name. 
 * Method references enable you to do this; they are compact, easy-to-read lambda 
 * expressions for methods that already have a name.
 *
 */
public class MethodReferenceDemo {
	
	public static Employee[] arrayOfEmps = {
			new Employee(1, "ABC EFG", 100000.0), 
			new Employee(2, "HIJ KLM", 200000.0), 
			new Employee(3, "NOP QRS", 300000.0)
	};
	public static List<Employee> empList = Arrays.asList(arrayOfEmps);
	
	public static void main(String[] args) {
		/***************** Types of Method References *****************/
		
		// 1. Reference to an instance method of an object – containingObject::instanceMethodName
		// The method reference myComparisonProvider::compareByName invokes the method 
		// compareByName that is part of the object myComparisonProvider. 
		// The JRE infers the method type arguments, which in this case are (Employee, Employee).
		ComparisonProvider myComparisonProvider = new ComparisonProvider();
		Arrays.sort(arrayOfEmps, myComparisonProvider::compareByName);
		
		
		
		
		// 2. Reference to a static method of a class – ContainingClass::staticMethodName
		Arrays.sort(arrayOfEmps, ComparisonProvider::compareBySalary);
		
		
		
		
		// 3. Reference to an instance method of an arbitrary object of a particular type
		// ContainingType::methodName
		// The equivalent lambda expression for the method reference String::compareToIgnoreCase
		// would have the formal parameter list (String a, String b), where a and b are arbitrary 
		// names used to better describe this example. The method reference would invoke the method 
		// a.compareToIgnoreCase(b).
		String[] stringArray = { "Barbara", "James", "Mary", "John",
			    "Patricia", "Robert", "Michael", "Linda" };
		Arrays.sort(stringArray, String::compareToIgnoreCase);
		
		
		
		
		// 4. Reference to a constructor – ClassName::new
		// We can reference a constructor in the same way that we referenced a static method.
		// The only difference is that we'll use the new keyword.
		List<String> bikeBrands = Arrays.asList("Giant", "Scott", "Trek", "GT");
		// using the new constructor from a method reference and making a Bicycle array from 
		// the original String list
		bikeBrands.stream()
		  .map(Bicycle::new)
		  .toArray(Bicycle[]::new);
		// called both Bicycle and Array constructors using a method reference

	}
}

class ComparisonProvider {
    public int compareByName(Employee a, Employee b) {
        return a.getName().compareTo(b.getName());
    }
    
    public static int compareBySalary(Employee a, Employee b) {
        return (int)a.getSalary() - (int)b.getSalary();
    }
}

class Bicycle{
	String brand;
	int frameSize;
	
	public Bicycle(String brand) {
	    this.brand = brand;
	    this.frameSize = 0;
	}
}

