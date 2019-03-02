package com.assignment.stack;
/********************************************************************************************
//* Filename: 		SuperStack.java
//* Revision: 		1.0
//* Author: 		Uvaraj Seerangan
//* Created On: 	Feb 2, 2019
//* Modified by: 	
//* Modified On: 	
//*     
//* Description:    PUSH / POP / INSERT element into the stack
//*    	   Rule:    1) Validate the input operation
//* 				2) invoke the stack operation to do the specific functionality & print output
/********************************************************************************************/
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

public class SuperStack {

	// APPLICATION CONSTANTS
	private static final String _PUSH = "push";
	private static final String _POP = "pop";
	private static final String _INC = "inc";
	private static final String _SPACE = " ";
	private static final String _EMPTY = "EMPTY";

	// ERROR CONSTANTS
	public static final String _INVALID_OPERATION = "Invalid operation - Supported operations are push, pop and inc and followed by number";
	public static final String _INVALID_INPUT = "Ooops, input value(s) are not valid";
	public static final String _INVALID_STACK_SIZE = "Ooops, the length should be between 1 to ";
	public static final String _TYPE_MISMATCH = "Input type mismatch - Expected number";

	private LinkedList<Integer> stack = null;

	/**
	 * CONSTRUCTOR - Initialize the stack
	 */
	public SuperStack() {
		this.stack = new LinkedList<Integer>();
	}

	/**
	 * ATOMIC SUBROUTINE - Push value into stack
	 * 
	 * @param value
	 */
	private void push(int value) {
		this.stack.addLast(value);
	}

	/**
	 * ATOMIC SUBROUTINE - Remove the last value from the stack
	 * 
	 * @return int
	 */
	private int pop() {
		int value = 0;
		
		if(!this.isEmpty()) {
			value =  this.stack.removeLast();
		}
		return value;
	}

	/**
	 * ATOMIC SUBROUTINE - Add value to each of the bottom elements of the stack
	 * 
	 * @param element
	 * @param value
	 */
	private void insert(int element, int value) {
		ListIterator<Integer> listIterator = this.stack.listIterator();
		int j = 1;
		while (listIterator.hasNext()) {
			if (j > element)
				break;
			listIterator.set(listIterator.next() + value);
			j++;
		}
	}

	/**
	 * ATOMIC SUBROUTINE - Check for stack empty
	 * 
	 * @return boolean
	 */
	private boolean isEmpty() {
		return this.stack.isEmpty();
	}

	/**
	 * ATOMIC SUBROUTINE - Return the top of stack value
	 * 
	 * @return int
	 */
	private int topOfStack() {
		return this.stack.getLast();
	}

	/**
	 * ATOMIC SUBROUTINE - push/pop/insert into the stack
	 * 
	 * @param operations
	 */
	private void superStack(String[] operations) {
		System.out.println(); // EMPTY LINE IN CONSOLE FOR READABILITY
		
		System.out.println("Printing output .....");
		
		for (int i = 0; i < operations.length; i++) {
			String current = operations[i];

			// IDENTIFIY THE OPERATION AND INVOKE THE ATOMIC SUBROUTINE
			if (current.startsWith(SuperStack._PUSH)) {
				int value = Integer.parseInt(current.split(SuperStack._SPACE)[1]);
				this.push(value);
			} else if (current.startsWith(SuperStack._POP)) {
				this.pop();
			} else if (current.startsWith(SuperStack._INC)) {
				int element = Integer.parseInt(current.split(SuperStack._SPACE)[1]);
				int value = Integer.parseInt(current.split(SuperStack._SPACE)[2]);
				this.insert(element, value);
			}

			// PRINT THE RESULT IN THE CONSOLE
			if (this.isEmpty()) {
				System.out.println(SuperStack._EMPTY);
			} else {
				System.out.println(this.topOfStack());
			}
		}

	}

	/**
	 * PUBLIC ACCESSOR - Entry point
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// MAX SIZE ALLOWD TO DO OPERATION IN THE STACK
		int maxSizeLimit = (int) (2 * Math.pow(10, 5));

		Scanner scan = new Scanner(System.in);

		try {
			// DECIDE THE LENGTH OF THE ARRAY
			System.out.println("Enter the stack size: ");
			int length = Integer.parseInt(scan.nextLine());

			// LENGTH SHOULD BE BETWEEN 1 to 2 * 10 pow 5
			if (length >= 1 && length <= maxSizeLimit) {
				SuperStack superStack = new SuperStack();
				boolean isValid = false;
				
				// OPERATIONS ARRAY TO STORE THE USER INPUT OPERATION
				String[] operations = new String[length];

				for (int i = 0; i < length; i++) {
					System.out.print("Enter the [" + (i) + "] operation : ");
					String operation = scan.nextLine();
					
					// VALIDATE THE OPERATION
					if (isValidOperation(operation)) {
						isValid = true;
						operations[i] = operation;
					} else {
						isValid = false;
						System.out.println(SuperStack._INVALID_OPERATION);
						break;
					}
				}

				// CLOSE THE SCANNER TO AVOID RESOURCE LEAK
				scan.close();

				if (isValid && operations.length >= 1) {
					superStack.superStack(operations);
				} else {
					System.err.println(SuperStack._INVALID_INPUT);
				}
			}
			else {
				System.err.println(SuperStack._INVALID_STACK_SIZE + maxSizeLimit);
			}
		} catch (NumberFormatException e) {
			// SCANNER NEXT INT SHOULD BE SATISIFED ONLY IF IT IS NUMBER
			System.err.print(SuperStack._TYPE_MISMATCH);
		}
	}

	/**
	 * ATOMIC SUBROUTINE - Validate the user input operation
	 * 
	 * @param operation
	 * @return boolean
	 */
	private static boolean isValidOperation(String operation) {
		boolean isValid = false;

		try {
			String[] operations = operation.split(SuperStack._SPACE);

			String op = operations[0];

			if (op.startsWith(SuperStack._PUSH) || op.startsWith(SuperStack._INC)) {
				// TO MAKE SURE THE INPUT IS NUMBER
				Integer.parseInt(operations[1]);
				isValid = true;
			} else if (op.startsWith(SuperStack._POP)) {
				isValid = true;
			}

		} catch (NumberFormatException e) {
			isValid = false;
		}

		return isValid;
	}
}
