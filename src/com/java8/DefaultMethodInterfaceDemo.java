package com.java8;

/**
 * Default & Static Methods in Interfaces
 *
 */
interface TestInterface1{  
    /* This is a default method so we need not
     * to implement this method in the implementation 
     * classes  
     */
    default void newMethod(){  
        System.out.println("TestInterface1 : Newly added default method");  
    }  
    /* Already existing public and abstract method
     * We must need to implement this method in 
     * implementation classes.
     */
    void existingMethod(String str);  
}  
class TestImplementor1 implements TestInterface1{ 
	// implementing abstract method
    public void existingMethod(String str){           
        System.out.println("String is: "+str);  
    }  

}





/**
 * Static Method in Interfaces
 *
 */
interface TestInterface2{  
	/* This is a default method so we need not
     * to implement this method in the implementation 
     * classes  
     */
    default void newMethod(){  
        System.out.println("TestInterface2 : Newly added default method");  
    } 
    
    /* This is a static method. Static method in interface is
     * similar to default method except that we cannot override 
     * them in the implementation classes.
     * Similar to default methods, we need to implement these methods
     * in implementation classes so we can safely add them to the 
     * existing interfaces.
     */
    static void anotherNewMethod(){
    	System.out.println("TestInterface2 : Newly added static method");
    }
    /* Already existing public and abstract method
     * We must need to implement this method in 
     * implementation classes.
     */
    void existingMethod(String str);  
}  

class TestImplementor2 implements TestInterface2{ 
	// implementing abstract method
    public void existingMethod(String str){           
        System.out.println("String is: "+str);  
    }   
}





/**
 * Default Method and Multiple Inheritance
 * The multiple inheritance problem can occur, 
 * when we have two interfaces with the default methods of same signature.
 * To solve this problem, we can implement this method in the implementation class like this
 */
class TestImplementor3 implements TestInterface1, TestInterface2 {

	@Override
	public void existingMethod(String str) {
		System.out.println("String is: "+str);  
	}

	@Override
	public void newMethod() {
		// TODO Auto-generated method stub
		TestInterface1.super.newMethod();
	}
	
}


public class DefaultMethodInterfaceDemo {

	public static void main(String[] args) {
		// Default Method
		TestImplementor1 obj1 = new TestImplementor1();
		//calling the default method of interface
	    obj1.newMethod();     
	    //calling the abstract method of interface
	    obj1.existingMethod("calling the abstract method of interface");
	    
	    
	    
	    // Static Method
	    TestImplementor2 obj2 = new TestImplementor2();
    	//calling the abstract method of interface
	    obj2.existingMethod("calling the abstract method of interface");;     
        //calling the static method of interface
	    TestInterface2.anotherNewMethod();
	    
	    
	    
	    // Default Method and Multiple Inheritance
	    TestImplementor3 obj3 = new TestImplementor3();
	    obj3.newMethod();
	}
	 
}
