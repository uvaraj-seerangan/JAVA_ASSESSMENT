package com.assignment.pair;
/********************************************************************************************
//* Filename: 		CountPair.java
//* Revision: 		1.0
//* Author: 		Uvaraj Seerangan
//* Created On: 	Feb 2, 2019
//* Modified by: 	
//* Modified On: 	
//*     
//* Description:    Count of pair with difference k in array.
/********************************************************************************************/
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CountPair {
	
	// ERROR CONSTANTS
	public static final String _INVALID_INPUT_FORMAT = "Length of input must be number";
	public static final String _INVALID_INPUT = "Ooops, input value(s) are not valid";
	public static final String _INVALID_SIZE = "Ooops, the length should be between 1 to ";
	public static final String _TYPE_MISMATCH = "Input type mismatch - Expected number";
	
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
			System.out.println("Enter the array size: ");
			int length = Integer.parseInt(scan.nextLine());

			// LENGTH SHOULD BE BETWEEN 2 to 2 * 10 pow 5
			if (length >= 2 && length <= maxSizeLimit) {
				int[] arr = new int[length];

				for (int i = 0; i < length; i++) {
					System.out.print("Enter the [" + (i + 1) + "] value : ");
					int value = scan.nextInt();
					arr[i] = value;
				}

				// TAKEN INPUT OF K - DIFFERENCE
				System.out.print("Enter the k value : ");
				int k = scan.nextInt();

				// CLOSE THE SCANNER TO AVOID RESOURCE LEAK
				scan.close();

				if (arr.length >= 2) {
					int pairCount = countPairsWithDifferenceK(arr, k);
					System.out.println("Pair count : " + pairCount);
				} else {
					System.err.println(CountPair._INVALID_INPUT);
				}
			} else {
				System.err.println(CountPair._INVALID_SIZE + maxSizeLimit);
			}
		} catch (NumberFormatException e) {
			// HANDLE THE INPUT LENGTH MUST BE NUMBER
			System.err.print(CountPair._INVALID_INPUT_FORMAT);
		} catch (InputMismatchException e) {
			// SCANNER NEXT INT SHOULD BE SATISIFED ONLY IF IT IS NUMBER
			System.err.print(CountPair._TYPE_MISMATCH);
		}
	}

	/**
	 * ATOMIC SUBROUTINE - Binary Search algorithm
	 * 
	 * @param arr
	 * @param low
	 * @param high
	 * @param searchValue
	 * @return int
	 */
	private static int binarySearch(int arr[], int low, int high, int searchValue) {
		if (high >= low) {
			int mid = low + (high - low) / 2;

			if (searchValue == arr[mid]) {
				return mid;
			} else if (searchValue > arr[mid]) {
				return binarySearch(arr, (mid + 1), high, searchValue);
			} else {
				return binarySearch(arr, low, (mid - 1), searchValue);
			}
		}

		return -1;
	}

	/**
	 * ATOMIC SUBROUTINE - Returns count of pairs with difference k in array. This
	 * method take care of below, 1. Sorting the array 2. Remove duplicate from
	 * array
	 * 
	 * @param arr
	 * @param k
	 * @return int
	 */
	private static int countPairsWithDifferenceK(int arr[], int k) {
		int count = 0;
		int searchValue = 0;

		// BINARY SEARCH REQUIRED SORTED ARRAY
		Arrays.sort(arr);

		// REMOVE DUPLICATE FROM THE ARRAY
		arr = Arrays.stream(arr).distinct().toArray();

		int length = arr.length;
		int high = length - 1;
		int low = 0;

		// ITERATE THE ELEMENT, INVOKE BINA
		for (int i = 0; i < length; i++) {
			low = i + 1; // LOW
			searchValue = arr[i] + k; // SEARCH VALUE FOR BINARY SEARCH

			// IF BINARY SEARCH VALUE FOUND THEN INCREMENT COUNT
			if (binarySearch(arr, low, high, searchValue) != -1) {
				count++;
			}
		}

		return count;
	}
}
