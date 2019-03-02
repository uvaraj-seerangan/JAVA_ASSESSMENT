package com.assignment.array;
/********************************************************************************************
//* Filename: 		MinimumUniqueSum.java
//* Revision: 		1.0
//* Author: 		Uvaraj Seerangan
//* Created On: 	Feb 3, 2019
//* Modified by: 	
//* Modified On: 	
//*     
//* Description:        Finding Minimum Unique Array Sum
//* Pre-requisites:     Must increment any duplicate elements until all its elements are unique.
/********************************************************************************************/
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MinimumUniqueSum {

	/**
	 * PUBLIC ACCESSOR - Main method to find minimum unique array sum
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
	    Scanner scan = new Scanner(System.in);
	    try {
	    	// DECIDE THE LENGTH OF THE ARRAY
		System.out.println("Enter the length of input: ");
		int length = Integer.parseInt(scan.nextLine());

		// LENGTH SHOULD BE BETWEEN 1 to 2000
		if (length >= 1 && length <= 2000) {
		    int[] arr = new int[length];

		    for (int i = 0; i < length; i++) {
		    	System.out.print("Enter the [" + (i + 1) + "] value : ");
			int value = scan.nextInt();
			arr[i] = value;
		    }

		    // CLOSE THE SCANNER TO AVOID RESOURCE LEAK
		    scan.close();

		    if (arr.length >= 1) {
		    	System.out.println("Output : " + getMinimumUniqueSum(arr));
		    } 
		    else {
			System.err.println("Ooops, input value(s) are not valid");
		    }
		} 
		else {
		    // LENGTH SHOULD BE BETWEEN 1 to 2000
		    System.err.println("Ooops, the length should be between 1 to 2000");
		}
	    } catch (NumberFormatException e) {
		// HANDLE THE INPUT LENGTH MUST BE NUMBER
		System.err.print("Lenght of input must be number");
	    } catch (InputMismatchException e) {
		// SCANNER NEXT INT SHOULD BE SATISIFED ONLY IF IT IS NUMBER
		System.err.print("Input type mismatch - Expected number");
	    }
	}

	/**
	 * ATOMIC SUBROUTINE - Calculate and provide minimum Unique Sum from Sorted
	 * array
	 * 
	 * @param arr
	 * @return int
	 */
	private static int getMinimumUniqueSum(int[] arr) {
	    // PRERQUISTIES - SORT THE ARRAY
	    Arrays.sort(arr);

	    // VARIABLE TO STORE SUM AND PREVIOUS VALUE IN THE ARRAY
	    int sum = arr[0];
	    int previousValue = arr[0];

	    // ITERATE THE ARRAY TO
	    for (int i = 1; i < arr.length; i++) {
	    	// CHECK THE ARRAY VALUE WITH PREVIOUSLY STORED VALUE
	    	// IF TRUE FOUND DUPLICATE
		if (arr[i] <= previousValue) {
		    // INCREMENT THE VALUE AND DO SUM
		    previousValue += 1;
		    sum += previousValue;
		} 
		else {
		    // IF NOT DUPLICATE THEN SUM (DO NOT INCREMENT)
		    sum = sum + arr[i];
		    previousValue = arr[i];
		}
	    }
	
	    return sum;
	}
}
