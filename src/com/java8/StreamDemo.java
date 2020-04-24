package com.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.common.Employee;

public class StreamDemo {

	public static Employee[] arrayOfEmps = {
			new Employee(1, "ABC EFG", 100000.0), 
			new Employee(2, "HIJ KLM", 200000.0), 
			new Employee(3, "NOP QRS", 300000.0)
	};

	public static List<Employee> empList = Arrays.asList(arrayOfEmps);

	public static void main(String[] args) {

		/*********************** Stream Creation ***********************/
		// obtain a stream from an existing array:
		Stream.of(arrayOfEmps);

		// obtain a stream from an existing list:
		empList.stream();

		// create a stream from individual objects using Stream.of():
		Stream.of(arrayOfEmps[0], arrayOfEmps[1], arrayOfEmps[2]);

		// using Stream.builder():
		Stream.Builder<Employee> empStreamBuilder = Stream.builder();
		empStreamBuilder.accept(arrayOfEmps[0]);
		empStreamBuilder.accept(arrayOfEmps[1]);
		empStreamBuilder.accept(arrayOfEmps[2]);
		Stream<Employee> empStream = empStreamBuilder.build();



		/*********************** Stream Operations ***********************/
		// forEach()
		// it loops over the stream elements, calling the supplied function on each element
		empList.stream().forEach(e -> e.salaryIncrement(10.0));
		System.out.println(empList);


		// map()
		// produces a new stream after applying a function to each element of the original 
		// stream. The new stream could be of different type.
		Integer[] empIds = { 2, 3, 4 };
		List<Employee> employees1 = Stream.of(empIds)
				.map(StreamDemo::getEmployee)
				.collect(Collectors.toList());
		System.out.println(employees1);


		// filter
		// produces a new stream that contains elements of the original stream that pass 
		// a given test (specified by a Predicate).
		List<Employee> employees2 = Stream.of(empIds)
				.map(StreamDemo::getEmployee)
				.filter(emp -> emp!=null)
				.filter(emp -> emp.getSalary() > 230000)
				.collect(Collectors.toList());
		System.out.println(employees2);


		// findFirst()
		// returns an Optional for the first entry in the stream; the Optional can, 
		// of course, be empty
		Employee employee1 = Stream.of(empIds)
				.map(StreamDemo::getEmployee)
				.filter(emp -> emp!=null)
				.filter(emp -> emp.getSalary() > 210000)
				.findFirst()
				.orElse(null);
		System.out.println(employee1);


		// toArray()
		// get an array out of the stream
		Employee[] empArray = Stream.of(empIds)
				.map(StreamDemo::getEmployee)
				.filter(emp -> emp!=null)
				.filter(emp -> emp.getSalary() > 210000)
				.toArray(Employee[]::new);
		for(Employee emp : empArray) {
			System.out.print(emp + " ");
		}
		System.out.println();


		// flatMap()
		// A stream can hold complex data structures like Stream<List<String>>. 
		// In cases like this, flatMap() helps us to flatten the data structure to simplify 
		// further operations
		List<List<String>> namesNested = Arrays.asList( 
				Arrays.asList("ABC", "EFG"), 
				Arrays.asList("HIJ", "KLM"), 
				Arrays.asList("NOP", "QRS"));

		List<String> namesFlatStream = namesNested.stream()
				.flatMap(Collection::stream)
				.collect(Collectors.toList());
		System.out.println(namesFlatStream);



		// Comparison Based Stream Operations
		// sorted()
		// sorts the stream elements based on the comparator passed
		List<Employee> employees3 = empList.stream()
				.sorted((e1, e2) -> e2.getName().compareTo(e1.getName()))
				.collect(Collectors.toList());
		System.out.println(employees3);


		// min() and max()
		// return the minimum and maximum element in the stream respectively, 
		// based on a comparator passed
		Employee empMin = empList.stream()
				.min((e1, e2) -> (int)e1.getSalary() - (int)e2.getSalary())
				.orElseThrow(NoSuchElementException::new);
		System.out.println("Min : " + empMin);
		Employee empMax = empList.stream()
				.max((e1, e2) -> (int)e1.getSalary() - (int)e2.getSalary())
				.orElseThrow(NoSuchElementException::new);
		System.out.println("Min : " + empMax);


		// distinct
		// returns the distinct elements in the stream, eliminating duplicates.
		// It uses the equals() method of the elements to decide whether two elements 
		// are equal or not
		List<Integer> intList1 = Arrays.asList(2, 5, 3, 2, 4, 3);
		List<Integer> distinctIntList = intList1.stream().distinct().collect(Collectors.toList());
		System.out.println(distinctIntList);


		// allMatch() 
		// checks if the predicate is true for all the elements in the stream
		boolean allEven = intList1.stream().allMatch(i -> i % 2 == 0);
		System.out.println("All Even : " + allEven);

		// anyMatch() 
		// checks if the predicate is true for any one element in the stream.
		boolean oneEven = intList1.stream().anyMatch(i -> i % 2 == 0);
		System.out.println("At least one Even : " + oneEven);

		// noneMatch() 
		// checks if there are no elements matching the predicate.
		boolean noneMultipleOfThree = intList1.stream().noneMatch(i -> i % 3 == 0);
		System.out.println("No multiples of 3 : " + noneMultipleOfThree);



		/*********************** Primitive Specialized Streams ***********************/
		// IntStream, LongStream, and DoubleStream – which are primitive specializations for 
		// int, long and double respectively. These are quite convenient when dealing with 
		// a lot of numerical primitives.

		// creating an IntStream by calling mapToInt() on an existing stream
		IntStream intStream = empList.stream()
				.mapToInt(Employee::getEmpId);

		// creating an IntStream using IntStream.of()
		IntStream.of(1, 2, 3);

		// creating an IntStream using IntStream.range()
		IntStream.range(10, 20);  // creates IntStream of numbers 10 to 19


		// Specialized Operations
		// Specialized streams provide additional operations as compared to the standard 
		// Stream – which are quite convenient when dealing with numbers
		// sum(), average(), range(), etc.
		Double avgSal = empList.stream()
				.mapToDouble(Employee::getSalary)
				.average()
				.orElse(0.0);
		System.out.println("Average Salary : " + avgSal);



		/*********************** Reduction Operations ***********************/
		// A reduction operation (also called as fold) takes a sequence of input elements 
		// and combines them into a single summary result by repeated application of a 
		// combining operation.
		// reduce
		Double sumSal = empList.stream()
				.map(Employee::getSalary)
				.reduce(0.0, Double::sum);
		System.out.println("Sum of salaries : " + sumSal);



		/*********************** Advanced collect  ***********************/
		// joining()
		// Collectors.joining() will insert the delimiter between the two String elements 
		// of the stream.
		String empNames = empList.stream()
				.map(Employee::getName)
				.collect(Collectors.joining(", "))
				.toString();
		System.out.println("Employee Names : " + empNames);


		// toSet()
		// to get a set out of stream elements
		Set<String> empNameSet = empList.stream()
				.map(Employee::getName)
				.collect(Collectors.toSet());
		System.out.println("Employee Names from a Set: " + empNameSet);


		// toCollection
		// We can use Collectors.toCollection() to extract the elements into any other 
		// collection by passing in a Supplier<Collection>. We can also use a constructor 
		// reference for the Supplier
		ArrayList<String> empNameList = empList.stream()
				.map(Employee::getName)
				.collect(Collectors.toCollection(ArrayList::new));
		System.out.println("Employee Names from a List: " + empNameSet);


		// summarizingDouble()
		// applies a double-producing mapping function to each input element and returns
		// a special class containing statistical information for the resulting values
		DoubleSummaryStatistics stats = empList.stream()
				.collect(Collectors.summarizingDouble(Employee::getSalary));
		System.out.println("Employee count : " + stats.getCount());
		System.out.println("Sum of salaries : " + stats.getSum());
		System.out.println("Minimum salary : " + stats.getMax());
		System.out.println("Maximum salary : " + stats.getMin());
		System.out.println("Average salary : " + stats.getAverage());


		// partitioningBy
		// We can partition a stream into two – based on whether the elements satisfy 
		// certain criteria or not.
		List<Integer> intList2 = Arrays.asList(2, 3, 4, 7, 10);
		Map<Boolean, List<Integer>> partitionedMap1 = intList2.stream()
				.collect(Collectors.partitioningBy(i -> i%2 == 0));
		System.out.println("Even integer list : " + partitionedMap1.get(true));
		System.out.println("Odd integer list : " + partitionedMap1.get(false));


		// groupingBy()
		// it offers advanced partitioning – where we can partition the 
		// stream into more than just two groups. It takes a classification function as 
		// its parameter. This classification function is applied to each element of the stream.
		// The value returned by the function is used as a key to the map that we 
		// get from the groupingBy collector
		Map<Character, List<Employee>> groupByAlphabets = empList.stream()
				.collect(Collectors.groupingBy(emp -> new Character(emp.getName().charAt(0))));
		System.out.println("Employees with name starting with 'H' : " + groupByAlphabets.get('H'));


		// mapping()
		// group data into a type other than the element type.
		// mapping() which can actually adapt the collector to a different type – 
		// using a mapping function
		Map<Character, List<Integer>> idGroupedByAlphabet = empList.stream().collect(
				Collectors.groupingBy(e -> new Character(e.getName().charAt(0)),
						Collectors.mapping(Employee::getEmpId, Collectors.toList())));
		System.out.println("EmpIds with name starting with 'H' : " + idGroupedByAlphabet.get('H'));


		// reducing
		// reducing() is most useful when used in a multi-level reduction, downstream of 
		// groupingBy() or partitioningBy()
		List<Integer> intList3 = Arrays.asList(2, 3, 4, 7, 10, 13);
		Map<Boolean, Optional<Integer>> partitionedMap2 = intList3.stream()
				.collect(Collectors.partitioningBy(i -> i%2 == 0,
				Collectors.reducing(BinaryOperator.maxBy((i1, i2) -> i1-i2))));
		System.out.println("Max even integer : " + partitionedMap2.get(true).orElse(0));
		System.out.println("Max odd integer : " + partitionedMap2.get(false).orElse(0));
	}

	public static Employee getEmployee(int empId) {
		Employee returnVal = null;
		for(Employee emp : empList) {
			if(emp.getEmpId() == empId) {
				returnVal =  emp;
				break;
			}
		}
		return returnVal;
	}
}
